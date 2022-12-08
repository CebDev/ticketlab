package dti.g25.tp1.ui.activite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.DatePicker;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleJalon;
import dti.g25.tp1.presentation.presenteur.presenteurJalon.PresenteurAjouterJalon;
import dti.g25.tp1.presentation.vue.vueJalon.VueAjouterJalon;

public class ActiviteAjouterJalon extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    public static final String EXTRA_ID = "dti.g25.jalon.string";
    public static final String EXTRA_POSITION = "dti.g25.jalon.position";
    public static final String PROJET_ID = "dti.g25.ticket.projetid";

    private PresenteurAjouterJalon _presenteur;
    private VueAjouterJalon _vue;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajouter_jalon);
        getSupportActionBar().hide(); //Cacher la barre de titre par défaut


        VueAjouterJalon vue = new VueAjouterJalon();
        ModeleJalon.MJalon modele = new ModeleJalon.MJalon();


        _presenteur = new PresenteurAjouterJalon(this, vue, modele);
        _presenteur.setIdProjet(getIntent().getIntExtra(PROJET_ID, 1));
        _vue = vue;
        vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_ajouter_jalon, vue);
        ft.commit();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBackPressed(){
        super.onBackPressed();

        Intent intentModifier = new Intent(this, ActiviteAfficherJalon.class);
        intentModifier.putExtra(EXTRA_ID, _presenteur.getJalon().getTitre());
        intentModifier.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, 0));

        startActivity(intentModifier);
        finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        _vue.onDateSet(year, month, dayOfMonth);
    }




}
