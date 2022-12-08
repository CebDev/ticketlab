package dti.g25.tp1.presentation.modele.DAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Liste;

/**
 * Contient les méthodes pour chercher les Listes dans la base de donnée.
 * @author Bryan Valdiviezo
 */
public class DAOListe implements DAO<Liste> {

    /**
     * Retourne la Liste dont le ID correspond à celui donnée en paramètre dans la base de donnée.
     * @param identifiant : Object
     * @return liste : Liste
     */
    @Override
    public Liste lire(Object identifiant) throws DAOException {
        Liste liste = null;

        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT Etiquette.*, liste.tableau_id FROM Etiquette INNER JOIN liste ON id = etiquette_id WHERE id = ?;";
            Cursor cursor;
            cursor = connect.rawQuery(requete, new String[]{String.valueOf(identifiant)});
            if (cursor.moveToFirst()) {
                    liste = new Liste(
                            cursor.getString(1),
                            cursor.getString(3)
                    );
                    liste.setId(cursor.getInt(0));
                    liste.setTableauID(cursor.getInt(5));
            }
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - modifier] Une erreur est survenue lors de la lecture " + e);
        }
        return liste;
    }

    /**
     * Ajoute la Liste entrée en paramètre dans la base de donnée et retourne ce dernier en le cherchant dans la même bd.
     * @param liste : Liste
     * @return liste : Liste
     */
    @Override
    public Liste ajouter(Liste liste) throws DAOException {
        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            connect.execSQL("INSERT INTO Etiquette (titre, description, couleur, est_liste) VALUES (?, '', ?, ?)", new String[]{liste.getTitre(), liste.getCouleur(), "1"});

            String requete = "INSERT INTO liste VALUES (?,?)";
            connect.execSQL(requete, new String[]{
                    Integer.toString(liste.getTableauID()),
                    Integer.toString(this.recupererDernierId())
            });

        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - modifier] Une erreur est survenue lors de l'ajout: " + e);
        }

        int lastId = this.recupererDernierId();
        return this.lire(lastId);
    }

    /**
     * Modifie la Liste entrée en paramètre dans la base de donnée et retourne ce dernier en le cherchant dans la même bd.
     * @param liste : Liste
     * @return liste : Liste
     */
    @Override
    public Liste modifier(Liste liste) throws DAOException {
        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "UPDATE Etiquette SET titre = ?, couleur = ? WHERE id = ?";
            connect.execSQL(requete, new String[]{
                    liste.getTitre(),
                    liste.getCouleur(),
                    Integer.toString(liste.getId())
            });
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - modifier] Une erreur est survenue lors de la modification: " + e);
        }

        int lastId = this.recupererDernierId();
        return this.lire(lastId);
    }

    /**
     * Supprime la Liste entré en paramètre dans la base de donnée.
     * @param liste : Liste
     */
    @Override
    public void supprimer(Liste liste) throws DAOException {
        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "DELETE FROM Etiquette WHERE id = ?";
            connect.execSQL(requete, new String[]{
                    Integer.toString(liste.getId())
            });

            requete = "DELETE FROM liste WHERE etiquette_id = ?";
            connect.execSQL(requete, new String[]{
                    Integer.toString(liste.getId())
            });
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - supprimer] Une erreur est survenue lors de la suppression: " + e);
        }
    }

    /**
     * Supprime toutes les Listes liée au Tableau dans la base de donnée dont le ID correspond à celui donnée en paramètre
     * @param tableauID : int
     */
    public void supprimerParTableau(int tableauID) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            String requete = "DELETE FROM liste WHERE tableau_id = ?";
            connexion.execSQL(requete, new String[]{Integer.toString(tableauID)});
        } catch (SQLException e) {
            throw new DAOException("Err. [DAOTableau - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }

    /**
     * Cherche dans la base de donnée toutes les Listes liéent au Tableau dans la base de donnée dont le ID correspond à celui donnée en paramètre
     * @param tableauID : int
     * @return listes : ArrayList<Liste>
     */
    public ArrayList<Liste> chercherTout(int tableauID) throws DAOException {
        ArrayList<Liste> listes = new ArrayList<Liste>();
        Liste ouvert = new Liste("Ouvert", "#4E4A4A");
        ouvert.setId(-1);
        listes.add(ouvert);
        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT etiquette_id FROM liste WHERE tableau_id = ?";
            Cursor cursor = connect.rawQuery(requete, new String[]{String.valueOf(tableauID)});
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                listes.add(this.lire(cursor.getInt(0)));
                //Log.e("test", Integer.toString((this.lire(cursor.getInt(0))).getId()));
            }

        } catch(SQLException e) {
            throw new DAOException("Err.[DAOList - creerTable] Une erreur est survenue lors de la rechercher de tout les projets: " + e);
        }
        Liste fermer = new Liste("Fermer", "#4E4A4A");
        fermer.setId(-2);
        listes.add(fermer);
        return listes;
    }

    /**
     * Récupère le ID de la dernière Liste ajoutée dans la base de donnée.
     * @return lastId : int
     */
    private int recupererDernierId() throws DAOException {
        int lastId = -1;

        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT max(id) from Etiquette";
            Cursor cursor;
            cursor = connect.rawQuery(requete, null);
            if (cursor.moveToFirst()) {
                lastId = cursor.getInt(0);
            }
            return lastId;
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - creerTable] Une erreur est survenue lors de la récupération du dernier id: " + e);
        }
    }

    /**
     * Crée la table de jointure Liste qui lie le ID d'un tableau avec le ID d'une étiquette
     */
    public static void creerTable() throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            connexion.execSQL("create table if not exists liste ( tableau_id Integer, etiquette_id Integer, CONSTRAINT FK_tableau FOREIGN KEY (tableau_id) REFERENCES tableau(id), CONSTRAINT FK_etiquette FOREIGN KEY (etiquette_id) REFERENCES Etiquette(id));");
            //connexion.execSQL("DROP TABLE liste2;");
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }

    public boolean tableauListeExiste (int etiquette_id, int tableau_id) throws DAOException {
        Cursor cursor;

        try {
            SQLiteDatabase connect = SQLiteConnexion.getConnexion();
            String requete = "SELECT * FROM liste WHERE etiquette_id = ? AND tableau_id = ?";
            cursor = connect.rawQuery(requete, new String[]{Integer.toString(etiquette_id), Integer.toString(tableau_id)});
            if (cursor.getCount() == 0)
                return false;
            else
                return true;
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }

    public void ajouterLien(int etiquette_id, int tableau_id) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            String requete = "INSERT INTO liste VALUES (?,?)";
            connexion.execSQL(requete, new String[]{Integer.toString(tableau_id), Integer.toString(etiquette_id)});
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }

    public void supprimerLien(int etiquette_id, int tableau_id) throws DAOException {
        try {
            SQLiteDatabase connexion = SQLiteConnexion.getConnexion();
            String requete = "DELETE FROM liste WHERE tableau_id = ? AND etiquette_id = ?";
            connexion.execSQL(requete, new String[]{Integer.toString(tableau_id), Integer.toString(etiquette_id)});
        } catch (SQLException e) {
            throw new DAOException("Err.[DAOList - creerTable] Une erreur est survenue lors de la création de la table: " + e);
        }
    }
}
