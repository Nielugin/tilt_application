package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReadingDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.AddHistoryActivity;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.ReadingFragmentCurrent;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.ReadingFragmentHistory;

import java.util.Calendar;
import java.util.List;

public class BibleReadingActivity  extends FragmentActivity {


    static final int ADD_READING_REQUEST = 1;  // The request code


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bible_reading);
        // Instantiate a ViewPager and a PagerAdapter.
        ImageView picture =  findViewById(R.id.reading_image_1);
        picture.setImageResource(R.drawable.book);

        TextView addButtonAction = findViewById(R.id.addReading);
        addButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BibleReadingActivity.this.getApplicationContext(), AddHistoryActivity.class);
                startActivityForResult(intent, ADD_READING_REQUEST);
            }

        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Dans le cas de retour de l'ajout de lecture
        if (requestCode == ADD_READING_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                Calendar calendar = initCalendar();

                Long dateBegin = getBeginDate(data, calendar);
                Long dateEnd = getEndDate(data, calendar);

                String readingDetails = generateReadingString(data);

                // Ajout de la lecture de la semaine en SQLite
                /*
      The data source for readings
     */
                saveReadings(dateBegin, dateEnd, readingDetails);

                /*
      The reading history fragment
     */
                ReadingFragmentHistory readingHistory = ( ReadingFragmentHistory ) this.getSupportFragmentManager ( ).findFragmentById ( R.id.reading_history_fragment );
                readingHistory.populateHistory();
                // Miste à jour de la lecture courante
                /*
      The current reading fragment
     */
                ReadingFragmentCurrent currentReading = ( ReadingFragmentCurrent ) this.getSupportFragmentManager ( ).findFragmentById ( R.id.reading_current_fragment );
                currentReading.populateCurrent();

            }
        }
    }

    private void saveReadings(Long dateBegin, Long dateEnd, String readingDetails) {
        WeeklyReadingDataSource dataSource = new WeeklyReadingDataSource ( BibleReadingActivity.this.getApplicationContext ( ) );
        dataSource.open();

        List<WeeklyReading> readings = dataSource.getAllWeeklyReading();
        if (readings.isEmpty())
            dataSource.createWeeklyReading(dateBegin, dateEnd,"" ,1, readingDetails);
        else
            dataSource.createWeeklyReading(dateBegin, dateEnd,"",
                    readings.get(0).getWeekNumber()+1, readingDetails);
        dataSource.close();
    }

    @NonNull
    private Long getEndDate(Intent data, Calendar calendar) {
        String[] dateDMY;
        String strDateEnd = data.getStringExtra("readEndDate");
        dateDMY = strDateEnd.split("/");
        calendar.set(Integer.parseInt(dateDMY[2]),Integer.parseInt(dateDMY[1])-1,Integer.parseInt(dateDMY[0]));
        return calendar.getTimeInMillis();
    }

    @NonNull
    private Long getBeginDate(Intent data, Calendar calendar) {
        String strDateBegin = data.getStringExtra("readBeginDate");
        String[] dateDMY = strDateBegin.split("/");
        calendar.set(Integer.parseInt(dateDMY[2]),Integer.parseInt(dateDMY[1])-1,Integer.parseInt(dateDMY[0]));
        return calendar.getTimeInMillis();
    }

    @NonNull
    private Calendar initCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar;
    }

    @NonNull
    private String generateReadingString(Intent data) {
        int totalReadingOfWeek = data.getIntExtra("totalReadingCount",-1);
        BibleBook book = BibleBook.fromString (data.getStringExtra("readBook_0"));
        int chapterBegin = Integer.parseInt(data.getStringExtra("readChapterBegin_0"));
        int chapterEnd = Integer.parseInt(data.getStringExtra("readChapterEnd_0"));
        StringBuilder readingDetailsBuilder = new StringBuilder ( book + "," + chapterBegin + "," + chapterEnd );
        for (int i = 1; i<totalReadingOfWeek; i++) {
            book = BibleBook.fromString (data.getStringExtra("readBook_"+i));
            chapterBegin = Integer.parseInt(data.getStringExtra("readChapterBegin_"+i));
            chapterEnd = Integer.parseInt(data.getStringExtra("readChapterEnd_"+i));
            readingDetailsBuilder.append ( ";" ).append ( book ).append ( "," ).append ( chapterBegin ).append ( "," ).append ( chapterEnd );
        }
        return readingDetailsBuilder.toString ( );
    }

}
