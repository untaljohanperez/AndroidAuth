package com.programacion.movil.estemanp.androidmvcapplication.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.programacion.movil.estemanp.androidmvcapplication.R;

/**
 * Created by lds on 22/08/2016.
 */
public class homeActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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



