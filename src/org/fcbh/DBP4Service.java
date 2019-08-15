package org.fcbh;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.HashMap;
import java.util.List;

public interface DBP4Service {
    @GET("bibles")
    Call<List<Bible>> getBibles(@QueryMap HashMap<String, String> options);
}
