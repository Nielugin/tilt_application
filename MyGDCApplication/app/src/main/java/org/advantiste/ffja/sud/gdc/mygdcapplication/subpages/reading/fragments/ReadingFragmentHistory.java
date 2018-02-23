package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReadingDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model.HistoryRowItem;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments.model.HistoryRowItemBook;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;


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

    private WeeklyReadingDataSource dataSource;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_reading_history, container, false);
        TableLayout tableLayout = (TableLayout) rootView.findViewById(R.id.reading_list_history);

        Button addReading = (Button) rootView.findViewById(R.id.btnAdd);
        addReading.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  //TODO : A voir quoi mettre dedans par la suite. Pour le moment, ça met un livre aleatoirement
                  // L'ajout se fait après avoir quitté la vue et revenir ... mais comme de toute façon faut changer cet ajout ... na foutre !

                  Calendar calendar = Calendar.getInstance();
                  calendar.setTimeInMillis(System.currentTimeMillis());
                  calendar.set(Calendar.HOUR,0);
                  calendar.set(Calendar.MINUTE,0);
                  calendar.set(Calendar.SECOND,0);
                  calendar.set(Calendar.MILLISECOND,0);
                  long beginDate = calendar.getTimeInMillis();
                  calendar.setTimeInMillis(beginDate+604800000); //oneWeek);
                  long endDate =  calendar.getTimeInMillis();

                  BibleBook book = BibleBook.values()[(int) (Math.random()*BibleBook.values().length)];
                  String readingDetails = book.name()+",1,2,3,4,5,6,7,8,9,10";


                  Log.w(ReadingFragmentHistory.class.getName(),
                          "Details de lecture : "+readingDetails);
                  dataSource = new WeeklyReadingDataSource(ReadingFragmentHistory.this.getContext());
                  dataSource.open();
                  List<WeeklyReading> readings = dataSource.getAllWeeklyReading();
                  if (readings.isEmpty())
                      dataSource.createWeeklyReading(beginDate, endDate,
                          "trop cool !",1, readingDetails);
                  else
                      dataSource.createWeeklyReading(beginDate, endDate,
                          "trop cool"+readings.get(0).getWeekNumber(),
                          readings.get(0).getWeekNumber()+1, readingDetails);
                  dataSource.close();
//                  Intent intent = new Intent(ReadingFragmentHistory.this.getContext(), PrayerActivity.class);
//                  startActivity(intent);


              }
        });


        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        //dataSource.deleteAllWeeklyReading();

        List<WeeklyReading> readings = dataSource.getAllWeeklyReading();

        for (WeeklyReading reading: readings) {
            tableLayout.addView(new HistoryRowItem(this.getContext(),reading));
            Iterator<BibleBook> kBook = reading.getReadingDetails().keySet().iterator();
            while (kBook.hasNext()) {
                BibleBook book = kBook.next();
                tableLayout.addView(new HistoryRowItemBook(this.getContext(),reading, book));
            }

        }
        return rootView;
    }

    @Override
    public void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    public void onPause() {
        dataSource.close();
        super.onPause();
    }


}
