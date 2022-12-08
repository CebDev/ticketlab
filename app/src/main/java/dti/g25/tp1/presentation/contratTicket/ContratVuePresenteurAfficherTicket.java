package dti.g25.tp1.presentation.contratTicket;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurAfficherTicket;

/**
 * Contrat Vue Présenteur pour L'affichage d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public interface ContratVuePresenteurAfficherTicket {
    interface IVueAfficherTicket {
        // Mutateur
        void setPresenteur(PresenteurAfficherTicket unPresenteur);

        // Action
        void fermer();
    }

    interface IPresenteurAfficherTicket {
        // Mutateur
        void setTicket(int unId);

        // Accesseur
        String getTitre();
        String getStatus();
        String getDescription();
        ArrayList<Etiquette> getEtiquettes();
        String getPseudoMembre();
        String getTitreJalon();
        String getDateEcheance();
        boolean estExpire();

        // Actions
        void fermer();
        void rouvrir();
        void modifier();
        void rafraichir();
    }
}