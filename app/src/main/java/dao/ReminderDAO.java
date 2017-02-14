package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import model.Reminder;

/**
 * Created by darryl on 7/12/2016.
 */

public class ReminderDAO extends DBDAO {

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_COLUMN + " =?";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    public ReminderDAO(Context context) {
        super(context);
    }

    public long save(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.TITLE_COLUMN, reminder.getEventTitle());
        values.put(DataBaseHelper.EVENT_DATE, formatter.format(reminder.getEventDate()));
        values.put(DataBaseHelper.EVENT_DESC, reminder.getEventDesc());

        return database.insert(DataBaseHelper.REMINDER_TABLE, null, values);
    }

    public long update(Reminder reminder) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.TITLE_COLUMN, reminder.getEventTitle());
        values.put(DataBaseHelper.EVENT_DATE, formatter.format(reminder.getEventDate()));
        values.put(DataBaseHelper.EVENT_DESC, reminder.getEventDesc());

        long result = database.update(DataBaseHelper.REMINDER_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(reminder.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int delete(Reminder reminder) {
        return database.delete(DataBaseHelper.REMINDER_TABLE, WHERE_ID_EQUALS,
                new String[] { reminder.getId() + "" });
    }

    //USING query() method
    public ArrayList<Reminder> getReminders() {
        ArrayList<Reminder> reminders = new ArrayList<Reminder>();

        Cursor cursor = database.query(DataBaseHelper.REMINDER_TABLE,
                new String[] { DataBaseHelper.ID_COLUMN,
                        DataBaseHelper.TITLE_COLUMN,
                        DataBaseHelper.EVENT_DATE,
                        DataBaseHelper.EVENT_DESC }, null, null, null,
                null, null);

        while (cursor.moveToNext()) {
            Reminder reminder = new Reminder();
            reminder.setId(cursor.getInt(0));
            reminder.setEventTitle(cursor.getString(1));
            try {
                reminder.setEventDate(formatter.parse(cursor.getString(2)));
            } catch (ParseException e) {
                reminder.setEventDate(null);
            }
            reminder.setEventDesc(cursor.getString(3));

            reminders.add(reminder);
        }
        return reminders;
    }

    //USING rawQuery() method
	public ArrayList<Reminder> getRemindersRQ() {
		ArrayList<Reminder> reminders = new ArrayList<Reminder>();

		String sql = "SELECT " + DataBaseHelper.ID_COLUMN + ","
				+ DataBaseHelper.TITLE_COLUMN + ","
				+ DataBaseHelper.EVENT_DATE + ","
				+ DataBaseHelper.EVENT_DESC + " FROM "
				+ DataBaseHelper.REMINDER_TABLE;

		Cursor cursor = database.rawQuery(sql, null);

		while (cursor.moveToNext()) {
			Reminder reminder = new Reminder();
            reminder.setId(cursor.getInt(0));
            reminder.setEventTitle(cursor.getString(1));
			try {
                reminder.setEventDate(formatter.parse(cursor.getString(2)));
			} catch (ParseException e) {
                reminder.setEventDate(null);
			}
            reminder.setEventDesc(cursor.getString(3));

            reminders.add(reminder);
		}
		return reminders;
	}

    //Retrieves a single reminder record with the given id
    public Reminder getReminder(long id) {
        Reminder reminder = null;

        String sql = "SELECT * FROM " + DataBaseHelper.REMINDER_TABLE
                + " WHERE " + DataBaseHelper.ID_COLUMN + " = ?";

        Cursor cursor = database.rawQuery(sql, new String[] { id + "" });

        if (cursor.moveToNext()) {
            reminder = new Reminder();
            reminder.setId(cursor.getInt(0));
            reminder.setEventTitle(cursor.getString(1));
            try {
                reminder.setEventDate(formatter.parse(cursor.getString(2)));
            } catch (ParseException e) {
                reminder.setEventDate(null);
            }
            reminder.setEventDesc(cursor.getString(3));
        }
        return reminder;
    }
}
