package dti.g25.tp1.presentation.contratTicket;

import java.util.ArrayList;
import java.util.List;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;

/**
 * Contrat Vue Présenteur pour la modification d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public interface ContratVuePresenteurModifierTicket {
    interface IVueModifierTicket {
        // Actions
        void rafraichir();
        void onDateSet(int annee, int mois, int jour);
    }

    interface IPresenteurModifierTicket {
        // Accesseur
        String getTitre();
        String getDescription();
        ArrayList<Etiquette> getEtiquettes();
        List<String> getTitresEtiquettes();
        String getPseudoMembre();
        ArrayList<String> getTitresJalons();
        int getPositionJalon();
        String getDateEcheance();

        // Mutateurs
        void setTicket(int unId);
        void setEtiquettes();
        void setJalons();

        // Actions
        void commencerModification(int id);
        void ajouterEtiquette(int positionEtiquette);
        void modifier(String unTitre, String uneDescription, String positionAffectation, int positionJalon, String uneDateEcheance);
        void terminerModification();
    }
}
