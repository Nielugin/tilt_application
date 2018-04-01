package org.advantiste.ffja.sud.gdc.mygdcapplication.model.sharings;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jean- on 01/04/2018.
 */

public class SQLiteSharingQuestion  extends SQLiteOpenHelper {

    public static final String TABLE_SHARE = "share";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_QUESTION = "question";

    private static final String DATABASE_NAME = "share.db";
    private static final int DATABASE_VERSION = 1;

    // Commande sql pour la création de la base de données
    private static final String DATABASE_CREATE = "create table "
            + TABLE_SHARE + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_QUESTION + " text not null "
            +" );";

    public SQLiteSharingQuestion (Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate ( SQLiteDatabase db ) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade ( SQLiteDatabase db, int oldVersion, int newVersion ) {
        Log.w(SQLiteSharingQuestion.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHARE);
        onCreate(db);
    }
}
