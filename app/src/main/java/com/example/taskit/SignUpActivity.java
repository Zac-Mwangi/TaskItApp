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
import android.widget.RadioButton;
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

public class SignUpActivity extends AppCompatActivity  implements View.OnClickListener{
EditText loginUsername,loginPhoneNumber, loginLocation, loginPassword, loginPassword2;
RadioButton radio_male , radio_female;
Button SignUp;
Vibrator v;
LinearLayout LL;
ProgressDialog pDialog;
TextView Login;
private final String add_userURL = savedInfo.theUrl+"addUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        loginUsername = findViewById(R.id.loginUsername);
        loginPhoneNumber = findViewById(R.id.loginPhoneNumber);
        loginLocation = findViewById(R.id.loginLocation);
        loginPassword = findViewById(R.id.loginPassword);
        loginPassword2 = findViewById(R.id.loginPassword2);
        Login = findViewById(R.id.Login);
        SignUp = findViewById(R.id.SignUp);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        LL = findViewById(R.id.mll);

        SignUp.setOnClickListener(this);
        Login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if ( v== SignUp){
           // Toast.makeText(this, "You're Signing Up", Toast.LENGTH_SHORT).show();
            validateInput();
        }
        if (v== Login){
            //Toast.makeText(this, "You're  Up", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
            startActivity(i);

        }

    }
    public void validateInput() {
        //getting values

        final String Username = loginUsername.getText().toString().trim();
        final String passwordFinal = loginPassword.getText().toString().trim();
        final String confirmPasswordFinal = loginPassword2.getText().toString().trim();
        final String phoneFinal = loginPhoneNumber.getText().toString().trim();
        final String locFinal = loginLocation.getText().toString().trim();

        //check if EditText are Filled
        if (TextUtils.isEmpty(Username)) {
            loginUsername.setError("Name Cannot be empty");
            loginUsername.requestFocus();
            v.vibrate(100);
            SignUp.setEnabled(true);
            return;
        }
        if (TextUtils.isEmpty(phoneFinal)) {
            loginPhoneNumber.setError("Phone Number Cannot be empty");
            loginPhoneNumber.requestFocus();
            v.vibrate(100);
            SignUp.setEnabled(true);
            return;
        }
        if(phoneFinal.length() < 10){
            loginPhoneNumber.setError("Phone Number must have 10 characters");
            loginPhoneNumber.requestFocus();
            v.vibrate(100);
            SignUp.setEnabled(true);
            return;
        }
        if (TextUtils.isEmpty(locFinal)) {
            loginLocation.setError("Location Cannot be empty");
            loginLocation.requestFocus();
            v.vibrate(100);
            SignUp.setEnabled(true);
            return;
        }

        if (TextUtils.isEmpty(passwordFinal)) {
            loginPassword.setError("Password Cannot be empty");
            loginPassword.requestFocus();
            v.vibrate(100);
            SignUp.setEnabled(true);
            return;
        }
        if (TextUtils.isEmpty(confirmPasswordFinal)) {
            loginPassword2.setError("Confirm Password Cannot be empty");
            loginPassword2.requestFocus();
            v.vibrate(100);
            SignUp.setEnabled(true);
            return;
        }
        if (!passwordFinal.equals(confirmPasswordFinal)) {
            loginPassword2.setError("Password and Confirm Password should match");
            loginPassword2.requestFocus();
            v.vibrate(100);
            SignUp.setEnabled(true);
            return;
        }

        //add shop If everything is fine
        AddUser();
    }

    private void AddUser() {
//display loader
        displayLoader();

        StringRequest request = new StringRequest(Request.Method.POST, add_userURL, response -> {
            //dismiss loader
            pDialog.dismiss();

            try {
                JSONObject obj = new JSONObject(response);
                if (obj.getBoolean("error")) {
                    //resetElements();
                    loginUsername.setText("");
                    v.vibrate(100);
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                }else {
                    resetElements();
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("loginUsername", loginUsername.getText().toString().trim());
                params.put("loginPassword", loginPassword.getText().toString().trim());
                params.put("loginPhoneNumber", loginPhoneNumber.getText().toString().trim());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
    private void resetElements() {
        loginUsername.setText("");
        loginPassword.setText("");
        loginPassword2.setText("");
        loginPhoneNumber.setText("");
        loginLocation.setText("");

    }

    private void displayLoader() {
        pDialog = new ProgressDialog(SignUpActivity.this,R.style.MyAlertDialogStyle);
        pDialog.setMessage("Adding User please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }
}