package rodolfo.izidoro.getninjas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class LinksModel {


    @SerializedName("self")
    @Expose
    private HrefModel self;

    @SerializedName("accept")
    @Expose
    private HrefModel accept;

    @SerializedName("reject")
    @Expose
    private HrefModel reject;

    public HrefModel getSelf() {
        return self;
    }

    public void setSelf(HrefModel self) {
        this.self = self;
    }

    public HrefModel getAccept() {
        return accept;
    }

    public void setAccept(HrefModel accept) {
        this.accept = accept;
    }

    public HrefModel getReject() {
        return reject;
    }

    public void setReject(HrefModel reject) {
        this.reject = reject;
    }
}
