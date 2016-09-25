package co.com.moviesathome.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.com.moviesathome.Controller.ApplicationController;
import co.com.moviesathome.Domain.Pelicula;
import co.com.moviesathome.Domain.User;
import co.com.moviesathome.R;
import co.com.moviesathome.Services.PeliculaRepository;
import co.com.moviesathome.Services.UserRepository;
import co.com.moviesathome.Util.DBHelper;

public class LoginActivity extends AppCompatActivity {

    ApplicationController appController;
    DBHelper dbHelper;
    UserRepository userRepository;
    PeliculaRepository peliculaRepository;
    EditText userName;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (android.widget.EditText) this.findViewById(R.id.editUsername);
        userName.requestFocus();
        password = (android.widget.EditText) this.findViewById(R.id.editPassword);
        appController = (ApplicationController) getApplication();
        dbHelper = new DBHelper(getApplicationContext());
        userRepository = new UserRepository(dbHelper);
        peliculaRepository = new PeliculaRepository(dbHelper);
    }

    public void login(View view) {
        final User user = userRepository.isValidUser(userName.getText().toString(),password.getText().toString());
        if(user != null && user.getId() > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.welcome) + " " + user.getName())
                    .setTitle(R.string.application_name);

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(getApplicationContext(), PeliculasActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });

            mockData();

            builder.create().show();
        }else{
            Toast.makeText(this, R.string.fail_login, Toast.LENGTH_SHORT).show();
            password.setText("");
            userName.requestFocus();
        }
    }

    private void mockData() {
        if(peliculaRepository.getAllPeliculas().size() != 0) return;

        mockPeliculas(new Pelicula("The Avengers", "142 min", "es una película estadounidense de superhéroes de 2012 escrita y dirigida por Joss Whedon. Fue producida por Marvel Studios y distribuida por Walt Disney Pictures, y basada en el cómic homónimo de Marvel Comics. ",
                5.0,"avenger.jpg"));
        mockPeliculas(new Pelicula("Los Croods", "98 min","Los Croods es una película estadounidense de animación de aventura y comedia producida por los estudios Dreamworks Animation y distribuida por 20th Century Fox, de hecho, la primera con este tipo de alianza",
                3.5,
                "croods.jpg"));
        mockPeliculas(new Pelicula("El Conjuro", "112 min",
                "The Conjuring (también conocida como The Warren Files, titulada Expediente Warren en España y El conjuro en Hispanoamérica) es una película de terror de 2013 dirigida por James Wan y protagonizada por Vera Farmiga y Patrick Wilson en el papel de los parapsicólogos Lorraine y Ed Warren.",
                4.0,
                "conjuro.jpg"));
    }
    public boolean mockPeliculas(Pelicula pelicula) {
        try {
            return peliculaRepository.insertPelicula(pelicula);
        }catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }

}
