package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.model.HairCategory;

/**
 * Created by leetaejun on 2016. 1. 22..
 */
public class HairCategoryListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<HairCategory> mHairCategoryList;

    public HairCategoryListAdapter(Context context, List<HairCategory> hairCategoryList) {
        if (hairCategoryList == null) throw new IllegalArgumentException("Data must not be null");
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mHairCategoryList = hairCategoryList;
    }

    @Override
    public int getCount() {
        return mHairCategoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHairCategoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sHairCategoryListViewHolder holder;
        if (convertView != null) {
            holder = (sHairCategoryListViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.row_haircategoryfirst_list, parent, false);
            holder = new sHairCategoryListViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.mNameTextView.setText(mHairCategoryList.get(position).getName().replace(",", " > "));

        return convertView;
    }

    public static class sHairCategoryListViewHolder {

        @Bind(R.id.row_haircategoryfirst_tv_name) TextView mNameTextView;

        public sHairCategoryListViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
