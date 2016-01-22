package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hairtouch.hairtouch_android.R;

public class HairCategoryActivity extends HTTBActivity {

    @OnClick(R.id.activity_hair_category_rl_clear)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_hair_category_rl_clear:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hair_category);

        ButterKnife.bind(this);
    }
}
