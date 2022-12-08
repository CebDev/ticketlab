package dti.g25.tp1.presentation.modele;

import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOProjet;
import dti.g25.tp1.presentation.modele.DAO.DAOTableau;

/**
 * Modèle pour les Tableaux
 * @author Bryan
 */
public class ModeleTableau {
    DAOTableau dao = new DAOTableau();
    private ArrayList<Tableau> tableaux;
    private ArrayList<Liste> listes;
    private int projetID;
    private Tableau unTableau;

    /**
     * Défini le ID du Projet affiché dans la Vue et défini les Tableaux à affiché dans la Vue selon
     * le ID du Projet donnée.
     * @param ProjetID : int
     */
    public void setTableauList (int ProjetID) throws DAOException {
        projetID = ProjetID;
        this.tableaux = dao.chercherParProjetID(ProjetID);
        if (this.tableaux == null || this.tableaux.size() == 0)
            this.tableaux = new ArrayList<Tableau>();
    }

    /**
     * Défini le Tableau à afficher dans la Vue Tableau Modifier.
     * @param tableauID : int
     */
    public void setUnTableau (int tableauID) throws DAOException{
        this.unTableau = dao.lire(tableauID);
    }

    /**
     * Retourne le Tableau afficher dans la Vue Tableau Modifier
     * @return unTableau : Tableau
     */
    public Tableau getUnTableau () {
        return unTableau;
    }

    /**
     * Modifie le Tableau donné en paramètre.
     * @param tableau : Tableau
     */
    public void modifierTableau (Tableau tableau) {
        try {
            dao.modifier(tableau);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    /**
     * Retourne le ID du projet afficher dans la Vue Gestion
     * @return projetID : int
     */
    public int getProjetID(){
        return projetID;
    }

    /**
     * Retourne les Tableaux associés au id du Projet afficher.
     * @return tableaux : ArrayList<Tableau>
     */
    public ArrayList<Tableau> Tableaux (){
        try {
            tableaux = dao.chercherParProjetID(projetID);
            return tableaux;
        } catch (DAOException e){
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return tableaux;
    }

    /**
     * Retourne le nombre de Tableau associé au id du Projet afficher.
     * @return Tableaux().size() : int
     */
    public int NbTableaux() {
        return Tableaux().size();
    }

    /**
     * Retourne le Tableau situé à la position donnée en paramètre de l'id du Projet afficher.
     * @param position : int
     * @return Tableaux().get(position)
     */
    public Tableau getTableau(int position) {
        return Tableaux().get(position);
    }

    /**
     * Supprime le Tableau donné en paramètre.
     * @param tableau : Tableau
     */
    public void supprimerTableau(Tableau tableau) {
        try {
            dao.supprimer(tableau);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    /**
     * Crée le Tableau donné en paramètre.
     * @param tableau : Tableau
     */
    public void creerTableau(Tableau tableau){
        try {
            dao.ajouter(tableau);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }
}
