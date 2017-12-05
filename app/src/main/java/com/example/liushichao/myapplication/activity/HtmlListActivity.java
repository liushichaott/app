package com.example.liushichao.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.liushichao.myapplication.R;
import com.example.liushichao.myapplication.adapter.HtmlListAdapter;
import com.example.liushichao.myapplication.listener.RemoveHtmlListener;
import com.example.liushichao.myapplication.mode.WebLinkBean;
import com.example.liushichao.myapplication.utils.DbHelperDao;
import com.example.liushichao.myapplication.utils.Params;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushichao on 2017/11/28.
 * 展示网页列表
 */

public class HtmlListActivity extends Activity implements RemoveHtmlListener {
    private ListView listView;
    private List<WebLinkBean> data = new ArrayList<>();
    private HtmlListAdapter adapter;
    private String type;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_list);
        listView = findViewById(R.id.list);
        type=getIntent().getStringExtra("type");
        if(type.isEmpty()){
            //无类型，查询所有
            data = DbHelperDao.getInstance(this).getHtmlItems();
        }else {
            //查询指定类型
            data = DbHelperDao.getInstance(this).getByTypeHtmlItem(type);
        }
        if (data.isEmpty()) {
            Toast.makeText(this, "无收藏记录", Toast.LENGTH_SHORT).show();
            return;
        }
        adapter = new HtmlListAdapter(this, data,this);
        listView.setAdapter(adapter);

        //点击查看网页
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(HtmlListActivity.this, WebViewActivity.class);
                intent.putExtra(Params.data, data.get(i).getUrl());
                intent.putExtra(Params.openType,Params.Look);
                startActivity(intent);
            }
        });

        //长按加入分类
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(type.isEmpty()){
                    Intent addIntent=new Intent(HtmlListActivity.this,CreateClassActivity.class);
                    addIntent.putExtra(Params.data,data.get(i));
                    addIntent.putExtra(Params.openType,Params.Input);
                    startActivity(addIntent);
                }
                return true;
            }
        });
    }

    //删除数据
    @Override
    public void onRemoveClickListener(int position, WebLinkBean bean) {
        DbHelperDao.getInstance(this).removeHtml(data.get(position).getUrl());
        data.remove(position);
        adapter.notifyDataSetChanged();
    }
}
