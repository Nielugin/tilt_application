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
       /* add_reading= (ImageButton) rootView.findViewById(R.id.add_reading);
        add_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = ReadingFragmentHistory.this.getContext();
                CharSequence text = "Whouuuhhhhooo!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });*/
        return rootView;
    }
}
