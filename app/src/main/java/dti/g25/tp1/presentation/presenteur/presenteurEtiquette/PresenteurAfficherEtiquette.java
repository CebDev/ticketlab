package dti.g25.tp1.presentation.presenteur.presenteurEtiquette;


import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.contratEtiquette.ContratVuePresenteurEtiquetteListe;
import dti.g25.tp1.presentation.modele.ModeleEtiquette;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAjouterEtiquette;
import dti.g25.tp1.ui.activite.ActiviteModifierEtiquette;

public class PresenteurAfficherEtiquette extends PresenteurBase implements ContratVuePresenteurEtiquetteListe.IPresenteurAfficherEtiquette {
    public static final String EXTRA_INT = "dti.g25.ticket.string";
    public static final String EXTRA_POSITION = "dti.g25.etiquette.position";


    private int _position = 1;
    private Activity _activite;
    private ContratVuePresenteurEtiquetteListe.IVueAfficherEtiquette _vue;
    private ModeleEtiquette.MEtiquette _modele;

    public  PresenteurAfficherEtiquette( Activity activite, ContratVuePresenteurEtiquetteListe.IVueAfficherEtiquette vue, ModeleEtiquette.MEtiquette modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;


    }

    @Override
    public String getEtiquetteTitre(int unIndex){
        return _modele.obtenir(unIndex).getTitre();
    }

    @Override
    public String getEtiquetteDescription(int unIndex){
        return _modele.obtenir(unIndex).getDescription();
    }

    @Override
    public String getEtiquetteCouleur(int unIndex){
        String couleur = "#FF00FF";

        if (!_modele.obtenir(unIndex).getCouleur().equals(""))
            couleur = _modele.obtenir(unIndex).getCouleur();

        return couleur;
    }


    @Override
    public int getNbEtiquette() {
        return _modele.taille();
    }


    public void supprimer(int unIndex) {
        return;

        //TODO : supprimer etiquette et jalon
        /**Intent intent = new Intent(_activite, ActiviteModifierEtiquette.class);
        intent.putExtra(EXTRA_INT, _modele.obtenir(unIndex).getId());
        intent.putExtra(EXTRA_POSITION, unIndex);
        */
    }

    public void modifier(int unIndex) {
         Intent intent = new Intent(_activite, ActiviteModifierEtiquette.class);
         intent.putExtra(EXTRA_INT, _modele.obtenir(unIndex).getId());
         intent.putExtra(EXTRA_POSITION, unIndex);

        _activite.startActivity(intent);
        _activite.finish();
    }

    public void ajouter() {
        Intent intent = new Intent(_activite, ActiviteAjouterEtiquette.class);
        _activite.startActivity(intent);
        _activite.finish();
    }

    public void setPosition(int position) { _position = position; }

    public int getPosition() { return _position; }

    @Override
    public ArrayList<Etiquette> getEtiquettes() { return _modele.getEtiquettes(); }
}
