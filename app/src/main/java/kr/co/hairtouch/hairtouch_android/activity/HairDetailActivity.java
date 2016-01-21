package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.adapter.HairImageListAdapter;
import kr.co.hairtouch.hairtouch_android.apimanager.HairService;
import kr.co.hairtouch.hairtouch_android.apimanager.ServiceGenerator;
import kr.co.hairtouch.hairtouch_android.model.Hair;
import kr.co.hairtouch.hairtouch_android.util.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class HairDetailActivity extends AppCompatActivity {

    private int mHairId;
    private Hair mHair;
    @Bind(R.id.activity_hair_detail_tv_title) TextView titleTextView;
    @Bind(R.id.activity_hair_detail_iv_zoom) ImageView zoomImageView;
    @Bind(R.id.activity_hair_detail_lv) ListView hairImageListView;

    @OnClick(R.id.activity_hair_detail_rl_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_hair_detail_rl_back:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.start_right_left_enter, R.anim.start_right_left_exit);
        setContentView(R.layout.activity_hair_detail);

        mHairId = getIntent().getExtras().getInt(Constants.EXTRA_HAIR_ID);

        ButterKnife.bind(this);

        // create service
        HairService hairService = ServiceGenerator.createService(HairService.class);
        Call<Hair> call = hairService.loadHair(mHairId);

        // asynchronous call
        call.enqueue(mCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.end_right_left_enter, R.anim.end_right_left_exit);
    }

    private Callback<Hair> mCallback = new Callback<Hair>() {
        @Override
        public void onResponse(Response<Hair> response, Retrofit retrofit) {
            mHair = response.body();

            titleTextView.setText(mHair.getTitle());
            Picasso.with(HairDetailActivity.this)
                    .load(Constants.API_SERVER_BASE_URL + mHair.getImages().get(0).getImage())
                    .into(zoomImageView);
            hairImageListView.setAdapter(new HairImageListAdapter(HairDetailActivity.this, mHair.getImages()));
            hairImageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Picasso.with(HairDetailActivity.this)
                            .load(Constants.API_SERVER_BASE_URL + mHair.getImages().get(position).getImage())
                            .into(zoomImageView);
                }
            });
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(HairDetailActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
