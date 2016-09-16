package co.com.moviesathome.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.programacion.movil.estemanp.moviesathome.R;

import co.com.moviesathome.Services.UserRepository;
import co.com.moviesathome.Util.DBHelper;

/**
 * Created by lds on 22/08/2016.
 */
public class homeActivity extends AppCompatActivity{

    DBHelper dbHelper;
    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        dbHelper = new DBHelper(getApplicationContext());
        userRepository = new UserRepository(dbHelper);
    }

    public void singIn(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void singUpp(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}



