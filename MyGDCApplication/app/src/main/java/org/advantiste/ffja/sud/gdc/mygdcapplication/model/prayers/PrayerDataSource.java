package org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by erc on 23/02/18.
 */

public class PrayerDataSource {
    // Champs de la base de données
    private SQLiteDatabase database;
    private SQLitePrayer dbPrayer;
    private String[] allColumns = { SQLitePrayer.COLUMN_ID,
            SQLitePrayer.COLUMN_BEGIN_DATE, SQLitePrayer.COLUMN_END_DATE,
            SQLitePrayer.COLUMN_TOPIC, SQLitePrayer.COLUMN_COMMENT};

    public PrayerDataSource(Context context) {
        dbPrayer = new SQLitePrayer(context);
    }

    public void open() throws SQLException {
        database = dbPrayer.getWritableDatabase();
    }

    public void close() {
        dbPrayer.close();
    }

    public Prayer createPrayer(long beginDate, long endDate, String topic, String comment) {
        ContentValues values = new ContentValues();
        values.put(SQLitePrayer.COLUMN_BEGIN_DATE, beginDate);
        if (endDate != -1)
            values.put(SQLitePrayer.COLUMN_END_DATE, endDate);
        values.put(SQLitePrayer.COLUMN_TOPIC, topic);
        if (comment != null)
            values.put(SQLitePrayer.COLUMN_COMMENT, comment);

        long insertId = database.insert(SQLitePrayer.TABLE_PRAYER, null,
                values);
        Cursor cursor = database.query(SQLitePrayer.TABLE_PRAYER,
                allColumns, SQLitePrayer.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Prayer newPrayer = cursorToPray(cursor);
        cursor.close();
        return newPrayer;
    }

    public void deleteAllPrayers() {
        Log.w(PrayerDataSource.class.getName()," All Prayers deleted ");
        database.delete(SQLitePrayer.TABLE_PRAYER, null, null);
    }

    public void deletePrayerById(long id) {
        Log.w(PrayerDataSource.class.getName()," Prayer "+id+" is deleted ");
        database.delete(SQLitePrayer.TABLE_PRAYER, SQLitePrayer.COLUMN_ID+" = "+id, null);
    }

    public List<Prayer> getAllPrayers() {
        List<Prayer> allPrayers = new ArrayList<Prayer>();

        Cursor cursor = database.query(SQLitePrayer.TABLE_PRAYER,
                allColumns, null, null, null, null, SQLitePrayer.COLUMN_ID+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Prayer pray = cursorToPray(cursor);
            allPrayers.add(pray);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return allPrayers;
    }


    private Prayer cursorToPray(Cursor cursor) {
        Prayer pray = new Prayer(cursor.getLong(1), cursor.getLong(2),
                cursor.getString(3), cursor.getString(4));
        pray.setId(cursor.getLong(0));
        return pray;
    }


}
