package com.android.app.aboutloc.ui.view.activity;

import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.app.aboutloc.R;
import com.android.app.aboutloc.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final int LAYOUT_ID=R.layout.activity_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Binding the activity
        ActivityMainBinding binding= DataBindingUtil.setContentView(this,LAYOUT_ID);
        setSupportActionBar(binding.toolbar);
    }
    @SuppressWarnings("EmptyMethod")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // avoid creating new activity on screen rotation
    }
}
