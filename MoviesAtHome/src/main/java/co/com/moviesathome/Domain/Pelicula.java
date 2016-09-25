package co.com.moviesathome.Domain;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;


/**
 * Created by Juanes on 15/09/2016.
 */
public class Pelicula {

    private int id;
    private String name;
    private String duracion;
    private String sinopsis;
    private double ranking;
    private String avatarUri;

    public Pelicula(String name,
                    String duracion, String sinopsis,
                    double ranking, String avatarUri) {
        this.name = name;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.ranking = ranking;
        this.avatarUri = avatarUri;
    }

    public Pelicula(){}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getSinopsis(){
        return sinopsis;
    }

    public double getRanking() {
        return ranking;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }
}
