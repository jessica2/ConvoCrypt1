package com.example.convocrypt1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;


public class LoginActivity extends Activity {

    //Ulysses S. Grant
    //password


    //Ibrahim
    //123

    protected EditText cUsername;
    protected EditText cPassword;
    protected Button cLoginButton;
    protected Button cCreateAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialise
        cUsername = (EditText)findViewById(R.id.usernameLogin);
        cPassword = (EditText)findViewById(R.id.passwordLogin);
        cLoginButton = (Button)findViewById(R.id.loginButton);
        cCreateAccountButton = (Button)findViewById(R.id.createAccountLogin);

        //listen to create Accountbutton click
        cCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take the user to register to convo crypt
                Intent takeUserToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(takeUserToRegister);
            }
        });




        //listen to when the nLogin Button is clicked
        cLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //take user input from edit text and convert to string
                String username = cUsername.getText().toString().trim();
                String password = cPassword.getText().toString().trim();


                //login user using parse sdk
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    public void done(ParseUser parseuser, ParseException e) {
                        if (e == null) {
                            // success, user has logged in
                            Toast.makeText(LoginActivity.this,"Welcome Back",Toast.LENGTH_LONG).show();

                        //take user to homepage
                            Intent takeUserHome = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(takeUserHome);
                        } else {
                            // Sign up failed
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sorry");
                            builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //close dialog
                                    dialogInterface.dismiss();

                                }
                            });
                            //create box
                            AlertDialog dialog =builder.create();
                            dialog.show();
                        }
                    }
                });

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
/*
      <item name="android:editTextBackground">@drawable/convocrypt_edit_text_holo_dark</item>

    <item name="android:buttonStyle">@style/ButtonConvoCrypt</item>

    <item name="android:imageButtonStyle">@style/ImageButtonConvoCrypt</item>

    <item name="android:listChoiceBackgroundIndicator">@drawable/convocrypt_list_selector_holo_dark</item>

    <item name="android:activatedBackgroundIndicator">@drawable/convocrypt_activated_background_holo_dark</item>

  </style>

    <style name="Theme.ConvoCrypt.Widget"></style>

*/