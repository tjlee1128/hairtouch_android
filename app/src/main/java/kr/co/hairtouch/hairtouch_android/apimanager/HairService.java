package kr.co.hairtouch.hairtouch_android.apimanager;

import java.util.List;

import kr.co.hairtouch.hairtouch_android.model.Hair;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by leetaejun on 2016. 1. 21..
 */
public interface HairService {
    @GET("/hairs.json")
    Call<List<Hair>> loadHairs(@Query("haircategory_id") Integer haircategory_id);

    @GET("/hairs/{id}.json")
    Call<Hair> loadHair(@Path("id") int id);
}
