package dti.g25.tp1.presentation.modele.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import dti.g25.tp1.domaine.entite.Etiquette;


public class DAOEtiquette implements DAO<Etiquette> {

    @Override
    public Etiquette lire(Object identifiant) throws DAOException {
        Cursor cursor;
        String id = Integer.toString((int) identifiant);

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            cursor = connexion.rawQuery("SELECT * FROM Etiquette WHERE id = ?", new String[]{id});
        } catch (SQLException | java.sql.SQLException e) {
            throw new DAOException("Err. [DAOEtiquette - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        cursor.moveToFirst();
        return creerEtiquette(cursor);
    }

    @Override
    public Etiquette ajouter(Etiquette etiquette) throws DAOException {
        Cursor cursor;
        Etiquette nouveauEtiquette = null;

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("INSERT INTO Etiquette (titre, description, couleur, est_liste) VALUES (?, ?, ?, ?)", new String[]{ etiquette.getTitre(), etiquette.getDescription(), etiquette.getCouleur(), "0"});

            // Va chercher l'identifiant
            cursor = connexion.rawQuery("SELECT max(id) FROM Etiquette", null);

            cursor.moveToFirst();
            nouveauEtiquette = lire(cursor.getInt(0));

        } catch (SQLException | java.sql.SQLException e) {
            throw new DAOException("Err. [DAOEtiquette - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }

        return nouveauEtiquette;
    }

    @Override
    public Etiquette modifier(Etiquette etiquette) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            ContentValues values = new ContentValues();

            values.put("id", etiquette.getId());
            values.put("titre", etiquette.getTitre());
            values.put("description", etiquette.getDescription());
            values.put("couleur", (etiquette.getCouleur()));
            values.put("est_liste", Boolean.valueOf(etiquette.estListe()));

            connexion.update("Etiquette", values, "id = ?", new String[]{Integer.toString(etiquette.getId())});
        } catch (SQLException | java.sql.SQLException e) {
            throw new DAOException("Err. [DAOEtiquette - modifier]: Une erreur est survenue lors de la modification: " + e);
        }

        return lire(etiquette.getId());
    }

    @Override
    public void supprimer(Etiquette etiquette) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("DELETE FROM Etiquette WHERE id = ?", new String[]{Integer.toString(etiquette.getId())});
        } catch (SQLException | java.sql.SQLException e) {
            throw new DAOException("Err. [DAOEtiquette - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }
    }

    public ArrayList<Etiquette> trouverTout() throws DAOException {
        Cursor cursor;
        ArrayList<Etiquette> etiquettes = new ArrayList<>();

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();

            cursor = connexion.rawQuery("SELECT * FROM Etiquette", new String[]{});
            while(cursor.moveToNext())
                etiquettes.add(creerEtiquette(cursor));

        } catch (SQLException | java.sql.SQLException e) {
            throw new DAOException("Err. [DAOEtiquette - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        return etiquettes;
    }

    public static void creerTable() throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("CREATE TABLE if NOT EXISTS Etiquette (id INTEGER PRIMARY KEY AUTOINCREMENT, titre VARCHAR(255) NOT NULL UNIQUE, description VARCHAR(255),couleur VARCHAR(7) NOT NULL, est_liste INTEGER NOT NULL);");
        } catch (java.sql.SQLException e) {
            throw new DAOException("Err. [DAOEtiquette - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }

    private Etiquette creerEtiquette(Cursor cursor) {
        Boolean est_liste = false;
        if(cursor.getInt(4) == 1)
            est_liste = true;

        Etiquette unEtiquette = new Etiquette(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), est_liste);

        return unEtiquette;
    }









}
