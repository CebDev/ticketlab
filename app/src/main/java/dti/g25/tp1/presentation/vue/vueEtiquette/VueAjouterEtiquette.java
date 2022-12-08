package dti.g25.tp1.presentation.vue.vueEtiquette;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.contratEtiquette.ContratVuePresenteurEtiquetteAjouter;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurEtiquette.PresenteurAjouterEtiquette;
import dti.g25.tp1.presentation.vue.VueBase;

/**
 * Vue qui permet l'entré et la sortie de données pour l'ajout d'une etiquette
 * @author Philippe Joubarne
 * @since 06/03/2020
 */

public class VueAjouterEtiquette extends VueBase implements ContratVuePresenteurEtiquetteAjouter.IVueAjouterEtiquette {

    private ContratVuePresenteurEtiquetteAjouter.IPresenteurAjouterEtiquette _presenteur;

    public void setPresenteur(PresenteurAjouterEtiquette presenteur) {_presenteur = presenteur; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // On créer la racine et ensuite on passe le on creatView personalisé à la vue de base (VueBase) pour intégrer les fonctions partagé par les vues
        final View racine = inflater.inflate(R.layout.fragment_etiquette_formulaire, container, false);
        super.onCreateView(inflater, container, savedInstanceState, racine, (PresenteurBase) _presenteur);

        // Écoute et réagit au bouton << terminer >> de la vue
        racine.findViewById(R.id.btnTerminer).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String titre = ((EditText) racine.findViewById(R.id.etiquetteTitre)).getText().toString();
                String description = ((EditText) racine.findViewById(R.id.etiquetteDescription)).getText().toString();
                String couleur = ((EditText) racine.findViewById(R.id.etiquetteCouleur)).getText().toString();

                if (titre.equals("")) {
                   afficherToast(getResources().getString(R.string.toast_titre));
                } 
                else if(!couleur.equals("") && !couleur.matches("^#([A-Fa-f0-9]{6})$")) {
                    afficherToast(getResources().getString(R.string.toast_couleur_etiquette));
                } 
                else {
                    Etiquette uneEtiquette = _presenteur.ajouter(titre, description, couleur);
                    if (uneEtiquette != null)
                        afficherToast(getResources().getString(R.string.toast_etiquette_ajoute));
                    else
                        afficherToast(getResources().getString(R.string.toast_etiquette_existe));
                }
            }
        });

        return racine;

    }
}
