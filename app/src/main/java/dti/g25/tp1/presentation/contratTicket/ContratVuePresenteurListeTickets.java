package dti.g25.tp1.presentation.contratTicket;

import android.app.AlertDialog;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauListe;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurListeTickets;

/**
 * Contrat Vue Présenteur pour l'affichage de la liste de tickets
 * @author Simon Roy
 * @since 06/03/2020
 */
public interface ContratVuePresenteurListeTickets {
    interface IVueListeTicket{
        void setPresenteur(PresenteurListeTickets unPresenteur);
        void rafraichir();
    }

    interface IPresenteurListeTicket {
        ArrayList<Ticket> getTickets();
        String getDate(Ticket unTicket);
        ArrayList<Etiquette> getEtiquettes(Ticket unTicket);

        void setIdProjet(int unId);

        // Gestion des options
        void afficherEtiquettes();
        void afficherJalons();
        void afficherTableaux();
        View afficherMenuPlus();
        AlertDialog.Builder creerAlertMenuPlus();

        void ajouterTicket();
        void afficherTicket(int id);

        void changerTypeTicket(int selectedTabPosition);
    }
}
