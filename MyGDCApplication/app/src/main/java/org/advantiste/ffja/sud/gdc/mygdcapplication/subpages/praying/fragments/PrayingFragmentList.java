package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.PrayerDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.PrayingActivity;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.model.ListRowItem;

import java.util.Calendar;
import java.util.List;

import static android.R.drawable.ic_input_add;

/**
 * Created by erc on 23/02/18.
 */

public class PrayingFragmentList extends Fragment {


    private PrayerDataSource dataSource;
    private TableLayout tableLayout;

    public void populateListPrayer() {
        tableLayout.removeAllViews();
        dataSource = new PrayerDataSource(this.getContext());
        dataSource.open();
        List<Prayer> prayers = dataSource.getAllPrayers();
        int cpt = 0;

        for (Prayer prayer: prayers) {
            tableLayout.addView(new ListRowItem(this.getContext(), prayer, cpt, new ListRowItem.ListRowListener() {
                @Override
                public void updateList() {
                    PrayingFragmentList.this.populateListPrayer();
                }
            }));
            cpt ++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_praying_list, container, false);
        tableLayout = (TableLayout) rootView.findViewById(R.id.praying_list_topics);

        // on recupere le bouton
        TextView addButtonAction = rootView.findViewById(R.id.addPrayerButton);

        // et on dit ce qu'il se passe quand on appuie dessus
        addButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create a new row to add
                TableRow row = new TableRow(getContext ());
                row.setWeightSum(5);
                //add Layouts to your new row



                // on crée une nouveau champ éditable
                final EditText editTopic = new EditText(getContext ());
                editTopic.setHint("Sujet");
                final EditText editComment = new EditText(getContext ());
                editComment.setHint("Description");

                ImageButton addTopic = new ImageButton(getContext ());
                addTopic.setImageDrawable(getResources().getDrawable(ic_input_add));

                addTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Calendar calendar = Calendar.getInstance();

                        // récupération de la date d'aujourd'hui à 00h00
                        calendar.setTimeInMillis(System.currentTimeMillis());
                        calendar.set(Calendar.HOUR,0);
                        calendar.set(Calendar.MINUTE,0);
                        calendar.set(Calendar.SECOND,0);
                        calendar.set(Calendar.MILLISECOND,0);

                        // récupération des données depuis la base de données
                        PrayerDataSource dataSource = new PrayerDataSource(getContext ());
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
                                populateListPrayer();
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
                TableLayout table = (TableLayout) rootView.findViewById(R.id.praying_list_topics);
                table.addView(row);
            }

        });




        /* Remove all prayers
        dataSource = new PrayerDataSource(this.getContext());
        dataSource.open();
        dataSource.deleteAllPrayers();
        dataSource.close();
        /* */

        //TODO : ajouter le bouton qui va avec

        /*

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long beginDate = calendar.getTimeInMillis()-3*604800000;
        calendar.setTimeInMillis(beginDate+604800000); //oneWeek);
        long endDate =  calendar.getTimeInMillis();
        dataSource.createPrayer(beginDate, -1, "Mega TILT",null);
        dataSource.createPrayer(beginDate, endDate, "Julie", "boulot");
        beginDate = endDate;
        endDate += 604800000;
        dataSource.createPrayer(beginDate, endDate, "Bob", "études");
        beginDate = endDate;
        dataSource.createPrayer(beginDate, -1, "Assemblée en ARLES",null);
        */

        populateListPrayer();
        return rootView;
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }

}
