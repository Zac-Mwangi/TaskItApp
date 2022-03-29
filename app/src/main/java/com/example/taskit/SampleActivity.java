package com.example.taskit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.taskit.Extras.SampleAdapter;
import com.example.taskit.Extras.SampleModel;
import com.example.taskit.Extras.savedInfo;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SampleActivity extends AppCompatActivity implements View.OnClickListener, SampleAdapter.OnItemClickListener {

    private final String selectURL = savedInfo.theUrl + "select.php";
    List<SampleModel> List;
    RecyclerView recyclerView;
    private ProgressDialog pDialog;
    Vibrator v;
    SwipeRefreshLayout swipeRefreshLayout;
    LinearLayout LL;
    String service;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        //action bar
        Intent intent = getIntent();
        service = intent.getStringExtra("service");
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(service);

        //initialize

        recyclerView = findViewById(R.id.sample_recycler);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        LL = findViewById(R.id.mll);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(SampleActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        List = new ArrayList<>();
        //creating adapter object and setting it to recyclerview
        Load();
        swipe();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void Load() {
        displayLoader();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, selectURL,
                response -> {
                    pDialog.dismiss();
                    try {
                        //converting the string to json array object
                        JSONArray array = new JSONArray(response);

                        //traversing through all the object
                        for (int i = 0; i < array.length(); i++) {

                            //getting product object from json array
                            JSONObject ls = array.getJSONObject(i);

                            //adding the product to product list
                            List.add(new SampleModel(
                                    ls.getString("fullname"),
                                    ls.getString("email"),
                                    ls.getString("phone"),
                                    //    product.getString("service_string"),
                                    ls.getString("location_string"),
                                    ls.getInt("user_id")
                            ));
                        }
                        //creating adapter object and setting it to recyclerview
                        SampleAdapter adapter = new SampleAdapter(SampleActivity.this, List);
                        //recyclerView.setAdapter(new SampleAdapter(SampleActivity.this,List));
                        recyclerView.setAdapter(adapter);
                        adapter.setOnItemClickListener(SampleActivity.this);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    v.vibrate(100);
                    pDialog.dismiss();
                    Snackbar snackbar = Snackbar
                            .make(LL, "Check your Internet Connection", Snackbar.LENGTH_INDEFINITE).setActionTextColor(Color.YELLOW).setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    startActivity(getIntent());
                                    finish();
                                    overridePendingTransition(0, 0);
                                }
                            });
                    snackbar.show();
                    //Display error message whenever an error occurs
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("service", service);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void displayLoader() {
        pDialog = new ProgressDialog(SampleActivity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Loading please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    public void swipe() {
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            finish();
            startActivity(getIntent());
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, Sample2Activity.class);
        SampleModel clickedItem = List.get(position);

        intent.putExtra("getFullname", clickedItem.getFullname());
        intent.putExtra("getPhone", clickedItem.getPhone());
        intent.putExtra("getLocation_string", clickedItem.getLocation_string());
        intent.putExtra("getUser_id", clickedItem.getUser_id());

        startActivity(intent);
        // Toast.makeText(this, "yoh", Toast.LENGTH_SHORT).show();
    }
}