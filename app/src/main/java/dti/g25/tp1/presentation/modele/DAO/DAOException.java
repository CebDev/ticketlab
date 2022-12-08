package dti.g25.tp1.presentation.modele.DAO;

/**
 * Classe d'exception DAO
 * @author Simon Roy
 * @since 20/02/2020
 */

public class DAOException extends Exception {

    /**
     * Créer un exception d'objet d'accès aux données (DAO)
     * @param message : String
     */

    public DAOException(String message) {
        super(message);
    }
}