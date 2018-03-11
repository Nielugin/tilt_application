package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.sharing;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.sharing.fragments.SharingFragmentPresentation;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.sharing.fragments.SharingFragmentQuestions;


public class SharingActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 2;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);
        ImageView picture = (ImageView) findViewById(R.id.sharing_image_1);
        picture.setImageResource(R.drawable.sharing_main);

    }



}