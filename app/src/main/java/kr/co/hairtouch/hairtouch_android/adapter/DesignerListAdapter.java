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
import kr.co.hairtouch.hairtouch_android.model.Designer;

/**
 * Created by leetaejun on 2016. 1. 20..
 */
public class DesignerListAdapter extends RecyclerView.Adapter<DesignerListAdapter.sDesignerViewHolder> {

    private LayoutInflater mInflater;
    private List<Designer> mDesignerList;

    public DesignerListAdapter(Context context, List<Designer> designerList) {
        if (designerList == null) throw  new IllegalArgumentException("Data must not be null");
        mInflater = LayoutInflater.from(context);
        mDesignerList = designerList;
    }

    @Override
    public sDesignerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_designer_list, parent, false);
        return new sDesignerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(sDesignerViewHolder holder, int position) {
        Designer designer = mDesignerList.get(position);
        holder.mGradeTextView.setText(new DecimalFormat("#.##").format(designer.getReview().getGrade()));
        holder.mReviewTextView.setText("" + designer.getReview().getCount());
        holder.mNameTextView.setText(designer.getName());
        holder.mPhoneTextView.setText(designer.getPhone());
    }

    @Override
    public int getItemCount() {
        return mDesignerList.size();
    }

    public static class sDesignerViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.row_designer_list_iv_profile)    ImageView mMainImageView;
        @Bind(R.id.row_designer_list_tv_grade)      TextView mGradeTextView;
        @Bind(R.id.row_designer_list_tv_review)     TextView mReviewTextView;
        @Bind(R.id.row_designer_list_tv_name)       TextView mNameTextView;
        @Bind(R.id.row_designer_list_tv_phone)      TextView mPhoneTextView;
        public sDesignerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
