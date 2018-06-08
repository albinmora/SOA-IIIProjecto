package com.example.albin.sportec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.albin.sportec.Model.Login;
import com.example.albin.sportec.Model.ResponseUser;
import com.example.albin.sportec.Session.Session;
import com.example.albin.sportec.networking.RestClientUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

public class RegisterActivity extends AppCompatActivity {

    private Button mButtonRegister;
    private EditText mContrasena;
    private EditText mRepeatContrasena;
    private EditText mUserEmail;
    private Gson gson;
    private TextView mStatusLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mButtonRegister = (Button) findViewById(R.id.btn_register);

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContrasena = (EditText) findViewById(R.id.editText_password);
                mRepeatContrasena = (EditText) findViewById(R.id.editText_confirmPassword);
                mUserEmail = (EditText) findViewById(R.id.editText_user);
                mStatusLogin = (TextView)findViewById(R.id.txtStatusLogin);
                if(mContrasena.getText().toString().equals(mRepeatContrasena.getText().toString())){
                    Login user = new Login(null,mUserEmail.getText().toString(), mContrasena.getText().toString());
                    RestClientUser
                            .with(getApplicationContext())
                            .registerUser(user, new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    gson = new Gson();
                                    ResponseUser response = gson.fromJson(result.toString(), ResponseUser.class);

                                    if(response.getStatus() == 1){
                                        Session.setmId(response.getUser().get_id());
                                        Session.setmUser(response.getUser().getUser());
                                        Intent intent = new Intent(RegisterActivity.this, FavoriteSportsActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else
                                        Toast.makeText(getApplicationContext(), "Ha ocurrido un error inesperado, intentalo más tarde", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else
                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
