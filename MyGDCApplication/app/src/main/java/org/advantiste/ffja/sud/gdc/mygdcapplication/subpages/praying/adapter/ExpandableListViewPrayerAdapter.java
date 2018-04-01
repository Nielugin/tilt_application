package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.praying.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers.Prayer;

import java.util.List;

/**
 * Created by erc on 31/03/2018.
 */

public class ExpandableListViewPrayerAdapter extends BaseExpandableListAdapter {


    // application context
    private Context context;

    //weekly reading list
    private List<Prayer> listOfPray;

    public ExpandableListViewPrayerAdapter(Context context, List<Prayer> listOfPray){
        this.context= context;
        this.listOfPray = listOfPray;

    }

    class PrayingDetail {
        private String subject;
        private String comments;

        public PrayingDetail(String subj, String coms) {
            subject = subj;
            comments = coms;
        }


        public String getSubject () {
            return subject;
        }

        public void setSubject ( String subject ) {
            this.subject = subject;
        }

        public String getComments () {
            return comments;
        }

        public void setComments ( String comments ) {
            this.comments = comments;
        }
    }

    @Override
    public int getGroupCount () {
        return listOfPray.size ();
    }

    @Override
    public int getChildrenCount ( int groupPosition ) {
        return 1;
    }

    @Override
    public Prayer getGroup ( int groupPosition ) {
        return listOfPray.get ( groupPosition );
    }

    @Override
    public PrayingDetail getChild ( int groupPosition, int childPosition ) {
        Prayer p = listOfPray.get(groupPosition);
        PrayingDetail prayerDetail = new PrayingDetail (p.getTopic (), p.getComments () );
        return prayerDetail;
    }

    @Override
    public long getGroupId ( int groupPosition ) {
        return groupPosition;
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
        String groupTitle = getGroup ( groupPosition ).getTopic ();
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            convertView=layoutInflater.inflate ( R.layout.list_group ,null);
        }
        TextView prayerHeader = convertView.findViewById ( R.id.labelReadingHeader );
        prayerHeader.setText ( groupTitle );
        return convertView;
    }

    @Override
    public View getChildView ( int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent ) {
        if (convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
            convertView=layoutInflater.inflate ( R.layout.prayer_list_item,null);
        }
        PrayingDetail child = getChild ( groupPosition, childPosition );

        TextView prayerComments =convertView.findViewById ( R.id.item_description );
        if( child!=null && child.getComments ()!=null){
            prayerComments.setText ( child.getComments () );
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable ( int groupPosition, int childPosition ) {
        return false;
    }
}
