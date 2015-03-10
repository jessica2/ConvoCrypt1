package com.example.convocrypt1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;


public class WelcomePage extends Activity {

    // Splash screen timer
    //android:background="@drawable/gradient_background"
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);




        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(WelcomePage.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "UMGBy2o2d0JPpp9CRzE2YfRteA8Z5i6013o96GLZ", "l62OFC0hyrEVp0a8B8IN906pGqd7bB1TDgrHXBrh");

    }


}
