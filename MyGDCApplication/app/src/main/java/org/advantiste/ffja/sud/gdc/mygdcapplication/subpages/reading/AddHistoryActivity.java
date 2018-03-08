package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBookChapterAssociation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddHistoryActivity extends AppCompatActivity {

    private ArrayAdapter<String> m_adapterForSpinner;
    private ArrayAdapter<Integer> m_adapterForSpinnerBegin;
    private ArrayAdapter<Integer> m_adapterForSpinnerEnd;
    private Spinner bookSpinner;
    private Spinner bookBegin;
    private Spinner bookEnd;
    private String[] mBookArray;
    private int maxChapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button addReadingButton = (Button) findViewById(R.id.addBookButton);
        final TableLayout readingList = (TableLayout) findViewById(R.id.readingList);

        context =this;
        addReadingButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                                                            .setAction("Action", null).show();

                                                }
                                            });

        bookSpinner = (Spinner) findViewById(R.id.bookSpinner);
        mBookArray = getResources().getStringArray(R.array.book_values);
        ////////////////////////////////////////////////////////////////
        //create an arrayAdapter an assign it to the spinner
        m_adapterForSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mBookArray);
        m_adapterForSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bookSpinner.setAdapter(m_adapterForSpinner);
        bookSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                BibleBook book = BibleBook.valueOf(parentView.getItemAtPosition(position).toString());
                Log.w(AddHistoryActivity.class.getName(),
                        "Selected book : "+book);

                BibleBookChapterAssociation bbca = new BibleBookChapterAssociation();
                maxChapter = bbca.getBibleIntegerEnumMap().get(book);

                bookBegin = (Spinner) findViewById(R.id.bookBegin);
                bookEnd = (Spinner) findViewById(R.id.bookEnd);

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


                bookBegin.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                        int chapBeginSelected = Integer.parseInt(parentView.getItemAtPosition(position).toString());
                        m_adapterForSpinnerEnd.clear();
                        for (int chap=chapBeginSelected; chap <= maxChapter; chap++){
                            m_adapterForSpinnerEnd.add(chap);
                        }



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
    }
}
