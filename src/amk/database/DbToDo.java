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

	private String[] allColumns = { DbHelper.COLUMN_ID, DbHelper.COLUMN_NAME, DbHelper.COLUMN_STATUS, DbHelper.COLUMN_ADDED, DbHelper.COLUMN_BACKLOG };

	public List<ToDo> getAllToDos() {
		List<ToDo> ToDos = new ArrayList<ToDo>();
		Cursor cursor = database.query(DbHelper.TABLE_TODO, allColumns, null, null, null, null, null);

		handleTodoWithCursor(ToDos, cursor);
		return ToDos;
	}
	
	public List<ToDo> getToDosByBackLog(int backlog) {
		String[] whereIds = { String.valueOf(backlog) };
		
		List<ToDo> ToDos = new ArrayList<ToDo>();
		Cursor cursor = database.query(DbHelper.TABLE_TODO, allColumns, DbHelper.COLUMN_STATUS + " = 0 AND " + DbHelper.COLUMN_BACKLOG + " = ?", whereIds, null, null, null);

		handleTodoWithCursor(ToDos, cursor);
		
		return ToDos;
	}

	private void handleTodoWithCursor(List<ToDo> ToDos, Cursor cursor) {
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			ToDo ToDo = cursorToToDo(cursor);
			ToDos.add(ToDo);
			cursor.moveToNext();
		}
		cursor.close();
	}
	
	private ToDo cursorToToDo(Cursor cursor) {
		ToDo t = new ToDo();
		t.setId(cursor.getInt(0));
		t.setName(cursor.getString(1));
		t.setDone((cursor.getInt(2) == 1));
		t.setCreatedDate(cursor.getString(3));
		t.setBackLog(cursor.getInt(4));
		return t;
	}

	public void addToDo(ToDo t) {		
		ContentValues values = new ContentValues();
		values.put(DbHelper.COLUMN_NAME, t.getName());
		values.put(DbHelper.COLUMN_BACKLOG, t.getBackLog());
		values.put(DbHelper.COLUMN_STATUS, (t.isDone() ? 1 : 0));

		database.insert(DbHelper.TABLE_TODO, null, values);
	}
	
	public void updateToDo(ToDo t) {
		ContentValues values = new ContentValues();
		values.put(DbHelper.COLUMN_STATUS, (t.isDone() ? 1 : 0));
		
		database.update(DbHelper.TABLE_TODO, values, DbHelper.COLUMN_ID + "=" + t.getId(), null);
	}

	public void addToDoToCategory(String toDoText, int currentCategory) {
		ToDo t = new ToDo();
		t.setName(toDoText);
		t.setBackLog(currentCategory);
		t.setDone(false);
		
		addToDo(t);
	}
}



