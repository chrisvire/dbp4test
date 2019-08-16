package org.sil;

import org.fcbh.DBP4;
import org.fcbh.model.Bibles;
import org.fcbh.model.Filesets;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        DBP4 dbp4 = new DBP4("53355c32fca5f3cac4d7a670d2df2e09");

        //getBibles(dbp4);
        getFilesets(dbp4);
    }

    private static void getFilesets(DBP4 dbp4) {
        dbp4.getFilesets("ENGNIVN1DA", "audio", "MAT", "1", dbp4.emptyOptions(), new Callback<Filesets>() {
            @Override
            public void onResponse(Call<Filesets> call, Response<Filesets> response) {
                if (response.isSuccessful()) {
                    Filesets filesets = response.body();
                    filesets.getData().forEach(fileset -> System.out.printf("%s %s: %s\n", fileset.getBookName(), fileset.getChapterStart(), fileset.getPath()));
                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Filesets> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    private static void getBibles(DBP4 dbp4) {
        HashMap<String, String> options = new HashMap<String, String>() {{
            put("media", "text_plain");
        }};
        dbp4.getBibles(options, new Callback<Bibles>() {
            @Override
            public void onResponse(Call<Bibles> call, Response<Bibles> response) {
                if (response.isSuccessful()) {
                    Bibles bibles = response.body();
                    bibles.getData().forEach(bible -> System.out.println(bible.getAbbr()));
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Bibles> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }
}
