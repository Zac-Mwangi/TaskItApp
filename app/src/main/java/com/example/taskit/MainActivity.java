package com.example.taskit;import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.taskit.Extras.HomeAdapter;
import com.example.taskit.Extras.SharedPref;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerHome;
    List<Integer> imageList = new ArrayList<>();
    List<String> imageDescriptionList = new ArrayList<>();
    TextView kaki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kaki=findViewById(R.id.kaki);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.exit);// set drawable icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (!SharedPref.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        String loggedUsername = SharedPref.getInstance(this).LoggedInUser();
        kaki.setText("Hello "+loggedUsername+", What task would you like help with today?");

        initialize();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
               // Toast.makeText(this, "click..!!", Toast.LENGTH_SHORT).show();
                finish();
                SharedPref.getInstance(getApplicationContext()).logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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