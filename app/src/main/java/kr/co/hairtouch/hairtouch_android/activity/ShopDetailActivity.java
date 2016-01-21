package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
import kr.co.hairtouch.hairtouch_android.util.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ShopDetailActivity extends HTActivity {

    private int mShopId;
    private Shop mShop;

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
    @Bind(R.id.backgroundImageView) ImageView shopImageView;
    @Bind(R.id.toolbar) Toolbar toolBar;
    @Bind(R.id.collapsingToolbarLayout) CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);

        mShopId = getIntent().getExtras().getInt(Constants.EXTRA_SHOP_ID);

        ButterKnife.bind(this);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // create service
        ShopService shopService = ServiceGenerator.createService(ShopService.class);
        Call<Shop> call = shopService.loadShop(mShopId);

        // asynchronous call
        call.enqueue(mCallback);
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

    private Callback<Shop> mCallback = new Callback<Shop>() {
        @Override
        public void onResponse(Response<Shop> response, Retrofit retrofit) {
            mShop = response.body();

            collapsingToolbarLayout.setTitle(mShop.getName());

            Picasso.with(ShopDetailActivity.this)
                    .load(Constants.API_SERVER_BASE_URL + mShop.getImage())
                    .placeholder(R.drawable.ic_main_logo)
                    .into(shopImageView);

            designerRecyclerView.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
            designerRecyclerView.setAdapter(new DesignerListAdapter(ShopDetailActivity.this, mShop.getDesigners()));
            designerRecyclerView.addItemDecoration(new DividerItemDecoration(ShopDetailActivity.this));
            reviewRecyclerView.setLayoutManager(new LinearLayoutManager(ShopDetailActivity.this));
            reviewRecyclerView.setAdapter(new ReviewListAdapter(ShopDetailActivity.this, mShop.getReviews()));
            reviewRecyclerView.addItemDecoration(new DividerItemDecoration(ShopDetailActivity.this));
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(ShopDetailActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
