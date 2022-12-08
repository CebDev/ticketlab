package dti.g25.tp1.presentation.presenteur.presenteurProjet;

import android.app.Activity;
import android.content.Intent;


import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.presentation.contratProjet.ContratVuePresenteurProjetModifier;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleProjet;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherProjets;

/**
 * Activité Menu Projet - Modification projet
 * @author Sébastien Vermandele
 * @since 12/03/2020
 * @version 1
 */

public class PresenteurProjetModifier extends PresenteurBase implements ContratVuePresenteurProjetModifier.IPresenteurProjetModifier {

    private ContratVuePresenteurProjetModifier.IVueProjetModifier _vue;
    private ModeleProjet _modeleProjet;
    private Activity _activite;

    public PresenteurProjetModifier(ContratVuePresenteurProjetModifier.IVueProjetModifier _vue, ModeleProjet _modeleProjet, Activity _activite) {
        super(_activite);

        this._vue = _vue;
        this._modeleProjet = _modeleProjet;
        this._activite = _activite;
    }

    /**
     * Méthode qui récupère l'ID du projet passé par l'activité précédente
     * @return int l'id du projet
     */
    public int getIdProjet(){
        return _activite.getIntent().getIntExtra("idProjet", 0);
    }

    /**
     * Méthode qui retourne le titre du projet en fonction de l'ID du projet à modifier
     * @return String le titre du projet
     */
    public String getTitreProjet() throws DAOException {
        Projet unProjet = _modeleProjet.recupererProjetParId(getIdProjet());
        return unProjet.getTitre();
    }

    /**
     * Méthode retourne la description du projet en fonction de l'ID du projet à modifier
     * @return String la description du projet
     */
    public String getDescriptionProjet() throws DAOException {
        Projet unProjet = _modeleProjet.recupererProjetParId(getIdProjet());
        return unProjet.getDescription();
    }

    /**
     * Méthode qui enregistre le titre et la description d'un projet dans le stockage
     * @param String : le titre du projet
     * @param String : la description du projet
     * @return int l'id du projet
     */
    public Projet EnregistrerModification(String titre, String description) throws DAOException {
        Projet unProjet = _modeleProjet.recupererProjetParId(getIdProjet());
        unProjet.setTitre(titre);
        unProjet.setDescription(description);
        int id = _modeleProjet.EnregistrerModificationProjet(unProjet);
        return _modeleProjet.recupererProjetParId(id);
    }

    /**
     * Méthode qui lance l'activité afficher la liste des projets
     */
    public void lancerActiviteAfficherProjets(){
        Intent intent = new Intent(_activite, ActiviteAfficherProjets.class);
        _activite.startActivity(intent);
    }
}
