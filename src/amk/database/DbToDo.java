package amk.database;

import java.util.ArrayList;
import java.util.List;

import amk.classes.ToDo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class DbToDo extends AkBaDb {
	public DbToDo(Context context) {
		super(context);
	}

	private String[] allColumns = { DbHelper.COLUMN_ID, DbHelper.COLUMN_NAME, DbHelper.COLUMN_STATUS, DbHelper.COLUMN_ADDED };

	public List<ToDo> getAllToDos() {
		List<ToDo> ToDos = new ArrayList<ToDo>();
		Cursor cursor = database.query(DbHelper.TABLE_TODO, allColumns, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ToDo ToDo = cursorToToDo(cursor);
			ToDos.add(ToDo);
			cursor.moveToNext();
		}
		cursor.close();
		return ToDos;
	}

	private ToDo cursorToToDo(Cursor cursor) {
		ToDo t = new ToDo();
		t.setId(cursor.getInt(0));
		t.setName(cursor.getString(1));
		return t;
	}

	public void addToDo(ToDo t) {		
		ContentValues values = new ContentValues();
		values.put(DbHelper.COLUMN_NAME, t.getName());

		database.insert(DbHelper.TABLE_TODO, null, values);
	}
}
