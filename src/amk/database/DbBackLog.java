package amk.database;

import java.util.ArrayList;
import java.util.List;

import amk.classes.BackLog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DbBackLog extends AkBaDb {

	public DbBackLog(Context context) {
		super(context);
	}

	private String[] allColumns = { DbHelper.COLUMN_ID, DbHelper.COLUMN_NAME };

	public List<BackLog> getAllBacklogs() {
		List<BackLog> backlogs = new ArrayList<BackLog>();
		Cursor cursor = database.query(DbHelper.TABLE_BACKLOG, allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			BackLog backLog = cursorToBacklog(cursor);
			backlogs.add(backLog);
			cursor.moveToNext();
		}
		cursor.close();
		return backlogs;
	}

	private BackLog cursorToBacklog(Cursor cursor) {
		BackLog b = new BackLog();
		b.setId(cursor.getInt(0));
		b.setName(cursor.getString(1));
		return b;
	}

	public void addBackLog(BackLog b) {		
		ContentValues values = new ContentValues();
		values.put(DbHelper.COLUMN_NAME, b.getName());

		database.insert(DbHelper.TABLE_BACKLOG, null, values);
	}

	public BackLog getBacklogById(int rating) {
		// TODO MAKE IT MEAN SOMETHING :D
		return getAllBacklogs().get(rating - 1);
	}

}
