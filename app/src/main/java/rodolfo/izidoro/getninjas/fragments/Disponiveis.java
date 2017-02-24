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
import rodolfo.izidoro.getninjas.activity.OfferActivity;
import rodolfo.izidoro.getninjas.adapter.ListOffersAdapter;
import rodolfo.izidoro.getninjas.api.ApiClient;
import rodolfo.izidoro.getninjas.api.ApiInterface;
import rodolfo.izidoro.getninjas.helper.RecyclerItemClickListener;
import rodolfo.izidoro.getninjas.model.offers.OffersModel;
import rodolfo.izidoro.getninjas.model.offers.OffersResponse;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class Disponiveis extends Fragment implements View.OnClickListener {

    private List<OffersModel> offers;
    private SwipeRefreshLayout swipeContainer;
    private  ApiInterface servis;
    private Call<OffersResponse> call;
    private  RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_disponiveis, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Buraya dikkat et.




         servis = ApiClient.getClient().create(ApiInterface.class);
        call = servis.getOffers();
        call.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                int statusCode = response.code();
                offers = response.body().getOffers();
                recyclerView.setAdapter(new ListOffersAdapter(offers, R.layout.item_lead, getContext()));

                recyclerView.addOnItemTouchListener(
                        new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                            @Override public void onItemClick(View view, int position) {
                                Intent i = new Intent(getContext(), OfferActivity.class);
                                i.putExtra("id",offers.get(position).get_links().getSelf().getHref().replace("https","http").replace(ApiClient.BASE_URL,""));
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
            public void onFailure(Call<OffersResponse> call, Throwable t) {

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
                fetchTimelineAsync(0);
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

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.

        call = servis.getOffers();
        call.enqueue(new Callback<OffersResponse>() {
            @Override
            public void onResponse(Call<OffersResponse> call, Response<OffersResponse> response) {
                int statusCode = response.code();
                offers = response.body().getOffers();

                ListOffersAdapter listOffersAdapter = (ListOffersAdapter) recyclerView.getAdapter();
                listOffersAdapter.clear();
                listOffersAdapter.addAll(offers);

//
//
//
                swipeContainer.setRefreshing(false);
//                recyclerView.addOnItemTouchListener(
//                        new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
//                            @Override public void onItemClick(View view, int position) {
//                                Intent i = new Intent(getContext(), LeadActivity.class);
//                                i.putExtra("id",offers.get(position).get_links().getSelf().getHref().replace("https","http").replace(ApiClient.BASE_URL,""));
//                                startActivity(i);
//
//                                // do whatever
//                            }
//
//                            @Override public void onLongItemClick(View view, int position) {
//                                // do whatever
//                            }
//                        })
//                );
            }

            @Override
            public void onFailure(Call<OffersResponse> call, Throwable t) {

                Log.i("MainActivity::onFailure", "Hata : " + t.toString());
            }
        });

    }

}