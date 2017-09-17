package org.advantiste.ffja.sud.tilt.tilt;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by JBOUCHER on 17/09/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT =  3000;


 protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_splash);
    new Handler().postDelayed(new Runnable(){

        @Override
        public void run() {
            Intent intent =  new Intent(SplashScreenActivity.this,MainTiltActivity.class);
            startActivity(intent);
            finish();
        }
    },SPLASH_TIME_OUT);
 }

}
