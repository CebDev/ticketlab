package dti.g25.tp1.presentation.modele.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Jalon;


public class DAOJalon  implements DAO<Jalon> {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Jalon lire(Object identifiant) throws DAOException {
        Cursor cursor;
        String id = Integer.toString((int) identifiant);

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            cursor = connexion.rawQuery("SELECT * FROM Jalon WHERE id = ?", new String[]{id});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOJalon - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        cursor.moveToFirst();
        return creerJalon(cursor);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Jalon ajouter(Jalon jalon) throws DAOException {
        Cursor cursor;
        Jalon nouveauJalon = null;

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("INSERT INTO Jalon (titre, description, date_Debut, date_Fin, projet_id) VALUES (?, ?, ?, ?, ?)", new String[]{jalon.getTitre(), jalon.getDescription(), jalon.getDateDebut().toString(), jalon.getDateFin().toString(), Integer.toString(jalon.getIdProjet())});

            // Va chercher l'identifiant
            cursor = connexion.rawQuery("SELECT max(id) FROM Jalon", null);

            cursor.moveToFirst();
            nouveauJalon = lire(cursor.getInt(0));

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOJalon - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }

        return nouveauJalon;
    }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public Jalon modifier(Jalon jalon) throws DAOException {
            try {
                SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
                ContentValues values = new ContentValues();

                values.put("titre", jalon.getTitre());
                values.put("description", jalon.getDescription());
                values.put("date_debut", jalon.getDateDebut().toString());
                values.put("date_fin", jalon.getDateFin().toString());

                connexion.update("Jalon", values, "id = ?", new String[]{String.valueOf(jalon.getId())});
            } catch (SQLException e) {
                throw new DAOException("Err. [DAOJalon - modifier]: Une erreur est survenue lors de la modification: " + e);
            }

            return lire(jalon.getId());
        }

    @Override
    public void supprimer(Jalon jalon) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("DELETE FROM Jalon WHERE id = ?", new String[]{(jalon.getTitre())});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOJalon - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Jalon> trouverTout() throws DAOException {
        Cursor cursor;
        ArrayList<Jalon> jalons = new ArrayList<>();

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();

            cursor = connexion.rawQuery("SELECT * FROM Jalon", new String[]{});
            while(cursor.moveToNext())
                jalons.add(creerJalon(cursor));

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOJalon - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        return jalons;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Jalon> trouverParProjet(int projetId) throws DAOException {
        Cursor cursor;
        ArrayList<Jalon> jalons = new ArrayList<>();

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();

            cursor = connexion.rawQuery("SELECT * FROM Jalon WHERE projet_id = ?", new String[]{Integer.toString(projetId)});
            while(cursor.moveToNext())
                jalons.add(creerJalon(cursor));

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOJalon - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        return jalons;
    }

    public static void creerTable() throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("CREATE TABLE if NOT EXISTS Jalon (id Integer PRIMARY KEY AUTOINCREMENT, titre VARCHAR(255) NOT NULL, description VARCHAR(255), date_debut DATE NOT NUll, date_fin DATE NOT NULL, projet_id INTEGER NOT NULL, FOREIGN KEY (projet_id) REFERENCES Projet(id), CHECK (date_fin >= date_debut));");
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOJalon - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private Jalon creerJalon(Cursor cursor) {
        Jalon unJalon = new Jalon(cursor.getInt(0), cursor.getString(1), cursor.getString(2), LocalDate.parse(cursor.getString(3)), LocalDate.parse(cursor.getString(4)),cursor.getInt(5));

        return unJalon;
    }




}
