package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.ModeleEtiquette;
import dti.g25.tp1.presentation.presenteur.presenteurEtiquette.PresenteurModifierEtiquette;

import dti.g25.tp1.presentation.vue.vueEtiquette.VueModifierEtiquette;

public class ActiviteModifierEtiquette extends AppCompatActivity {
    public static final String EXTRA_STRING = "dti.g25.etiquette.string";
    public static final String EXTRA_POSITION = "dti.g25.etiquette.position";

    private PresenteurModifierEtiquette _presenteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activite_modifier_etiquette);
        getSupportActionBar().hide(); //Cacher la barre de titre par défaut

        VueModifierEtiquette vue = new VueModifierEtiquette();
        ModeleEtiquette.MEtiquette modele = new ModeleEtiquette.MEtiquette();

        _presenteur = new PresenteurModifierEtiquette(this, vue, modele);
        vue.setPresenteur(_presenteur);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.layout_modifier_etiquette, vue);
        ft.commit();

        _presenteur.commencerModification(getIntent().getStringExtra(EXTRA_STRING),getIntent().getIntExtra(EXTRA_POSITION, 1));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intentModifier = new Intent(this, ActiviteAfficherEtiquette.class);
        intentModifier.putExtra(EXTRA_STRING, _presenteur.getEtiquette().getTitre());
        intentModifier.putExtra(EXTRA_POSITION, getIntent().getIntExtra(EXTRA_POSITION, 0));

        startActivity(intentModifier);
        finish();
    }
}
