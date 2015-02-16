package com.example.convocrypt1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class RegisterActivity extends Activity {

	protected EditText cUsername;
	protected EditText cEmail;
	protected EditText cPassword;
	protected Button cRegisterButton;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 // Enable Local Datastore.
      //  Parse.enableLocalDatastore(this);
         
       // Parse.initialize(this, "UMGBy2o2d0JPpp9CRzE2YfRteA8Z5i6013o96GLZ", "l62OFC0hyrEVp0a8B8IN906pGqd7bB1TDgrHXBrh");

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);


		//initialise
		cUsername = (EditText)findViewById(R.id.username);
		cEmail = (EditText)findViewById(R.id.email);
		cPassword = (EditText)findViewById(R.id.password);
		cRegisterButton = (Button)findViewById(R.id.register);

		//listen to register button click

		cRegisterButton.setOnClickListener(new View.OnClickListener() {


			@Override
			public void onClick(View v) {
				Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_LONG).show();				

				//get the username, password, email and convert to string
				String username = cUsername.getText().toString().trim();
				String email = cEmail.getText().toString().trim();
				String password = cPassword.getText().toString().trim();				
				//trim() to check for extra unnecessary spaces (user shouldn't have to)


				//store user in parse
				ParseUser user = new ParseUser();
				user.setUsername(username);
				user.setEmail(email);
				user.setPassword(password);
				user.signUpInBackground(new SignUpCallback() {

					@Override
					public void done(ParseException e) {
						if (e == null) {
							// sign up successful
							Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_LONG).show();				
							//string resource folder for localisation **

                            //take user to home screen
                            Intent takeUserHome = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(takeUserHome);

						} else {
							// Sign up didn't succeed
						}
					}
				});

			}
		});
	}
		
	



	@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.register, menu);
			return true;
		}

	@Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// Handle action bar item clicks here. The action bar will
			// automatically handle clicks on the Home/Up button, so long
			// as you specify a parent activity in AndroidManifest.xml.
			int id = item.getItemId();
			if (id == R.id.action_settings) {
				return true;
			}
			return super.onOptionsItemSelected(item);
		}

	}

