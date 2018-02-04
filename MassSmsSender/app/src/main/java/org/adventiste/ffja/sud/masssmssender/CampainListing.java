package org.adventiste.ffja.sud.masssmssender;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import org.adventiste.ffja.sud.masssmssender.hmi.CampainListAdapter;
import org.adventiste.ffja.sud.masssmssender.model.Campain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampainListing extends AppCompatActivity {


    private FloatingActionButton fab;
    private ExpandableListView campainListView;
    private final HashMap<String, Campain> listDataChild = new HashMap<>();
    private final List<String> listDataAdapter = new ArrayList<>();


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campain_listing);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.campainListView = (ExpandableListView) findViewById(R.id.campainListing);

        this.campainListView.setAdapter(new CampainListAdapter(this, this.listDataAdapter, this.listDataChild));
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Intent intent = new Intent(CampainListing.this, CampainFilling.class);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_campain_listing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
