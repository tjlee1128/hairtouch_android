package kr.co.hairtouch.hairtouch_android.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
 *
 * HairCategoryFragment 에서는 mIndex가 0일 때는 대분류 검색으로 분기하며,
 *                                    1일 때는 소분류로 검색을 한다.
 */
public class HairCategoryFragment extends Fragment {

    @Bind(R.id.fragment_hair_category_lv) ListView hairCategoryListView;

    private int mIndex;
    private List<HairCategory> mHairCategoryList;
    private int mSelectedCategoryCodeId;
    private int mSelectedCategoryId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mIndex = args.getInt(Constants.ARGUMENT_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hair_category, container, false);

        ButterKnife.bind(this, view);

        // create service
        HairCategoryService hairCategoryService = ServiceGenerator.createService(HairCategoryService.class);
        Call<List<HairCategory>> call;
        if (mIndex == 0) {
            call = hairCategoryService.loadHairCategoryCodes();
        } else {
            call = hairCategoryService.loadHairCategoryes(getArguments().getInt(Constants.ARGUMENT_CATEGORY_CODE_ID));
        }

        // asynchronous call
        call.enqueue(mCallback);

        return view;
    }

    private Callback<List<HairCategory>> mCallback = new Callback<List<HairCategory>>() {
        @Override
        public void onResponse(Response<List<HairCategory>> response, Retrofit retrofit) {
            mHairCategoryList = response.body();

            hairCategoryListView.setAdapter(new HairCategoryListAdapter(getActivity(), mHairCategoryList));
            hairCategoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int nextIndex = mIndex + 1;
                    Bundle args = new Bundle();
                    args.putInt(Constants.ARGUMENT_INDEX, nextIndex);
                    if (mIndex == 0) {
                        args.putInt(Constants.ARGUMENT_CATEGORY_CODE_ID, mHairCategoryList.get(position).getId());
                        HairCategoryFragment hairCategoryFragment = new HairCategoryFragment();
                        hairCategoryFragment.setArguments(args);

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.replace(R.id.fragment_hair_category_container, hairCategoryFragment, "Fragment" + nextIndex);
                        ft.addToBackStack(nextIndex < 2 ? "" + nextIndex : "2");
                        ft.commit();
                    } else {
                        args.clear();
                        args.putInt(Constants.ARGUMENT_CATEGORY_ID, mHairCategoryList.get(position).getId());
                        args.putInt(Constants.ARGUMENT_CATEGORY_CODE_ID, mHairCategoryList.get(position).getHaircategorycode_id());
                        Intent intent = new Intent();
                        intent.putExtras(args);
                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();
                    }
                }
            });
        }

        @Override
        public void onFailure(Throwable t) {
            Toast.makeText(getActivity(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
