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
import android.widget.RatingBar;
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
    private RatingBar mRatingBar;
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
        mRatingBar = (RatingBar) root.findViewById(R.id.ratingBar);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                if(fromUser){
                    if(peliculaRepository.updateRating(mPeliculasId ,rating)){
                        Toast.makeText(getContext(), String.valueOf(rating), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

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

    private void showPeliculas(Pelicula pelicula) {
        mCollapsingView.setTitle(pelicula.getName());
        Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + pelicula.getAvatarUri()))
                .centerCrop()
                .into(mAvatar);
        mDuracion.setText(pelicula.getDuracion());
        mSinopsis.setText(pelicula.getSinopsis());
        mRatingBar.setRating((float)pelicula.getRanking());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

    }
}
