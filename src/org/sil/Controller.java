package org.sil;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.fcbh.Bible;
import org.fcbh.DBP4Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Controller implements Callback<List<Bible>> {
    static final String BASE_URL="https://api.v4.dbt.io";

    String apiKey;

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient(getApiKey()))
                .build();

        DBP4Service dbp4Service = retrofit.create(DBP4Service.class);

        Call<List<Bible>> call = dbp4Service.getBibles(new HashMap<String, String>() {{
            put("media", "text_plain");
        }});
        call.enqueue(this);
    }

    protected OkHttpClient getClient(String apiKey) {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder()
                        .header("v", "4")
                        .header("Authorization", "Bearer " + apiKey);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        return client;
    }

    @Override
    public void onResponse(Call<List<Bible>> call, Response<List<Bible>> response) {
        if (response.isSuccessful()) {
            List<Bible> bibles = response.body();
            bibles.forEach(bible -> System.out.println(bible.getAbbr()));
        } else {
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Bible>> call, Throwable throwable) {
        throwable.printStackTrace();
    }
}
