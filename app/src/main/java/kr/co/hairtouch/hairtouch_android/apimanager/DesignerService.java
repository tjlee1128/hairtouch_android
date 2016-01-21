package kr.co.hairtouch.hairtouch_android.apimanager;

import java.util.List;

import kr.co.hairtouch.hairtouch_android.model.Designer;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public interface DesignerService {
    @GET("/designers.json")
    Call<List<Designer>> loadDesigners();

    @GET("/designers/{id}.json")
    Call<Designer> loadDesigner(@Path("id") int id);
}
