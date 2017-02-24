package rodolfo.izidoro.getninjas.model.leads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rodolfo.izidoro.getninjas.model.LinksModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class LeadsModel {
    @SerializedName("created_at")
    @Expose
    private String created_at;

    @SerializedName("_embedded")
    @Expose
    private EmbeddedLeadsModel _embedded;

    @SerializedName("_links")
    @Expose
    private LinksModel _links;


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public EmbeddedLeadsModel get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbeddedLeadsModel _embedded) {
        this._embedded = _embedded;
    }

    public LinksModel get_links() {
        return _links;
    }

    public void set_links(LinksModel _links) {
        this._links = _links;
    }
}
