package co.com.moviesathome.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.programacion.movil.estemanp.moviesathome.R;

import co.com.moviesathome.Controller.ApplicationController;
import co.com.moviesathome.Domain.User;
import co.com.moviesathome.Services.UserRepository;
import co.com.moviesathome.Util.DBHelper;

public class LoginActivity extends AppCompatActivity {

    ApplicationController appController;
    DBHelper dbHelper;
    UserRepository userRepository;
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
    }

    public void login(View view) {
        final User user = userRepository.isValidUser(userName.getText().toString(),password.getText().toString());
        if(user != null && user.getId() > 0) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.welcome) + " " + user.getName())
                    .setTitle(R.string.application_name);

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
            });
            builder.create().show();
        }else{
            Toast.makeText(this, R.string.fail_login, Toast.LENGTH_SHORT).show();
            password.setText("");
            userName.requestFocus();
        }
    }
}
