package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.model.HairImage;
import kr.co.hairtouch.hairtouch_android.util.Constants;

/**
 * Created by leetaejun on 2016. 1. 22..
 */
public class HairImageListAdapter extends HTBaseAdapter<HairImage> {

    public HairImageListAdapter(Context context, List<HairImage> hairImageList) {
        super(context, hairImageList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        sHairImageViewHolder holder;
        if (convertView != null) {
            holder = (sHairImageViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.row_hairimage_list, parent, false);
            holder = new sHairImageViewHolder(convertView);
            convertView.setTag(holder);
        }

        Picasso.with(mContext)
                .load(Constants.API_SERVER_BASE_URL + mList.get(position).getImage())
                .into(holder.mImageView);

        return convertView;
    }

    public static class sHairImageViewHolder {
        @Bind(R.id.row_hairimage_list_iv) ImageView mImageView;
        public sHairImageViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
