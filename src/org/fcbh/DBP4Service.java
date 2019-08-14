package org.fcbh;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface DBP4Service {
    @GET("bibles")
    Call<List<Bible>> listBibles(@QueryMap Map<String, String> options)
}
