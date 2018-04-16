package org.advantiste.ffja.sud.gdc.mygdcapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.HelpActivity;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.PrayingActivity;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.BibleReadingActivity;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.sharing.SharingActivity;


public class Presentation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // appel de la methode parents
        super.onCreate(savedInstanceState);

        // je ne sais pas ce que ça fait
        // mais ça a l'air bien
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Ca c'est la vue qu'on va chercher
        this.setContentView(R.layout.activity_presentation);

        // on va chercher l'image qui porte l'id prayer image
        ImageView prayImage = (ImageView) findViewById(R.id.prayImage);




        // on definit ce qui se passe quand on clique dessus
        prayImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on declare l'intent d'accès à la vue praying
                Intent intent = new Intent(Presentation.this.getApplicationContext(), PrayingActivity.class);

               // on bascule sur l'autre activité
                startActivity(intent);

            }
        });

        ImageButton helpButton = (ImageButton) findViewById ( R.id.helpButton );
        // on definit ce qui se passe quand on clique dessus
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on declare l'intent d'accès à la vue praying
                Intent intent = new Intent(Presentation.this.getApplicationContext(), HelpActivity.class);

                // on bascule sur l'autre activité
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
