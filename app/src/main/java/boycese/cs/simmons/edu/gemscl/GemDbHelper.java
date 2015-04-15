package boycese.cs.simmons.edu.gemscl;

/**
 * Created by susanboyce on 4/14/15.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class GemDbHelper {

    public static final String KEY_NAME = "gemName";
    public static final String KEY_COLOR = "gemColor";
    public static final String KEY_WEIGHT = "gemWeight";
    public static final String KEY_ROWID = "_id";

    /* Adapted from NotepadDemo
    http://developer.android.com/training/notepad/index.html
     */
    private static final String TAG = "GemDbHelper";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "create table gems (_id integer primary key autoincrement, "
                    + "gemName text not null, gemColor text not null, gemWeight text not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "gems";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS notes");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public GemDbHelper(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     * initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public GemDbHelper open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    public long createGem(String name, String color, String weight) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_COLOR, color);
        initialValues.put(KEY_WEIGHT, weight);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }


}
