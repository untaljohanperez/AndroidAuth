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
                + PELICULAS_NAME + " TEXT NOT NULL,"
                + PELICULAS_DURACION + " TEXT NOT NULL,"
                + PELICULAS_SINOPSIS + " TEXT NOT NULL,"
                + PELICULAS_RANKING + " TEXT NOT NULL,"
                + PELICULAS_AVATAR_URI + " TEXT )");

        mockData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PELICULAS_TABLE_NAME);
        onCreate(db);
    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockPeliculas(new Pelicula("The Avengers", "142 min", "es una película estadounidense de superhéroes de 2012 escrita y dirigida por Joss Whedon. Fue producida por Marvel Studios y distribuida por Walt Disney Pictures, y basada en el cómic homónimo de Marvel Comics. ",
                "5","avenger.jpg"));
        mockPeliculas(new Pelicula("Los Croods", "98 min","Los Croods es una película estadounidense de animación de aventura y comedia producida por los estudios Dreamworks Animation y distribuida por 20th Century Fox, de hecho, la primera con este tipo de alianza",
                "3",
                "croods.jpg"));
        mockPeliculas(new Pelicula("El Conjuro", "112 min",
                "The Conjuring (también conocida como The Warren Files, titulada Expediente Warren en España y El conjuro en Hispanoamérica) es una película de terror de 2013 dirigida por James Wan y protagonizada por Vera Farmiga y Patrick Wilson en el papel de los parapsicólogos Lorraine y Ed Warren.",
                "4",
                "conjuro.jpg"));
    }
    public boolean mockPeliculas(Pelicula pelicula) {
        try {
            PeliculaRepository peliculaRepository = new PeliculaRepository(this);
            return peliculaRepository.insertPelicula(pelicula);
        }catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }



}
