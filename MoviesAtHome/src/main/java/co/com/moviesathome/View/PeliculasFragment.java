package co.com.moviesathome.View;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import co.com.moviesathome.DataContract.PeliculaContract;
import co.com.moviesathome.R;
import co.com.moviesathome.Services.PeliculaRepository;
import co.com.moviesathome.Util.DBHelper;
import co.com.moviesathome.Util.PeliculasCursorAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PeliculasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PeliculasFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_PELICULAS = 2;
    private DBHelper dbHelper;
    private PeliculaRepository peliculaRepository;
    private ListView mPeliculasList;
    private PeliculasCursorAdapter mPeliculasAdapter;
    private FloatingActionButton mAddButton;

    public PeliculasFragment() {

    }

    public static PeliculasFragment newInstance() {
        return new PeliculasFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_peliculas, container, false);

        mPeliculasList = (ListView) root.findViewById(R.id.lawyers_list);
        mPeliculasAdapter = new PeliculasCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton)getActivity().findViewById(R.id.fab);

        mPeliculasList.setAdapter(mPeliculasAdapter);

        mPeliculasList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mPeliculasAdapter.getItem(i);
                int currentPeliculaId = currentItem.getInt(
                        currentItem.getColumnIndex(PeliculaContract.PeliculaEntry.PELICULAS_ID));

                showDetailScreen(currentPeliculaId);
            }
        });

        dbHelper = new DBHelper(getContext());
        peliculaRepository = new PeliculaRepository(dbHelper);

        loadPeliculas();

        return root;
    }

    private void showDetailScreen(int peliculasId) {
        Intent intent = new Intent(getActivity(), PeliculasDetailActivity.class);
        intent.putExtra(PeliculasActivity.EXTRA_LAWYER_ID, peliculasId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_PELICULAS);
    }

    private void loadPeliculas() {
        new PeliculasLoasTask().execute();
    }

    private class PeliculasLoasTask extends AsyncTask<Void, Void, Cursor>
    {
        protected Cursor doInBackground(Void... voids)
        {
            return peliculaRepository.getAllPeliculasCursor();
        }

        protected void onPostExecute(Cursor cursor)
        {
            if(cursor != null && cursor.getCount() > 0)
            {
                mPeliculasAdapter.swapCursor(cursor);
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case REQUEST_UPDATE_DELETE_PELICULAS:
                    loadPeliculas();
                    break;
            }
        }
    }
}
