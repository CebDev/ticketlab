package dti.g25.tp1.ui.activite;

/**
 * Activité Ajouter Projet
 * @author Sébastien Vermandele
 * @since 04/03/2020
 * @version 1
 */

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;
import dti.g25.tp1.presentation.modele.ModeleProjet;
import dti.g25.tp1.presentation.presenteur.presenteurProjet.PresenteurProjetModifier;
import dti.g25.tp1.presentation.vue.vueProjet.VueModifierProjet;

public class ActiviteModifierProjet extends AppCompatActivity {

    private PresenteurProjetModifier presenteurModifierProjet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajouter_projet);
        getSupportActionBar().hide();
        SQLiteConnexion.setContext(this);

        // Création du Modele de gestion des projets
        ModeleProjet modeleProjet = null;
        modeleProjet = new ModeleProjet();

        // Insertion du fragment formulaire pour les modifiers projets
        VueModifierProjet vueModifierProjet = new VueModifierProjet();
        presenteurModifierProjet = new PresenteurProjetModifier(vueModifierProjet, modeleProjet, this);

        vueModifierProjet.set_presenteur(presenteurModifierProjet);

        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layoutProjetNouveau, vueModifierProjet);
        ft.commit();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ActiviteModifierProjet.this, ActiviteAfficherProjets.class);
        startActivity(intent);
        ActiviteModifierProjet.this.finish();
    }
}
