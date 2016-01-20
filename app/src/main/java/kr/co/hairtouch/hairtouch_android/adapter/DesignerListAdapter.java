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
public class DesignerListAdapter extends RecyclerView.Adapter<DesignerListAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private List<Designer> designerList;

    public DesignerListAdapter(Context context, List<Designer> designerList) {
        if (designerList == null) throw  new IllegalArgumentException("Data must not be null");
        mInflater = LayoutInflater.from(context);
        this.designerList = designerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_designer_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Designer designer = designerList.get(position);
        holder.mGradeTextView.setText(new DecimalFormat("#.##").format(designer.getReview().getGrade()));
        holder.mReviewTextView.setText("" + designer.getReview().getCount());
        holder.mNameTextView.setText(designer.getName());
        holder.mPhoneTextView.setText(designer.getPhone());
    }

    @Override
    public int getItemCount() {
        return designerList.size();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.row_designer_list_iv_profile)    ImageView mMainImageView;
        @Bind(R.id.row_designer_list_tv_grade)      TextView mGradeTextView;
        @Bind(R.id.row_designer_list_tv_review)     TextView mReviewTextView;
        @Bind(R.id.row_designer_list_tv_name)       TextView mNameTextView;
        @Bind(R.id.row_designer_list_tv_phone)      TextView mPhoneTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
