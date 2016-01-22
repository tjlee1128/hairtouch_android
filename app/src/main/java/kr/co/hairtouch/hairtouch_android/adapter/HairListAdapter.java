package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class HairListAdapter extends RecyclerView.Adapter<HairListAdapter.sHairListViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Hair> mHairList;

    public HairListAdapter(Context context, List<Hair> reviewList) {
        if (reviewList == null) throw new IllegalArgumentException("Data must not be null");
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mHairList = reviewList;
    }

    @Override
    public sHairListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_hair_list, parent, false);
        return new sHairListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(sHairListViewHolder holder, int position) {
        Hair hair = mHairList.get(position);
        Picasso.with(mContext)
                .load(Constants.API_SERVER_BASE_URL + mHairList.get(position).getImages().get(0).getImage())
                .into(holder.mImageView);
        holder.mTitleTextView.setText(hair.getTitle());
        holder.mSubTitleTextView.setText(hair.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return mHairList.size();
    }

    public static class sHairListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row_hair_list_iv) ImageView mImageView;
        @Bind(R.id.row_hair_list_tv_title) TextView mTitleTextView;
        @Bind(R.id.row_hair_list_tv_subtitle) TextView mSubTitleTextView;
        public sHairListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
