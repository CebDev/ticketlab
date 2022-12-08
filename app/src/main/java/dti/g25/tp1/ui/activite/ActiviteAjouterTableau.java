package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleTableau;
import dti.g25.tp1.presentation.presenteur.presenteurTableau.PresenteurTableauAjouter;
import dti.g25.tp1.presentation.presenteur.presenteurTableau.PresenteurTableauListe;
import dti.g25.tp1.presentation.vue.vueTableau.VueTableauAjouter;

public class ActiviteAjouterTableau extends AppCompatActivity {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajouter_tableau);
        getSupportActionBar().hide();

        VueTableauAjouter vueTableauAjouter = new VueTableauAjouter();
        ModeleTableau modele = new ModeleTableau();
        try {
            modele.setTableauList(getIntent().getIntExtra(PROJET_ID, 1));
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }

        PresenteurTableauAjouter presenteur = new PresenteurTableauAjouter(modele, this,vueTableauAjouter);
        vueTableauAjouter.set_presenteur(presenteur);
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.ajouterTableau, vueTableauAjouter);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), ActiviteAfficherTableaux.class);
        intent.putExtra(PROJET_ID, getIntent().getIntExtra(PROJET_ID, 1));
        startActivity(intent);
        finish();
    }
}
