package kr.co.hairtouch.hairtouch_android.apimanager;


import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class ServiceGenerator {

    public static final String API_BASE_URL = "http://192.168.0.8:3000/";

    private static OkHttpClient httpClient  = new OkHttpClient();
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}