package com.example.albin.sportec;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by albin on 30/04/18.
 */

public class SplashActivity extends Activity {

    //Splash visible during 3 seconds
    private final int SPLASH_DURATION = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            ;
        }, SPLASH_DURATION);
    }
}
