package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;

import static org.advantiste.ffja.sud.gdc.mygdcapplication.R.layout.fragment_praying_presentation;

/**
 * Created by erc on 23/02/18.
 */

public class PrayingFragmentPresentation extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                fragment_praying_presentation, container, false);

        TextView textViewPresentation = (TextView) rootView.findViewById(R.id.praying_text);
        textViewPresentation.setText(R.string.prayer_text);

        return rootView;
    }
}
