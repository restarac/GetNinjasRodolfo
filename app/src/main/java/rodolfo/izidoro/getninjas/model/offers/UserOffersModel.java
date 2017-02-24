package rodolfo.izidoro.getninjas.model.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class UserOffersModel {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("_embedded")
    @Expose
    private EmbeddedOffersModel embedded;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmbeddedOffersModel getEmbedded() {
        return embedded;
    }

    public void setEmbedded(EmbeddedOffersModel embedded) {
        this.embedded = embedded;
    }
}
