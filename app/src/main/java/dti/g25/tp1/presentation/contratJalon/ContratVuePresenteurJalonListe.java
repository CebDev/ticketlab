package dti.g25.tp1.presentation.contratJalon;

import java.time.LocalDate;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.presentation.modele.DAO.DAOException;

public interface ContratVuePresenteurJalonListe {

    public interface IVueAfficherJalon {
        void rafraichir();
    }

    public interface IPresenteurAfficherJalon {
        void setIdProjet(int idProjet);
        ArrayList<Jalon> getJalons() throws DAOException;
        String getJalonTitre(int unIndex);
        String getJalonDescription(int unIndex);
        LocalDate getJalonDateDebut(int unIndex);
        LocalDate getJalonDateFin(int unIndex);
        int getNbJalon() throws DAOException;
        void modifier(int unIndex);
        void ajouter();

    }
}
