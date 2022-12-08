package dti.g25.tp1.presentation.modele.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.domaine.entite.Membre;
import dti.g25.tp1.domaine.entite.Ticket;

public class DAOTicket implements DAO<Ticket> {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Ticket lire(Object identifiant) throws DAOException {
        Cursor cursor;
        String id = Integer.toString((int) identifiant);

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            cursor = connexion.rawQuery("SELECT * FROM Ticket WHERE id = ?", new String[]{id});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        cursor.moveToFirst();
        return creerTicket(cursor);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Ticket ajouter(Ticket ticket) throws DAOException {
        Cursor cursor;
        Ticket nouveauTicket = null;
        String dateEcheance = "";
        DAOEtiquetteTicket daoEtiquetteTicket = new DAOEtiquetteTicket();
        String unJalon = null;
        String unPseudo = null;

        if (ticket.getDateEcheance() != null)
            dateEcheance = ticket.getDateEcheance().toString();

        if (ticket.getJalon() != null)
            unJalon = Integer.toString(ticket.getJalon().getId());

        if (ticket.getAffectation() != null)
            unPseudo = ticket.getAffectation().getPseudo();

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("INSERT INTO Ticket (titre, description, est_ouvert, date_echeance, jalon_id, affectation) VALUES (?, ?, ?, ?, ?, ?)", new String[]{ticket.getTitre(), ticket.getDescription(), "1", dateEcheance, unJalon, unPseudo});

            // Va chercher l'identifiant
            cursor = connexion.rawQuery("SELECT max(id) FROM Ticket", null);
            cursor.moveToFirst();

            for(int i=0; i < ticket.getEtiquettes().size(); i++)
                daoEtiquetteTicket.ajouterTicketEtiquette(ticket.getEtiquettes().get(i).getId(), cursor.getInt(0));

            nouveauTicket = lire(cursor.getInt(0));

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }

        return nouveauTicket;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Ticket setProjet(Ticket ticket, int idProjet) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            ContentValues values = new ContentValues();

            values.put("projet_id", idProjet);

            connexion.update("Ticket", values, "id = ?", new String[]{Integer.toString(ticket.getId())});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - modifier]: Une erreur est survenue lors de la modification: " + e);
        }

        return lire(ticket.getId());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Ticket modifier(Ticket ticket) throws DAOException {
        DAOEtiquetteTicket daoEtiquetteTicket = new DAOEtiquetteTicket();

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            ContentValues values = new ContentValues();

            values.put("titre", ticket.getTitre());
            values.put("description", ticket.getDescription());
            values.put("est_ouvert", Boolean.valueOf(ticket.estOuvert()));

            if (ticket.getJalon() != null)
                values.put("jalon_id", ticket.getJalon().getId());
            else
                values.putNull("jalon_id");

            if (ticket.getAffectation() != null)
                values.put("affectation", ticket.getAffectation().getPseudo());
            else
                values.putNull("affectation");

            // Met les nouvelles étiquettes
            daoEtiquetteTicket.supprimerToutParTicket(ticket.getId());
            for(int i=0; i < ticket.getEtiquettes().size(); i++)
                daoEtiquetteTicket.ajouterTicketEtiquette(ticket.getEtiquettes().get(i).getId(), ticket.getId());

            if (ticket.getDateEcheance() != null)
                values.put("date_echeance", ticket.getDateEcheance().toString());
            else
                values.putNull("date_echeance");

            connexion.update("Ticket", values, "id = ?", new String[]{Integer.toString(ticket.getId())});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - modifier]: Une erreur est survenue lors de la modification: " + e);
        }

        return lire(ticket.getId());
    }

    @Override
    public void supprimer(Ticket ticket) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("DELETE FROM Ticket WHERE id = ?", new String[]{Integer.toString(ticket.getId())});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - Ajouter]: Une erreur est survenue lors de l'ajout: " + e);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Ticket> trouverTout() throws DAOException {
        Cursor cursor;
        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();

            cursor = connexion.rawQuery("SELECT * FROM Ticket", new String[]{});
            while(cursor.moveToNext())
                tickets.add(creerTicket(cursor));

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        return tickets;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Ticket> trouverParProjet(int projetId) throws DAOException {
        Cursor cursor;
        ArrayList<Ticket> tickets = new ArrayList<>();

        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();

            cursor = connexion.rawQuery("SELECT * FROM Ticket WHERE projet_id = ?", new String[]{Integer.toString(projetId)});
            while(cursor.moveToNext())
                tickets.add(creerTicket(cursor));

        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - lire]: Une erreur est survenue lors de la lecture: " + e);
        }

        return tickets;
    }

    public static void creerTable() throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("CREATE TABLE if NOT EXISTS Ticket (id INTEGER PRIMARY KEY, titre VARCHAR(255) NOT NULL, description VARCHAR(255), est_ouvert INTEGER, date_echeance VARCHAR(255) DEFAULT NULL, jalon_id INTEGER DEFAULT NULL, affectation VARCHAR(255) DEFAULT NULL, projet_id INTEGER DEFAULT NULL, FOREIGN KEY (jalon_id) REFERENCES Jalon(id), FOREIGN KEY (projet_id) REFERENCES Projet(id));");
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTicket - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }

    /**
     * Créer un ticket à partir des données cherché dans la base de données
     * TODO: Ajouter la recherche de jalon, la recherche de membre et la date d'échéance
     *
     * @param cursor
     * @return un ticket
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private Ticket creerTicket(Cursor cursor) {
        Ticket unTicket;
        LocalDate dateEcheance = null;
        ArrayList<Etiquette> etiquettes = new ArrayList<>();
        Jalon unJalon = null;
        Membre uneAffectation = null;

        DAOEtiquetteTicket daoEtiquetteTicket = new DAOEtiquetteTicket();
        DAOJalon daoJalon = new DAOJalon();

        if (cursor.getString(4) != null && !cursor.getString(4).equals(""))
            dateEcheance = LocalDate.parse(cursor.getString(4));

        if (cursor.getString(6) != null)
            uneAffectation = new Membre(cursor.getString(6));

        try {
            etiquettes = daoEtiquetteTicket.trouverToutParTicket(cursor.getInt(0));
            if (cursor.getString(5) != null)
                unJalon = daoJalon.lire(cursor.getInt(5));

        } catch (DAOException e) {
            Log.println(Log.ERROR, "creerTicket", "Une erreur est survenue lors de la création du ticket" + e);
        }

        unTicket = new Ticket(cursor.getInt(0), cursor.getString(1), etiquettes, cursor.getString(2), uneAffectation, unJalon, dateEcheance);

        if (cursor.getInt(3) == 0)
            unTicket.fermer();

        return unTicket;
    }
}