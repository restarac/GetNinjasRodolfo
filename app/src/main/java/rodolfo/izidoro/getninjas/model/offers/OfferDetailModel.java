package rodolfo.izidoro.getninjas.model.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rodolfo.izidoro.getninjas.model.LinksModel;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class OfferDetailModel {

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
    private EmbeddedOffersModel embedded;

    @SerializedName("_links")
    @Expose
    private LinksModel links;


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

    public EmbeddedOffersModel getEmbedded() {
        return embedded;
    }

    public void setEmbedded(EmbeddedOffersModel embedded) {
        this.embedded = embedded;
    }


    public LinksModel getLinks() {
        return links;
    }

    public void setLinks(LinksModel links) {
        this.links = links;
    }
}
