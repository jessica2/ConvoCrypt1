package com.example.convocrypt1;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class MainActivity extends ListActivity {

    protected List<ParseObject> cStatus;
    //change in MainActivity : Test for GitHub

	/*
	 * 
	 * To make it make more sense, on your register xml sheet, click on the button you want to link, and on text view, add android:onClick="register"

THEN on your register java class,

public void register(View view){
        Intent reg = new Intent(this, page you want to link to.class); //you need .class after file name
        startActivity(reg);
    }

and now it should work once you click on the button, it should lead you to register page. (non-Javadoc)
	 * 
	 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show the user the homepage
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Status");
            query.orderByDescending("createdAt");

            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> status, ParseException e) {
                    if(e == null){
                        //success
                        cStatus = status;
                        StatusAdapter adapter =new StatusAdapter(getListView().getContext(),cStatus);
                        setListAdapter(adapter);

                    }else{
                        //there was a problem
                    }
                }
            });
        } else {
            // show the login class screen
            Intent takeUserToLogin =new Intent(this, LoginActivity.class);
            startActivity(takeUserToLogin);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.updateStatus:
                //take user to update status activity
                Intent intent = new Intent(this,UpdateStatusActivity.class);
                startActivity(intent);
                break;


            case R.id.logoutUser:
                //log out the user
                ParseUser.logOut();

                //Take the user back to the log in screen
                Intent takeUserToLogin = new Intent(this, LoginActivity.class);
                startActivity(takeUserToLogin);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v,int position,long id){
        super.onListItemClick(l,v,position,id);
        ParseObject statusObject = cStatus.get(position);
        String objectId = statusObject.getObjectId();

        //confirm and display each status id
       // Toast.makeText(getApplicationContext(),objectId,Toast.LENGTH_LONG).show();

    Intent goToDetailView = new Intent(MainActivity.this, StatusDetailView.class);
    goToDetailView.putExtra("objectID",objectId);
    startActivity(goToDetailView);


    }
}
