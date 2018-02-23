package org.advantiste.ffja.sud.gdc.mygdcapplication.model.prayers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by erc on 23/02/18.
 */

public class SQLitePrayer extends SQLiteOpenHelper {

    public static final String TABLE_PRAYER = "prayer";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_BEGIN_DATE = "beginDate";
    public static final String COLUMN_END_DATE = "endDate";
    public static final String COLUMN_COMMENT = "comment";
    public static final String COLUMN_TOPIC = "topic";

    private static final String DATABASE_NAME = "prayer.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_PRAYER + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_BEGIN_DATE + " integer not null, "
            + COLUMN_END_DATE + " integer, "
            + COLUMN_TOPIC + " text not null, "
            + COLUMN_COMMENT + " text"
            +" );";

    public SQLitePrayer(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(SQLitePrayer.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_PRAYER);
        onCreate(sqLiteDatabase);
    }
}
