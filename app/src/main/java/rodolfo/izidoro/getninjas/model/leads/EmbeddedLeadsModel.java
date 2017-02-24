package rodolfo.izidoro.getninjas.model.leads;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import rodolfo.izidoro.getninjas.model.AddressModel;
import rodolfo.izidoro.getninjas.model.InfoModel;
import rodolfo.izidoro.getninjas.model.NumberModel;

/**
 * Created by Rocketz on 23/02/2017.
 */

public class EmbeddedLeadsModel {

    @SerializedName("address")
    @Expose
    private AddressModel address;

    @SerializedName("user")
    @Expose
    private UserLeadsModel user;

    @SerializedName("request")
    @Expose
    private RequestLeadsModel request;


    @SerializedName("phones")
    @Expose
    private List<NumberModel> phones;

    @SerializedName("info")
    @Expose
    private List<InfoModel> info;




    public AddressModel getAddress() {
        return address;
    }

    public void setAddress(AddressModel address) {
        this.address = address;
    }

    public UserLeadsModel getUser() {
        return user;
    }

    public void setUser(UserLeadsModel user) {
        this.user = user;
    }

    public RequestLeadsModel getRequest() {
        return request;
    }

    public void setRequest(RequestLeadsModel request) {
        this.request = request;
    }

    public List<NumberModel> getPhones() {
        return phones;
    }

    public void setPhones(List<NumberModel> phones) {
        this.phones = phones;
    }


    public List<InfoModel> getInfo() {
        return info;
    }

    public void setInfo(List<InfoModel> info) {
        this.info = info;
    }
}
