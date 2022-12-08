package dti.g25.tp1.presentation.contratJalon;

import java.time.LocalDateTime;

public interface ContratVuePresenteurJalonAjouter {

    public interface IVueAjouterJalon{
    }

    public interface IPresenteurAjouterJalon{
        void ajouter(int unId, String unTitre, String uneDescription, String uneDateDebut, String uneDateFin);
        void terminerAjout();
        int getNbJalons();
    }
}
