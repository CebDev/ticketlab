package dti.g25.tp1.presentation.vue.vueEtiquette;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratEtiquette.ContratVuePresenteurEtiquetteModifier;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurEtiquette.PresenteurModifierEtiquette;
import dti.g25.tp1.presentation.vue.VueBase;


/**
 * Vue qui permet l'entré et la sortie de données pour la modification d'un Etiquette
 * @author Philippe Joubarne
 * @since 06/03/2020
 */
public class VueModifierEtiquette extends VueBase implements ContratVuePresenteurEtiquetteModifier.IVueModifierEtiquette {
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";

    private ContratVuePresenteurEtiquetteModifier.IPresenteurModifierEtiquette _presenteur;

    public void setPresenteur(PresenteurModifierEtiquette presenteur) {_presenteur = presenteur; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View racine = inflater.inflate(R.layout.fragment_etiquette_formulaire, container, false);
        super.onCreateView(inflater, container, savedInstanceState, racine, (PresenteurBase) _presenteur);


        ((EditText)racine.findViewById(R.id.etiquetteTitre)).setText(_presenteur.getEtiquette().getTitre());
        ((EditText)racine.findViewById(R.id.etiquetteDescription)).setText(_presenteur.getEtiquette().getDescription());
        ((EditText)racine.findViewById(R.id.etiquetteCouleur)).setText(_presenteur.getEtiquette().getCouleur());

        racine.findViewById(R.id.btnTerminer).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                String titre = ((EditText)racine.findViewById(R.id.etiquetteTitre)).getText().toString();
                String description = ((EditText)racine.findViewById(R.id.etiquetteDescription)).getText().toString();
                String couleur = ((EditText)racine.findViewById(R.id.etiquetteCouleur)).getText().toString();

                if (titre.equals("")) {
                   afficherToast(getResources().getString(R.string.toast_titre));
                } 
                else if(!couleur.equals("") && !couleur.matches("^#([A-Fa-f0-9]{6})$")) {
                    afficherToast(getResources().getString(R.string.toast_couleur_etiquette));
                } 
                else {
                    _presenteur.modifier(_presenteur.getEtiquette().getId(), titre,description, couleur);
                    afficherToast(getResources().getString(R.string.toast_etiquette_modifie));
                }
            }
        });

        return racine;

    }
}
