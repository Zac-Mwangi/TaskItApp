package com.example.taskit;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.taskit.Extras.SampleModel;
import com.example.taskit.Extras.savedInfo;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Sample2Activity extends AppCompatActivity implements View.OnClickListener{
    private final String submit_feedbackURL = savedInfo.theUrl+"feedback.php";
    private final String loadURL = savedInfo.theUrl+"ttl_avr_feedback.php";
    String getFullname,getPhone,getLocation_string,rating;
    int getUser_id,response_total; float response_average;
    TextView name,ttlTV,aveTV;
    Button msg, call,BTN_feedback;
    EditText et_feedback;
    RatingBar ratingbar;
    SwipeRefreshLayout swipeRefreshLayout;
    Vibrator v;
    LinearLayout LL;
    private final int MY_PERMISSIONS_REQUEST_CALL = 1;
    private ProgressDialog pDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample2);

        name = findViewById(R.id.name);
        msg = findViewById(R.id.msg);
        call = findViewById(R.id.call);
        BTN_feedback = findViewById(R.id.btn_feedback);
        ratingbar = findViewById(R.id.ratingBar);
        et_feedback = findViewById(R.id.et_feedback);
        ttlTV = findViewById(R.id.ttl_feedback_value);
        aveTV = findViewById(R.id.avr_rating_value);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        LL = findViewById(R.id.mll);

        Intent intent = getIntent();
        getFullname = intent.getStringExtra("getFullname");
        getPhone = intent.getStringExtra("getPhone");
        getLocation_string = intent.getStringExtra("getLocation_string");
        getUser_id = intent.getIntExtra("getUser_id",0);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(getFullname+" ("+getLocation_string+")");
        name.setText(getFullname);

        load();
        swipe();

        msg.setOnClickListener(this);
        call.setOnClickListener(this);
        BTN_feedback.setOnClickListener(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v==msg){
            //Toast.makeText(this, "msg2", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MsgActivity.class);
            intent.putExtra("getPhone",getPhone);
            startActivity(intent);
        }
        if(v==call) {
            if (ContextCompat.checkSelfPermission(Sample2Activity.this, Manifest.permission.CALL_PHONE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Sample2Activity.this, new String[]{Manifest.permission.CALL_PHONE},
                        MY_PERMISSIONS_REQUEST_CALL);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                //callIntent.setData(Uri.parse("tel:0790780464"));
                callIntent.setData(Uri.parse("tel:"+getPhone));
                startActivity(callIntent);
            }
        }
        if(v==BTN_feedback){
           // Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show();
           // String rating=String.valueOf(ratingbar.getRating());
           // Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            AlertDialog();
        }
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(Sample2Activity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Data Submission in Progress...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    private void displayLoader2() {
        pDialog = new ProgressDialog(Sample2Activity.this, R.style.MyAlertDialogStyle);
        pDialog.setMessage("Loading details...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    private void AlertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        rating=String.valueOf(ratingbar.getRating());
        alertDialogBuilder.setMessage("Are you sure you want to Rate "+getFullname+ " a "+rating+" rating");
        alertDialogBuilder.setPositiveButton("YES",
                (arg0, arg1) -> Submit());

        alertDialogBuilder.setNegativeButton("NO",
                (dialog, which) -> Toast.makeText(Sample2Activity.this, "Feedback not posted", Toast.LENGTH_SHORT).show());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void Submit() {
        displayLoader();
        StringRequest request = new StringRequest(Request.Method.POST, submit_feedbackURL, response -> {
            //dismiss loader
            pDialog.dismiss();
            try {
                JSONObject obj = new JSONObject(response);
                if (obj.getBoolean("error")) {
                    v.vibrate(100);
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
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
                params.put("feedback_by", String.valueOf(0));
                params.put("rating", rating);
                params.put("user_id", String.valueOf(getUser_id));
                params.put("feedback_text", et_feedback.getText().toString().trim());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void load() {
        displayLoader2();
        StringRequest request = new StringRequest(Request.Method.POST, loadURL, response -> {
            //dismiss loader
            pDialog.dismiss();
            try {
                JSONObject obj = new JSONObject(response);
                    response_total = obj.getInt("total");
                    response_average = (float) obj.getDouble("average");
                    ttlTV.setText(""+response_total);
                    aveTV.setText(""+response_average);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", String.valueOf(getUser_id));
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void swipe() {
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            finish();
            startActivity(getIntent());
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}