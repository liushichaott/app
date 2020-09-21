package com.example.liushichao.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liushichao.myapplication.R;

import java.util.List;

/**
 * Created by liushichao on 2017/12/1.
 * 自定义分类adapter hhhhhhh
 */

public class CreateClassAdapter extends BaseAdapter{

    private List<String> list;
    protected LayoutInflater mInflater;
    private Context context;

    public CreateClassAdapter(Context context, List<String> list) {
        this.list = list;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        String bean = list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_class, null);
            viewHolder.text = convertView.findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(bean);
        return convertView;
    }

    public static class ViewHolder {
        public TextView text;
    }
}
