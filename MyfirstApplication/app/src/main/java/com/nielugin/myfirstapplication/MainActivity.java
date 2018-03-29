package com.nielugin.myfirstapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

        TextView text = new TextView ( this )
                text.setText ( "JJ is Marvelous" );
        setContentView ( text );
    }
}
