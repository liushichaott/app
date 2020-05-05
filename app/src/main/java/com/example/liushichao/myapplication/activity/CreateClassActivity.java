package com.example.liushichao.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.liushichao.myapplication.R;
import com.example.liushichao.myapplication.adapter.CreateClassAdapter;
import com.example.liushichao.myapplication.mode.WebLinkBean;
import com.example.liushichao.myapplication.utils.DbHelper;
import com.example.liushichao.myapplication.utils.DbHelperDao;
import com.example.liushichao.myapplication.utils.Params;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liushichao on 2017/12/1.
 * 创建自定义分类
 */

public class CreateClassActivity extends Activity {



    private EditText editText;
    private GridView grid_view;
    private LinearLayout inputLayout;
    private CreateClassAdapter adapter;
    private List<String> data = new ArrayList<>();
    private int openType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);
        grid_view = findViewById(R.id.grid);
        editText = findViewById(R.id.editText);
        inputLayout = findViewById(R.id.inputLayout);
        openType = getIntent().getIntExtra(Params.openType, 0);
        if (openType == Params.Add) {
            //只有添加分类时才显示输入框
            inputLayout.setVisibility(View.VISIBLE);
        }
        data = DbHelperDao.getInstance(this).getHtmlTypeItems();
        if (data.isEmpty()) {
            Toast.makeText(this, "暂无分类", Toast.LENGTH_SHORT).show();
        }
        adapter = new CreateClassAdapter(this, data);
        grid_view.setAdapter(adapter);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    String tempClass = editText.getText().toString();
                    if (!tempClass.isEmpty() && DbHelperDao.getInstance(CreateClassActivity.this).getHtmlTypeItem(tempClass).isEmpty()) {
                        DbHelperDao.getInstance(CreateClassActivity.this).addHtmlType(tempClass);
                        data.add(tempClass);
                        editText.setText("");
                        adapter.notifyDataSetChanged();
                    }
                    return true;
                }
                return false;
            }
        });
        //选择当前分类,1。加入到该分类下 2。查看该分类下网页
        grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (openType == Params.Input) {
                    //加入到该分类下
                    WebLinkBean bean = getIntent().getParcelableExtra(Params.data);
                    bean.setType(data.get(i));
                    //修改数据库type
                    DbHelperDao.getInstance(CreateClassActivity.this).upDateUrl(bean);
                    Toast.makeText(CreateClassActivity.this, "加入分类成功", Toast.LENGTH_SHORT).show();
                } else if (openType == Params.Look) {
                    //查看该分类下网页
                    Intent intent = new Intent(CreateClassActivity.this, HtmlListActivity.class);
                    intent.putExtra("type", data.get(i));
                    startActivity(intent);
                }
            }
        });

        //长按删除分类框
        grid_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DbHelperDao.getInstance(CreateClassActivity.this).removeHtmlType(data.get(i));
                data.remove(i);
                //todo 将分类下所有网页重新划分
                /*List<WebLinkBean> list=DbHelperDao.getInstance(CreateClassActivity.this).getByTypeHtmlItem(data.get(i));
                for (WebLinkBean bean:list){
                    bean.setType("default");
                    DbHelperDao.getInstance(CreateClassActivity.this).upDateUrl(bean);
                }*/
                adapter.notifyDataSetChanged();
                Toast.makeText(CreateClassActivity.this, "分类已删除", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


}
