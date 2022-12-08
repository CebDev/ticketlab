package dti.g25.tp1.presentation.presenteur.presenteurProjet;

/**
 * Activité Nouveau Projet - Présenteur
 * @author Sébastien Vermandele
 * @since 04/03/2020
 * @version 1
 */

import android.app.Activity;
import android.content.Intent;

import dti.g25.tp1.presentation.contratProjet.ContratVuePresenteurProjetNouveau;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleProjet;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;
import dti.g25.tp1.ui.activite.ActiviteAjouterTicket;

public class PresenteurProjetNouveau extends PresenteurBase implements ContratVuePresenteurProjetNouveau.IPresenteurProjetNouveau {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";

    private ContratVuePresenteurProjetNouveau.IVueProjetNouveau _vue;
    private ModeleProjet _modeleProjet;
    private Activity _activite;

    public PresenteurProjetNouveau(ContratVuePresenteurProjetNouveau.IVueProjetNouveau _vue, ModeleProjet _modeleProjet, Activity _activite) {
        super(_activite);

        this._vue = _vue;
        this._modeleProjet = _modeleProjet;
        this._activite = _activite;
    }

    /**
     * Méthode qui enregistre un projet dans le stockage
     * @param String le titre du projet
     * @param String la description du projet
     * @return int l'id du projet
     */
    public int enregistrerNouveauProjet(String titre, String description) throws DAOException {
        return _modeleProjet.EnregistrerNouveauProjet(titre, description);
    }

    /**
     * Méthode qui lance l'activité qui permet de créer un ticket
     * @param int id du projet dans le stockage
     */
    public void lancerActiviteAjouterTicket(int idProjet){
        Intent intent = new Intent(_activite, ActiviteAjouterTicket.class);
        intent.putExtra(PROJET_ID, idProjet);
        _activite.startActivity(intent);
    }

    /**
     * Méthode qui lance l'activité AfficherTableaux
     * @param int la position du projet dans le tableau de projet
     */
    public void lancerActiviteAfficherTableaux(int idProjet){
        Intent intent = new Intent(_activite, ActiviteAfficherTableaux.class);
        intent.putExtra(PROJET_ID, idProjet);
        _activite.startActivity(intent);
    }


}
