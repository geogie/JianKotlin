package com.georgeren.jiankotlin.ui.activity;

import android.os.Bundle;

import com.georgeren.jiankotlin.R;

/**
 * Created by georgeRen on 2017/12/7.
 */

public class TestActivity extends BaseOriginalActivity{
    @Override
    void setupActivityComponent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }
}
