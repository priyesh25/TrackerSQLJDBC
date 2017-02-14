package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by darryl on 7/12/2016.
 */

// References
// http://androidopentutorials.com/android-sqlite-example/
// http://www.vogella.com/tutorials/AndroidSQLite/article.html

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "reminderdb";
    private static final int DATABASE_VERSION = 1;

    public static final String REMINDER_TABLE = "reminder";

    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "evtTitle";
    public static final String EVENT_DATE = "evtDate";
    public static final String EVENT_DESC = "evtDesc";

    public static final String CREATE_REMINDER_TABLE = "CREATE TABLE "
            + REMINDER_TABLE + "(" + ID_COLUMN + " INTEGER PRIMARY KEY, "
            + TITLE_COLUMN + " TEXT, " + EVENT_DATE + " DATE, "
            + EVENT_DESC + " TEXT" + ")";

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DataBaseHelper(context);
        return instance;
    }

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_REMINDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DataBaseHelper.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + REMINDER_TABLE);
        onCreate(db);
    }
}
