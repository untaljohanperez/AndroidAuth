package co.com.moviesathome.DataContract;

import android.provider.BaseColumns;

/**
 * Created by JohanPerez on 20/09/2016.
 */
public class UserContract {
    public static abstract class UserEntry implements BaseColumns {
        public static final String USERS_TABLE_NAME = "users";

        public static final String USERS_COLUMN_ID = "id";
        public static final String USERS_COLUMN_NAME = "name";
        public static final String USERS_COLUMN_LASTNAME = "lastName";
        public static final String USERS_COLUMN_USERNAME = "userName";
        public static final String USERS_COLUMN_PASSWORD = "password";
        public static final String USERS_COLUMN_AGE = "age";
    }
}