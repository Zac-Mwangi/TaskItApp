package com.example.taskit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import java.util.Objects;

public class Sample2Activity extends AppCompatActivity {
    String getFullname,getPhone,getLocation_string;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample2);

        Intent intent = getIntent();
        getFullname = intent.getStringExtra("getFullname");
        getPhone = intent.getStringExtra("getPhone");
        getLocation_string = intent.getStringExtra("getLocation_string");

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getFullname+" ("+getLocation_string+")");
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}