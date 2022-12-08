package dti.g25.tp1.presentation.modele.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.domaine.entite.Tableau;

/**
 * Activité Projet - Interaction base de données
 * @author Sébastien Vermandele
 * @since 05/03/2020
 * @version 1
 */

public class DAOProjet implements DAO<Projet> {

    @SuppressLint("Recycle")
    @Override
    public Projet lire(Object identifiant) throws DAOException {
        Projet unProjet = null;

        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT * FROM projet WHERE id = ?";
            Cursor cursor;
            cursor = connect.rawQuery(requete, new String[]{String.valueOf(identifiant)});
            if (cursor.moveToFirst()){
                unProjet = new Projet(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        new ArrayList<Tableau>()
                );
            }
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - lire]: Une erreur est survenue lors de la lecture: " + e);
        }
        return unProjet;
    }

    public LinkedList<Projet> lireTout() throws DAOException {
        Projet unProjet = null;
        LinkedList<Projet> projets = new LinkedList<>();

        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT * FROM projet";
            Cursor cursor;
            cursor = connect.rawQuery(requete, null);
            while (cursor.moveToNext()){
                unProjet = new Projet(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        new ArrayList<Tableau>()
                );
                projets.add(unProjet);
            }
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - lireTout]: Une erreur est survenue lors de la lecture: " + e);
        }
        return projets;
    }

    @Override
    public Projet ajouter(Projet projet) throws DAOException {

        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            ContentValues values = new ContentValues();
            values.put("titre", projet.getTitre());
            values.put("description", projet.getDescription());

            connect.insert("projet", null, values);
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }

        int lastId = this.recupererDernierId();
        return this.lire(lastId);
    }

    @Override
    public Projet modifier(Projet projet) throws DAOException {
        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            ContentValues values = new ContentValues();
            values.put("titre", projet.getTitre());
            values.put("description", projet.getDescription());

            connect.update("projet", values, "id = " + projet.getId(), null);
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - modifier]: Une erreur est survenue lors de l'enregistrement: " + e);
        }

        return lire(projet.getId());
    }

    @Override
    public void supprimer(Projet projet) throws DAOException {
        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            ContentValues values = new ContentValues();

            connect.delete("projet", "id = " + projet.getId(), null);
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - supprimer]: Une erreur est survenue lors de la suppression: " + e);
        }
    }

    private int recupererDernierId() throws DAOException {
        int lastId = -1;

        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT max(id) from projet";
            Cursor cursor;
            cursor = connect.rawQuery(requete, null);
            if (cursor.moveToFirst()){
                lastId =  cursor.getInt(0);
            }
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - lireTout]: Une erreur est survenue lors de la recherche du dernier ID: " + e);
        }
        return lastId;
    }

    public int recupererNbrEnregistrement() throws DAOException{
        int nbrEnregistrement = 0;
        Cursor cursor;
        try{
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT COUNT(*) FROM projet";
            cursor = connect.rawQuery(requete, null);
            if (cursor.moveToFirst()){
                nbrEnregistrement = cursor.getInt(0);
            }
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - lire nombre enregistrements]: Une erreur est survenue lors de la lecture: " + e);
        }
        return nbrEnregistrement;
    }

    public static void creerTable() throws DAOException {
        try{
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("create table if not exists projet ( id Integer PRIMARY KEY AUTOINCREMENT, titre VARCHAR(255) NOT NULL, description VARCHAR(255));");
        } catch (SQLException e){
            throw new DAOException("Err. [DAOProjet - lireTout]: Une erreur est survenue lors de la création de la table: " + e);
        }
    }

}
