package dti.g25.tp1.ui.activite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleTicket;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurModifierTicket;
import dti.g25.tp1.presentation.vue.vueTicket.VueModifierTicket;

/**
 * Activité de modification d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class ActiviteModifierTicket extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";
    public static final String EXTRA_AFFICHAGE_LISTE = "dti.g25.ticket.affichage";

    private PresenteurModifierTicket _presenteur;
    private VueModifierTicket _vue;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_modifier_ticket);
        getSupportActionBar().hide(); // Cacher la barre de titre par défaut

        _vue = new VueModifierTicket();
        ModeleTicket modele = new ModeleTicket();

        _presenteur = new PresenteurModifierTicket(this, _vue, modele);
        _vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_modifier_ticket, _vue);
        ft.commit();

        _presenteur.commencerModification(getIntent().getIntExtra(EXTRA_ID, 1));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intentModifier = new Intent(this, ActiviteAfficherTicket.class);
        intentModifier.putExtra(EXTRA_ID, getIntent().getIntExtra(EXTRA_ID, 1));

        if (getIntent().getBooleanExtra(EXTRA_AFFICHAGE_LISTE, false))
            intentModifier.putExtra(EXTRA_AFFICHAGE_LISTE, true);

        startActivity(intentModifier);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int annee, int mois, int jour) {
        _vue.onDateSet(annee, mois, jour);
    }
}