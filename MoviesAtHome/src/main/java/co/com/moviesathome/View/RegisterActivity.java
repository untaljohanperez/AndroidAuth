package co.com.moviesathome.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.programacion.movil.estemanp.moviesathome.R;

import co.com.moviesathome.Controller.ApplicationController;
import co.com.moviesathome.Controller.ApplicationControllerSingleton;
import co.com.moviesathome.Domain.User;
import co.com.moviesathome.Services.UserRepository;
import co.com.moviesathome.Util.DBHelper;

/**
 * Created by UnTalJohanPerez on 22/08/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    DBHelper dbHelper;
    UserRepository userRepository;
    ApplicationController appController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        appController = (ApplicationController) getApplication();
        dbHelper = new DBHelper(getApplicationContext());
        userRepository = new UserRepository(dbHelper);
    }

    public void singUp(View view){

        EditText nombre = (android.widget.EditText) this.findViewById(R.id.editNombre);
        nombre.requestFocus();
        EditText apellido = (android.widget.EditText) this.findViewById(R.id.editApellido);
        EditText edad = (android.widget.EditText) this.findViewById(R.id.editEdad);
        EditText userName = (android.widget.EditText) this.findViewById(R.id.editUsuario);
        EditText password1 = (android.widget.EditText) this.findViewById(R.id.editPassword1);
        EditText password2 = (android.widget.EditText) this.findViewById(R.id.editPassword2);

        if (!password1.getText().toString().equals(password2.getText().toString())){
            Toast.makeText(this, R.string.incorrect_passwords, Toast.LENGTH_SHORT).show();
            password1.setText("");
            password2.setText("");
            password1.requestFocus();
            return;
        }

        if(userRepository.getUserByUserName(userName.getText().toString()) != null){
            Toast.makeText(this, R.string.username_no_valid, Toast.LENGTH_SHORT).show();
            userName.setText("");
            userName.requestFocus();
            return;
        }

        User newUser = new User(nombre.getText().toString(),apellido.getText().toString()
                                ,userName.getText().toString(),password2.getText().toString(), Integer.parseInt(edad.getText().toString()));


        if (userRepository.insertUser(newUser)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(R.string.success_registry)
                    .setTitle(R.string.application_name);

            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            });

            builder.create().show();
        }else{
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
        }
    }
}
