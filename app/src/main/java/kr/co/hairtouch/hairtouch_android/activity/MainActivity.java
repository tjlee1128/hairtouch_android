package kr.co.hairtouch.hairtouch_android.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hairtouch.hairtouch_android.R;

public class MainActivity extends AppCompatActivity {

    @OnClick({R.id.activity_main_btn_store, R.id.activity_main_btn_designer, R.id.activity_main_btn_hair, R.id.activity_main_btn_mypage})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.activity_main_btn_store:
                intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                break;

            case R.id.activity_main_btn_designer:
                intent = new Intent(MainActivity.this, DesignerActivity.class);
                startActivity(intent);
                break;

            case R.id.activity_main_btn_hair:
                intent = new Intent(MainActivity.this, HairActivity.class);
                startActivity(intent);
                break;

            case R.id.activity_main_btn_mypage:
                intent = new Intent(MainActivity.this, MyPageActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
