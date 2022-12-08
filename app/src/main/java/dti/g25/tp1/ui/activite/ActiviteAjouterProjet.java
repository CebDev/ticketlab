package dti.g25.tp1.ui.activite;

/**
 * Activité Ajouter Projet
 * @author Sébastien Vermandele
 * @since 04/03/2020
 * @version 1
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import java.sql.SQLException;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;
import dti.g25.tp1.presentation.modele.ModeleProjet;
import dti.g25.tp1.presentation.presenteur.presenteurProjet.PresenteurProjetNouveau;
import dti.g25.tp1.presentation.vue.vueProjet.VueProjetNouveau;

public class ActiviteAjouterProjet extends AppCompatActivity {

    private PresenteurProjetNouveau presenteurProjetNouveau;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajouter_projet);
        getSupportActionBar().hide();
        SQLiteConnexion.setContext(this);

        // Création du Modele de gestion des projets
        ModeleProjet modeleProjet = null;
        modeleProjet = new ModeleProjet();

        // Insertion du fragment formulaire pour les nouveaux projets
        VueProjetNouveau vueProjetNouveau = new VueProjetNouveau();
        presenteurProjetNouveau = new PresenteurProjetNouveau(vueProjetNouveau, modeleProjet, this);
        vueProjetNouveau.set_presenteur(presenteurProjetNouveau);
        // inclusion des fragments dans l'activité AjouterProjet
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layoutProjetNouveau, vueProjetNouveau);
        ft.commit();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(ActiviteAjouterProjet.this, ActiviteAfficherProjets.class);
        startActivity(intent);
        finish();
    }
}
