package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.common.eventbus.EventBus;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.controller.EventManager;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.events.ReadingDeleteEvent;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReadingDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.adapter.ExpandableListViewPrayerAdapter;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by jean- on 25/03/2018.
 */

public class ExpandableReadingListViewAdapter extends BaseExpandableListAdapter{

    private final EventBus eventBus;
    // application context
    private Context context;

    //weekly reading list
    private List<WeeklyReading> weeklyReadings;


    public ExpandableReadingListViewAdapter (Context context, List<WeeklyReading> weeklyReadings){
        this.context= context;
        this.weeklyReadings = weeklyReadings;
        this.eventBus = EventManager.getInstance().getEventBus();

    }


    class ReadingDetail{
        private BibleBook bibleBook;
        private int begin;
        private int end;
        ReadingDetail(BibleBook bibleBook, int begin, int end){
            this.bibleBook = bibleBook;
            this.begin=  begin;
            this.end = end;
            System.out.println (this.bibleBook +" "+this.begin+" "+this.end);
        }


        BibleBook getBibleBook() {
            return bibleBook;
        }

        public int getBegin () {
            return begin;
        }

        public void setBegin ( int begin ) {
            this.begin = begin;
        }

        public int getEnd () {
            return end;
        }

        public void setEnd ( int end ) {
            this.end = end;
        }
    }

    @Override
    public int getGroupCount () {
        return weeklyReadings.size ();
    }

    @Override
    public int getChildrenCount ( int groupPosition ) {
        // to add the delete button de un comment this line
        return weeklyReadings.get ( groupPosition ).getReadingDetails ().size ()+1;
        //return weeklyReadings.get ( groupPosition ).getReadingDetails ().size ();
    }

    @Override
    public WeeklyReading getGroup ( int groupPosition ) {
        return weeklyReadings.get ( groupPosition );
    }

    @Override
    public ReadingDetail getChild ( int groupPosition, int childPosition ) {
        Map<BibleBook, List<Integer>> readingDetails = weeklyReadings.get ( groupPosition ).getReadingDetails ( );
        int count=0;
        ReadingDetail readingDetail =null;

        for (BibleBook bibleBook: readingDetails.keySet ()             ) {
            if(count==childPosition){
                List<Integer> integers = readingDetails.get ( bibleBook );
                readingDetail = new ReadingDetail ( bibleBook ,integers.get ( 0 ),integers.get ( 1 ));
            }
            count++;
        }
        return readingDetail;
    }

    @Override
    public long getGroupId ( int groupPosition  ) {
        return groupPosition ;
    }

    @Override
    public long getChildId ( int groupPosition, int childPosition ) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds () {
        return false;
    }

    @Override
    public View getGroupView ( int groupPosition, boolean isExpanded, View convertView, ViewGroup parent ) {

        long endDate = getGroup(groupPosition).getEndDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(endDate);
        String groupTitle = "Lecture pour le  " +
                String.format(context.getString(R.string.two_digit_format), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
        if (convertView==null && layoutInflater!=null){
            convertView=layoutInflater.inflate ( R.layout.list_group ,null);
        }
        if (convertView!=null){
            TextView readingHeader = convertView.findViewById ( R.id.labelReadingHeader );
            readingHeader.setText ( groupTitle );
        }
        return convertView;
    }

    @Override
    public View getChildView (final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent ) {
        System.out.println(groupPosition +"=>"+getChildrenCount(groupPosition)+" => "+ childPosition + " "+getChild(groupPosition,childPosition)+" "+ isLastChild);
        // For delete button display, uncomment the following lines
        if(childPosition==getChildrenCount(groupPosition)-1){
            convertView = loadDeleteItem(groupPosition, convertView);

        }else{
            convertView = loadReadingItem(groupPosition, childPosition, convertView);
        }
        return convertView;
    }

    /**
     * Loads a delete item
     * @param groupPosition The given group position
     * @param convertView The old (and reusable) view
     * @return The updated reading list item view
     */
    private View loadDeleteItem(final int groupPosition, View convertView) {
        convertView = checkAndLoadDeleteItem(convertView);
        ImageButton deleteReading = convertView.findViewById(R.id.delete_reading);
        deleteReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeeklyReading weeklyReading = getGroup(groupPosition);
                WeeklyReadingDataSource weeklyReadingDataSource = new WeeklyReadingDataSource(context);
                weeklyReadingDataSource.open();
                weeklyReadingDataSource.deleteWeeklyReading(weeklyReading);
                weeklyReadingDataSource.close();
                eventBus.post(new ReadingDeleteEvent(weeklyReading));
            }
        });
        return convertView;
    }

    /**
     * Loads a reading item
     * @param groupPosition The given group position
     * @param childPosition The given child position within the group
     * @param convertView The old (and reusable) view
     * @return The updated reading list item view
     */
    private View loadReadingItem(int groupPosition, int childPosition, View convertView) {
        convertView = checkAndLoadReadingItem(convertView);

        ReadingDetail child = getChild ( groupPosition, childPosition );
        if(child!=null){

            TextView readingBook =convertView.findViewById ( R.id.item_book_name);
            TextView readingBookReference =convertView.findViewById ( R.id.item_book_reference );
            int begin = child.getBegin ( );
            int end = child.getEnd ( );
            if(  readingBook!=null && child.getBibleBook ()!=null){
                String longName = child.getBibleBook ( ).getLongName ( );
                readingBook.setText ( longName );

                String references = " chap " +String.valueOf ( begin );
                references += " à "+String.valueOf ( end );
                readingBookReference.setText ( references  );
            }
        }
        return convertView;
    }

    /**
     * Checks if the current convert view is already the write one otherwise, it loads a delete item view.
     * @param convertView The old convert view
     * @return The given view if the check is ok, a new delete item view otherwise.
     */
    private View checkAndLoadDeleteItem(View convertView) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView==null &&layoutInflater!=null) {
            convertView = layoutInflater.inflate(R.layout.reading_list_item_delete, null);
        }
        if(convertView!=null){

            ImageButton deleteReading = convertView.findViewById(R.id.delete_reading);
            if(deleteReading==null &&layoutInflater!=null) {
                convertView = layoutInflater.inflate(R.layout.reading_list_item_delete, null);
            }
        }else{
            Log.e(ExpandableListViewPrayerAdapter.class.getSimpleName(),"Convert view shall not be null here");
        }
        return convertView;
    }


    /**
     * Checks if the current convert view is already the write one otherwise, it loads a reading item view.
     * @param convertView The old convert view
     * @return The given view if the check is ok, a new reading item view otherwise.
     */
    private View checkAndLoadReadingItem(View convertView) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
        if (convertView==null && layoutInflater!=null){
            convertView=layoutInflater.inflate ( R.layout.reading_history_list_item,null);
        }
        if(convertView!=null){

            TextView readingBook =convertView.findViewById ( R.id.item_book_name);
            if (readingBook==null&& layoutInflater!=null){
                convertView=layoutInflater.inflate ( R.layout.reading_history_list_item,null);
            }
        }
        else{
            Log.e(ExpandableListViewPrayerAdapter.class.getSimpleName(),"Convert view shall not be null here");
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable ( int groupPosition, int childPosition ) {
        return false;
    }
}
