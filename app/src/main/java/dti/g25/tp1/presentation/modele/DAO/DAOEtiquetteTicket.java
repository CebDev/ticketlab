package dti.g25.tp1.presentation.modele.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.sql.SQLException;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;

public class DAOEtiquetteTicket  implements DAO<Etiquette> {
    @Override
    public Etiquette lire(Object identifiant) throws DAOException {
//        Cursor cursor;
//        String id = Integer.toString((int) identifiant);
//
//        try {
//            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
//            cursor = connexion.rawQuery("SELECT * FROM EtiquetteTicket WHERE id = ?", new String[]{id});
//        } catch (android.database.SQLException | java.sql.SQLException e) {
//            throw new DAOException("Err. [DAOEtiquette - lire]: Une erreur est survenue lors de la lecture: " + e);
//        }
//
//        cursor.moveToFirst();
//        return creerEtiquette(cursor);
        return null;
    }


    @Override
    public Etiquette ajouter(Etiquette etiquette) throws DAOException {
        return null;
    }

    @Override
    public Etiquette modifier(Etiquette etiquette) throws DAOException {
        return null;
    }

    @Override
    public void supprimer(Etiquette etiquette) throws DAOException {
        // TODO
    }

    /**
     * @author Bryan Valdiviezo
     * Supprime dans la base de donner le lien entre une étiquette et un ticket
     * @param etiquette_id : int
     * @param ticket_id : int
     */
    public void supprimerTicketEtiquette(int etiquette_id, int ticket_id) throws DAOException {
        try{
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("DELETE FROM EtiquetteTicket WHERE etiquette_id = ? AND ticket_id = ?", new String[]{Integer.toString(etiquette_id), Integer.toString(ticket_id)});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }
    }


    public void ajouterTicketEtiquette(int etiquette_id, int ticket_id) throws DAOException {
        Log.println(Log.ERROR, "DAOEtiquetteTicket", String.format("%d - %d", etiquette_id, ticket_id));

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("INSERT INTO EtiquetteTicket (etiquette_id, ticket_id) VALUES (?, ?)", new String[]{Integer.toString(etiquette_id), Integer.toString(ticket_id)});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }
    }

    /**
     * @author Bryan Valdiviezo
     * Cherche si un lien entre une étiquette et un ticket existe dans la base de donnée
     * @param etiquette_id : int
     * @param ticket_id : int
     */
    public boolean TicketEtiquetteExiste(int etiquette_id, int ticket_id) throws DAOException{
        Cursor cursor;

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            cursor = connexion.rawQuery("SELECT * FROM EtiquetteTicket WHERE etiquette_id = ? AND ticket_id = ?", new String[]{Integer.toString(etiquette_id), Integer.toString(ticket_id)});
            if (cursor.moveToFirst())
                return true;
            else
                return false;

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOEtiquetteTicket - trouverToutParTicket] Une erreur est survenue lors de la recherche: " + e);
        }
    }

    public ArrayList<Etiquette> trouverToutParTicket(int idTicket) throws DAOException {
        DAOEtiquette daoEtiquette = new DAOEtiquette();
        ArrayList<Etiquette> etiquettes = new ArrayList<Etiquette>();
        Cursor cursor;

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            cursor = connexion.rawQuery("SELECT * FROM EtiquetteTicket WHERE ticket_id = ?", new String[]{Integer.toString(idTicket)});

            while (cursor.moveToNext())
                etiquettes.add(daoEtiquette.lire(cursor.getInt(0)));

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOEtiquetteTicket - trouverToutParTicket] Une erreur est survenue lors de la recherche: " + e);
        }

        return etiquettes;
    }

    /**
     * @author Bryan Valdiviezo
     * Cherche tous les tickets ayant un lien avec le ID de Liste donnée dans la base de donnée.
     * @param liste_id : int
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Ticket> trouverTicketParListe(int liste_id) throws DAOException {
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            Cursor cursor = connexion.rawQuery("SELECT ticket_id FROM EtiquetteTicket WHERE etiquette_id = ?", new String[]{Integer.toString(liste_id)});
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                tickets.add(new DAOTicket().lire(cursor.getInt(0)));
            }
        } catch (SQLException e) {
            throw new DAOException("" + e);
        }
        return tickets;
    }

    public static void supprimerToutParTicket(int idTicket) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.delete("EtiquetteTicket", "ticket_id = ?", new String[]{Integer.toString(idTicket)});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOEtiquetteTicket - supprimerToutParTIcket] Une erreur est survenue lors de la suppression: " + e);
        }
    }


    public static void creerTable() throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("CREATE TABLE if NOT EXISTS  EtiquetteTicket (etiquette_id INTEGER NOT NULL, ticket_id INTEGER NOT NULL, CONSTRAINT etiquette_ticket_pk PRIMARY KEY (etiquette_id,ticket_id), FOREIGN KEY (etiquette_id) REFERENCES Etiquette(id),FOREIGN KEY (ticket_id) REFERENCES Ticket(id));\n");
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOEtiquetteTicket - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }
}
