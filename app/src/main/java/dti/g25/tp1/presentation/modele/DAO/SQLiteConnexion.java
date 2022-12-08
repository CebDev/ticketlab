package dti.g25.tp1.presentation.modele.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;


/**
 * Classe pour créer une connexion avec la base de données SQLite
 * @author Simon Roy
 * @since 20/02/2020
 */
public class SQLiteConnexion {
    private static SQLiteDatabase connexion = null;
    private static Context mContext;

    /**
     * Change le context pour celui passé en paramètre
     * @param unContext : Context
     */
    public static void setContext(Context unContext) { mContext = unContext; }

    /**
     * Méthode de connexion à la base de données (database).
     *
     * @throws SQLException : Lance une exception SQL en cas d'erreur de connexion
     * @return connexion : SQLiteDatabase
     */
    public static SQLiteDatabase getConnexion() throws SQLException {
        if (connexion == null)
            connexion = mContext.openOrCreateDatabase("database.db", 0, null);
        return connexion;
    }
}