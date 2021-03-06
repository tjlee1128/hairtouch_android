package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.model.Hair;
import kr.co.hairtouch.hairtouch_android.util.Constants;

/**
 * Created by leetaejun on 2016. 1. 22..
 */
public class HairGridAdapter extends HTBaseAdapter<Hair> {

    public HairGridAdapter(Context context, List<Hair> hairList) {
        super(context, hairList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sHairGridViewHolder holder;
        if (convertView != null) {
            holder = (sHairGridViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.column_hair_list, parent, false);
            holder = new sHairGridViewHolder(convertView);
            convertView.setTag(holder);
        }

        Picasso.with(mContext)
                .load(Constants.API_SERVER_BASE_URL + mList.get(position).getImages().get(0).getImage())
                .resize(160, 200)
                .into(holder.mMainImageView);
        holder.mNameTextView.setText(mList.get(position).getTitle());

        return convertView;
    }

    public static class sHairGridViewHolder {
        @Bind(R.id.column_hair_iv_main) ImageView mMainImageView;
        @Bind(R.id.column_hair_tv_name) TextView mNameTextView;

        public sHairGridViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
