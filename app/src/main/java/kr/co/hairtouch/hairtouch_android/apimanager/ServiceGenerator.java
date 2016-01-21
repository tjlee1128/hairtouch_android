package kr.co.hairtouch.hairtouch_android.apimanager;


import com.squareup.okhttp.OkHttpClient;

import kr.co.hairtouch.hairtouch_android.util.Constants;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class ServiceGenerator {
    private static OkHttpClient httpClient  = new OkHttpClient();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Constants.API_SERVER_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}