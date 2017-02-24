package rodolfo.izidoro.getninjas.model.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import rodolfo.izidoro.getninjas.model.LinksModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class OffersResponse {
    @SerializedName("offers")
    @Expose
    private List<OffersModel> offers;

    @SerializedName("_links")
    @Expose
    private LinksModel _links;


    public List<OffersModel> getOffers() {
        return offers;
    }

    public void setOffers(List<OffersModel> offers) {
        this.offers = offers;
    }

    public LinksModel get_links() {
        return _links;
    }

    public void set_links(LinksModel _links) {
        this._links = _links;
    }
}
