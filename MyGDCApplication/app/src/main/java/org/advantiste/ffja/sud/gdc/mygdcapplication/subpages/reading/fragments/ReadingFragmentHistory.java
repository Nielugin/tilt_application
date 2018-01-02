package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.controller.ReadingController;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model.HistoryRowItem;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReadingFragmentHistory} interface
 * to handle interaction events.
 * Use the {@link ReadingFragmentHistory#} factory method to
 * create an instance of this fragment.
 */
public class ReadingFragmentHistory extends Fragment {


    private ImageButton add_reading;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_reading_history, container, false);
        ImageView picture = (ImageView)rootView.findViewById(R.id.reading_image_2);
        TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.reading_list_history);
        picture.setImageResource(R.drawable.bible);

        ReadingController readingController = ReadingController.getInstance();
        List<WeeklyReading> readings = readingController.getReadings();
        for (WeeklyReading reading: readings) {
            tableLayout.addView(new HistoryRowItem(this.getContext(),reading));
        }
        return rootView;
    }
}
