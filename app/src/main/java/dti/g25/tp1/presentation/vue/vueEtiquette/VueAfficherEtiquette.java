package dti.g25.tp1.presentation.vue.vueEtiquette;


import android.content.Intent;
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
import dti.g25.tp1.presentation.contratEtiquette.ContratVuePresenteurEtiquetteListe;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;
import dti.g25.tp1.ui.activite.ActiviteAjouterEtiquette;


/**
 * Vue qui permet l'entré et la sortie de données pour l'affichage d'un Etiquette
 * @author Philippe Joubarne
 * @since 07/03/2020
 */
public class VueAfficherEtiquette extends VueBase implements ContratVuePresenteurEtiquetteListe.IVueAfficherEtiquette {

    private ContratVuePresenteurEtiquetteListe.IPresenteurAfficherEtiquette _presenteur;
    private RecyclerView rvListe;
    private RecyclerView.Adapter adapter;
    private Button btnNouvelleEtiquette;


    public void setPresenteur(ContratVuePresenteurEtiquetteListe.IPresenteurAfficherEtiquette presenteurAfficherEtiquette) {
        _presenteur = presenteurAfficherEtiquette; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View racine = inflater.inflate(R.layout.fragment_afficher_etiquette, container, false);
        super.onCreateView(inflater, container, savedInstanceState, racine, (PresenteurBase) _presenteur);

        rvListe = racine.findViewById(R.id.rvListeEtiquettes);
        rvListe.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new EtiquetteAdapter(_presenteur);
        rvListe.setAdapter(adapter);

        racine.findViewById(R.id.btnNouvelleEtiquette).setOnClickListener(new View.OnClickListener() {
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
