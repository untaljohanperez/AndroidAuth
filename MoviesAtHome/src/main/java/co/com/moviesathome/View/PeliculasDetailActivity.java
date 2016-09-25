package co.com.moviesathome.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import co.com.moviesathome.R;

public class PeliculasDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int id = getIntent().getIntExtra(PeliculasActivity.EXTRA_LAWYER_ID, 0);

        PeliculasDetailFragment fragment = (PeliculasDetailFragment)getSupportFragmentManager().findFragmentById(R.id.peliculas_detail_container);
        if(fragment == null)
        {
            fragment = PeliculasDetailFragment.newInstance(id);
            getSupportFragmentManager().beginTransaction().add(R.id.peliculas_detail_container, fragment).commit();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_peliculas_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return true;
    }
}

