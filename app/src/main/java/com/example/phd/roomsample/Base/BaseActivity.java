package com.example.phd.roomsample.Base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.phd.roomsample.R;

public class BaseActivity extends AppCompatActivity implements BaseInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
}