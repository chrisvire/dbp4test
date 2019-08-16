package org.fcbh;

import org.fcbh.model.Bibles;
import org.fcbh.model.Filesets;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

import java.util.HashMap;

public interface DBP4Service {
    @GET("bibles")
    Call<Bibles> getBibles(@QueryMap HashMap<String, String> options);

    @GET("bibles/filesets/{fileset_id}")
    Call<Filesets> getFilesets(@Path("fileset_id") String filesetId, @Query("type") String type, @QueryMap HashMap<String,String> options);
}
