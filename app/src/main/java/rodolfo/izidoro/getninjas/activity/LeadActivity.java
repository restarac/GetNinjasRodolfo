package rodolfo.izidoro.getninjas.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodolfo.izidoro.getninjas.R;
import rodolfo.izidoro.getninjas.adapter.LeadInfoAdapter;
import rodolfo.izidoro.getninjas.api.ApiClient;
import rodolfo.izidoro.getninjas.api.ApiInterface;
import rodolfo.izidoro.getninjas.model.leads.LeadDetailModel;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class LeadActivity extends AppCompatActivity {


    /*
    *
    * Activity que carrega os leads nos fragments
    *
    *
    * */



    private TextView title;
    private TextView usuario;
    private TextView local;
    private TextView distanciaUsuario;
    private TextView telefone;
    private TextView email;
    private RecyclerView recyclerView;
    private SupportMapFragment mapFragment;

    private AlertDialog dialog;

    private TextView ligarUsuario;
    private TextView whatsapp;
    //  private MapObservableProvider mapObservableProvider;
    private String id;

    private LatLng location =  new LatLng(0,0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);

        //seta do back
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Abre o dialog de carregando
        dialog = new SpotsDialog(LeadActivity.this);
        dialog.show();


        //Instanciando os objetos
        title = (TextView) findViewById(R.id.title);
        usuario = (TextView) findViewById(R.id.usuario);
        local = (TextView) findViewById(R.id.local);
        distanciaUsuario = (TextView) findViewById(R.id.distanciaUsuario);
        telefone = (TextView) findViewById(R.id.telefone);
        email = (TextView) findViewById(R.id.email);
        ligarUsuario = (TextView) findViewById(R.id.call_user);
        whatsapp = (TextView) findViewById(R.id.message_user);


        //instanciando o recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.informacoesAdicionais);
        recyclerView.setLayoutManager(new LinearLayoutManager(LeadActivity.this));


        //pego o id do lead
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            id = null;
        } else {
            id = extras.getString("id");
        }

        usuario.requestFocus();

        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));







        ApiInterface servis = ApiClient.getClient().create(ApiInterface.class);

        //Chamada para obter os dados
        Call<LeadDetailModel> call = servis.getLeadDetail(id);
        call.enqueue(new Callback<LeadDetailModel>() {
            @Override
            public void onResponse(Call<LeadDetailModel> call, Response<LeadDetailModel> response) {
                int statusCode = response.code();


                //pega o location
                location = new LatLng(response.body().getEmbedded().getAddress().getGeolocation().getLatitude(),response.body().getEmbedded().getAddress().getGeolocation().getLongitude());


                //carrega o mapa
                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        googleMap.addMarker(new MarkerOptions().position(location));
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,13.0f));
                        googleMap.addCircle(new CircleOptions()
                                .center(location)
                                .radius(1000)
                                .strokeColor(ContextCompat.getColor(getApplicationContext(), R.color.circle_map_border))
                                .fillColor(ContextCompat.getColor(getApplicationContext(), R.color.circle_map_inside)));

                    }
                });

                title.setText(response.body().getTitle());
                usuario.setText(response.body().getEmbedded().getUser().getName());
                getSupportActionBar().setTitle(response.body().getEmbedded().getUser().getName());
                local.setText(response.body().getEmbedded().getAddress().getNeighborhood() + "-" + response.body().getEmbedded().getAddress().getCity());
                email.setText(response.body().getEmbedded().getUser().getEmail());
                telefone.setText(response.body().getEmbedded().getUser().getEmbedded().getPhones().get(0).getNumber());
                distanciaUsuario.setText("A "+ String.valueOf(Integer.parseInt( response.body().getDistance().toString())/1000) + "Km de você");;
                recyclerView.setAdapter(new LeadInfoAdapter(response.body().getEmbedded().getInfo(),R.layout.info_lead,LeadActivity.this));


                //abre whathsapp
                whatsapp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            Intent sendIntent = new Intent(Intent.ACTION_SEND);
                            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.default_whatsapp_message));
                            sendIntent.setType("text/plain");
                            sendIntent.setPackage(getString(R.string.whatsapp_package_name));
                            startActivity(sendIntent);
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Não há whatsapp instalado!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //liga para o usuario
                ligarUsuario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startDialer(telefone.getText().toString());
                    }
                });


                //fecha o dialog de carregando
                dialog.cancel();

            }


            @Override
            public void onFailure(Call<LeadDetailModel> call, Throwable t) {
                dialog.cancel();
                Log.i("MainActivity::onFailure", "Hata : " + t.toString());
            }
        });







    }

    public void startDialer(String Telefone) {
        Telefone = Telefone.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + "+55"+ Telefone));
        startActivity(callIntent);
    }


}
