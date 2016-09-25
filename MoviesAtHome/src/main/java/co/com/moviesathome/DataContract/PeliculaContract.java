package co.com.moviesathome.DataContract;

import android.provider.BaseColumns;
/**
 * Created by Juanes on 15/09/2016.
 */
public class PeliculaContract {
    public static abstract class PeliculaEntry implements BaseColumns {
        public static final String PELICULAS_TABLE_NAME ="peliculas";

        public static final String PELICULAS_ID_ID = "_id";
        public static final String PELICULAS_ID = "id";
        public static final String PELICULAS_NAME = "name";
        public static final String PELICULAS_DURACION = "duracion";
        public static final String PELICULAS_SINOPSIS = "sinopsis";
        public static final String PELICULAS_AVATAR_URI = "avatarUri";
        public static final String PELICULAS_RANKING = "ranking";
    }
}