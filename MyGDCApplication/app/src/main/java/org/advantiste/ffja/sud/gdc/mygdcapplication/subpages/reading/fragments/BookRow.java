package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBookChapterAssociation;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * create an instance of this fragment.
 */
public class BookRow extends Fragment {

    private ArrayAdapter<Integer> m_adapterForSpinnerEnd;
    private Spinner bookSpinner;
    private Spinner bookBegin;
    private Spinner bookEnd;
    private int maxChapter;
    private Context context;
    private InteractionInterface interactionInterface;

    public void setInteractionInterface ( InteractionInterface interactionInterface ) {
        this.interactionInterface = interactionInterface;
    }


    public interface InteractionInterface{

        void removeAction();
    }

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
    public static BookRow newInstance ( ) {
        return new BookRow ( );
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
        bookSpinner = view.findViewById( R.id.bookSpinner);
        String[] mBookArray = getResources().getStringArray(R.array.book_values);
        ////////////////////////////////////////////////////////////////
        //create an arrayAdapter an assign it to the spinner
        ArrayAdapter<String> m_adapterForSpinner = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mBookArray);
        m_adapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookSpinner.setAdapter(m_adapterForSpinner);
        bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                onBookSelected(parentView, position, view);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        return  view;
    }

    private void onBookSelected(AdapterView<?> parentView, int position, View view) {
        String bookName = parentView.getItemAtPosition ( position ).toString ( );
        BibleBook book = BibleBook.fromString (bookName);
        Log.w(AddHistoryActivity.class.getName(),
                "Selected book : "+book);

        BibleBookChapterAssociation bbca = new BibleBookChapterAssociation();
        maxChapter = bbca.getBibleIntegerEnumMap().get(book);

        initBeginEndBook(view);

        initCloseButton(view);
    }

    private void initBeginEndBook(View view) {
        bookBegin =  view.findViewById(R.id.bookBegin);
        bookEnd = view.findViewById(R.id.bookEnd);

        ArrayAdapter<Integer> m_adapterForSpinnerBegin = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        m_adapterForSpinnerBegin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookBegin.setAdapter(m_adapterForSpinnerBegin);
        m_adapterForSpinnerEnd = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
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

    private void initCloseButton(View view) {
        ImageButton closeButton = view.findViewById(R.id.deleteReadingButton);

        closeButton.setOnClickListener (new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                interactionInterface.removeAction ();

            }
        });
    }


    public int getBookBeginValue () {
        return (Integer)bookBegin.getSelectedItem ();
    }

    public int getBookEndValue () {
        return (Integer)bookEnd.getSelectedItem ();
    }

    public String getBook(){
        return (String) bookSpinner.getSelectedItem ();
    }


}
