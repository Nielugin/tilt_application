package org.adventiste.ffja.sud.masssmssender.hmi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import org.adventiste.ffja.sud.masssmssender.model.Campain;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jean- on 03/12/2017.
 */

public class CampainListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<String> listDataHeader;
    private final HashMap<String, Campain> listChildData;

    public CampainListAdapter(final Context context, final List<String> listDataHeader,
                              final HashMap<String, Campain> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listChildData = listChildData;
    }


    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(final int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(final int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(final int groupPosition, final int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(final int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(final int groupPosition, final int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, final boolean isExpanded, final View convertView, final ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, final View convertView, final ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(final int groupPosition, final int childPosition) {
        return false;
    }
}
