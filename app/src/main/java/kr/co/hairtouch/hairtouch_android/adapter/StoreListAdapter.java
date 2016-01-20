package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.model.Shop;

/**
 * Created by leetaejun on 2016. 1. 19..
 */
public class StoreListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<Shop> shopList;

    public StoreListAdapter(Context context, List<Shop> list) {
        mInflater = LayoutInflater.from(context);
        shopList = list;
    }
    @Override
    public int getCount() {
        return shopList.size();
    }

    @Override
    public Object getItem(int position) {
        return shopList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.row_shop_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.mGradeTextView.setText(new DecimalFormat("#.##").format(shopList.get(position).getReview().getGrade()));
        holder.mReviewTextView.setText("" + shopList.get(position).getReview().getCount());
        holder.mNameTextView.setText(shopList.get(position).getName());
        holder.mAddressTextView.setText(shopList.get(position).getAddress());

        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.row_shop_list_iv_profile)    ImageView mMainImageView;
        @Bind(R.id.row_shop_list_tv_grade)      TextView mGradeTextView;
        @Bind(R.id.row_shop_list_tv_review)     TextView mReviewTextView;
        @Bind(R.id.row_shop_list_tv_name)       TextView mNameTextView;
        @Bind(R.id.row_shop_list_tv_address)    TextView mAddressTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
