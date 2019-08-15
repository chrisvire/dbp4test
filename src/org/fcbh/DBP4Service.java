package org.fcbh;

import org.fcbh.model.Bibles;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;

public interface DBP4Service {
    @GET("bibles")
    Call<Bibles> getBibles(@QueryMap HashMap<String, String> options);
}
