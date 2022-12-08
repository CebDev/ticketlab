package dti.g25.tp1.presentation.modele;

/**
 * Activité Menu Projet - Modele
 * @author Sébastien Vermandele
 * @since 01/03/2020
 * @version 1
 */

import java.util.LinkedList;

import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.domaine.interacteur.InteracteurProjet;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOProjet;

public class ModeleProjet {
    
    private LinkedList<Projet> listefiltre;

    /**
     * Filtre les liste des projets en fonction de la chaine de caractères passée en paramètre
     * @param recherche : String
     */
    public void setListeProjet(String recherche) throws DAOException {
        // Recupère la liste complète des projets
        DAOProjet dao = new DAOProjet();
        LinkedList<Projet> listeProjet = this.recupererProjets();
        // Filtre les projets via l'interacteur
        InteracteurProjet interacteurProjet = new InteracteurProjet();
        listefiltre = interacteurProjet.filtrerProjets(listeProjet, recherche);
    }

    /**
     * Méthode qui retourne un tableau contenant la liste des projets correspondant à la recherche
     * @return liste de projets : LinkedList<Projet>
     */
    public LinkedList<Projet> getListeProjets() throws DAOException {
        if(listefiltre == null)
            this.setListeProjet("");
        return listefiltre;
    }

    /**
     * Méthode qui retourne le nombre denregistrement dans le stockage
     * @return le nombre de projet dans la liste : int
     */
    public int recupererNbrEnregistrement() throws DAOException {
        DAOProjet dao = new DAOProjet();
        return dao.recupererNbrEnregistrement();
    }


    /**
     * Méthode qui retourne le nombre de projet dans la liste à afficher
     * @return int le nombre de projet dans la liste
     */
    public int getNbProjetAfficher() throws DAOException {
        DAOProjet dao = new DAOProjet();
        LinkedList<Projet> listeProjet = this.getListeProjets();
        if(listeProjet!=null)
            return getListeProjets().size();
        else
            return 0;
    }

    /**
     * Méthode qui retourne un projet à partir de sa postion
     * @param position int position dans le tableau
     * @return Projet le projet recherché
     */
    public Projet getProjet (int position) throws DAOException {
        return getListeProjets().get(position);
    }

    /**
     * Méthode qui enregistre un nouveau projet dans la base donnée
     * @param String le titre du projet
     * @param String la description du projet
     * @return int id du projet enregistré
     */
    public int EnregistrerNouveauProjet (String titre, String description) throws DAOException {
        InteracteurProjet interacteurProjet = new InteracteurProjet();
        Projet unProjet = interacteurProjet.creerProjet(titre, description);

        DAOProjet dao = new DAOProjet();
        unProjet = dao.ajouter(unProjet);
        return unProjet.getId();
    }

    /**
     * Méthode qui enregistre les modifications d'un projet dans la base donnée
     * @param String le titre du projet
     * @param String la description du projet
     * @return int id du projet enregistré
     */
    public int EnregistrerModificationProjet (Projet unProjet) throws DAOException {
        DAOProjet dao = new DAOProjet();
        unProjet = dao.modifier(unProjet);

        return unProjet.getId();
    }

    /**
     * Méthode qui retourne un projet à partir de son id
     * @param int un id du projet recherché
     * @return Projet le projet recherché
     */
    public Projet recupererProjetParId (int idProjet) throws DAOException {
        Projet unProjet = null;
        DAOProjet dao = new DAOProjet();
        unProjet = dao.lire(idProjet);
        return unProjet;
    }

    /**
     * Méthode qui recupere tous les projets contenus dans la base de donnée
     * @return LinkedList<Projet> un tableau de projet
     */
    public LinkedList<Projet> recupererProjets() throws DAOException {
        LinkedList<Projet> listeProjets = new LinkedList<>();
        DAOProjet dao = new DAOProjet();
        listeProjets = dao.lireTout();
        return listeProjets;
    }

    /**
     * Méthode qui supprime un projets contenus dans la base de donnée
     */
    public void supprimerProjetParId(int idProjet) throws DAOException {
        Projet unProjet;
        DAOProjet dao = new DAOProjet();
        unProjet = dao.lire(idProjet);
        dao.supprimer(unProjet);
    }


}
