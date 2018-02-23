package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;

/**
 * Created by erc on 23/02/18.
 */

public class PrayingActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);
        // Instantiate a ViewPager and a PagerAdapter.
        ImageView picture = (ImageView)findViewById(R.id.imageView);
        picture.setImageResource(R.drawable.pray);


    }
}
