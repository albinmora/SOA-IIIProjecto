package com.example.albin.sportec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.albin.sportec.Model.ResponseSport;
import com.example.albin.sportec.Model.ResponseUser;
import com.example.albin.sportec.Model.Sport;
import com.example.albin.sportec.Model.UserSport;
import com.example.albin.sportec.Session.Session;
import com.example.albin.sportec.networking.RestClientSport;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;
import java.util.List;

public class FavoriteSportsActivity extends AppCompatActivity {
    private Gson gson;
    LinearLayout mLinearLayout;
    ResponseSport mResponseSports;
    Button mBtnConfirm;
    List<Integer> mListChecks;
    List<String> mAllID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favory_sports);

        RestClientSport
                .with(getApplicationContext())
                .getAllSports(new FutureCallback<JsonObject>() {

                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        gson = new Gson();
                         mResponseSports = gson.fromJson(result.toString(), ResponseSport.class);

                        mLinearLayout = (LinearLayout)findViewById(R.id.linear_layout_sports);
                        List<Sport> listSports = mResponseSports.getSports();
                        mAllID = new ArrayList<String>();
                        mListChecks  = new ArrayList<Integer>();

                        for (int i = 0; i < listSports.size(); i++)
                        {
                            //mListChecks.add(-1);
                            mAllID.add("-1");
                        }
                        for (int i = 0; i < listSports.size(); i++)
                        {
                            //TableRow row =new TableRow(this);
                            //row.setId(i);
                            //row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
                            mAllID.set(i,listSports.get(i).get_id());
                            CheckBox checkBox = new CheckBox(FavoriteSportsActivity.this);
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                                    if(compoundButton.isChecked())
                                        mListChecks.add(compoundButton.getId());
                                    else
                                        mListChecks.remove(compoundButton.getId());

                                    Toast.makeText(getApplicationContext(), compoundButton.getText(), Toast.LENGTH_SHORT).show();

                                }
                            });
                            checkBox.setId(i);
                            checkBox.setText(listSports.get(i).getSport());
                            mLinearLayout.addView(checkBox);
                        }
                    }
                });

        mBtnConfirm = (Button) findViewById(R.id.btn_confirmSports);

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<UserSport> listID = new ArrayList<UserSport>();
                UserSport userSport;
                for(int i = 0; i< mListChecks.size(); i++){
                    userSport = new UserSport(Session.getmId(),mAllID.get(mListChecks.get(i)));
                    RestClientSport
                            .with(getApplicationContext())
                            .addSportToUser(userSport, new FutureCallback<JsonObject>() {

                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    //gson = new Gson();
                                    //mResponseSports = gson.fromJson(result.toString(), ResponseSport.class);
                                    Toast.makeText(getApplicationContext(), "Operación realizada con éxito", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FavoriteSportsActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();


                                }
                            });
                }





            }
        });




    }
}
