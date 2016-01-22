package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
import kr.co.hairtouch.hairtouch_android.adapter.ReviewListAdapter;
import kr.co.hairtouch.hairtouch_android.apimanager.DesignerService;
import kr.co.hairtouch.hairtouch_android.apimanager.ServiceGenerator;
import kr.co.hairtouch.hairtouch_android.model.Designer;
import kr.co.hairtouch.hairtouch_android.recyclerview.DividerItemDecoration;
import kr.co.hairtouch.hairtouch_android.util.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class DesignerDetailActivity extends HTLRActivity {

    private int mDesignerId;
    private Designer mDesigner;

    @OnClick({R.id.activity_designer_detail_btn_designer, R.id.activity_designer_detail_btn_hair, R.id.activity_designer_detail_btn_review})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_designer_detail_btn_designer:
                designerInfoTextView.setVisibility(View.VISIBLE);
                hairRecyclerView.setVisibility(View.INVISIBLE);
                reviewRecyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.activity_designer_detail_btn_hair:
                designerInfoTextView.setVisibility(View.INVISIBLE);
                hairRecyclerView.setVisibility(View.VISIBLE);
                reviewRecyclerView.setVisibility(View.INVISIBLE);
                break;
            case R.id.activity_designer_detail_btn_review:
                designerInfoTextView.setVisibility(View.INVISIBLE);
                hairRecyclerView.setVisibility(View.INVISIBLE);
                reviewRecyclerView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Bind(R.id.activity_designer_detail_rv_hair) RecyclerView hairRecyclerView;
    @Bind(R.id.activity_designer_detail_rv_review) RecyclerView reviewRecyclerView;
    @Bind(R.id.emptyView) TextView designerInfoTextView;
    @Bind(R.id.toolbar) Toolbar toolBar;
    @Bind(R.id.collapsingToolbarLayout) CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_detail);

        mDesignerId = getIntent().getExtras().getInt(Constants.EXTRA_DESIGNER_ID);

        ButterKnife.bind(this);

        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // create service
        DesignerService designerService = ServiceGenerator.createService(DesignerService.class);
        Call<Designer> call = designerService.loadDesigner(mDesignerId);

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

    private Callback<Designer> mCallback = new Callback<Designer>() {
        @Override
        public void onResponse(Response<Designer> response, Retrofit retrofit) {
            mDesigner = response.body();

            collapsingToolbarLayout.setTitle(mDesigner.getName());
            reviewRecyclerView.setLayoutManager(new LinearLayoutManager(DesignerDetailActivity.this));
            reviewRecyclerView.setAdapter(new ReviewListAdapter(DesignerDetailActivity.this, mDesigner.getReviews()));
            reviewRecyclerView.addItemDecoration(new DividerItemDecoration(DesignerDetailActivity.this));
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(DesignerDetailActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
