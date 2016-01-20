package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.adapter.DesignerListAdapter;
import kr.co.hairtouch.hairtouch_android.adapter.ReviewListAdapter;
import kr.co.hairtouch.hairtouch_android.apimanager.ServiceGenerator;
import kr.co.hairtouch.hairtouch_android.apimanager.ShopService;
import kr.co.hairtouch.hairtouch_android.model.Shop;
import kr.co.hairtouch.hairtouch_android.recyclerview.DividerItemDecoration;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ShopDetailActivity extends AppCompatActivity {

    private int id;
    private Shop shop;

    @OnClick({R.id.activity_shop_detail_btn_shop, R.id.activity_shop_detail_btn_designer, R.id.activity_shop_detail_btn_review})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_shop_detail_btn_shop:
                shopInfoTextView.setVisibility(View.VISIBLE);
                designerRecyclerView.setVisibility(View.INVISIBLE);
                reviewRecyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.activity_shop_detail_btn_designer:
                shopInfoTextView.setVisibility(View.INVISIBLE);
                designerRecyclerView.setVisibility(View.VISIBLE);
                reviewRecyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.activity_shop_detail_btn_review:
                shopInfoTextView.setVisibility(View.INVISIBLE);
                designerRecyclerView.setVisibility(View.INVISIBLE);
                reviewRecyclerView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Bind(R.id.activity_shop_detail_rv_designer) RecyclerView designerRecyclerView;
    @Bind(R.id.activity_shop_detail_rv_review) RecyclerView reviewRecyclerView;
    @Bind(R.id.emptyView) TextView shopInfoTextView;
    @Bind(R.id.toolbar) Toolbar toolBar;
    @Bind(R.id.collapsingToolbarLayout) CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        id = getIntent().getExtras().getInt("id");

        ButterKnife.bind(this);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // create service
        ShopService shopService = ServiceGenerator.createService(ShopService.class);
        Call<Shop> call = shopService.loadShop(id);

        // asynchronous call
        call.enqueue(callback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    Callback<Shop> callback = new Callback<Shop>() {
        @Override
        public void onResponse(Response<Shop> response, Retrofit retrofit) {
            shop = response.body();

            collapsingToolbarLayout.setTitle(shop.getName());

            designerRecyclerView.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
            designerRecyclerView.setAdapter(new DesignerListAdapter(ShopDetailActivity.this, shop.getDesigners()));
            designerRecyclerView.addItemDecoration(new DividerItemDecoration(ShopDetailActivity.this));
            reviewRecyclerView.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
            reviewRecyclerView.setAdapter(new ReviewListAdapter(ShopDetailActivity.this, shop.getReviews()));
            reviewRecyclerView.addItemDecoration(new DividerItemDecoration(ShopDetailActivity.this));
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(ShopDetailActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
