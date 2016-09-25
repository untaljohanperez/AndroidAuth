package co.com.moviesathome.Services;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.com.moviesathome.DataContract.PeliculaContract;
import co.com.moviesathome.Domain.Pelicula;
import co.com.moviesathome.Domain.User;
import co.com.moviesathome.Util.DBHelper;
import static co.com.moviesathome.DataContract.PeliculaContract.PeliculaEntry.*;
import static co.com.moviesathome.DataContract.PeliculaContract.PeliculaEntry.PELICULAS_DURACION;
import static co.com.moviesathome.DataContract.UserContract.UserEntry.USERS_COLUMN_USERNAME;
import static co.com.moviesathome.DataContract.UserContract.UserEntry.USERS_TABLE_NAME;

/**
 * Created by kedwin.perez on 16/09/2016.
 */
public class PeliculaRepository {
    DBHelper dbHelper;

    public PeliculaRepository(DBHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    public boolean insertPelicula(Pelicula pelicula)
    {
        try {

            ContentValues contentValues = new ContentValues();
            contentValues.put(PELICULAS_ID_ID, UUID.randomUUID().toString());
            contentValues.put(PELICULAS_NAME, pelicula.getName());
            contentValues.put(PELICULAS_DURACION, pelicula.getDuracion());
            contentValues.put(PELICULAS_SINOPSIS, pelicula.getSinopsis());
            contentValues.put(PELICULAS_AVATAR_URI, pelicula.getAvatarUri());
            contentValues.put(PELICULAS_RANKING, pelicula.getAvatarUri());
            long a = dbHelper.db.insert(PELICULAS_TABLE_NAME, null, contentValues);
            List<Pelicula> p = getAllPeliculas();
            return true;
        }catch(Exception e){
            System.out.println(e.toString());
            return false;
        }
    }

    public Pelicula getPeliculaByName(String peliculaName){
        Pelicula pelicula = null;
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + PELICULAS_TABLE_NAME
                            + " WHERE " + PELICULAS_NAME + " = '" + peliculaName + "'"
                    , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                pelicula = mapPelicula(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return pelicula;
    }

    public Cursor getAllPeliculasCursor() {
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + PELICULAS_TABLE_NAME
                            + " ORDER BY " + PELICULAS_NAME
                    , null );
            query.moveToFirst();
            return query;
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    public List<Pelicula> getAllPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + PELICULAS_TABLE_NAME
                            + " ORDER BY " + PELICULAS_NAME
                            , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                peliculas = mapPeliculas(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return peliculas;
    }

    public Pelicula getPeliculaById(int peliculaId){
        try {
            Cursor query =  dbHelper.db.rawQuery("SELECT * FROM "
                            + PELICULAS_TABLE_NAME
                            + " WHERE " + PELICULAS_ID + " = " + peliculaId
                    , null );
            query.moveToFirst();

            if(query.getCount() > 0){
                return mapPelicula(query);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return null;
    }

    private Pelicula mapPelicula(Cursor query){
        Pelicula pelicula = new Pelicula();
        try {
            pelicula.setId(query.getInt(query.getColumnIndex(PELICULAS_ID)));
            pelicula.setDuracion(query.getString(query.getColumnIndex(PELICULAS_DURACION)));
            pelicula.setSinopsis(query.getString(query.getColumnIndex(PELICULAS_SINOPSIS)));
            pelicula.setRanking(query.getString(query.getColumnIndex(PELICULAS_RANKING)));
            pelicula.setAvatarUri(query.getString(query.getColumnIndex(PELICULAS_AVATAR_URI)));
        }catch(Exception e){
            System.out.println(e.toString());
        }
        return pelicula;
    }

    private List<Pelicula> mapPeliculas(Cursor query) {
        List<Pelicula> peliculas = new ArrayList<>();
        for(query.moveToFirst(); !query.isAfterLast(); query.moveToNext()){
            peliculas.add(mapPelicula(query));
        }
        return peliculas;
    }
}
