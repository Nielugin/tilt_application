package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.controller.EventManager;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.events.PrayerDeleteEvent;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.PrayerDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.adapter.ExpandableListViewPrayerAdapter;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments.model.ListRowItem;

import java.util.Calendar;
import java.util.List;

/**
 * Created by erc on 23/02/18.
 */

public class PrayingFragmentList extends Fragment {


    private PrayerDataSource dataSource;
    private ExpandableListView listView;
    private boolean isOpened=false;
    private EventBus eventBus;


    public void populateListPrayer() {
        dataSource = new PrayerDataSource(this.getContext());
        dataSource.open();
        List<Prayer> prayers = dataSource.getAllPrayers();
        ExpandableListViewPrayerAdapter expandableListViewPrayerAdapter = new ExpandableListViewPrayerAdapter ( this.getContext ( ), prayers );
        listView.setAdapter ( expandableListViewPrayerAdapter );


    }

    @Subscribe
    public void invalidatePrayerList(PrayerDeleteEvent prayerDeleteEvent){
        populateListPrayer();
    }





    @Override
    public View onCreateView( final LayoutInflater inflater, final ViewGroup container,
                              Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_praying_list, container, false);
        listView =  rootView.findViewById(R.id.praying_list);
        TableLayout tableLayout = rootView.findViewById(R.id.praying_list_topics);



        // on recupere le bouton
        final TextView addButtonAction = rootView.findViewById(R.id.addPrayerButton);
        final LinearLayout addPrayerHolder= rootView.findViewById(R.id.add_prayer_holder);
        // et on dit ce qu'il se passe quand on appuie dessus

        addButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onAddButton(inflater, container, addPrayerHolder, addButtonAction);
            }

        });

        populateListPrayer();
        return rootView;
    }

    private void onAddButton(LayoutInflater inflater, ViewGroup container, final LinearLayout addPrayerHolder, final TextView addButtonAction) {
        if(!isOpened){
            LinearLayout ajouterPriereBlock = (LinearLayout ) inflater.inflate ( R.layout.add_prayer_view, container, false );

            // on crée une nouveau champ éditable
            final EditText editTopic = ajouterPriereBlock.findViewById ( R.id.prayer_topic );
            editTopic .setHint(R.string.subject);

            final EditText editComment = ajouterPriereBlock.findViewById ( R.id.prayer_description);
            editComment.setHint(R.string.description);


            ImageButton addTopic = ajouterPriereBlock.findViewById ( R.id.add_prayer_button);
            addTopic.getBackground ().setAlpha ( 0 );
            Drawable drawable = getResources ( ).getDrawable ( R.drawable.ic_add_circle_black_24dp);
            addTopic.setImageDrawable(drawable);

            addTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addTopicAction(editTopic, editComment, addPrayerHolder, addButtonAction);
                }
            });

            addPrayerHolder.addView ( ajouterPriereBlock );
            isOpened =!isOpened;

            addButtonAction.setText ( R.string.annuler );

        }
        else{
            finishEdit ( addPrayerHolder, addButtonAction );
        }
    }

    private void addTopicAction(EditText editTopic, EditText editComment, LinearLayout addPrayerHolder, TextView addButtonAction) {
        Editable textTopic = editTopic.getText ( );
        Editable textTopicComment = editComment.getText ( );

        if(textTopic!=null && !textTopic.toString ().isEmpty () && textTopicComment!=null && !textTopicComment.toString ().isEmpty ()){
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

            isOpened = false;
            addPrayerHolder.removeAllViews ();
            addButtonAction.setText ( R.string.ajouter );
            isOpened =!isOpened;

        }
        else{
            if(!(textTopic!=null && !textTopic.toString ().isEmpty ()) ){
                editTopic.setError ( "Le sujet ne peut  être vide." );
            }
            if(!(textTopicComment!=null && !textTopicComment.toString ().isEmpty ())){
                editComment.setError ( "Le commentaire ne peut  être vide." );

            }
        }
    }

    private void finishEdit ( LinearLayout addPrayerHolder, TextView addButtonAction ) {
        addPrayerHolder.removeAllViews ();
        addButtonAction.setText ( R.string.ajouter );
        isOpened =!isOpened;
    }

    @Override
    public void onResume() {
        dataSource.open();
        EventManager eventManager = EventManager.getInstance();
        this.eventBus = eventManager.getEventBus();
        this.eventBus.register(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        dataSource.close();
        this.eventBus.unregister(this);
        super.onPause();
    }



}
