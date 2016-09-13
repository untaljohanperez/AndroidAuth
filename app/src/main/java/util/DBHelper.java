package util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.programacion.movil.estemanp.androidmvcapplication.Domain.User;

import java.util.HashMap;

/**
 * Created by lds on 12/09/2016.
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
    private HashMap hp;

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE users " +
                        "(id integer primary key AUTOINCREMENT, name text, lastName text, userName text unique, password text, age integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean insertUser(User user)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(USERS_COLUMN_NAME, user.getName());
            contentValues.put(USERS_COLUMN_LASTNAME, user.getLastName());
            contentValues.put(USERS_COLUMN_USERNAME, user.getUserName());
            contentValues.put(USERS_COLUMN_PASSWORD, user.getPassword());
            contentValues.put(USERS_COLUMN_AGE, user.getAge());
            db.insert(USERS_TABLE_NAME, null, contentValues);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public User getUserData(String userName){
        User user = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery( "SELECT * FROM " + USERS_COLUMN_NAME + " where " + USERS_COLUMN_USERNAME + " = " + userName , null );
            res.moveToFirst();

            if(res.getCount() > 0){
                user.setId(res.getInt(res.getColumnIndex(USERS_COLUMN_ID)));
                user.setName(res.getString(res.getColumnIndex(USERS_COLUMN_NAME)));
                user.setLastName(res.getString(res.getColumnIndex(USERS_COLUMN_LASTNAME)));
                user.setUserName(res.getString(res.getColumnIndex(USERS_COLUMN_USERNAME)));
                user.setPassword(res.getString(res.getColumnIndex(USERS_COLUMN_PASSWORD)));
                user.setAge(res.getInt(res.getColumnIndex(USERS_COLUMN_AGE)));
            }
        }catch(Exception e){

        }
        return user;
    }
}
