package de.fh_zwickau.taskerapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity  {
    private static int SPLASH_TIME_OUT = 2000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(this, MainActivity.class);
            startActivity(homeIntent);
        }, SPLASH_TIME_OUT);
    }
}
