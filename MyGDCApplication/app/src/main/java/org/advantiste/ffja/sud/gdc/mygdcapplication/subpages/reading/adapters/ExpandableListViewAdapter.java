package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.BibleBook;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by jean- on 25/03/2018.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter{

    // application context
    private Context context;

    //weekly reading list
    private List<WeeklyReading> weeklyReadings;


    public ExpandableListViewAdapter(Context context, List<WeeklyReading> weeklyReadings){
        this.context= context;
        this.weeklyReadings = weeklyReadings;

    }


    class ReadingDetail{
        private BibleBook bibleBook;
        private int begin;
        private int end;
        public ReadingDetail(BibleBook bibleBook, int begin, int end){
    this.bibleBook = bibleBook;
    this.begin=  begin;
    this.end = end;
        }


        public BibleBook getBibleBook () {
            return bibleBook;
        }

        public void setBibleBook ( BibleBook bibleBook ) {
            this.bibleBook = bibleBook;
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
        return weeklyReadings.get ( groupPosition ).getReadingDetails ().size ();
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
        String groupTitle = "Semaine "+getGroup ( groupPosition ).getWeekNumber ();
if (convertView==null){
    LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
    convertView=layoutInflater.inflate ( R.layout.list_group ,null);
}
        TextView readingHeader =(TextView) convertView.findViewById ( R.id.labelReadingHeader );
    readingHeader.setText ( groupTitle );
return convertView;
    }

    @Override
    public View getChildView ( int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent ) {
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            convertView=layoutInflater.inflate ( R.layout.list_item ,null);
        }
        ReadingDetail child = getChild ( groupPosition, childPosition );

        TextView readingBook =convertView.findViewById ( R.id.item_book_name );
        if( child!=null && child.getBibleBook ()!=null){
            readingBook.setText ( child.getBibleBook ().toString () );


        }
  /*      TextView readingBookBegin =convertView.findViewById ( R.id.item_book_begin);
        readingBookBegin.setText ( child.getEnd () );
        TextView readingBookEnd = convertView.findViewById ( R.id.item_book_end );
        readingBookEnd.setText ( child.getEnd () );//*/
        return convertView;
    }

    @Override
    public boolean isChildSelectable ( int groupPosition, int childPosition ) {
        return false;
    }
}
