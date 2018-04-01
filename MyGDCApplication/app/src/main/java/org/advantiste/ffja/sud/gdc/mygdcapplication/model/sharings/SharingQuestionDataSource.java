package org.advantiste.ffja.sud.gdc.mygdcapplication.model.sharings;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jean- on 01/04/2018.
 */

public class SharingQuestionDataSource {


    // Champs de la base de données
    private SQLiteDatabase database;
    private SQLiteSharingQuestion dbShareQuestions;
    private String[] allColumns = { SQLiteSharingQuestion.COLUMN_ID, SQLiteSharingQuestion.COLUMN_QUESTION};

    public SharingQuestionDataSource(Context context) {
        dbShareQuestions = new SQLiteSharingQuestion (context);
    }

    public void open() throws SQLException {
        database = dbShareQuestions.getWritableDatabase();
    }

    public void close() {
        dbShareQuestions.close();
    }

    public SharingQuestion createShareQuestion(String question) {
        ContentValues values = new ContentValues();
        values.put(SQLiteSharingQuestion.COLUMN_QUESTION, question);

        long insertId = database.insert(SQLiteSharingQuestion.TABLE_SHARE, null,
                values);
        Cursor cursor = database.query(SQLiteSharingQuestion.TABLE_SHARE,
                allColumns, SQLiteSharingQuestion.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        SharingQuestion newQuestion = cursorToShareQuestion(cursor);
        cursor.close();
        return newQuestion;
    }

    public void deleteAllQuestions() {
        Log.w(SharingQuestionDataSource.class.getName()," All Prayers deleted ");
        database.delete(SQLiteSharingQuestion.TABLE_SHARE, null, null);
    }

    public void deleteQuestionById(long id) {
        Log.w(SharingQuestionDataSource.class.getName()," Prayer "+id+" is deleted ");
        database.delete(SQLiteSharingQuestion.TABLE_SHARE, SQLiteSharingQuestion.COLUMN_ID+" = "+id, null);
    }

    public List<SharingQuestion> getAllQuestions() {
        List<SharingQuestion> allQuestions = new ArrayList<SharingQuestion> ();

        Cursor cursor = database.query( SQLiteSharingQuestion.TABLE_SHARE,
                allColumns, null, null, null, null, SQLiteSharingQuestion.COLUMN_ID+" DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            SharingQuestion question = cursorToShareQuestion (cursor);
            allQuestions.add(question);
            cursor.moveToNext();
        }
        // assurez-vous de la fermeture du curseur
        cursor.close();
        return allQuestions;
    }


    private SharingQuestion cursorToShareQuestion(Cursor cursor) {
        SharingQuestion question = new SharingQuestion (cursor.getLong(0),
                cursor.getString(1));
        return question;
    }



}
