package dti.g25.tp1.presentation.presenteur.presenteurEtiquette;

import android.app.Activity;
import android.content.Intent;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.contratEtiquette.ContratVuePresenteurEtiquetteAjouter;
import dti.g25.tp1.presentation.modele.ModeleEtiquette;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherEtiquette;

public class PresenteurAjouterEtiquette extends PresenteurBase implements ContratVuePresenteurEtiquetteAjouter.IPresenteurAjouterEtiquette {
    public static final String EXTRA_POSITION="dti.g25.etiquette.position";

    Activity _activite;
    ContratVuePresenteurEtiquetteAjouter.IVueAjouterEtiquette _vue;
    ModeleEtiquette.MEtiquette _modele;

    public PresenteurAjouterEtiquette(Activity activite, ContratVuePresenteurEtiquetteAjouter.IVueAjouterEtiquette vue, ModeleEtiquette.MEtiquette modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    public Etiquette ajouter(String unTitre, String uneDescription, String uneCouleur) {
        if (uneCouleur == null || uneCouleur.equals(""))
            uneCouleur = "#99004c";
        Etiquette uneEtiquette = new Etiquette(-1, unTitre, uneDescription, uneCouleur, false);
        uneEtiquette = _modele.ajouter(uneEtiquette);
        if (uneEtiquette != null)
            terminerAjout();
        return uneEtiquette;
    }

    @Override
    public void terminerAjout() {
        Intent donneesRetour = new Intent(_activite, ActiviteAfficherEtiquette.class);
        _activite.startActivity(donneesRetour);
        _activite.finish();
    }

    @Override
    public int getNbEtiquettes() { return _modele.taille(); }
}
