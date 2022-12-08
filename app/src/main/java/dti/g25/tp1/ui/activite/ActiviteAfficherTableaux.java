package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.sql.SQLException;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;
import dti.g25.tp1.presentation.modele.ModeleProjet;
import dti.g25.tp1.presentation.modele.ModeleTableau;
import dti.g25.tp1.presentation.presenteur.presenteurTableau.PresenteurTableauListe;
import dti.g25.tp1.presentation.vue.vueTableau.VueTableauListe;

public class ActiviteAfficherTableaux extends AppCompatActivity {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_afficher_tableaux);
        getSupportActionBar().hide();
        SQLiteConnexion.setContext(this);

        ModeleTableau modeleTableau = new ModeleTableau();

        try {
            modeleTableau.setTableauList(getIntent().getIntExtra(PROJET_ID, 1));
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }

        VueTableauListe vueTableauListe = new VueTableauListe();
        PresenteurTableauListe presenteurTableauListe = new PresenteurTableauListe(vueTableauListe, modeleTableau, this);
            presenteurTableauListe.setIdProjet(getIntent().getIntExtra(PROJET_ID, 1));
        vueTableauListe.set_presenteur(presenteurTableauListe);
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.afficherTableau, vueTableauListe);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ActiviteAfficherProjets.class);
        startActivity(intent);
        finish();
    }
}
