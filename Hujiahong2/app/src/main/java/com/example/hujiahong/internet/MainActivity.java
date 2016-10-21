package com.example.hujiahong.internet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import nf.framework.act.browser.InnerBrowserActivity;

/**
 * 主菜单
 */
public class MainActivity extends MyBaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.navigationBarLayout.setVisibility(View.VISIBLE);
        super.top_textview.setText("医大帮Test");
        initView();
        Intent intent = new Intent();
        intent.setClass(this, InnerBrowserActivity.class);
        intent.putExtra(InnerBrowserActivity.INTENT_SOURCE, "");
        intent.putExtra(InnerBrowserActivity.INTENT_TITLE, "");
        intent.putExtra(InnerBrowserActivity.INTENT_URL, "http://58.57.165.71:5001/login.aspx");
        startActivity(intent);
        finish();

    }

    private void initView() {
        View mainView = LayoutInflater.from(this).inflate(R.layout.main_layout, super.mainlayout, false);
        super.mainlayout.addView(mainView);
    }
}
