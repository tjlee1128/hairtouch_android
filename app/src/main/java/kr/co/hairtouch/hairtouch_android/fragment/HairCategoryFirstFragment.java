package kr.co.hairtouch.hairtouch_android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.adapter.HairCategoryListAdapter;
import kr.co.hairtouch.hairtouch_android.apimanager.HairCategoryService;
import kr.co.hairtouch.hairtouch_android.apimanager.ServiceGenerator;
import kr.co.hairtouch.hairtouch_android.model.HairCategory;
import kr.co.hairtouch.hairtouch_android.util.Constants;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by leetaejun on 2016. 1. 22..
 */
public class HairCategoryFirstFragment extends Fragment {

    @Bind(R.id.fragment_hair_category_first_lv) ListView hairCategoryFirstListView;

    private int mIndex;
    private List<HairCategory> mHairCategoryList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mIndex = args.getInt(Constants.ARGUMENT_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hair_category_first, container, false);

        ButterKnife.bind(this, view);

        // create service
        HairCategoryService hairCategoryService = ServiceGenerator.createService(HairCategoryService.class);
        Call<List<HairCategory>> call = hairCategoryService.loadHairCategoryCodes();

        // asynchronous call
        call.enqueue(mCallback);

        return view;
    }

    private Callback<List<HairCategory>> mCallback = new Callback<List<HairCategory>>() {
        @Override
        public void onResponse(Response<List<HairCategory>> response, Retrofit retrofit) {
            mHairCategoryList = response.body();

            Log.i("test", mHairCategoryList.get(0).getName());

            hairCategoryFirstListView.setAdapter(new HairCategoryListAdapter(getActivity(), mHairCategoryList));
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
