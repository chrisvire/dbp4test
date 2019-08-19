package org.fcbh;

import com.sun.istack.internal.NotNull;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.fcbh.model.Bibles;
import org.fcbh.model.Filesets;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;

public class DBP4 {
    static final String BASE_URL="https://api.dev.v4.dbt.io";
    final String apiKey;
    final DBP4Service service;
    final boolean logging = false;

    public DBP4(String apiKey) {
        this.apiKey = apiKey;
        this.service = createService();
    }

    public HashMap<String, String> emptyOptions() { return new HashMap<>(); }

    public String getApiKey() { return apiKey; }

    public DBP4Service getService() { return service; }

    protected DBP4Service createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient(getApiKey()))
                .build();

        DBP4Service dbp4Service = retrofit.create(DBP4Service.class);
        return dbp4Service;
    }

    protected OkHttpClient getClient(String apiKey) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder()
                        .header("v", "4")
                        .header("Authorization", "Bearer " + apiKey);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        });

        if (logging) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        return builder.build();
    }

    public void getBibles(@NotNull HashMap<String, String> options, Callback<Bibles> callback) {
        Call<Bibles> call = service.getBibles(options);
        call.enqueue(callback);
    }

    public void getFilesets(@NotNull String filesetId, @NotNull String type, String bookId, String chapterId, @NotNull HashMap<String, String> options, Callback<Filesets> callback)
    {
        if (bookId != null) { options.put("book_id", bookId); }
        if (chapterId != null) { options.put("chapter_id", chapterId); }
        Call<Filesets> call = service.getFilesets(filesetId, type, options);
        call.enqueue(callback);
    }
}
