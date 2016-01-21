package kr.co.hairtouch.hairtouch_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.adapter.ShopListAdapter;
import kr.co.hairtouch.hairtouch_android.apimanager.ServiceGenerator;
import kr.co.hairtouch.hairtouch_android.apimanager.ShopService;
import kr.co.hairtouch.hairtouch_android.model.Shop;
import kr.co.hairtouch.hairtouch_android.util.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ShopActivity extends AppCompatActivity {

    @Bind(R.id.activity_shop_lv) ListView shopListView;
    private List<Shop> mShopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ButterKnife.bind(this);

        // create service
        ShopService shopService = ServiceGenerator.createService(ShopService.class);
        Call<List<Shop>> call = shopService.loadShops();

        // asynchronous call
        call.enqueue(mCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    // create interface
    private Callback<List<Shop>> mCallback = new Callback<List<Shop>>() {
        @Override
        public void onResponse(Response<List<Shop>> response, Retrofit retrofit) {
            mShopList = response.body();

            shopListView.setAdapter(new ShopListAdapter(ShopActivity.this, mShopList));
            shopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ShopActivity.this, ShopDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_SHOP_ID, mShopList.get(position).getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(ShopActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
