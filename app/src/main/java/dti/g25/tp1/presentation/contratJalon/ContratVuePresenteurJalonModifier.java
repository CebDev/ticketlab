package dti.g25.tp1.presentation.contratJalon;

import java.time.LocalDateTime;

import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.presentation.modele.DAO.DAOException;

public interface ContratVuePresenteurJalonModifier {

    public interface IVueModifierJalon{

    }

    public interface IPresenteurModifierJalon{
        void modifier( String unTitre, String uneDescription, String uneDateDebut, String uneDateFin);
        void terminerModification();
        int getNbJalons();
        Jalon getJalon();
    }
}
