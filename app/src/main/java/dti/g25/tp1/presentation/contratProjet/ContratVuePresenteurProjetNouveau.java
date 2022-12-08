package dti.g25.tp1.presentation.contratProjet;

import java.sql.SQLException;

import dti.g25.tp1.presentation.modele.DAO.DAOException;

public interface ContratVuePresenteurProjetNouveau {

    interface IVueProjetNouveau{
    }

    interface IPresenteurProjetNouveau{
        int enregistrerNouveauProjet(String titre, String description) throws DAOException;
        void lancerActiviteAjouterTicket(int idProjet);
        void lancerActiviteAfficherTableaux(int idProjet);
    }
}
