package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleEtiquette;
import dti.g25.tp1.presentation.presenteur.presenteurEtiquette.PresenteurAfficherEtiquette;
import dti.g25.tp1.presentation.vue.vueEtiquette.VueAfficherEtiquette;

public class ActiviteAfficherEtiquette extends AppCompatActivity {
    public static final  String EXTRA_POSITION="dti.g25.etiquette.position";

    private PresenteurAfficherEtiquette _presenteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_afficher_etiquette);
        getSupportActionBar().hide(); // Cacher la barre de titre par défaut

        VueAfficherEtiquette vue = new VueAfficherEtiquette();
        ModeleEtiquette.MEtiquette modele = new ModeleEtiquette.MEtiquette();

        _presenteur = new PresenteurAfficherEtiquette(this, vue, modele);
        vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_afficher_etiquette, vue);
        ft.commit();
    }
}
