package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleListe;
import dti.g25.tp1.presentation.presenteur.presenteurListe.PresenteurListeNouveau;
import dti.g25.tp1.presentation.vue.vueListe.VueFormulaireListe;

public class ActiviteAjouterListe extends AppCompatActivity {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    public static final String TABLEAU_ID = "dti.g25.ticket.tableauid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajouter_liste);
        getSupportActionBar().hide();
        VueFormulaireListe vue = new VueFormulaireListe();
        ModeleListe modele = new ModeleListe();
        modele.setListes(getIntent().getIntExtra(TABLEAU_ID,1));
        PresenteurListeNouveau presenteur = new PresenteurListeNouveau(modele, this, vue);

        vue.set_presenteur(presenteur);

        FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
        ft.add(R.id.ajouterListe, vue);
        ft.commit();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, ActiviteAfficherTableaux.class);
        intent.putExtra(PROJET_ID, getIntent().getIntExtra(PROJET_ID,1));
        startActivity(intent);
        finish();
    }
}
