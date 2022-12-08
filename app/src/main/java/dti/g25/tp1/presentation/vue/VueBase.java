package dti.g25.tp1.presentation.vue;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherProjets;
import dti.g25.tp1.ui.activite.ActiviteModifierTableau;


/**
 * Vue de base qui contient des fonctions partagées par toutes les vues
 * @author Simon Roy
 * @since 06/03/2020
 */
public class VueBase extends Fragment {

    private PresenteurBase _presenteur;

    /**
     * Affiche un TOAST à l'écrans
     * @param message
     */
    public void afficherToast(String message) {
        Toast t = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 60);
        t.show();
    }

    // TODO: Affiche le menu latéral
    public void afficherMenu() {
        afficherToast(getResources().getString(R.string.toast_menu_ouvert));
    }

    // TODO faire un PrésenteurBase
    public void retourListeProjet() {
        _presenteur.retourActivitePrincipale();
    }

    /**
     * Vue qui permet l'entré et la sortie de donnée pour l'ajout d'un ticket
     *
     * @param inflater : LayoutInflater
     * @param container : ViewGroup
     * @param savedInstanceState : Bundle
     * @param racine : View
     *
     * @retrun racine : View
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState, View racine, PresenteurBase presenteur) {
        _presenteur = presenteur;

        // Écouteur du bouton du menu
        racine.findViewById(R.id.btnMenu).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                retourListeProjet();
            }
        });

        return racine;
    }
}
