package dti.g25.tp1.ui.activite;

/**
 * Activité Menu Projet
 * @author Sébastien Vermandele
 * @since 02/03/2020
 * @version 1
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;
import dti.g25.tp1.presentation.modele.ModeleProjet;
import dti.g25.tp1.presentation.presenteur.presenteurProjet.PresenteurProjetAfficher;
import dti.g25.tp1.presentation.vue.vueProjet.VueListeProjet;

public class ActiviteAfficherProjets extends AppCompatActivity {

    PresenteurProjetAfficher presenteurProjetAfficher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_menu);
        getSupportActionBar().hide();
        SQLiteConnexion.setContext(this);

        // Création du Modele de gestion des projets
        ModeleProjet modeleProjet = null;
        modeleProjet = new ModeleProjet();

        // Insertion du fragment présentant la liste des projets
        VueListeProjet vueListeProjet = new VueListeProjet();
        presenteurProjetAfficher = new PresenteurProjetAfficher(vueListeProjet, modeleProjet, this);
        vueListeProjet.set_presenteur(presenteurProjetAfficher);

        // inclusion des fragments dans l'activité ProjetMenu
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layoutProjetMenu, vueListeProjet);
        ft.commit();

    }

    @Override
    public void onBackPressed(){
        ActiviteAfficherProjets.this.finish();
    }
}
