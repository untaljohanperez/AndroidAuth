package co.com.moviesathome.View;


import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import co.com.moviesathome.Domain.Pelicula;
import co.com.moviesathome.R;
import co.com.moviesathome.Services.PeliculaRepository;
import co.com.moviesathome.Util.DBHelper;

public class PeliculasDetailFragment extends Fragment {

    private static final String ARG_PELICULAS_ID = "peliculasId";

    private int mPeliculasId;
    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mSinopsis;
    private TextView mDuracion;
    private TextView mRanking;
    DBHelper dbHelper;
    PeliculaRepository peliculaRepository;


    public PeliculasDetailFragment() {
        // Required empty public constructor
    }


    public static PeliculasDetailFragment newInstance(int peliculasId) {
        PeliculasDetailFragment fragment = new PeliculasDetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PELICULAS_ID, peliculasId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPeliculasId = getArguments().getInt(ARG_PELICULAS_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_peliculas_detail, container, false);
        mCollapsingView = (CollapsingToolbarLayout)getActivity().findViewById(R.id.toolbar_layout);
        mAvatar = (ImageView)getActivity().findViewById(R.id.iv_avatar);
        mSinopsis = (TextView)root.findViewById(R.id.tv_sinopsis);
        mDuracion = (TextView)root.findViewById(R.id.tv_duracion);
        mRanking = (TextView)root.findViewById(R.id.tv_rankig);

        dbHelper = new DBHelper(getContext());
        peliculaRepository = new PeliculaRepository(dbHelper);
        loadPeliculas();
        return root;
    }

    private void loadPeliculas() {
        new GetPeliculasByIdTask().execute();
    }

    private class GetPeliculasByIdTask extends AsyncTask<Void, Void, Pelicula> {


        protected Pelicula doInBackground(Void... voids) {
            return peliculaRepository.getPeliculaById(mPeliculasId);
        }

        protected void onPostExecute(Pelicula pelicula) {
            if (pelicula != null) {
                showPeliculas(pelicula);
            } else {
                showLoadError();
            }
        }

    }

    private void showPeliculas(Pelicula peliculas) {
        mCollapsingView.setTitle(peliculas.getName());
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + peliculas.getAvatarUri()))
                .centerCrop()
                .into(mAvatar);
        mDuracion.setText(peliculas.getDuracion());
        mSinopsis.setText(peliculas.getSinopsis());
        mRanking.setText(peliculas.getRanking());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }
}
