package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBookChapterAssociation;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.AddHistoryActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class BookRow extends Fragment {

    private ArrayAdapter<String> m_adapterForSpinner;
    private ArrayAdapter<Integer> m_adapterForSpinnerBegin;
    private ArrayAdapter<Integer> m_adapterForSpinnerEnd;
    private Spinner bookSpinner;
    private Spinner bookBegin;
    private Spinner bookEnd;
    private String[] mBookArray;
    private int maxChapter;
    private Context context;




    public BookRow () {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment book_row.
     */
    // TODO: Rename and change types and number of parameters
    public static android.app.Fragment newInstance ( ) {
        BookRow fragment = new BookRow ( );
        return fragment;
    }

    @Override
    public void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );

    }

    @Override
    public View onCreateView ( LayoutInflater inflater, ViewGroup container,
                               Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate ( R.layout.book_row, container, false );

        context=view.getContext ();

            /* ************** Gestion des Spinner */
        bookSpinner = (Spinner )  view.findViewById( R.id.bookSpinner);
        mBookArray = getResources().getStringArray(R.array.book_values);
        ////////////////////////////////////////////////////////////////
        //create an arrayAdapter an assign it to the spinner
        m_adapterForSpinner = new ArrayAdapter<String> (getActivity (), android.R.layout.simple_spinner_item, mBookArray);
        m_adapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookSpinner.setAdapter(m_adapterForSpinner);
        bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                BibleBook book = BibleBook.valueOf(parentView.getItemAtPosition(position).toString());
                Log.w(AddHistoryActivity.class.getName(),
                        "Selected book : "+book);

                BibleBookChapterAssociation bbca = new BibleBookChapterAssociation();
                maxChapter = bbca.getBibleIntegerEnumMap().get(book);

                bookBegin = (Spinner) view.findViewById(R.id.bookBegin);
                bookEnd = (Spinner) view.findViewById(R.id.bookEnd);

                m_adapterForSpinnerBegin = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item);
                m_adapterForSpinnerBegin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bookBegin.setAdapter(m_adapterForSpinnerBegin);
                m_adapterForSpinnerEnd = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item);
                m_adapterForSpinnerEnd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                bookEnd.setAdapter(m_adapterForSpinnerEnd);

                for (int chap=1; chap <= maxChapter; chap++){
                    m_adapterForSpinnerBegin.add(chap);
                    m_adapterForSpinnerEnd.add(chap);
                }

                bookEnd.setSelection(m_adapterForSpinnerEnd.getCount());

                bookBegin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                        int chapBeginSelected = Integer.parseInt(parentView.getItemAtPosition(position).toString());
                        m_adapterForSpinnerEnd.clear();
                        for (int chap=chapBeginSelected; chap <= maxChapter; chap++){
                            m_adapterForSpinnerEnd.add(chap);
                        }
                        bookEnd.setSelection(m_adapterForSpinnerEnd.getCount());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }

                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        return  view;
    }



}
