package org.adventiste.ffja.sud.masssmssender;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CampainFilling extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campain_filling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button sendSmsButton = (Button) findViewById(R.id.sendSmsButton);
        final EditText messageTextField = (EditText) findViewById(R.id.messageTextField);

       sendSmsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("0671470092", null, messageTextField.getText().toString()    , null, null);
                    Toast.makeText(getApplicationContext(), "Message Sent",
                            Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                            Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        });



    }



}
