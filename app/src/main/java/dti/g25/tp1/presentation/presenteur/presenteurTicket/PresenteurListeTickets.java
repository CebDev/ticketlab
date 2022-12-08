package dti.g25.tp1.presentation.presenteur.presenteurTicket;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurListeTickets;
import dti.g25.tp1.presentation.modele.ModeleTicket;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.vueTicket.VueListeTickets;
import dti.g25.tp1.ui.activite.ActiviteAfficherEtiquette;
import dti.g25.tp1.ui.activite.ActiviteAfficherJalon;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;
import dti.g25.tp1.ui.activite.ActiviteAfficherTicket;
import dti.g25.tp1.ui.activite.ActiviteAjouterTicket;

/**
 * Présenteur pour l'affichage de la liste de ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class PresenteurListeTickets extends PresenteurBase implements ContratVuePresenteurListeTickets.IPresenteurListeTicket {
    public static final String EXTRA_POSITION="dti.g25.ticket.position";
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    public static final String AFFICHAGE_LISTE = "dti.g25.ticket.affichage";

    private Activity _activite;
    private ContratVuePresenteurListeTickets.IVueListeTicket _vue;
    private ModeleTicket _modele;
    private int _projetId;
    private int _typeTicket = 0;

    public PresenteurListeTickets(Activity activite, VueListeTickets vue, ModeleTicket modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<Ticket> getTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        ArrayList<Ticket> desTickets = _modele.getTicketsParProjet(_projetId);

        if (_typeTicket == 0) {
            for(int i=0; i < desTickets.size(); i++) {
                if (desTickets.get(i).estOuvert())
                    tickets.add(desTickets.get(i));
            }
        }
        else if (_typeTicket == 1) {
            for (int i=0; i < desTickets.size(); i++)
                if (!desTickets.get(i).estOuvert())
                    tickets.add(desTickets.get(i));
        }
        else {
            tickets = desTickets;
        }

        return tickets;
    }


    @Override
    public void setIdProjet(int unId) { _projetId = unId; }

    // Options menu plus
    @Override
    public void afficherEtiquettes() {
        Intent intent = new Intent(_activite, ActiviteAfficherEtiquette.class);
        intent.putExtra(PROJET_ID,_projetId);

        _activite.startActivity(intent);
    }

    @Override
    public void afficherJalons() {
        Intent intent = new Intent(_activite, ActiviteAfficherJalon.class);

        _activite.startActivity(intent);
    }

    @Override
    public void afficherTableaux() {
        Intent intent = new Intent(_activite, ActiviteAfficherTableaux.class);
        intent.putExtra(PROJET_ID,_projetId);

        _activite.startActivity(intent);
        _activite.finish();
    }

    @Override
    public View afficherMenuPlus() {
        View vue = LayoutInflater.from(this._activite).inflate(
                R.layout.fragment_menu_plus_liste_tickets,
                (ViewGroup) this._activite.findViewById(R.id.fragmentMenuPlus)
        );

        return vue;
    }


    /**
     * @author : Sébastien
     */
    @Override
    public AlertDialog.Builder creerAlertMenuPlus() {
        return new AlertDialog.Builder(this._activite);
    }

    public void ajouterTicket() {
        Intent donneesRetour = new Intent(_activite, ActiviteAjouterTicket.class);
        donneesRetour.putExtra(AFFICHAGE_LISTE, true);

        _activite.startActivity(donneesRetour);
        _vue.rafraichir();
    }

    /**
     * @author : Simon Roy
     */
    @Override
    public void afficherTicket(int id) {
        Intent donneesRetour = new Intent(_activite, ActiviteAfficherTicket.class);
        donneesRetour.putExtra("dti.g25.ticket.id", id);
        donneesRetour.putExtra(PROJET_ID, _projetId);
        donneesRetour.putExtra(AFFICHAGE_LISTE, true);

        _activite.startActivity(donneesRetour);
        _vue.rafraichir();
    }

    @Override
    public void changerTypeTicket(int selectedTabPosition) {
        _typeTicket = selectedTabPosition;
        _vue.rafraichir();
    }

    /**
     * @autor: Simon Roy
     *
     * Retourne Retourne la date si elle est expiré et null si elle ne l'est pas ou si elle n'existe pas
     * @return vrai si la date est expiré, faux si elle ne l'est pas
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String getDate(Ticket unTicket) {
        LocalDate aujourdhui = LocalDate.now();
        String date = null;

        if (unTicket.getDateEcheance() != null && unTicket.getDateEcheance().compareTo(aujourdhui) < 0)
            date = String.format("%s : %s", _activite.getResources().getString(R.string.expire), unTicket.getDateEcheance());

        return date;
    }


    /**
     * Retourne les étiquettes du Ticket donnée en paramètre.
     */
    @Override
    public ArrayList<Etiquette> getEtiquettes(Ticket unTicket) {
        ArrayList<Etiquette> etiquettes = new ArrayList<Etiquette>();

        for (int i=0; i < unTicket.getEtiquettes() .size(); i++)
            etiquettes = unTicket.getEtiquettes();

        return etiquettes;
    }
}
