package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;
import dti.g25.tp1.presentation.modele.ModeleTicket;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurListeTickets;
import dti.g25.tp1.presentation.vue.vueTicket.VueListeTickets;

/**
 * Activité de gestion et affiche des tickets
 * @author Simon Roy
 * @since 06/03/2020
 */
public class ActiviteListeTickets extends AppCompatActivity {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";

    private PresenteurListeTickets _presenteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_liste_tickets);
        getSupportActionBar().hide(); // Cacher la barre de titre par défaut
        SQLiteConnexion.setContext(this);

        VueListeTickets vue = new VueListeTickets();
        ModeleTicket modele = new ModeleTicket();

        _presenteur = new PresenteurListeTickets(this, vue, modele);
        _presenteur.setIdProjet(getIntent().getIntExtra(PROJET_ID, 1));
        vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_liste_tickets, vue);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intentAfficherTableau = new Intent(this, ActiviteAfficherProjets.class);

        startActivity(intentAfficherTableau);
        finish();
    }
}
