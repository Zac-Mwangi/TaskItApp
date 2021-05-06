package com.example.taskit;import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toolbar;

import com.example.taskit.Extras.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerHome;
    List<Integer> imageList = new ArrayList<>();
    List<String> imageDescriptionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }
    public void initialize(){
        recyclerHome = findViewById(R.id.recyclerView);
        //Setting layout manager for our RecyclerView
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerHome.setLayoutManager(gridLayoutManager);
        //next create an adapter for the recyclerView in  HomeAdapter;

            imageList.add(R.drawable.laundry);
            imageList.add(R.drawable.electriacian);
            imageList.add(R.drawable.plumber);
            imageList.add(R.drawable.painting);
            imageList.add(R.drawable.movers);
            imageList.add(R.drawable.cobbler);
            imageList.add(R.drawable.delivery);
            imageList.add(R.drawable.mechanic);

            imageDescriptionList.add(getResources().getString(R.string.laundry));
            imageDescriptionList.add(getResources().getString(R.string.electrician));
            imageDescriptionList.add(getResources().getString(R.string.plumber));
            imageDescriptionList.add(getResources().getString(R.string.painting));
            imageDescriptionList.add(getResources().getString(R.string.movers));
            imageDescriptionList.add(getResources().getString(R.string.cobbler));
            imageDescriptionList.add(getResources().getString(R.string.delivery));
            imageDescriptionList.add(getResources().getString(R.string.mechanics));

        //after list are ready we send it to the adapter

        recyclerHome.setAdapter(new HomeAdapter(imageList,imageDescriptionList));
    }
}