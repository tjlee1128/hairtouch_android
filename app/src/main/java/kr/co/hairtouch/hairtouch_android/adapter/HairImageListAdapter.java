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
public class HairImageListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<HairImage> mHairImageList;

    public HairImageListAdapter(Context context, List<HairImage> hairImageList) {
        if (hairImageList == null) throw new IllegalArgumentException("Data must not be null");
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mHairImageList = hairImageList;
    }

    @Override
    public int getCount() {
        return mHairImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHairImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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
                .load(Constants.API_SERVER_BASE_URL + mHairImageList.get(position).getImage())
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
