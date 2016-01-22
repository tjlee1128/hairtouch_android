package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import kr.co.hairtouch.hairtouch_android.R;

public class MyPageActivity extends HTLRActivity {

    @OnClick(R.id.activity_mypage_rl_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_mypage_rl_back:
                onBackPressed();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);
    }

}
