package com.example.taskit;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.taskit.Extras.SharedPref;
import com.example.taskit.Extras.savedInfo;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextView sign_up, forgot_pass;
    EditText loginUsername, loginPassword;
    Button BtnLogin;
    LinearLayout LL;
    Vibrator v;
    ProgressDialog pDialog;

    private final String login_url = savedInfo.theUrl+"login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sign_up=findViewById(R.id.create_acc);
        forgot_pass=findViewById(R.id.forgot_pass);

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);

        BtnLogin = findViewById(R.id.BtnLogin);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        LL = findViewById(R.id.mll);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        sign_up.setOnClickListener(this);
        BtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==sign_up){
            //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);
        }
        if(v==BtnLogin){
            validateInput();
        }
    }
    public void validateInput() {
        //getting values
        final String userNameFinal = loginUsername.getText().toString().trim();
        final String passwordFinal = loginPassword.getText().toString().trim();

        //check if EditText are Filled
        if (TextUtils.isEmpty(userNameFinal)) {
            loginUsername.setError("Please enter your username");
            loginUsername.requestFocus();
            v.vibrate(100);
            BtnLogin.setEnabled(true);
            return;
        }
        if (TextUtils.isEmpty(passwordFinal)) {
            loginPassword.setError("Please enter your Password");
            loginPassword.requestFocus();
            v.vibrate(100);
            BtnLogin.setEnabled(true);
            return;
        }
        //Login If everything is fine
        Login();
    }
    private void Login() {
//display loader
        displayLoader();
        // showSimpleProgressDialog(this, "Loading...","Please be patient",false);

        StringRequest request = new StringRequest(Request.Method.POST, login_url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //dismiss loader
                pDialog.dismiss();

                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")) {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                    } else {
                        //get username from json and userID
                        String Username = obj.getString("username");
                        String userID = obj.getString("userID");


                        //storing the user and ID in shared preferences
                        SharedPref.getInstance(getApplicationContext()).storeUserName(Username);
                        SharedPref.getInstance(getApplicationContext()).storeUserID(userID);

                        //starting the Dashboard activity
                        finish();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            pDialog.dismiss();
            Snackbar snackbar = Snackbar
                    .make(LL, "Check your Internet Connection", Snackbar.LENGTH_INDEFINITE).setActionTextColor(Color.YELLOW).setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Login();
                        }
                    });
            snackbar.show();
            //Display error message whenever an error occurs
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("username", loginUsername.getText().toString().trim());
                params.put("password", loginPassword.getText().toString().trim());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void displayLoader() {
        pDialog = new ProgressDialog(LoginActivity.this,R.style.MyAlertDialogStyle);
        pDialog.setMessage("Logging in please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

}