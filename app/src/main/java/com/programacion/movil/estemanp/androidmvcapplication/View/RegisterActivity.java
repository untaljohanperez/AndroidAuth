package com.programacion.movil.estemanp.androidmvcapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.programacion.movil.estemanp.androidmvcapplication.Controller.ApplicationControllerSingleton;
import com.programacion.movil.estemanp.androidmvcapplication.Domain.User;
import com.programacion.movil.estemanp.androidmvcapplication.R;

/**
 * Created by UnTalJohanPerez on 22/08/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void singUp(View view){

        EditText nombre = (android.widget.EditText) this.findViewById(R.id.editNombre);
        EditText apellido = (android.widget.EditText) this.findViewById(R.id.editApellido);
        EditText edad = (android.widget.EditText) this.findViewById(R.id.editEdad);
        EditText userName = (android.widget.EditText) this.findViewById(R.id.editUsuario);
        EditText password1 = (android.widget.EditText) this.findViewById(R.id.editPassword1);
        EditText password2 = (android.widget.EditText) this.findViewById(R.id.editPassword2);

        TextView message = (android.widget.TextView) this.findViewById(R.id.txtMessage);

        if (!password1.getText().toString().equals(password2.getText().toString())){
            message.setText("Contraseñas incorrectas");
            return;
        }

        for(User user : ApplicationControllerSingleton.getInstance().getUsers()){
            if(user.getName().toString().equals(userName.getText().toString())){
                message.setText("Usuario no valido");
                return;
            }
        }

        User newUser = new User(nombre.getText().toString(),apellido.getText().toString()
                                ,userName.getText().toString(),password2.getText().toString(), Integer.parseInt(edad.getText().toString()));

        if (ApplicationControllerSingleton.getInstance().addUser(newUser)){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
