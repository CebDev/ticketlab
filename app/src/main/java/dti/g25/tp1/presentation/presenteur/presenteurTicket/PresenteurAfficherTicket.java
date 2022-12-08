package dti.g25.tp1.presentation.presenteur.presenteurTicket;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteModifierTicket;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurAfficherTicket;
import dti.g25.tp1.presentation.modele.ModeleTicket;

/**
 * Présenteur pour l'affichage d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class PresenteurAfficherTicket extends PresenteurBase implements ContratVuePresenteurAfficherTicket.IPresenteurAfficherTicket {
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";
    public static final String EXTRA_AFFICHAGE_LISTE = "dti.g25.ticket.affichage";

    private Ticket _ticket;

    private Activity _activite;
    private ContratVuePresenteurAfficherTicket.IVueAfficherTicket _vue;
    private ModeleTicket _modele;

    public PresenteurAfficherTicket(Activity activite, ContratVuePresenteurAfficherTicket.IVueAfficherTicket vue, ModeleTicket modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    /**
     * Intialise le ticket avec l'identifiant passé en paramètre.
     * @param unId
     */
    @Override
    public void setTicket(int unId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            _ticket = _modele.obtenir(unId);
        }
    }

    /**
     * Retourne le titre formaté du ticket
     * @return titre : String
     */
    @Override
    public String getTitre() { return String.format("#%d - %s", _ticket.getId(), _ticket.getTitre()); }

    /**
     * Retourne le status formaté dans la langue local du ticket et met le ticket en mode fermé s'il est fermé.
     * @return ouvert si le ticket est ouvert ou fermé si le ticket est ferm
     */
    @Override
    public String getStatus() {
        String status = _activite.getResources().getString(R.string.ouvert);

        if (!_ticket.estOuvert()) {
            _vue.fermer();
            status = _activite.getResources().getString(R.string.ferme);
        }

        return status;
    }

    /**
     * Retourne la description du ticket.
     * @return La description du ticket.
     */
    @Override
    public String getDescription() { return _ticket.getDescription(); }

    /**
     * Retournne le pseudo du memmbre assigné.
     * @return Le pseudo du membre ou null si aucun n'est assigné
     */
    @Override
    public String getPseudoMembre() {
        String pseudo = null;

        if (_ticket.getAffectation() != null)
            pseudo = _ticket.getAffectation().getPseudo();

        return pseudo;
    }

    /**
     * Retourne les étiquettes du ticket
     * @return la liste des etiquettes du ticket
     */
    @Override
    public ArrayList<Etiquette> getEtiquettes() { return _ticket.getEtiquettes(); }

    /**
     * Retourne le titre du jalon assigné au ticket
     * @return le titre du jalon ou null si aucun jalon n'est assigné
     */
    @Override
    public String getTitreJalon() {
        String titreJalon = null;

        if (_ticket.getJalon() != null)
            titreJalon = _ticket.getJalon().getTitre();

        return titreJalon;
    }

    /**
     * Retourne la date d'échance du ticket formaté en chaine de charactère (AAAA-MM-JJ).
     * @return la date d'échance ou null si aucune date n'est assigné
     */
    @Override
    public String getDateEcheance() {
        String dateEcheanceString = null;

        if (_ticket.getDateEcheance() != null)
            dateEcheanceString = String.format("%s : %s", _activite.getResources().getText(R.string.date_echeance), _ticket.getDateEcheance().toString().split("T")[0]);

        return dateEcheanceString;
    }

    // Actions

    /**
     * Retourne vrai ou faux si la date est expiré ou non
     * @return vrai si la date est expiré, faux si elle ne l'est pas
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean estExpire() {
        LocalDate aujourdhui = LocalDate.now();
        boolean estExpire = false;

        if (_ticket.getDateEcheance() != null && _ticket.getDateEcheance().compareTo(aujourdhui) < 0)
            estExpire = true;

        return estExpire;
    }

    /** Ferme le ticket et rafraichie l'activité */
    @Override
    public void fermer() {
        Ticket ticket = _ticket;

        ticket.fermer();
        _modele.remplacer(ticket);
        rafraichir();
    }

    /** Rouvre le ticket et rafraichie l'activité */
    @Override
    public void rouvrir() {
        Ticket ticket = _ticket;

        ticket.ouvrir();
        _modele.remplacer(ticket);
        rafraichir();
    }

    /** Envois vers l'activité de modification du ticket */
    @Override
    public void modifier() {
        Intent intentModifier = new Intent(_activite, ActiviteModifierTicket.class);
        intentModifier.putExtra(EXTRA_ID, _ticket.getId());
        intentModifier.putExtra(EXTRA_AFFICHAGE_LISTE, _activite.getIntent().getBooleanExtra(EXTRA_AFFICHAGE_LISTE, false));

        _activite.startActivity(intentModifier);
        _activite.finish();
    }

    /** Ferme et rouvre l'activité pour rafraichire les informations sur le ticket */
    @Override
    public void rafraichir() {
        _activite.finish();
        _activite.startActivity(_activite.getIntent());
    }
}
