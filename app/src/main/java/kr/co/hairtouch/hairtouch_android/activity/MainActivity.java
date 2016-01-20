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
                Toast.makeText(MainActivity.this, "store", Toast.LENGTH_LONG).show();
                intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.activity_main_btn_designer:
                Toast.makeText(MainActivity.this, "designer", Toast.LENGTH_LONG).show();
                break;
            case R.id.activity_main_btn_hair:
                Toast.makeText(MainActivity.this, "hair", Toast.LENGTH_LONG).show();
                break;
            case R.id.activity_main_btn_mypage:
                Toast.makeText(MainActivity.this, "mypage", Toast.LENGTH_LONG).show();
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
