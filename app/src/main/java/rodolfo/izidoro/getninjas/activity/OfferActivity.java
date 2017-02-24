package rodolfo.izidoro.getninjas.activity;

import android.app.AlertDialog;
import android.content.Intent;
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
import rodolfo.izidoro.getninjas.adapter.OfferInfoAdapter;
import rodolfo.izidoro.getninjas.api.ApiClient;
import rodolfo.izidoro.getninjas.api.ApiInterface;
import rodolfo.izidoro.getninjas.model.offers.OfferDetailModel;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class OfferActivity extends AppCompatActivity {

    private TextView title;
    private TextView usuario;
    private TextView local;
    private TextView distanciaUsuario;
    private TextView telefone;
    private TextView email;
    private RecyclerView recyclerView;
    private SupportMapFragment mapFragment;

    private TextView decline;
    private TextView accept;
    //  private MapObservableProvider mapObservableProvider;
    private String id;

    private LatLng location =  new LatLng(0,0);


    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);


        //istancio os objetos
        title = (TextView) findViewById(R.id.title);
        usuario = (TextView) findViewById(R.id.usuario);
        local = (TextView) findViewById(R.id.local);
        distanciaUsuario = (TextView) findViewById(R.id.distanciaUsuario);
        telefone = (TextView) findViewById(R.id.telefone);
        email = (TextView) findViewById(R.id.email);
        decline = (TextView) findViewById(R.id.decline);
        accept = (TextView) findViewById(R.id.accept);

        //troco o titulo da barra
        getSupportActionBar().setTitle("Oferta");


        //abro o progress dialog
        dialog = new SpotsDialog(OfferActivity.this);
        dialog.show();


        //instancio o recycler view
        recyclerView = (RecyclerView) findViewById(R.id.informacoesAdicionais);
        recyclerView.setLayoutManager(new LinearLayoutManager(OfferActivity.this));

        //pego o id das offers
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            id = null;
        } else {
            id = extras.getString("id");
        }


        //declaracao do google maps
        mapFragment = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));





        //declaracao da api
        ApiInterface servis = ApiClient.getClient().create(ApiInterface.class);


        Call<OfferDetailModel> call = servis.getOfferDetail(id);
        call.enqueue(new Callback<OfferDetailModel>() {
            @Override
            public void onResponse(Call<OfferDetailModel> call, final Response<OfferDetailModel> response) {
                int statusCode = response.code();

                location = new LatLng(response.body().getEmbedded().getAddress().getGeolocation().getLatitude(),response.body().getEmbedded().getAddress().getGeolocation().getLongitude());

                //carrego o mapa
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

                //carrego as informacoes do detail
                title.setText(response.body().getTitle());
                usuario.setText(response.body().getEmbedded().getUser().getName());
                local.setText(response.body().getEmbedded().getAddress().getNeighborhood() + "-" + response.body().getEmbedded().getAddress().getCity());
                email.setText(response.body().getEmbedded().getUser().getEmail());
                telefone.setText(response.body().getEmbedded().getUser().getEmbedded().getPhones().get(0).getNumber());
                distanciaUsuario.setText("A "+ String.valueOf(Integer.parseInt( response.body().getDistance().toString())/1000) + "Km de você");
                recyclerView.setAdapter(new OfferInfoAdapter(response.body().getEmbedded().getInfo(),R.layout.info_offer,OfferActivity.this));



                //botao de aceitar e leva para o lead que gera
                accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{

                            Intent i = new Intent(OfferActivity.this, LeadActivity.class);
                            i.putExtra("id",response.body().getLinks().getAccept().getHref().replace("https","http").replace(ApiClient.BASE_URL,""));
                            startActivity(i);
                            finish();
                        }
                        catch (Exception e){
                            Toast.makeText(getApplicationContext(), "Não foi possivel aceitar a oferta!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                //fecha a activity
                decline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });


                //fecha o progress dialog
                dialog.cancel();

            }


            @Override
            public void onFailure(Call<OfferDetailModel> call, Throwable t) {

                dialog.cancel();
                Log.i("MainActivity::onFailure", "Hata : " + t.toString());
            }
        });







    }



}
