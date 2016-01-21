package kr.co.hairtouch.hairtouch_android.apimanager;

import java.util.List;

import kr.co.hairtouch.hairtouch_android.model.Shop;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public interface ShopService {
    @GET("/shops.json")
    Call<List<Shop>> loadShops();

    @GET("/shops/{id}.json")
    Call<Shop> loadShop(@Path("id") int id);
}
