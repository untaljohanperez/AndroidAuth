package co.com.moviesathome.Services;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

import co.com.moviesathome.Domain.User;
import co.com.moviesathome.Util.DBHelper;

/**
 * Created by kedwin.perez on 16/09/2016.
 */
public class UserRepository {
    DBHelper dbHelper;

    public UserRepository(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public boolean insertUser(User user)
    {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(dbHelper.USERS_COLUMN_NAME, user.getName());
            contentValues.put(dbHelper.USERS_COLUMN_LASTNAME, user.getLastName());
            contentValues.put(dbHelper.USERS_COLUMN_USERNAME, user.getUserName());
            contentValues.put(dbHelper.USERS_COLUMN_PASSWORD, user.getPassword());
            contentValues.put(dbHelper.USERS_COLUMN_AGE, user.getAge());
            long a = dbHelper.db.insert(dbHelper.USERS_TABLE_NAME, null, contentValues);

            User user2 = getUserByUserName(user.getUserName());
            return true;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    public User getUserByUserName(String userName){
        User user = null;
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + dbHelper.USERS_TABLE_NAME
                            + " WHERE " + dbHelper.USERS_COLUMN_USERNAME + " = '" + userName + "'"
                    , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                user = mapUser(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return user;
    }

    public User isValidUser(String userName, String password){
        User user = null;
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + dbHelper.USERS_TABLE_NAME
                            + " WHERE " + dbHelper.USERS_COLUMN_USERNAME + " = '" + userName + "'"
                            + " AND " + dbHelper.USERS_COLUMN_PASSWORD + " = '" + password + "'"
                    , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                user = mapUser(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + dbHelper.USERS_TABLE_NAME
                            + " ORDER BY " + DBHelper.USERS_COLUMN_NAME
                            , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                users = mapUsers(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return users;
    }

    private User mapUser(Cursor query){
        User user = new User();
        try {
            user.setId(query.getInt(query.getColumnIndex(dbHelper.USERS_COLUMN_ID)));
            user.setName(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_NAME)));
            user.setLastName(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_LASTNAME)));
            user.setUserName(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_USERNAME)));
            user.setPassword(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_PASSWORD)));
            user.setAge(query.getInt(query.getColumnIndex(dbHelper.USERS_COLUMN_AGE)));
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return user;
    }

    private List<User> mapUsers(Cursor query) {
        List<User> users = new ArrayList<User>();
        for(query.moveToFirst(); !query.isAfterLast(); query.moveToNext()){
            User user = new User();
            user.setId(query.getInt(query.getColumnIndex(dbHelper.USERS_COLUMN_ID)));
            user.setName(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_NAME)));
            user.setLastName(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_LASTNAME)));
            user.setUserName(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_USERNAME)));
            user.setPassword(query.getString(query.getColumnIndex(dbHelper.USERS_COLUMN_PASSWORD)));
            user.setAge(query.getInt(query.getColumnIndex(dbHelper.USERS_COLUMN_AGE)));
            users.add(user);
        }
        return users;
    }
}
