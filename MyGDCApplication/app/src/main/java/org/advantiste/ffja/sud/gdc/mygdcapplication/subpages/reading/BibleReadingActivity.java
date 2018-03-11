package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import org.advantiste.ffja.sud.gdc.mygdcapplication.Presentation;
import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.ReadingFragmentHistory;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.ReadingFragmentPresentation;

public class BibleReadingActivity  extends FragmentActivity {


    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;


    static final int ADD_READING_REQUEST = 1;  // The request code

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_reading);
        // Instantiate a ViewPager and a PagerAdapter.
        ImageView picture = (ImageView) findViewById(R.id.reading_image_1);
        picture.setImageResource(R.drawable.book);

        FloatingActionButton addButtonAction = (FloatingActionButton) findViewById(R.id.addReadingButton);
        addButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BibleReadingActivity.this.getApplicationContext(), AddHistoryActivity.class);
                startActivityForResult(intent, ADD_READING_REQUEST);
            }

        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Dans le cas de retour de l'ajout de lecture
        if (requestCode == ADD_READING_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                BibleBook book = BibleBook.valueOf(data.getStringExtra("readBook"));
                int chapterBegin = Integer.parseInt(data.getStringExtra("readChapterBegin"));
                int chapterEnd = Integer.parseInt(data.getStringExtra("readChapterEnd"));
                String dateBegin = data.getStringExtra("readBeginDate");
                String dateEnd = data.getStringExtra("readEndDate");

                System.out.println("************ \n Du "+dateBegin+" au "+dateEnd+", la lecture est "+book+"."+chapterBegin+"-"+chapterEnd);
            }
        }
    }

}
