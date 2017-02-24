package rodolfo.izidoro.getninjas.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rodolfo.izidoro.getninjas.model.leads.LeadDetailModel;
import rodolfo.izidoro.getninjas.model.leads.LeadsResponse;
import rodolfo.izidoro.getninjas.model.offers.OfferDetailModel;
import rodolfo.izidoro.getninjas.model.offers.OffersResponse;

/**
 * Created by Furkan Bozkurt on 24.11.2016.
 */

public interface ApiInterface {

    //obtem os leads
    @GET("leads")
    Call<LeadsResponse> getLeads();

    //obtem o lead detail pelo id
    @GET("{id}")
    Call<LeadDetailModel> getLeadDetail(@Path("id") String id);


    //obtem os offers
    @GET("offers")
    Call<OffersResponse> getOffers();

    //obtem o offerdatail pelo id
    @GET("{id}")
    Call<OfferDetailModel> getOfferDetail(@Path("id") String id);

}
