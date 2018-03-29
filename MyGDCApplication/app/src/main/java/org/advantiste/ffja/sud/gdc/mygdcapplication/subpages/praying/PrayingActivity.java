package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.PrayerDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.PrayingFragmentList;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.model.ListRowItem;

import java.util.Calendar;
import java.util.List;

import static android.R.drawable.ic_delete;
import static android.R.drawable.ic_input_add;
import static android.R.drawable.ic_menu_add;

/**
 * Created by erc on 23/02/18.
 */

public class PrayingActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // on fait appel a la classe mere pour la creation
        super.onCreate(savedInstanceState);

        // on dit à l'application quelle est la vue a charger
        setContentView(R.layout.activity_prayer);

        // Instantiate a ViewPager and a PagerAdapter.
        ImageView picture = (ImageView)findViewById(R.id.imageView);

        // mise en place de la source de l'image
        picture.setImageResource(R.drawable.prayer_hands);



    }
}
