package co.com.moviesathome.Controller;

import android.app.Application;

import co.com.moviesathome.Domain.User;
import co.com.moviesathome.Services.UserRepository;
import co.com.moviesathome.Util.DBHelper;

/**
 * Created by estemanp on 19/08/16.
 */
public class ApplicationController extends Application {

    public  ApplicationController(){
        fillDateBases();
    }

    private void fillDateBases(){
        fillUsers();
    }

    private void fillUsers(){

    }

}
