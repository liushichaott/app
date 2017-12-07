package com.example.liushichao.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.liushichao.myapplication.R;
import com.example.liushichao.myapplication.activity.WebViewActivity;
import com.example.liushichao.myapplication.listener.RemoveHtmlListener;
import com.example.liushichao.myapplication.mode.WebLinkBean;
import com.example.liushichao.myapplication.utils.Params;

import java.util.List;

/**
 * Created by liushichao on 2017/11/28.
 * 网页列表adapter
 */

public class HtmlListAdapter extends BaseAdapter {
    private List<WebLinkBean> list;
    protected LayoutInflater mInflater;
    private Context context;
    private RemoveHtmlListener listener;

    public HtmlListAdapter(Context context, List<WebLinkBean> list, RemoveHtmlListener listener) {
        this.list = list;
        this.context = context;
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        final WebLinkBean bean = list.get(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_html, null);
            viewHolder.name = convertView.findViewById(R.id.name);
            viewHolder.time = convertView.findViewById(R.id.time);
            viewHolder.delete = convertView.findViewById(R.id.delete);
            viewHolder.content = convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.time.setText(bean.getTime());
        viewHolder.name.setText(bean.getTitle());
        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onRemoveClickListener(position,bean);
                }
            }
        });

        viewHolder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra(Params.data, bean.getUrl());
                intent.putExtra(Params.openType, Params.Look);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView time;
        public TextView delete;
        public LinearLayout content;
    }
}
