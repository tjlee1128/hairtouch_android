package kr.co.hairtouch.hairtouch_android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by leetaejun on 2016. 1. 22..
 */
public abstract class HTBaseAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mList;

    public HTBaseAdapter(Context context, List<T> tList) {
        if (tList == null) throw new IllegalArgumentException("Data must not be null");
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = tList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
