package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.model.Review;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.ViewHolder> {

    LayoutInflater mInflater;
    List<Review> reviewList;

    public ReviewListAdapter(Context context, List<Review> reviewList) {
        if (reviewList == null) throw new IllegalArgumentException("Data must not be null");
        mInflater = LayoutInflater.from(context);
        this.reviewList = reviewList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_review_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Review review = reviewList.get(position);
        holder.mGradeTextView.setText(new DecimalFormat("#.##").format(review.getGrade()));
        holder.mNameTextView.setText(review.getMember().getMember_name());
        holder.mDetailTextView.setText(review.getDetail());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mMainImageView;
        TextView mGradeTextView;
        TextView mNameTextView;
        TextView mDetailTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mMainImageView  = (ImageView) itemView.findViewById(R.id.row_review_list_iv_profile);
            mGradeTextView  = (TextView) itemView.findViewById(R.id.row_review_list_tv_grade);
            mNameTextView   = (TextView) itemView.findViewById(R.id.row_review_list_tv_name);
            mDetailTextView  = (TextView) itemView.findViewById(R.id.row_review_list_tv_detail);
        }
    }
}
