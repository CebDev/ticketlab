package dti.g25.tp1.presentation.vue.vueJalon;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratJalon.ContratVuePresenteurJalonListe;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

/**
 * Vue qui permet l'entré et la sortie de données pour l'affichage d'un Jalon
 * @author Philippe Joubarne
 * @since 07/03/2020
 */

public class VueAfficherJalon extends VueBase implements ContratVuePresenteurJalonListe.IVueAfficherJalon {

    private ContratVuePresenteurJalonListe.IPresenteurAfficherJalon _presenteur;
    private RecyclerView rvListe;
    private RecyclerView.Adapter adapter;
    private Button btnNouveauJalon;

    public void setPresenteur(ContratVuePresenteurJalonListe.IPresenteurAfficherJalon presenteurAfficherJalon) {
        _presenteur = presenteurAfficherJalon; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        final View racine = inflater.inflate(R.layout.fragment_afficher_jalon, container, false);
        super.onCreateView(inflater, container, savedInstanceState, racine, (PresenteurBase) _presenteur);

        rvListe = racine.findViewById(R.id.rvListeJalon);
        rvListe.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new JalonAdapter(_presenteur);
        rvListe.setAdapter(adapter);

        racine.findViewById(R.id.btnNouveauJalon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.ajouter();
            }
        });

        return racine;

    }

    @Override
    public void rafraichir() {
        if(adapter!=null)
            adapter.notifyDataSetChanged();
    }

}
