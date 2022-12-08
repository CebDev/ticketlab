package dti.g25.tp1.presentation.vue.vueTableau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauAjouter;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

public class VueTableauAjouter extends VueBase implements ContratVuePresenteurTableauAjouter.IVueTableauAjouter {
    private ContratVuePresenteurTableauAjouter.IPresenteurTableauAjouter presenteur;
    private TextView nouveauTableauTitre;
    private EditText titre;
    private TextView projetTitre;
    private Button ajouter;
    private Button annuler;

    public void set_presenteur (ContratVuePresenteurTableauAjouter.IPresenteurTableauAjouter presenteur){
        this.presenteur = presenteur;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_formulaire_tableau, container, false);
        super.onCreateView(inflater, container, savedInstanceState, view, (PresenteurBase) presenteur);
        nouveauTableauTitre = view.findViewById(R.id.nouveauTableauTitre);
        titre = view.findViewById(R.id.titre);
        projetTitre = view.findViewById(R.id.projetTitre);
        ajouter = view.findViewById(R.id.ajouter);
        annuler = view.findViewById(R.id.annuler);
        projetTitre.setText(presenteur.getProjetTitre());

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenteur.ajouter(titre);
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenteur.annuler();
            }
        });

        return view;
    }
}
