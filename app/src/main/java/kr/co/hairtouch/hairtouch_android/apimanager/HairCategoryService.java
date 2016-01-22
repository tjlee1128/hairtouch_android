package kr.co.hairtouch.hairtouch_android.apimanager;

import java.util.List;

import kr.co.hairtouch.hairtouch_android.model.HairCategory;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by leetaejun on 2016. 1. 22..
 */
public interface HairCategoryService {
    @GET("/haircategorycodes.json")
    Call<List<HairCategory>> loadHairCategoryCodes();

    @GET("/haircategorys.json")
    Call<List<HairCategory>> loadHairCategoryes(@Query("code") int code);
}
