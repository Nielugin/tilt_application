package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.sharing.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SharingFragmentPresentation} interface
 * to handle interaction events.
 * Use the {@link SharingFragmentPresentation#} factory method to
 * create an instance of this fragment.
 */
public class SharingFragmentPresentation extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_sharing_presentation, container, false);


        return rootView;
    }
}
