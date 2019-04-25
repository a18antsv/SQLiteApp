package org.brohede.marcus.sqliteapp;

import android.provider.BaseColumns;

public class MountainReaderContract {
    public static final String SQL_CREATE_TABLE = "" +
            "CREATE TABLE " +
            MountainEntry.TABLE_NAME + " (" +
            MountainEntry.COLUMN_ID + " INTEGER PRIMARY KEY, " +
            MountainEntry.COLUMN_NAME + " TEXT NOT NULL UNIQUE," +
            MountainEntry.COLUMN_HEIGHT + " INTEGER NOT NULL," +
            MountainEntry.COLUMN_LOCATION + " TEXT," +
            MountainEntry.COLUMN_IMG_URL + " TEXT," +
            MountainEntry.COLUMN_ARTICLE_URL + " TEXT)";
    public static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + MountainEntry.TABLE_NAME;

    private MountainReaderContract() {}

    public static class MountainEntry implements BaseColumns {
        public static final String TABLE_NAME = "mountain";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_HEIGHT = "height";
        public static final String COLUMN_LOCATION = "location";
        public static final String COLUMN_IMG_URL = "imgurl";
        public static final String COLUMN_ARTICLE_URL = "articleurl";
    }

}