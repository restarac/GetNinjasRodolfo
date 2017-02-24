package rodolfo.izidoro.getninjas.model.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class RequestOffersModel {


    @SerializedName("created_at")
    @Expose
    private String created_at;


    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("_embedded")
    @Expose
    private EmbeddedOffersModel _embedded;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public EmbeddedOffersModel get_embedded() {
        return _embedded;
    }

    public void set_embedded(EmbeddedOffersModel _embedded) {
        this._embedded = _embedded;
    }
}
