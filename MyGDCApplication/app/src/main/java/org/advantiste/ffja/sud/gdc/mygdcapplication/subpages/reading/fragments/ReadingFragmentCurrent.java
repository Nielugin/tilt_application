package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import static org.advantiste.ffja.sud.gdc.mygdcapplication.R.layout.fragment_reading_current;

import org.advantiste.ffja.sud.gdc.mygdcapplication.controller.EventManager;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.events.ReadingDeleteEvent;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReadingDataSource;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by erc on 23/02/18.
 */

public class ReadingFragmentCurrent extends Fragment {

    private WeeklyReadingDataSource dataSource;
    private LinearLayout currentReadings;
    private EventBus eventBus;

    @Subscribe
    public void onReadingListInvalidated(ReadingDeleteEvent readingDeleteEvent){
        populateCurrent();
    }


    public void populateCurrent() {
        currentReadings.removeAllViews();
        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        String txtCurrentReading="";
        WeeklyReading currReading = dataSource.getThisWeekReading();
        if (currReading != null) {
            Map<BibleBook, List<Integer>> reading = currReading.getReadingDetails();
            Iterator<BibleBook> itBook = reading.keySet().iterator();
            while (itBook.hasNext()) {
                BibleBook book = itBook.next();
                List<Integer> chapters = reading.get(book);
                LayoutInflater     inflater = getLayoutInflater();
                LinearLayout currentReadingItem = (LinearLayout) inflater.inflate(R.layout.reading_list_item, null);
                TextView bookReference = currentReadingItem.findViewById(R.id.item_book_reference);
                if(book!=null && chapters!=null){

                    txtCurrentReading = book.getLongName ()+" du chapitre "+String.valueOf(chapters.get(0)) + " à  "
                            + String.valueOf(chapters.get(1));

                    TextView readingLink = currentReadingItem.findViewById(R.id.item_read_button);


                    String link = " <a  href=\"https://www.bible.com/bible/133/"+book.getBibleAppId()+"."+chapters.get(0)+".pdv2017\">lire</a>";
                    readingLink.setText(Html.fromHtml(link));
                    readingLink.setMovementMethod(LinkMovementMethod.getInstance());


                } else {
                    txtCurrentReading = "Aucune lecture";
                }
                if(bookReference!=null){

                bookReference.setText(txtCurrentReading);
                }
                currentReadings.addView(currentReadingItem);
            }
        }


        dataSource.close();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                fragment_reading_current, container, false);
        currentReadings =  rootView.findViewById(R.id.current_readings);

        populateCurrent();

        return rootView;
    }


    @Override
    public void onPause() {
        this.eventBus.unregister(this);

        super.onPause();
    }

    @Override
    public void onResume() {
        this.eventBus = EventManager.getInstance().getEventBus();
        this.eventBus.register(this);

        super.onResume();
    }
}
