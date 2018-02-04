package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;

import static org.advantiste.ffja.sud.gdc.mygdcapplication.R.layout.fragment_reading_presentation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReadingFragmentPresentation} interface
 * to handle interaction events.
 * Use the {@link ReadingFragmentPresentation#} factory method to
 * create an instance of this fragment.
 */
public class ReadingFragmentPresentation extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                fragment_reading_presentation, container, false);

        TextView textViewPresentation = (TextView) rootView.findViewById(R.id.reading_text);
        textViewPresentation.setText(R.string.bible_reading_text);


        return rootView;
    }
}
