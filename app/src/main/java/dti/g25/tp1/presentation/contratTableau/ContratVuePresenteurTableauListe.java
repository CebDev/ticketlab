package dti.g25.tp1.presentation.contratTableau;

import android.app.AlertDialog;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.domaine.entite.Ticket;

/**
 * Contrat Vue Présenteur pour le visionnement/gestion des Tableau et Liste
 * @author Bryan Valdiviezo
 */
public interface ContratVuePresenteurTableauListe {

    public interface IVueTableauMenu {
        void rafraichir();
        void derouleRV(int x, int y);
    }

    public interface IPresenteurTableauMenu {
        public void setIdProjet(int idProjet);

        // Gestion des Tableaux afficher
        public int getTableauIDAfficher();
        public int getNbTableaux();
        public Tableau getTableau(int position);
        public String getTableauxTitre(int position);
        public void supprimerTableau(Tableau tableau);
        public void creationTableau();
        public void modiferTableau(Tableau tableau);

        // Gestion des Listes afficher
        public void setNouvelleListe(int position);
        public Liste estOuvertFermer(int id);
        public int getNbListes();
        public Liste getListe(int position);
        public String getListeTitre(int position);
        public String getListeCouleur(int position);
        public void gestionListe(Tableau tableau);
        public void supprimerListe(Liste liste);
        public String titreVertical(String titre);
        public void listesEtat();
        public void testEtat();
        public void changerEtat(int position, boolean etat);
        public boolean getEtat(int position);
        public ViewGroup.LayoutParams changementTaille (ViewGroup.LayoutParams lp, boolean etat);

        // Gestion des Tickets afficher
        public ArrayList<Ticket> getTicketsProjet(String typeListe, Liste liste);
        public void commencerAjouterTicket();
        void afficherTicket(int id);

        @RequiresApi(api = Build.VERSION_CODES.O)
        String getDate(Ticket unTicket);

        public ArrayList<Etiquette> getEtiquettes(Ticket unTicket);
        public void dragChangement(int liste);
        public void supprimerToutEtiquetteListe(Ticket ticket);

        // Gestion des options
        void afficherEtiquettes();
        void afficherJalons();
        void afficherListeTickets();
        View afficherMenuPlus();
        AlertDialog.Builder creerAlertMenuPlus();

        // Gestion de la Vue
        public void raffraichirRV();
        public void derouleRV(int x, int y);
        public String internationalisation(int id);
    }

}
