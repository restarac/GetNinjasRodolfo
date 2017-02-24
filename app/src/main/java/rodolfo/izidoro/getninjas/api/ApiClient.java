package rodolfo.izidoro.getninjas.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Furkan Bozkurt on 24.11.2016.
 */

public class ApiClient {

    public static final String BASE_URL = "http://testemobile.getninjas.com.br/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }
}
