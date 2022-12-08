package dti.g25.tp1.ui.activite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleTicket;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurAjouterTicket;
import dti.g25.tp1.presentation.vue.vueTicket.VueAjouterTicket;

/**
 * Activité d'ajout d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class ActiviteAjouterTicket extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    public static final String EXTRA_AFFICHAGE_LISTE = "dti.g25.ticket.affichage";


    private PresenteurAjouterTicket _presenteur;
    private VueAjouterTicket _vue;
    private int _idProjet;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajouter_ticket);
        getSupportActionBar().hide(); // Cacher la barre de titre par défaut

        _vue = new VueAjouterTicket();
        ModeleTicket modele = new ModeleTicket();

        _presenteur = new PresenteurAjouterTicket(this, _vue, modele);

        _idProjet = getIntent().getIntExtra(PROJET_ID, 1);

        _presenteur.commencerAjout(_idProjet);
        _vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_ajouter_ticket, _vue);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intentAfficherTableau;

        if (!getIntent().getBooleanExtra(EXTRA_AFFICHAGE_LISTE, false))
            intentAfficherTableau = new Intent(this, ActiviteAfficherTableaux.class);
        else
            intentAfficherTableau = new Intent(this, ActiviteListeTickets.class);

        intentAfficherTableau.putExtra(PROJET_ID,  _idProjet);

        startActivity(intentAfficherTableau);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int annee, int mois, int jour) {
        _vue.onDateSet(annee, mois, jour);
    }
}
