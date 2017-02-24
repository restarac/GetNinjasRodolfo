package rodolfo.izidoro.getninjas.model.offers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import rodolfo.izidoro.getninjas.model.AddressModel;
import rodolfo.izidoro.getninjas.model.InfoModel;
import rodolfo.izidoro.getninjas.model.NumberModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class EmbeddedOffersModel {

    @SerializedName("request")
    @Expose
    private RequestOffersModel request;

    @SerializedName("user")
    @Expose
    private UserOffersModel user;

    @SerializedName("address")
    @Expose
    private AddressModel address;

    @SerializedName("info")
    @Expose
    private List<InfoModel> info;

    @SerializedName("phones")
    @Expose
    private List<NumberModel> phones;

    public RequestOffersModel getRequest() {
        return request;
    }

    public void setRequest(RequestOffersModel request) {
        this.request = request;
    }

    public UserOffersModel getUser() {
        return user;
    }

    public void setUser(UserOffersModel user) {
        this.user = user;
    }

    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }


    public List<InfoModel> getInfo() {
        return info;
    }

    public void setInfo(List<InfoModel> info) {
        this.info = info;
    }

    public List<NumberModel> getPhones() {
        return phones;
    }

    public void setPhones(List<NumberModel> phones) {
        this.phones = phones;
    }
}
