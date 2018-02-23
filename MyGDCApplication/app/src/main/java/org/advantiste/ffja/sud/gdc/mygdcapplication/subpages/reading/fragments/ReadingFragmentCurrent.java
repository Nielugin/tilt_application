package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import static org.advantiste.ffja.sud.gdc.mygdcapplication.R.layout.fragment_reading_current;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                fragment_reading_current, container, false);

        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        String txtCurrentReading;
        WeeklyReading currReading = dataSource.getThisWeekReading();
        if (currReading != null) {
            Map<BibleBook, List<Integer>> reading = currReading.getReadingDetails();
            Iterator<BibleBook> itBook = reading.keySet().iterator();
            BibleBook book = itBook.next();
            List<Integer> chapters = reading.get(book);
            txtCurrentReading = book.name()+"."+String.valueOf(chapters.get(0)) + "-"
                    + String.valueOf(chapters.get(chapters.size() - 1));
            while (itBook.hasNext()) {
                book = itBook.next();
                chapters = reading.get(book);
                txtCurrentReading += "; "+book.name()+"."+String.valueOf(chapters.get(0)) + "-"
                        + String.valueOf(chapters.get(chapters.size() - 1));
            }
        } else {
            txtCurrentReading = "Aucune lecture";
        }
        TextView textViewPresentation = (TextView) rootView.findViewById(R.id.reading_current_book);
        textViewPresentation.setText(txtCurrentReading);

        dataSource.close();

        return rootView;
    }

}
