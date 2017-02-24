package rodolfo.izidoro.getninjas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocketz on 24/02/2017.
 */

public class NumberModel {

    @SerializedName("number")
    @Expose
    private String number;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
