package amk.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AkBaDb {
	public SQLiteDatabase database;
	private DbHelper dbHelper;

	public AkBaDb(Context context) {
		dbHelper = new DbHelper(context);
	}

	public void open() {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
}
