package org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.advantiste.ffja.sud.gdc.mygdcapplication.R;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReading;
import org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings.WeeklyReadingDataSource;
import org.advantiste.ffja.sud.gdc.mygdcapplication.subpages.reading.adapters.ExpandableListViewAdapter;

import java.util.ArrayList;


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

    private ExpandableListView historyListView;
    private ArrayList<WeeklyReading> readings;
    private AppAdapter mAdapter;
    public void populateHistory() {
        // Supprimer toutes les données du tableau afin de recréer tout proprement

        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        //dataSource.deleteAllWeeklyReading();


        readings = new ArrayList<> ( dataSource.getAllWeeklyReading());

        historyListView.setAdapter ( new ExpandableListViewAdapter ( getContext (),readings ) );
        historyListView.setMinimumHeight ( readings.size ()*30 );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       final  ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_reading_history, container, false);

        rootView.post(new Runnable() {
                      @Override
                      public void run() {
                          int height=rootView.getMeasuredHeight(); // for instance
                            System.out.println (height);
                      }
                  });



        historyListView =   rootView.findViewById(R.id.history_list_view);





        populateHistory();

        /* *** TODO : Remet à vide le SQLite de LECTURE
        dataSource = new WeeklyReadingDataSource(this.getContext());
        dataSource.open();
        dataSource.deleteAllWeeklyReading();
        /**/
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
    private void open(WeeklyReading item) {
        // open app

    }



    class AppAdapter extends BaseSwipListAdapter {

        @Override
        public int getCount() {
            return readings.size();
        }

        @Override
        public WeeklyReading getItem( int position) {
            return readings.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getContext (),
                        R.layout.item_list_app, null);
                new ViewHolder(convertView);
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            WeeklyReading item = getItem(position);

            holder.tv_name.setText("Semaine "+ item.getWeekNumber ());
            holder.iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext (), "iv_icon_click", Toast.LENGTH_SHORT).show();
                }
            });
            holder.tv_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText( getContext (),"iv_icon_click",Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;

            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                view.setTag(this);
            }
        }

        @Override
        public boolean getSwipEnableByPosition(int position) {
            if(position % 2 == 0){
                return false;
            }
            return true;
        }
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }

}
