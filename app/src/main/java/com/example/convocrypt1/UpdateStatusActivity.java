package com.example.convocrypt1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.ParseException;


public class UpdateStatusActivity extends Activity {


    protected EditText cStatusUpdate;
    protected Button cStatusUpdateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        //initialise
        cStatusUpdate = (EditText)findViewById(R.id.updateStatusTextBox);
        cStatusUpdateButton = (Button)findViewById(R.id.statusUpdateButton);

        //listen to status update button click
        cStatusUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get current user
                ParseUser currentUser = ParseUser.getCurrentUser();
                String currentUserUsername = currentUser.getUsername();


                //get the status user has to enter, convert to string
                String newStatus = cStatusUpdate.getText().toString();

                if(newStatus.isEmpty()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                    builder.setMessage("Status should not be empty");
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
                }else {

                    ParseObject statusObject = new ParseObject("Status");
                    statusObject.put("newStatus", newStatus);
                    statusObject.put("user", currentUserUsername);
                    statusObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(com.parse.ParseException e) {
                            if (e == null) {
                                Toast.makeText(UpdateStatusActivity.this, "Success", Toast.LENGTH_LONG).show();
                                //take user to homepage
                                Intent takeUserHome = new Intent(UpdateStatusActivity.this, MainActivity.class);
                                startActivity(takeUserHome);
                            } else {


                                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                                builder.setMessage(e.getMessage());
                                builder.setTitle("Sorry");
                                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //close dialog
                                        dialogInterface.dismiss();

                                    }
                                });
                                //create box
                                AlertDialog dialog = builder.create();
                                dialog.show();
                            }


                        }
                    });
                }

            }






        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_status, menu);
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
