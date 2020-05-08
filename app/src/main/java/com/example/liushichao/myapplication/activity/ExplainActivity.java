package com.example.liushichao.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.example.liushichao.myapplication.R;

/**
 * Created by liushichao on 2017/12/2.
 * 使用说明飞洒范德萨发撒法
 */

public class ExplainActivity extends Activity {
    private TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explain);
        text = findViewById(R.id.text);
        text.setText("1.使用该app应在手机设置应用管理里，找到该应用打开悬浮窗开关\n" +
                "2.点击悬浮窗自动显示桌面悬浮窗\n" +
                "3.所有收藏包含所有收藏记录\n" +
                "4.可自定义添加分类\n" +
                "5.长按分类模块删除该模块\n" +
                "6.点击分类模块可查看该模块下的所有收藏\n" +
                "7.点击收藏记录可查看详情\n" +
                "8.长按收藏记录可选择要加入的分类模块\n" +
                "9.卸载本应用后收藏数据将被删除\n" +
                "10.列表页提供侧滑删除功能\n" +
                "11.使用该应用收藏功能是保证网络通畅，复制要保存的网页的链接然后点击桌面浮窗，点击收藏按钮即可");
    }
}
