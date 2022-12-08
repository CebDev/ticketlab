package dti.g25.tp1.presentation.contratProjet;

import java.sql.SQLException;

import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.presentation.modele.DAO.DAOException;

public interface ContratVuePresenteurProjetModifier {

    public interface IVueProjetModifier{
        public void afficherToast(String message);
    }

    public interface IPresenteurProjetModifier{
        public int getIdProjet();
        public String getTitreProjet() throws DAOException;
        public String getDescriptionProjet() throws DAOException;
        public Projet EnregistrerModification(String titre, String description) throws DAOException;
        public void lancerActiviteAfficherProjets();
    }
}
