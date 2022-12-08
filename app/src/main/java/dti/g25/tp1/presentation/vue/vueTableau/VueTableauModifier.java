package dti.g25.tp1.presentation.vue.vueTableau;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauModifier;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

public class VueTableauModifier extends VueBase implements ContratVuePresenteurTableauModifier.IVueTableauModifier{
    private ContratVuePresenteurTableauModifier.IPresenteurTableauModifier _presenteur;
    private TextView tableauModifierTitre;
    private EditText titre;
    private TextView projetTitre;
    private Button modifier;
    private Button annuler;


    public void set_presenteur(ContratVuePresenteurTableauModifier.IPresenteurTableauModifier _presenteur) {
        this._presenteur = _presenteur;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tableau_modifier, container, false);
        super.onCreateView(inflater, container, savedInstanceState, view, (PresenteurBase) _presenteur);
        tableauModifierTitre = view.findViewById(R.id.tableauModifierTitre);
        titre = view.findViewById(R.id.titre);
        projetTitre = view.findViewById(R.id.projetTitre);
        modifier = view.findViewById(R.id.modifier);
        annuler = view.findViewById(R.id.annuler);

        tableauModifierTitre.setText(_presenteur.TableauTitreModifier());

        projetTitre.setText(_presenteur.getProjetTitre());

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.modifier(titre);
            }
        });

        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.annuler();
            }
        });

        return view;
    }
}
