package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleTableau;
import dti.g25.tp1.presentation.presenteur.presenteurTableau.PresenteurTableauModifier;
import dti.g25.tp1.presentation.vue.vueTableau.VueTableauModifier;

public class ActiviteModifierTableau extends AppCompatActivity {
    ModeleTableau modele;
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    public static final String TABLEAU_ID = "dti.g25.ticket.tableauid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_modifier_tableau);
        getSupportActionBar().hide();

        VueTableauModifier vueTableauModifier = new VueTableauModifier();
        modele = new ModeleTableau();
        try {
            modele.setUnTableau(getIntent().getIntExtra(TABLEAU_ID, 1));
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        PresenteurTableauModifier presenteur = new PresenteurTableauModifier(vueTableauModifier, this, modele);
        vueTableauModifier.set_presenteur(presenteur);
        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.modifierTableau, vueTableauModifier);
        ft.commit();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(getApplicationContext(), ActiviteAfficherTableaux.class);
        intent.putExtra(PROJET_ID, modele.getUnTableau().getProjetID());
        startActivity(intent);
        finish();
    }
}
