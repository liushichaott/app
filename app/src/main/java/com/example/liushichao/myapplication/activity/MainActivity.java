package com.example.liushichao.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.liushichao.myapplication.R;
import com.example.liushichao.myapplication.service.FloatWindowService;
import com.example.liushichao.myapplication.utils.Params;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button html;//ha1
    private Button startFloatWindow;
    private Button createClass;
    private Button lookClass;
    private Button explain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startFloatWindow = findViewById(R.id.start_float_window);
        createClass = findViewById(R.id.createClass);
        lookClass = findViewById(R.id.lookClass);
        html = findViewById(R.id.html);
        explain = findViewById(R.id.explain);
        startFloatWindow.setOnClickListener(this);
        createClass.setOnClickListener(this);
        lookClass.setOnClickListener(this);
        html.setOnClickListener(this);
        explain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //打开悬浮窗
            case R.id.start_float_window:
                Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
                startService(intent);
                finish();
                break;
            //所有网页
            case R.id.html:
                Intent htmlIntent = new Intent(this, HtmlListActivity.class);
                htmlIntent.putExtra("type","");
                startActivity(htmlIntent);
                break;
            //创建分类
            case R.id.createClass:
                Intent createClassIntent = new Intent(this, CreateClassActivity.class);
                createClassIntent.putExtra(Params.openType, Params.Add);
                startActivity(createClassIntent);
                break;
            //查看分类
            case R.id.lookClass:
                Intent lookClassIntent = new Intent(this, CreateClassActivity.class);
                lookClassIntent.putExtra(Params.openType, Params.Look);
                startActivity(lookClassIntent);
                break;
            //说明
            case R.id.explain:
                Intent explainIntent = new Intent(this, ExplainActivity.class);
                startActivity(explainIntent);
                break;
        }
    }
}
