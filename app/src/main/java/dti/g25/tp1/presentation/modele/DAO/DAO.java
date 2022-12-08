package dti.g25.tp1.presentation.modele.DAO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.sql.SQLException;

/**
 * Interface DAO qui implement les fonctions de
 * bases des interactions avec une base de données
 *
 * @author Simon Roy
 * @since 28/10/2019
 */
public interface DAO<T> {
    /**
     * Cherche et retourne <T> trouvé avec l'identifiant passé en paramètre
     *
     * @param identifiant
     * @return <T>
     * @throws DAOException
     */
    public T lire(Object identifiant) throws DAOException;

    /**
     * Ajoute l'objet <T> passé en paramètre à la bd
     *
     * @param t
     * @return
     * @throws DAOException
     */
    public T ajouter(T t) throws DAOException;

    /**
     * Modifie et Retourne l'objet <T> passée en paramètre
     *
     * @param t
     * @return
     * @throws DAOException
     */
    public T modifier(T t) throws DAOException;

    /**
     * Retire de la base de données l'objet <T> passé en paramètre
     *
     * @param t
     * @throws DAOException
     */
    public void supprimer(T t) throws DAOException;
}