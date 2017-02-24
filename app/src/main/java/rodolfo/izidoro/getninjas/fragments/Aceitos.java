package rodolfo.izidoro.getninjas.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rodolfo.izidoro.getninjas.R;
import rodolfo.izidoro.getninjas.activity.LeadActivity;
import rodolfo.izidoro.getninjas.adapter.ListLeadsAdapter;
import rodolfo.izidoro.getninjas.api.ApiClient;
import rodolfo.izidoro.getninjas.api.ApiInterface;
import rodolfo.izidoro.getninjas.helper.RecyclerItemClickListener;
import rodolfo.izidoro.getninjas.model.leads.LeadsModel;
import rodolfo.izidoro.getninjas.model.leads.LeadsResponse;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class Aceitos extends Fragment implements View.OnClickListener {

private List<LeadsModel> leads;
    private SwipeRefreshLayout swipeContainer;
    private  ApiInterface servis;
    private Call<LeadsResponse> call;
    private  RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_aceitos, container, false);


        //Pull to refresh
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        //recicler view
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

       servis = ApiClient.getClient().create(ApiInterface.class);


         call = servis.getLeads();
        call.enqueue(new Callback<LeadsResponse>() {
            @Override
            public void onResponse(Call<LeadsResponse> call, Response<LeadsResponse> response) {
                int statusCode = response.code();
                 leads = response.body().getLeads();
                recyclerView.setAdapter(new ListLeadsAdapter(leads, R.layout.item_lead, getContext()));

                //abre o detail do lead
                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Intent i = new Intent(getContext(), LeadActivity.class);
                                //passo o id por parametro, removendo a url somente o Id : lead-1
                                i.putExtra("id",leads.get(position).get_links().getSelf().getHref().replace("https","http").replace(ApiClient.BASE_URL,""));
                                startActivity(i);

                                // do whatever
                            }

                            @Override public void onLongItemClick(View view, int position) {
                                // do whatever
                            }
                        })
                );
            }

            @Override
            public void onFailure(Call<LeadsResponse> call, Throwable t) {

                Log.i("MainActivity::onFailure", "Hata : " + t.toString());
            }
        });



        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);






        return view;
    }
    @Override
    public void onClick(View view) {

    }

    public void fetchTimelineAsync() {

        call = servis.getLeads();
        call.enqueue(new Callback<LeadsResponse>() {
            @Override
            public void onResponse(Call<LeadsResponse> call, Response<LeadsResponse> response) {
                int statusCode = response.code();
                leads = response.body().getLeads();
                ListLeadsAdapter listLeadsAdapter = (ListLeadsAdapter) recyclerView.getAdapter();
                //limpa a lista
                listLeadsAdapter.clear();

                //atualiza a lista
                listLeadsAdapter.addAll(leads);



//
//
//
                swipeContainer.setRefreshing(false);
//
            }

            @Override
            public void onFailure(Call<LeadsResponse> call, Throwable t) {

                Log.i("MainActivity::onFailure", "Hata : " + t.toString());
            }
        });

    }
}
