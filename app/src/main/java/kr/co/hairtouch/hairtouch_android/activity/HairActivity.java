package kr.co.hairtouch.hairtouch_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import kr.co.hairtouch.hairtouch_android.util.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class HairActivity extends HTLRActivity {

    @Bind(R.id.activity_hair_gv) GridView hairGridView;
    private List<Hair> mHairList;

    @OnClick({R.id.activity_hair_rl_back, R.id.activity_hair_rl_title})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.activity_hair_rl_back:
                onBackPressed();
                break;

            case R.id.activity_hair_rl_title:
                intent = new Intent(HairActivity.this, HairCategoryActivity.class);
                startActivity(intent);
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
            hairGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(HairActivity.this, HairDetailActivity.class);
                    intent.putExtra(Constants.EXTRA_HAIR_ID, mHairList.get(position).getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onFailure(Throwable t) {

        }
    };
}
