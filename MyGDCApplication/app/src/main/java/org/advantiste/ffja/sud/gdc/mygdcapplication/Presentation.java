package org.advantiste.ffja.sud.gdc.mygdcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.BibleReadingActivity;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.sharing.SharingActivity;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;

public class Presentation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.activity_presentation);
        ImageView prayImage = (ImageView) findViewById(R.id.prayImage);
        prayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Presentation.this.getApplicationContext(), PrayerActivity.class);
                startActivity(intent);

            }
        });
        ImageView sharingActivity = (ImageView) findViewById(R.id.sharingImage);
        sharingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Presentation.this.getApplicationContext(), SharingActivity.class);
                startActivity(intent);

            }
        });
        ImageView bibleReadingActivity = (ImageView) findViewById(R.id.readingBibleImage);
        bibleReadingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Presentation.this.getApplicationContext(), BibleReadingActivity.class);
                startActivity(intent);

            }
        });
    }
}
