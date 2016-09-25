package co.com.moviesathome.Util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static co.com.moviesathome.DataContract.UserContract.UserEntry.*;
import static co.com.moviesathome.DataContract.PeliculaContract.PeliculaEntry.*;

import java.util.HashMap;

import co.com.moviesathome.Domain.Pelicula;
import co.com.moviesathome.Services.PeliculaRepository;

/**
 * Created by UnTalJohanPerez on 12/09/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Movies.db";
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

        db.execSQL("CREATE TABLE " + PELICULAS_TABLE_NAME + " ("
                + PELICULAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PELICULAS_ID_ID + " TEXT NOT NULL,"
                + PELICULAS_NAME + " TEXT NOT NULL,"
                + PELICULAS_DURACION + " TEXT NOT NULL,"
                + PELICULAS_SINOPSIS + " TEXT NOT NULL,"
                + PELICULAS_RANKING + " TEXT NOT NULL,"
                + PELICULAS_AVATAR_URI + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PELICULAS_TABLE_NAME);
        onCreate(db);
    }

}
