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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer);
        // Instantiate a ViewPager and a PagerAdapter.
        ImageView picture = (ImageView)findViewById(R.id.imageView);
        picture.setImageResource(R.drawable.pray);

        FloatingActionButton addButtonAction = (FloatingActionButton) findViewById(R.id.addPrayerButton);
        addButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a new row to add
                TableRow row = new TableRow(PrayingActivity.this);
                row.setWeightSum(5);
                //add Layouts to your new row
                final EditText editTopic = new EditText(PrayingActivity.this);
                editTopic.setHint("Sujet");
                final EditText editComment = new EditText(PrayingActivity.this);
                editComment.setHint("Description");

                ImageButton addTopic = new ImageButton(PrayingActivity.this);
                addTopic.setImageDrawable(getResources().getDrawable(ic_input_add));
                addTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR,0);
                        calendar.set(Calendar.MINUTE,0);
                        calendar.set(Calendar.SECOND,0);
                        calendar.set(Calendar.MILLISECOND,0);

                        PrayerDataSource dataSource = new PrayerDataSource(PrayingActivity.this);
                        dataSource.open();
                        //TODO : vérifier les paramètres !!
                        dataSource.createPrayer(calendar.getTimeInMillis(),-1,editTopic.getText().toString(),editComment.getText().toString());
                        dataSource.close();

                        System.out.println(" ############## AJOUTé TO SQLite");
                        // Mise à jour de l'historique
                        ListRowItem.ListRowListener lrl = new ListRowItem.ListRowListener() {
                            @Override
                            public void updateList() {
                                //TODO : Doit mettre à jour la liste de sujet :(
                                //PrayingFragmentList.this.populateListPrayer();
                                System.out.println(" ############## Doit mettre a jour !");
                            }
                        };
                        lrl.updateList();

                    }
                });

                TableRow.LayoutParams lpT = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 1.5f);
                editTopic.setLayoutParams(lpT);
                TableRow.LayoutParams lpC = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 3f);
                editComment.setLayoutParams(lpC);
                TableRow.LayoutParams lpTR = new TableRow.LayoutParams(0, TableRow.LayoutParams.MATCH_PARENT, 0.5f);
                addTopic.setLayoutParams(lpTR);


                row.addView(editTopic);
                row.addView(editComment);
                row.addView(addTopic);
                //add your new row to the TableLayout:
                TableLayout table = (TableLayout) findViewById(R.id.praying_list_topics);
                table.addView(row);
            }

        });
    }
}
