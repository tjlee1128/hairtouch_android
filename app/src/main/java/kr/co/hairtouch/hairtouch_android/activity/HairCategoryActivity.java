package kr.co.hairtouch.hairtouch_android.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.fragment.HairCategoryFirstFragment;
import kr.co.hairtouch.hairtouch_android.util.Constants;

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

        Bundle args = new Bundle();
        args.putInt(Constants.ARGUMENT_INDEX, 0);

        HairCategoryFirstFragment hairCategoryFirstFragment = new HairCategoryFirstFragment();
        hairCategoryFirstFragment.setArguments(args);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_hair_category_container, hairCategoryFirstFragment, "first");
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack("1", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            return ;
        }
        super.onBackPressed();
    }
}
