package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kr.co.hairtouch.hairtouch_android.R;

/**
 * Created by leetaejun on 2016. 1. 22..
 */
public class HTActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.overridePendingTransition(R.anim.start_right_left_enter, R.anim.start_right_left_exit);
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.end_right_left_enter, R.anim.end_right_left_exit);
    }
}
