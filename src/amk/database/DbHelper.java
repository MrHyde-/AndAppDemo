package amk.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "db.akBaDb";

	public static final String TABLE_BACKLOG = "BACKLOG";
	public static final String TABLE_TODO = "TODO";

	public static final String COLUMN_ID = "id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_BACKLOG = "backlogid";
	public static final String COLUMN_STATUS = "status";
	public static final String COLUMN_ADDED = "createdDate";

	private static final int DATABASE_VERSION = 1;

	public static final String CREATE_TABLEBACKLOG = "create table "
			+ TABLE_BACKLOG + "(" + COLUMN_ID
			+ " integer primary key autoincrement," + COLUMN_NAME + " text not null);";

	public static final String CREATE_TABLETODOS = "create table "
			+ TABLE_TODO + "(" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_NAME
			+ " text not null, " + COLUMN_BACKLOG
			+ " integer not null, " + COLUMN_STATUS
			+ " integer not null, " + COLUMN_ADDED
			+ " datetime default current_timestamp);";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLEBACKLOG);
		db.execSQL(CREATE_TABLETODOS);
		
		//add two sample groups
		ContentValues values = new ContentValues();
		values.put(DbHelper.COLUMN_NAME, "Work");
		db.insert(DbHelper.TABLE_BACKLOG, null, values);
		
		ContentValues values2 = new ContentValues();
		values2.put(DbHelper.COLUMN_NAME, "Home");
		db.insert(DbHelper.TABLE_BACKLOG, null, values2);
		
		ContentValues values3 = new ContentValues();
		values3.put(DbHelper.COLUMN_NAME, "Sample");
		values3.put(DbHelper.COLUMN_BACKLOG, 1);
		values3.put(DbHelper.COLUMN_STATUS, 0);

		//for test this should be set as 1 after 1 minute it is week old
		db.insert(DbHelper.TABLE_TODO, null, values3);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DbHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BACKLOG);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
		onCreate(db);

	}

}
