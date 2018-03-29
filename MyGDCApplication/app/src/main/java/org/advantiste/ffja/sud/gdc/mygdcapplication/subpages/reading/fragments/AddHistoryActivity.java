package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddHistoryActivity extends AppCompatActivity {


    private EditText dateEnd;
    DatePickerDialog datePickerDialog;

List<BookRow> bookRows = new ArrayList<> (  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton saveButton= (FloatingActionButton ) findViewById(R.id.floatingActionButton);
        saveButton.setOnClickListener ( new View.OnClickListener ( ) {

            @Override
            public void onClick ( View v ) {
                save ();
            }
        } );

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

                // TODO: regader pour fermer le clavier
                //datePickerDialog.setOnDateSetListener (  );

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
                final BookRow bookRow = BookRow.newInstance ( );
                bookRow.setInteractionInterface ( new BookRow.InteractionInterface ( ) {
                                                      @Override
                                                      public void removeAction () {
                                                          readingList.removeView ( bookRow.getView () );
                                                          bookRows.remove ( bookRow );
                                                      }

                                                  }
                );
                fragmentTransaction.add(R.id.readingList,(Fragment ) bookRow);
                fragmentTransaction.commit ();
                bookRows.add ( bookRow );

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

        // TODO: véfifier que les champs ne sont pas nuls  : date de fin
        String dateEndText = dateEnd.getText ().toString ();

        // Prepare data intent
        Intent data = new Intent();

        data.putExtra("readBeginDate", mDay+"/"+(mMonth+1)+"/"+mYear);
        data.putExtra("readEndDate", dateEndText);

        formatReadings ( bookRows, data );

        // Activity finished ok, return the data
        setResult(RESULT_OK, data);
        finish ();
    }

    private void formatReadings(List<BookRow> bookRows, Intent intent){
        intent.putExtra("totalReadingCount", bookRows.size () );

        // pour chacun des livrre on va ajouter les valeurs saisies
        for (int i=0; i< bookRows.size (); i++) {
            intent.putExtra("readBook_"+i,bookRows.get ( i ).getBook ());
            intent.putExtra("readChapterBegin_"+i, String.valueOf ( bookRows.get ( i ).getBookBeginValue ()));
          intent.putExtra("readChapterEnd_"+i, String.valueOf ( bookRows.get ( i ).getBookEndValue ()));
        }
    }


    @Override
    public void finish() {

        super.finish();
    }
}
