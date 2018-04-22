package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddHistoryActivity extends AppCompatActivity {


    private EditText dateEnd;
    DatePickerDialog datePickerDialog;

    List<BookRow> bookRows = new ArrayList<> (  );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_history);

        FloatingActionButton saveButton= findViewById(R.id.floatingActionButton);
        saveButton.setOnClickListener ( new View.OnClickListener ( ) {

            @Override
            public void onClick ( View v ) {
                save ();
            }
        } );

        final TableLayout readingList = (TableLayout) findViewById(R.id.readingList);

        final TextView addReadingButton = (TextView) findViewById(R.id.addBookButton);



        /* *************** Gestion de la date de fin
        * http://abhiandroid.com/ui/datepicker
        * */

        dateEnd =  findViewById(R.id.endDate);

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
                                dateEnd.setText(String.format("%02d", dayOfMonth )+ "/"
                                        + String.format("%02d", (monthOfYear + 1) )+ "/" + String.format("%04d", year));


                            }
                        }, mYear, mMonth, mDay);

                // TODO: regader pour fermer le clavier
                //datePickerDialog.setOnDateSetListener (  );

                datePickerDialog.show();


            }
        });
        dateEnd.setActivated(false);

        dateEnd.setOnEditorActionListener ( new TextView.OnEditorActionListener ( ) {
            @Override
            public boolean onEditorAction ( TextView v, int actionId, KeyEvent event ) {
                System.out.println (actionId);
                if(actionId == EditorInfo.IME_ACTION_DONE){

                    addReadingButton.requestFocus ();
                }

                return false;
            }
        } );



        /* ************** Gestion du boutton pour l'ajout */

        addReadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FragmentTransaction fragmentTransaction = getFragmentManager ( ).beginTransaction ( );
                final BookRow bookRow = BookRow.newInstance ( );
                bookRow.setInteractionInterface ( new BookRow.InteractionInterface ( ) {
                                                      @Override
                                                      public void removeAction () {
                                                          System.out.println(readingList.getChildCount());
                                                          if(readingList.getChildCount()>2){
                                                              readingList.removeView ( bookRow.getView () );
                                                              bookRows.remove ( bookRow );
                                                          }

                                                      }

                                                  }
                );

                fragmentTransaction.add(R.id.readingList,(Fragment ) bookRow);
                fragmentTransaction.commit ();
                bookRows.add ( bookRow );

                //save();

            }
        });


        addReadingButton.callOnClick ();

    }


    private void save(){

        String dateEndText = dateEnd.getText ().toString ();

        if(dateEndText!=null && !dateEndText.isEmpty () && checkEndDate(dateEndText)){



            // calender class's instance and get current date , month and year from calender
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR); // current year
            int mMonth = c.get(Calendar.MONTH); // current month
            int mDay = c.get(Calendar.DAY_OF_MONTH); // current day

            // TODO: véfifier que les champs ne sont pas nuls  : date de fin

            // Prepare data intent
            Intent data = new Intent();

            data.putExtra("readBeginDate", mDay+"/"+(mMonth+1)+"/"+mYear);

            data.putExtra("readEndDate", dateEndText);

            formatReadings ( bookRows, data );

            // Activity finished ok, return the data
            setResult(RESULT_OK, data);
            finish ();
        }
        else{
            dateEnd.setError ( "La date doit être remplie au format dd/mm/YYYY! " );
        }
    }

    private boolean checkEndDate(String dateEndText) {
        return dateEndText.matches("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
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
