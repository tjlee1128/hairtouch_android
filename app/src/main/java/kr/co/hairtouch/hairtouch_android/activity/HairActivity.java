package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.adapter.HairGridAdapter;
import kr.co.hairtouch.hairtouch_android.apimanager.HairService;
import kr.co.hairtouch.hairtouch_android.apimanager.ServiceGenerator;
import kr.co.hairtouch.hairtouch_android.model.Hair;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class HairActivity extends AppCompatActivity {

    @Bind(R.id.activity_hair_gv) GridView hairGridView;
    private List<Hair> mHairList;

    @OnClick(R.id.activity_hair_rl_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_hair_rl_back:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair);

        ButterKnife.bind(this);

        // create service
        HairService hairService = ServiceGenerator.createService(HairService.class);
        Call<List<Hair>> call = hairService.loadHairs();

        // asynchronous call
        call.enqueue(mCallback);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private Callback<List<Hair>> mCallback = new Callback<List<Hair>>() {
        @Override
        public void onResponse(Response<List<Hair>> response, Retrofit retrofit) {
            mHairList = response.body();

            hairGridView.setAdapter(new HairGridAdapter(HairActivity.this, mHairList));
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };
}
