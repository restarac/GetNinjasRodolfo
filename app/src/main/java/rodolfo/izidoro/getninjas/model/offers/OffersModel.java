package rodolfo.izidoro.getninjas.model.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rodolfo.izidoro.getninjas.model.LinksModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class OffersModel {

    @SerializedName("state")
    @Expose
    private String state;

    @SerializedName("_embedded")
    @Expose
    private EmbeddedOffersModel _embedded;

    @SerializedName("_links")
    @Expose
    private LinksModel _links;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public EmbeddedOffersModel get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbeddedOffersModel _embedded) {
        this._embedded = _embedded;
    }

    public LinksModel get_links() {
        return _links;
    }

    public void set_links(LinksModel _links) {
        this._links = _links;
    }
}
