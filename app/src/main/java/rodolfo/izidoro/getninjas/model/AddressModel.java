package rodolfo.izidoro.getninjas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class AddressModel {


    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("street")
    @Expose
    private String street;


    @SerializedName("neighborhood")
    @Expose
    private String neighborhood;


    @SerializedName("uf")
    @Expose
    private String uf;

    @SerializedName("geolocation")
    @Expose
    private GeolocationModel geolocation;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public GeolocationModel getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeolocationModel geolocation) {
        this.geolocation = geolocation;
    }
}
