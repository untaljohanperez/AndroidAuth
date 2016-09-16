package co.com.moviesathome.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by UnTalJohanPerez on 12/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Movies.db";

    public static final String USERS_TABLE_NAME = "users";
    public static final String USERS_COLUMN_ID = "id";
    public static final String USERS_COLUMN_NAME = "name";
    public static final String USERS_COLUMN_LASTNAME = "lastName";
    public static final String USERS_COLUMN_USERNAME = "userName";
    public static final String USERS_COLUMN_PASSWORD = "password";
    public static final String USERS_COLUMN_AGE = "age";
    public SQLiteDatabase db;
    private HashMap hp;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE "
                     + USERS_TABLE_NAME
                     +  "("
                    + USERS_COLUMN_ID + " integer primary key AUTOINCREMENT, "
                    + USERS_COLUMN_NAME + " text, "
                    + USERS_COLUMN_LASTNAME + " text, "
                    + USERS_COLUMN_USERNAME + " text unique, "
                    + USERS_COLUMN_PASSWORD + " text, "
                    + USERS_COLUMN_AGE + " integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

}
