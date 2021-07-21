package siya.ram.retroapianycall;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.ram.hanumanjetpacklibrery.RetroApiAnyCall;
import com.ram.hanumanjetpacklibrery.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getdata();
        getCategory();

    }


    public void getdata(){

        api   service =  RetrofitClient.getStringClient("http:/xyz.com/").create(api.class);
        Call<String> stringCall = service.placeorder("8","1");
        RetroApiAnyCall.ApiStringCallRetro(stringCall, new RetroApiAnyCall.RetroCallbackApi() {
            @Override
            public void onSuccess(String result) {

                //do stuff here
            }

            @Override
            public void onError(String result) {

                //do stuff here
            }
        });

    }



    private void getCategory() {

        api service = RetrofitClient.getModelClient("https://simplifiedcoding.net/demos/").create(api.class);


        Call<List<Hero>> stringCall = service.getHeroes();

        RetroApiAnyCall.ApiModelCallRetro(stringCall, new RetroApiAnyCall.RetroCallbackApiModel() {
            @Override
            public void onError(String result) {
                Log.i("PPZ", "" + result + "");
            }

            @Override
            public <E> void onSuccess(List<E> body) {

                List<Hero>    ty = (List<Hero>) body;
                Log.i("PPZ", "" + ty.size()+ "");
            }

        });
    }


    public  interface  api{


        @GET("marvel")
        Call<List<Hero>> getHeroes();

        @FormUrlEncoded
        @POST("Api/details.php")
        Call<String> placeorder(@Field("class_id") String id, @Field("sec_id") String secid );

    }
}