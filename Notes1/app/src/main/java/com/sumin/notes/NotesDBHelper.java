package com.sumin.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class NotesDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="notes,db";
    private static final int DB_VERSION=1;

    public NotesDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(NotesContract.NotesEntry.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(NotesContract.NotesEntry.DROP_COMMAND);
        onCreate(db);
    }
}
