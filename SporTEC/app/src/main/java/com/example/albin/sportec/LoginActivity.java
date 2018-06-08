package com.example.albin.sportec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.albin.sportec.Model.Login;
import com.example.albin.sportec.Model.ResponseUser;
import com.example.albin.sportec.Session.Session;
import com.example.albin.sportec.networking.RestClientUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {


    private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private Gson gson;
    private TextView mStatusLogin;
    private TextView mLinkResgiter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);



        Button mLoginButton = (Button) findViewById(R.id.login_button);

        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                Login loginModel = new Login(null,email,password);
                //LoginDBAccess db = new LoginDBAccess(loginModel);
                //db.execute("");

                RestClientUser
                        .with(getApplicationContext())
                        .loginUser(loginModel, new FutureCallback<JsonObject>() {
                            @Override
                            public void onCompleted(Exception e, JsonObject result) {
                                gson = new Gson();
                                ResponseUser response = gson.fromJson(result.toString(), ResponseUser.class);
                                if(response.getStatus() == 1){
                                    Session.setmId(response.getUser().get_id());
                                    Session.setmUser(response.getUser().getUser());
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }else{
                                    mStatusLogin = (TextView)findViewById(R.id.txtStatusLogin);
                                    mStatusLogin.setText(response.getMessage());
                                }
                            }


                        });
            }
        });

        mLinkResgiter = (TextView) findViewById(R.id.txtLinkRegister);

        mLinkResgiter.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });





    }
}

