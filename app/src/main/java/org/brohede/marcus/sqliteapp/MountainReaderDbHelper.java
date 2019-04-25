package org.brohede.marcus.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.brohede.marcus.sqliteapp.MountainReaderContract.*;


public class MountainReaderDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mountain.db";
    public static final int DATABASE_VERSION = 1;

    public MountainReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MountainReaderContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MountainReaderContract.SQL_DELETE_TABLE);
        onCreate(db);
    }

    public boolean insertRow(int id, String name, int height, String location, String imgURL, String articleURL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MountainEntry.COLUMN_ID, id);
        values.put(MountainEntry.COLUMN_NAME, name);
        values.put(MountainEntry.COLUMN_HEIGHT, height);
        values.put(MountainEntry.COLUMN_LOCATION, location);
        values.put(MountainEntry.COLUMN_IMG_URL, imgURL);
        values.put(MountainEntry.COLUMN_ARTICLE_URL, articleURL);
        long result = db.insert(MountainEntry.TABLE_NAME, null, values);

        return (result == -1) ? false : true;
    }

    public Cursor getData(String table, String[] columns, String whereClause, String[] whereArgs, String groupBy, String having, String orderBy) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                table,
                columns,
                whereClause,
                whereArgs,
                groupBy,
                having,
                orderBy
        );
        return cursor;
    }

    public int updateData(int id, String name, int height, String location, String imgURL, String articleURL) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MountainEntry.COLUMN_NAME, name);
        values.put(MountainEntry.COLUMN_HEIGHT, height);
        values.put(MountainEntry.COLUMN_LOCATION, location);
        values.put(MountainEntry.COLUMN_IMG_URL, imgURL);
        values.put(MountainEntry.COLUMN_ARTICLE_URL, articleURL);
        int count = db.update(MountainEntry.TABLE_NAME, values, MountainEntry.COLUMN_ID + "=?", new String[] {String.valueOf(id)});
        return count;
    }

    public int deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MountainEntry.TABLE_NAME, MountainEntry.COLUMN_ID + "=?", new String[] {String.valueOf(id)});
    }

    public boolean isDataInDatabase(String table, String columns[], String whereClause, String[] whereArgs) {
        Cursor c = getData(table, columns, whereClause, whereArgs, null, null, null);
        if (c.getCount() <= 0) {
            c.close();
            return false;
        }
        c.close();
        return true;
    }
}