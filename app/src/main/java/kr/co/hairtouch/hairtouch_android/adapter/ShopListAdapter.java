package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.model.Shop;
import kr.co.hairtouch.hairtouch_android.util.Constants;

/**
 * Created by leetaejun on 2016. 1. 19..
 */
public class ShopListAdapter extends HTBaseAdapter<Shop> {

    public ShopListAdapter(Context context, List<Shop> shopList) {
        super(context, shopList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sShopViewHolder holder;

        if (convertView != null) {
            holder = (sShopViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.row_shop_list, parent, false);
            holder = new sShopViewHolder(convertView);
            convertView.setTag(holder);
        }

        Picasso.with(mContext)
                .load(Constants.API_SERVER_BASE_URL + mList.get(position).getImage())
                .resize(100, 80)
                .into(holder.mMainImageView);
        holder.mGradeTextView.setText(new DecimalFormat("#.##").format(mList.get(position).getReview().getGrade()));
        holder.mReviewTextView.setText("" + mList.get(position).getReview().getCount());
        holder.mNameTextView.setText(mList.get(position).getName());
        holder.mAddressTextView.setText(mList.get(position).getAddress());

        return convertView;
    }

    public static class sShopViewHolder {
        @Bind(R.id.row_shop_list_iv_profile)    ImageView mMainImageView;
        @Bind(R.id.row_shop_list_tv_grade)      TextView mGradeTextView;
        @Bind(R.id.row_shop_list_tv_review)     TextView mReviewTextView;
        @Bind(R.id.row_shop_list_tv_name)       TextView mNameTextView;
        @Bind(R.id.row_shop_list_tv_address)    TextView mAddressTextView;

        public sShopViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
