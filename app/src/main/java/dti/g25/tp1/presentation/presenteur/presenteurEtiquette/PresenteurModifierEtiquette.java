package dti.g25.tp1.presentation.presenteur.presenteurEtiquette;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.contratEtiquette.ContratVuePresenteurEtiquetteModifier;
import dti.g25.tp1.presentation.modele.ModeleEtiquette;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurEtiquette.PresenteurAfficherEtiquette;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurModifierTicket;
import dti.g25.tp1.presentation.vue.vueEtiquette.VueModifierEtiquette;
import dti.g25.tp1.ui.activite.ActiviteAfficherEtiquette;

public class PresenteurModifierEtiquette extends PresenteurBase implements ContratVuePresenteurEtiquetteModifier.IPresenteurModifierEtiquette {
    public static final String EXTRA_STRING = "dti.g25.ticket.string";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";

    private Activity _activite;
    private ContratVuePresenteurEtiquetteModifier.IVueModifierEtiquette _vue;
    private ModeleEtiquette.MEtiquette _modele;

    private String _titre;
    private int _position;

    public  PresenteurModifierEtiquette(Activity activite, VueModifierEtiquette vue, ModeleEtiquette.MEtiquette modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    @Override
    public void commencerModification(String titre, int position) {
        _titre = titre;
        _position = position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void modifier(int unId, String unTitre, String uneDescription, String uneCouleur) {

        if (uneCouleur == null || uneCouleur.equals(""))
            uneCouleur = "#99004c";
            _modele.remplacer(unId, new Etiquette(unId,unTitre, uneDescription, uneCouleur, false));

        terminerModification();
    }

    @Override
    public void terminerModification() {
        Intent donneesRetour = new Intent(_activite, ActiviteAfficherEtiquette.class);
        donneesRetour.putExtra(EXTRA_STRING, _titre);
        donneesRetour.putExtra(EXTRA_POSITION, _position);

        _activite.startActivity(donneesRetour);
        _activite.finish();
    }

    @Override
    public Etiquette getEtiquette() { return _modele.obtenir(_position); }

}
