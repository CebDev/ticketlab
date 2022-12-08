package dti.g25.tp1.presentation.modele.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Tableau;
/**
 * Contient les méthodes pour chercher les Tableaux dans la base de donnée.
 * @author Bryan Valdiviezo
 */
public class DAOTableau implements DAO<Tableau>{

    /**
     * Retourne le Tableau dont le ID correspond à celui donnée en paramètre dans la base de donnée.
     * @param identifiant : Object
     * @return tableau : Tableau
     */
    @Override
    public Tableau lire(Object identifiant) throws DAOException {
        Tableau tableau = null;

        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT * FROM tableau WHERE id = ?";
            Cursor cursor;
            cursor = connect.rawQuery(requete, new String[]{String.valueOf(identifiant)});
            if (cursor.moveToFirst()){
                tableau = new Tableau(
                        cursor.getInt(0),
                        cursor.getString(1),
                        "",
                        "",
                        new ArrayList<Liste>(),
                        cursor.getInt(2)
                );
                tableau.setUneListe(new DAOListe().chercherTout(tableau.getId()));
            }
        } catch (SQLException e){
            throw new DAOException("Err. [DAOTableau - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        return tableau;
    }

    /**
     * Ajoute le Tableau entrée en paramètre dans la base de donnée et retourne ce dernier en le cherchant dans la même bd.
     * @param tableau : Tableau
     * @return tableaux : tableaux
     */
    @Override
    public Tableau ajouter(Tableau tableau) throws DAOException {
        Tableau tableaux;
        try{
            DAOListe dao = new DAOListe();
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "INSERT INTO tableau (titre, projetID) values (?, ?)";
            connect.execSQL(requete, new String[]{
                    tableau.getTitre(),
                    Integer.toString(tableau.getProjetID())
            });
            int lastId = this.recupererDernierId();
            tableaux = this.lire(lastId);
        } catch (SQLException e){
                throw new DAOException("Err. [DAOTableau - ajouterTableau]: Une erreur est survenue lors de l'ajout d'un tableau': " + e);
            }
        return tableaux;
    }

    /**
     * Modifie le Tableau entrée en paramètre dans la base de donnée et retourne ce dernier en le cherchant dans la même bd.
     * @param tableau : Tableau
     * @return unTableau : Tableau
     */
    @Override
    public Tableau modifier(Tableau tableau) throws DAOException {
        Tableau unTableau;
        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "UPDATE tableau SET titre = ? WHERE id = ?";
            connect.execSQL(requete, new String[]{
                    tableau.getTitre(),
                    Integer.toString(tableau.getId())
            });
            int lastId = this.recupererDernierId();
            unTableau = this.lire(lastId);
        } catch (SQLException e){
            throw new DAOException("Err. [DAOTableau - modifierTableau]: Une erreur est survenue lors de la modification d'un tableau': " + e);
        }
        return unTableau;
    }

    /**
     * Supprime le tableau entré en paramètre dans la base de donnée.
     * @param tableau : Tableau
     */
    @Override
    public void supprimer(Tableau tableau) throws DAOException {
        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            new DAOListe().supprimerParTableau(tableau.getId());
            String requete = "DELETE FROM tableau WHERE id = ?";
            connect.execSQL(requete, new String[]{
                    Integer.toString(tableau.getId())
            });
        } catch (SQLException e){
            throw new DAOException("Err. [DAOTableau - supprimerTableau]: Une erreur est survenue lors de la suppression d'un tableau': " + e);
        }

    }

    /**
     * Chercher dans la base de donnée tous les Tableaux qui appartiennent au projet dont le ID
     * correspond à celui donnée en paramètre.
     * @param projetID : int
     * @return tableaux : ArrayList<Tableau>
     */
    public ArrayList<Tableau> chercherParProjetID(int projetID) throws DAOException  {
        ArrayList<Tableau> tableaux = new ArrayList<Tableau>();

        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT * FROM tableau WHERE projetID = ?";
            Cursor cursor = connect.rawQuery(requete, new String[]{String.valueOf(projetID)});
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                tableaux.add(this.lire(cursor.getInt(0)));
            }
        } catch (SQLException e){
            throw new DAOException("Err. [DAOTableau - chercherParProjetID]: Une erreur est survenue lors de la recherche d'un projet par ID': " + e);
        }
        return tableaux;
    }

    /**
     * Récupère le ID du dernier Tableau ajouté dans la base de donnée.
     * @return lastId : int
     */
    private int recupererDernierId() throws DAOException {
        int lastId = -1;

        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT max(id) from tableau";
            Cursor cursor;
            cursor = connect.rawQuery(requete, null);
            if (cursor.moveToFirst()){
                lastId =  cursor.getInt(0);
            }
        } catch (SQLException e){
            throw new DAOException("Err. [DAOTableau - recupereDernierId]: Une erreur est survenue lors de la recherche du dernier ID': " + e);
        }

        return lastId;
    }

    /**
     * Crée la table Tableau dans la base de donnée.
     */
    public static void creerTable() throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("create table if not exists tableau ( id Integer PRIMARY KEY AUTOINCREMENT, titre VARCHAR(255) NOT NULL, projetID Integer NOT NULL);");
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTableau - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }
}
