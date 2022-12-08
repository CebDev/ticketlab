package dti.g25.tp1.ui.activite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleTicket;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurAfficherTicket;
import dti.g25.tp1.presentation.vue.vueTicket.VueAfficherTicket;

/**
 * Activité d'affichage d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class ActiviteAfficherTicket extends AppCompatActivity {
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION="dti.g25.ticket.position";
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    public static final String EXTRA_AFFICHAGE_LISTE="dti.g25.ticket.affichage";


    private PresenteurAfficherTicket _presenteur;
    private static int _idProjet = 1;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_afficher_ticket);
        getSupportActionBar().hide(); // Cacher la barre de titre par défaut

        VueAfficherTicket vue = new VueAfficherTicket();
        ModeleTicket modele = new ModeleTicket();

        _presenteur = new PresenteurAfficherTicket(this, vue, modele);
        vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_afficher_ticket, vue);
        ft.commit();

        _idProjet = getIntent().getIntExtra(PROJET_ID, _idProjet);
        _presenteur.setTicket(getIntent().getIntExtra(EXTRA_ID, 1));
    }


    @Override
    public void onBackPressed() {
        Intent intentAfficherTableau;

        if (!getIntent().getBooleanExtra(EXTRA_AFFICHAGE_LISTE, false))
            intentAfficherTableau = new Intent(this, ActiviteAfficherTableaux.class);
        else
            intentAfficherTableau = new Intent(this, ActiviteListeTickets.class);

        intentAfficherTableau.putExtra(PROJET_ID, _idProjet);
        startActivity(intentAfficherTableau);
        finish();
    }
}
