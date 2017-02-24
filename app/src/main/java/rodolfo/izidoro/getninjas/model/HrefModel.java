package rodolfo.izidoro.getninjas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class HrefModel
{

    @SerializedName("href")
    @Expose
    private String href;


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
