package org.advantiste.ffja.sud.gdc.mygdcapplication.model.readings;

import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Created by erc on 22/02/18.
 */

public class WeeklyReadingDataSource {

    // Champs de la base de données
    private SQLiteDatabase database;
    private SQLiteWeeklyReading dbWeeklyReading;
    private String[] allColumns = { SQLiteWeeklyReading.COLUMN_ID,
            SQLiteWeeklyReading.COLUMN_BEGIN_DATE, SQLiteWeeklyReading.COLUMN_END_DATE,
            SQLiteWeeklyReading.COLUMN_COMMENT, SQLiteWeeklyReading.COLUMN_WEEK_NUMBER,
            SQLiteWeeklyReading.COLUMN_READING_DETAILS};


    public WeeklyReadingDataSource(Context context) {
        dbWeeklyReading = new SQLiteWeeklyReading(context);
    }

    public void open() throws SQLException {
        database = dbWeeklyReading.getWritableDatabase();
    }

    public void close() {
        dbWeeklyReading.close();
    }

    public WeeklyReading createWeeklyReading(long beginDate, long endDate, String comment,
                                             int weekNumber, String readingDetails) {
        ContentValues values = new ContentValues();
        values.put(SQLiteWeeklyReading.COLUMN_BEGIN_DATE, beginDate);
        values.put(SQLiteWeeklyReading.COLUMN_END_DATE, endDate);
        values.put(SQLiteWeeklyReading.COLUMN_COMMENT, comment);
        values.put(SQLiteWeeklyReading.COLUMN_WEEK_NUMBER, weekNumber);
        values.put(SQLiteWeeklyReading.COLUMN_READING_DETAILS, readingDetails);

        long insertId = database.insert(SQLiteWeeklyReading.TABLE_WEEKLY_READING, null,
            values);
        Cursor cursor = database.query(SQLiteWeeklyReading.TABLE_WEEKLY_READING,
            allColumns, SQLiteWeeklyReading.COLUMN_ID + " = " + insertId, null,
            null, null, null);
        cursor.moveToFirst();
        WeeklyReading newWeeklyReading = cursorToWeeklyReading(cursor);
        cursor.close();
        return newWeeklyReading;
    }

    public void deleteAllWeeklyReading() {
        Log.w(WeeklyReadingDataSource.class.getName()," All WeeklyReading deleted ");
        database.delete(SQLiteWeeklyReading.TABLE_WEEKLY_READING, null, null);
    }


    public void deleteWeeklyReading(WeeklyReading weekRead) {
        long id = weekRead.getId();
        Log.w(WeeklyReadingDataSource.class.getName(),"WeeklyReading deleted with id: " + id);
        database.delete(SQLiteWeeklyReading.TABLE_WEEKLY_READING, SQLiteWeeklyReading.COLUMN_ID
            + " = " + id, null);
    }

    public WeeklyReading getThisWeekReading() {
        WeeklyReading weekReading;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        long currentDate = calendar.getTimeInMillis();

        Cursor cursor = database.query(SQLiteWeeklyReading.TABLE_WEEKLY_READING,
                allColumns, "beginDate<=? and endDate>=?",
                new String[] { String.valueOf(currentDate), String.valueOf(currentDate) },
                null, null, SQLiteWeeklyReading.COLUMN_WEEK_NUMBER+" DESC");


        if (cursor!=null && cursor.getCount()>0) {
            cursor.moveToFirst();
            weekReading = cursorToWeeklyReading(cursor);
        } else
            weekReading = null;
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return weekReading;
    }

    public List<WeeklyReading> getAllWeeklyReading() {
        List<WeeklyReading> weeklyReadings = new ArrayList<>();

        Cursor cursor = database.query(SQLiteWeeklyReading.TABLE_WEEKLY_READING,
                allColumns, null, null, null, null, SQLiteWeeklyReading.COLUMN_WEEK_NUMBER+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            WeeklyReading weekRead = cursorToWeeklyReading(cursor);
            weeklyReadings.add(weekRead);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return weeklyReadings;
    }

    private WeeklyReading cursorToWeeklyReading(Cursor cursor) {
        String readings = cursor.getString(5);
        Map<BibleBook,List<Integer>> readingDetails = new HashMap<>();

        String[] bookAndChapters = readings.split(";");
        for (String bc : bookAndChapters) {
            String[] chapters = bc.split(",");
            BibleBook book = BibleBook.fromString (chapters[0]);
            List<Integer> listChapters = new ArrayList<>();
            listChapters.add(Integer.parseInt(chapters[1]));
            listChapters.add(Integer.parseInt(chapters[2]));
//            for (int i = 1; i < chapters.length; i++) {
//                listChapters.add(Integer.parseInt(chapters[i]));
//            }
            readingDetails.put(book, listChapters);
        }

        WeeklyReading weekRead = new WeeklyReading(cursor.getLong(1), cursor.getLong(2),
                cursor.getString(3), cursor.getInt(4), readingDetails);
        weekRead.setId(cursor.getLong(0));
        return weekRead;
    }

}
