package com.eggwall.Touch.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Database helper for storing to the Friend database.
 */
public class FriendDbHelper extends SQLiteOpenHelper {
// TODO(viki) Turn this into a Content Provider.
// Perhaps not required?

    /** The current version of the database */
    public static final int VERSION = 1;
    /** Name of the database on disk */
    public static final String DB_NAME = "friends.sqlite";

    private static final String TABLE_FRIEND = "friend";

    private static final String COL_ID = "_id";
    private static final String COL_NAME = "name";
    private static final String COL_FREQ = "frequency";
    private static final String COL_LAST_CALLED = "last";

    private static final String[] ALL_COLUMNS = {
            COL_ID, COL_NAME, COL_FREQ, COL_LAST_CALLED
    };

    public FriendDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    /** Testing only, create a test database. */
    public FriendDbHelper(Context context, String dbName){
        super(context, dbName, null, VERSION);
    }

    /**
     * The database was just created, so make one.
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_FRIEND + "("
                + COL_ID + " integer primary key autoincrement"
                + ", " + COL_NAME + " string not null"
                + ", " + COL_FREQ + " integer not null"
                + ", " + COL_LAST_CALLED + " string not null"
                + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        // No upgrade logic yet.
    }

    /**
     * Looks up a list of friends.
     * @return
     */
    public List<Friend> lookup () {
        SQLiteDatabase db = getReadableDatabase();
        if (db == null) {
            return Collections.emptyList();
        }
        Cursor q = db.query(TABLE_FRIEND, ALL_COLUMNS, null, null, null, null, null);
        if (!q.moveToFirst()) {
            return Collections.emptyList();
        }
        ArrayList<Friend> r = new ArrayList<Friend>(q.getCount());
        do {
            int id = q.getInt(0);
            String name = q.getString(1);
            int freq = q.getInt(2);
            String lastCalled = q.getString(3);
            r.add(new Friend(id, name, freq, lastCalled));
        } while (q.moveToNext());
        db.close();
        return r;
    }

    /**
     * Update the database with the friend provided here.
     */
    public boolean update(Friend friend) {
        ContentValues v = new ContentValues();
        v.put(COL_NAME, friend.NAME);
        v.put(COL_FREQ, friend.CALL_FREQUENCY);
        v.put(COL_LAST_CALLED, friend.lastCalled());
        SQLiteDatabase db = getWritableDatabase();
        if (db == null) return false;
        int numRows = db.update(TABLE_FRIEND, v,
                COL_ID + " = ?", new String[]{friend.ID + ""});
        // We wanted to impact a single row.
        return 1 == numRows;
    }

    /**
     * Insert the given friend into the database.
     * @param friend
     * @return
     */
    public long insertFriend(Friend friend) {
        ContentValues v = new ContentValues();
        v.put(COL_NAME, friend.NAME);
        v.put(COL_FREQ, friend.CALL_FREQUENCY);
        v.put(COL_LAST_CALLED, friend.lastCalled());
        return getWritableDatabase().insert(TABLE_FRIEND, null, v);
    }

    /**
     * Delete the friend provided here
     * @param friend to delete
     * @return true if a friend was deleted, false otherwise.
     */
    public boolean deleteFriend(Friend friend) {
        // Delete just a single friend.
        return getWritableDatabase().delete(TABLE_FRIEND,
                COL_ID + " = ?",
                new String[]{friend.ID + ""}) == 1;
    }
}
