package dti.g25.tp1.presentation.vue.vueProjet;

/**
 * Activité Projet Nouveau
 * @author Sébastien Vermandele
 * @since
 * @version 1
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratProjet.ContratVuePresenteurProjetNouveau;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

public class VueProjetNouveau extends VueBase implements ContratVuePresenteurProjetNouveau.IVueProjetNouveau {

    private ContratVuePresenteurProjetNouveau.IPresenteurProjetNouveau _presenteur;
    private Button btnEnregistrerProjet;
    private EditText etTitreProjet;
    private EditText etDescriptionProjet;

    public void set_presenteur(ContratVuePresenteurProjetNouveau.IPresenteurProjetNouveau presenteurProjetNouveau){
        _presenteur = presenteurProjetNouveau;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vueProjetNouveau = inflater.inflate(R.layout.fragment_projet_ajouter, container, false);
        super.onCreateView(inflater, container, savedInstanceState, vueProjetNouveau, (PresenteurBase) _presenteur);

        etTitreProjet = (EditText) vueProjetNouveau.findViewById(R.id.etTitreProjet);
        etDescriptionProjet = (EditText) vueProjetNouveau.findViewById(R.id.etDescriptionProjet);

        btnEnregistrerProjet = vueProjetNouveau.findViewById(R.id.btnEnregistrerProjet);
        btnEnregistrerProjet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titreProjet = etTitreProjet.getText().toString().trim();
                String descriptionProjet = etDescriptionProjet.getText().toString().trim();

                if (titreProjet.trim().equals("")){
                    afficherToast(getResources().getString(R.string.toast_titre));
                } else {
                    try {
                        int idProjet = _presenteur.enregistrerNouveauProjet(titreProjet, descriptionProjet);
                        _presenteur.lancerActiviteAfficherTableaux(idProjet);
                        getActivity().finish();
                        afficherToast(getResources().getString(R.string.toast_projet_ajoute));
                    } catch (DAOException e) {
                        e.printStackTrace();
                        afficherToast(getResources().getString(R.string.toast_projet_probleme));
                    }
                }
            }
        });
        return vueProjetNouveau;
    }
}
