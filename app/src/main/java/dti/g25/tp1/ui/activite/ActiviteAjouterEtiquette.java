package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleEtiquette;
import dti.g25.tp1.presentation.presenteur.presenteurEtiquette.PresenteurAjouterEtiquette;
import dti.g25.tp1.presentation.vue.vueEtiquette.VueAjouterEtiquette;

public class ActiviteAjouterEtiquette extends AppCompatActivity {

    private PresenteurAjouterEtiquette _presenteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_ajouter_etiquette);
        getSupportActionBar().hide(); // Cacher la barre de titre par défaut

        VueAjouterEtiquette vue=new VueAjouterEtiquette();
        ModeleEtiquette.MEtiquette modele = new ModeleEtiquette.MEtiquette();

        _presenteur = new PresenteurAjouterEtiquette(this, vue, modele);


        vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_ajouter_etiquette, vue);
        ft.commit();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ActiviteAfficherEtiquette.class);
        startActivity(intent);
        finish();
    }
}
