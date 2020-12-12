package com.example.logreg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Accounts";

    public static final String ACCOUNT_TABLE = "Account";

    public static String COL_ID = "id";
    public static String COL_EMAIL = "email";
    public static String COL_FELHNEV = "felhnev";
    public static String COL_JELSZO = "jelszo";
    public static String COL_TELJESNEV = "teljesnev";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + ACCOUNT_TABLE + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EMAIL + " VARCHAR(255) NOT NULL, " +
                COL_FELHNEV + " VARCHAR(255) NOT NULL, " +
                COL_JELSZO + " VARCHAR(255) NOT NULL, " +
                COL_TELJESNEV + " VARCHAR(255) NOT NULL " +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + ACCOUNT_TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean dataRecording(String email, String felhnev, String jelszo, String teljesnev) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_EMAIL, email);
        values.put(COL_FELHNEV, felhnev);
        values.put(COL_JELSZO, jelszo);
        values.put(COL_TELJESNEV, teljesnev);

        long result = db.insert(ACCOUNT_TABLE, null, values);

        return result != -1;
    }

    public boolean loginCheck(String felhnev, String jelszo) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT COUNT(*) FROM " + ACCOUNT_TABLE + " WHERE " + COL_FELHNEV + " = ? OR " + COL_EMAIL + " = ? AND " + COL_JELSZO + " = ?"
                , new String[]{felhnev,felhnev, jelszo});
        result.moveToFirst();
        return result.getInt(0) == 1;
    }

    public Cursor idQuery(String felhnev, String jelszo) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + COL_ID + " FROM " + ACCOUNT_TABLE + " WHERE " + COL_FELHNEV + " = ? OR " + COL_EMAIL + " = ? AND " + COL_JELSZO + " = ?"
                , new String[]{felhnev,felhnev, jelszo});
    }

    public Cursor idRequest(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + COL_TELJESNEV + " FROM " + ACCOUNT_TABLE + " WHERE " + COL_ID + " = ? ",
                new String[]{id});
    }
}

