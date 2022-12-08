package dti.g25.tp1.presentation.contratTicket;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurAjouterTicket;

/**
 * Contrat Vue Présenteur pour l'ajout d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public interface ContratVuePresenteurAjouterTicket {
    interface IVueAjouterTicket {
        // Mutateurs
        void setPresenteur(PresenteurAjouterTicket unPresenteur);
        Spinner setSpinner(Spinner unSpinner, List uneListe);

        // Actions
        void rafraichir();
        void onDateSet(int annee, int mois, int jour);
    }

    interface IPresenteurAjouterTicket {
        // Accesseur
        ArrayList<String> getTitresJalons();
        ArrayList<Etiquette> getEtiquettes();
        List<String> getTitresEtiquettes();

        // Mutateur
        void setIdProjet(int idProjet);
        void setJalons();

        // Actions
        void ajouter(String unTitre, String uneDescription, String positionAffectation, int positionJalon, String uneDateEcheance);
        void terminerAjout();
        void ajouterEtiquette(int positionEtiquette);
    }
}
