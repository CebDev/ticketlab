package dti.g25.tp1.presentation.vue.vueListe;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratListe.ContratVuePresenteurListeNouveau;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

public class VueFormulaireListe extends VueBase implements ContratVuePresenteurListeNouveau.IVueListeNouveau{
    ContratVuePresenteurListeNouveau.IPresenteurListeNouveau _presenteur;
    RecyclerView etiquettes;;
    Button ajouter;
    TextView titreFormulaire;

    public void set_presenteur(ContratVuePresenteurListeNouveau.IPresenteurListeNouveau _presenteur){
        this._presenteur = _presenteur;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulaire_liste, container, false);
        super.onCreateView(inflater, container, savedInstanceState, view, (PresenteurBase) _presenteur);

        titreFormulaire = view.findViewById(R.id.titreFormulaire);
        titreFormulaire.setText(getResources().getString(R.string.gestion_liste_titre)+_presenteur.getTableauTitre());
        etiquettes = view.findViewById(R.id.etiquettes);
        EtiquetteCheckboxAdapter adapter = new EtiquetteCheckboxAdapter(_presenteur);
        etiquettes.setAdapter(adapter);
        etiquettes.setLayoutManager(new LinearLayoutManager(getContext()));
        ajouter = view.findViewById(R.id.ajouter);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.redirectionVueTableauListe();
            }
        });

        return view;
    }
}
