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


/**
 * The {@link Presentation} class is the controller of the main page of the application
 * It is composed by three circle prividng access to other features (reading, praying and sharing).
 * It also provide a way to access help page.
 */
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
        initPrayingButton();

        initHelpButton();
        initReadingButton();
        initSharingButton();
    }

    /**
     * Inits the sharing action on button click
     */
    private void initSharingButton() {
        ImageView sharingActivity = findViewById(R.id.sharingImage);
        sharingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Presentation.this.getApplicationContext(), SharingActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
          * Inits the reading action on button click

     */
    private void initReadingButton() {
        ImageView bibleReadingActivity = findViewById(R.id.readingBibleImage);
        bibleReadingActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Presentation.this.getApplicationContext(), BibleReadingActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Inits the help action on button click
     */
    private void initHelpButton() {
        ImageButton helpButton =  findViewById ( R.id.helpButton );
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
    }

    /**
     * Inits the praying action on button click
     */
    private void initPrayingButton() {
        // on va chercher l'image qui porte l'id prayer image
        ImageView prayImage = findViewById(R.id.prayImage);

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
    }
}
