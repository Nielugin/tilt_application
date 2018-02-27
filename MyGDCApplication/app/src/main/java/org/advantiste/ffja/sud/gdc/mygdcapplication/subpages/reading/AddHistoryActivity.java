package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;

public class AddHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button addReadingButton = (Button) findViewById(R.id.addBookButton);
        final TableLayout redingList = (TableLayout) findViewById(R.id.readingList);

        addReadingButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                                                            .setAction("Action", null).show();

                                                }
                                            });

    }
}
