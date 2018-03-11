package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBookChapterAssociation;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.BookRow;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AddHistoryActivity extends AppCompatActivity {


    private EditText dateEnd;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TableLayout readingList = (TableLayout) findViewById(R.id.readingList);




        /* *************** Gestion de la date de fin
        * http://abhiandroid.com/ui/datepicker
        * */

        dateEnd = (EditText) findViewById(R.id.endDate);
        // perform click event on edit text
        dateEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddHistoryActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                dateEnd.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        /* ************** Gestion du boutton pour l'ajout */
        final ImageButton addReadingButton = (ImageButton) findViewById(R.id.addBookButton);
        addReadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Lecture ajoutée", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                FragmentTransaction fragmentTransaction = getFragmentManager ( ).beginTransaction ( );
                fragmentTransaction.add(R.id.readingList,(Fragment ) BookRow.newInstance());
                fragmentTransaction.commit ();

                //save();

            }
        });




    }


    private void save(){
        // calender class's instance and get current date , month and year from calender
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

        // Prepare data intent
        Intent data = new Intent();

        data.putExtra("readBeginDate", mDay+"/"+(mMonth+1)+"/"+mYear);
        data.putExtra("totalReadingCount", 1);
//        data.putExtra("readChapterEnd", bookEnd.getSelectedItem().toString());
        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        finish ();
    }

    private void formatReadings(List<String> strings, Intent intent){
        intent.putExtra("totalReadingCount", String.valueOf ( strings.size () ));
        for (int i=0; i< strings.size (); i++) {
  //          intent.putExtra("readBook_"+i,bookSpinner.getSelectedItem().toString());
    //        intent.putExtra("readChapterBegin_"+i, bookBegin.getSelectedItem().toString());
      //      intent.putExtra("readChapterEnd_"+i, bookEnd.getSelectedItem().toString());
        }
    }


    @Override
    public void finish() {

        super.finish();
    }
}
