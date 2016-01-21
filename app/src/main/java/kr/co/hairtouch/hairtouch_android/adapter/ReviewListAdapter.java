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

import butterknife.Bind;
import butterknife.ButterKnife;
import kr.co.hairtouch.hairtouch_android.R;
import kr.co.hairtouch.hairtouch_android.model.Review;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class ReviewListAdapter extends RecyclerView.Adapter<ReviewListAdapter.sReviewViewHolder> {

    private LayoutInflater mInflater;
    private List<Review> mReviewList;

    public ReviewListAdapter(Context context, List<Review> reviewList) {
        if (reviewList == null) throw new IllegalArgumentException("Data must not be null");
        mInflater = LayoutInflater.from(context);
        mReviewList = reviewList;
    }

    @Override
    public sReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_review_list, parent, false);
        return new sReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(sReviewViewHolder holder, int position) {
        Review review = mReviewList.get(position);
        holder.mGradeTextView.setText(new DecimalFormat("#.##").format(review.getGrade()));
        holder.mNameTextView.setText(review.getMember().getMember_name());
        holder.mDetailTextView.setText(review.getDetail());
    }

    @Override
    public int getItemCount() {
        return mReviewList.size();
    }

    public static class sReviewViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.row_review_list_iv_profile)      ImageView mMainImageView;
        @Bind(R.id.row_review_list_tv_grade)        TextView mGradeTextView;
        @Bind(R.id.row_review_list_tv_name)         TextView mNameTextView;
        @Bind(R.id.row_review_list_tv_detail)       TextView mDetailTextView;
        public sReviewViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
