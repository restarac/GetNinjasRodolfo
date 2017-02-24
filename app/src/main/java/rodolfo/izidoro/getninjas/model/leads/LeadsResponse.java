package rodolfo.izidoro.getninjas.model.leads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import rodolfo.izidoro.getninjas.model.LinksModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class LeadsResponse {
    @SerializedName("leads")
    @Expose
    private List<LeadsModel> leads;

    @SerializedName("_links")
    @Expose
    private LinksModel _links;


    public List<LeadsModel> getLeads() {
        return leads;
    }

    public void setLeads(List<LeadsModel> leads) {
        this.leads = leads;
    }

    public LinksModel get_links() {
        return _links;
    }

    public void set_links(LinksModel _links) {
        this._links = _links;
    }
}
