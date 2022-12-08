package dti.g25.tp1.ui.activite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Build;
import android.os.Bundle;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleJalon;
import dti.g25.tp1.presentation.presenteur.presenteurJalon.PresenteurAfficherJalon;
import dti.g25.tp1.presentation.vue.vueJalon.VueAfficherJalon;

public class ActiviteAfficherJalon extends AppCompatActivity {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";

    private PresenteurAfficherJalon _presenteur;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_afficher_jalon);
        getSupportActionBar().hide();

        VueAfficherJalon vue = new VueAfficherJalon();
        ModeleJalon.MJalon modele = new ModeleJalon.MJalon();

        _presenteur = new PresenteurAfficherJalon(this, vue, modele);
        // recupération et passage de l'ID du projet en cours au présenteur
        _presenteur.setIdProjet(getIntent().getIntExtra("idProjet", 1));
        vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_afficher_jalon, vue);
        ft.commit();

    }
}
