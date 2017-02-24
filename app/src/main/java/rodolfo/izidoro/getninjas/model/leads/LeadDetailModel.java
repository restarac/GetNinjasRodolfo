package rodolfo.izidoro.getninjas.model.leads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rodolfo.izidoro.getninjas.model.leads.EmbeddedLeadsModel;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class LeadDetailModel {



    @SerializedName("distance")
    @Expose
    private Long distance;

    @SerializedName("lead_price")
    @Expose
    private Long price;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("_embedded")
    @Expose
    private EmbeddedLeadsModel embedded;


    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EmbeddedLeadsModel getEmbedded() {
        return embedded;
    }

    public void setEmbedded(EmbeddedLeadsModel embedded) {
        this.embedded = embedded;
    }
}
