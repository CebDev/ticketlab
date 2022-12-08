package dti.g25.tp1.presentation.presenteur.presenteurTicket;


import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.domaine.entite.Membre;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleEtiquette;
import dti.g25.tp1.presentation.modele.ModeleJalon;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;
import dti.g25.tp1.ui.activite.ActiviteAfficherTicket;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurModifierTicket;
import dti.g25.tp1.presentation.modele.ModeleTicket;
import dti.g25.tp1.presentation.vue.vueTicket.VueModifierTicket;
import dti.g25.tp1.ui.activite.ActiviteListeTickets;

/**
 * Présenteur pour la modification d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class PresenteurModifierTicket extends PresenteurBase implements ContratVuePresenteurModifierTicket.IPresenteurModifierTicket {
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";
    public static final String EXTRA_AFFICHAGE_LISTE = "dti.g25.ticket.position";

    private Activity _activite;
    private ContratVuePresenteurModifierTicket.IVueModifierTicket _vue;
    private ModeleTicket _modele;

    private Ticket _ticket;
    private ArrayList<Etiquette> _etiquettes =  new ArrayList<Etiquette>();
    private ArrayList<Jalon> _jalons = new ArrayList<>();

    public PresenteurModifierTicket(Activity activite, VueModifierTicket vue, ModeleTicket modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    /**
     * Retourne le titre du ticket
     * @return le titre
     */
    @Override
    public String getTitre() { return _ticket.getTitre(); }

    /**
     * Retourne la description du ticket
     * @return la description
     */
    @Override
    public String getDescription() { return _ticket.getDescription(); }

    /**
     * Retourne le pseudo du membre assigné au ticket
     * @return le peseudo du membre s'il y en a un assigné au ticket sinon retourne null
     */
    @Override
    public String getPseudoMembre() {
        String pseudo = null;

        if (_ticket.getAffectation() != null)
            pseudo = _ticket.getAffectation().getPseudo();

        return pseudo;
    }

    /**
     * Retourne tous les titres des jalons disponible lors de l'édition du ticket
     * @return la liste des titres
     */
    @Override
    public ArrayList<String> getTitresJalons() {
        ArrayList<String> tousJalons = new ArrayList<>();

        tousJalons.add(_activite.getResources().getString(R.string.choix_aucun_jalon));

        for (int i=0; i < _jalons.size(); i++)
            tousJalons.add(_jalons.get(i).getTitre());

        return tousJalons;
    }

    /**
     * Retourne la position du jalon assigné au ticket
     * @return retourne la position du jalon s'il y en a un assigné sinon retourne 0
     */
    @Override
    public int getPositionJalon() {
        int position = 0;

        if (_ticket.getJalon() != null)
            position = _ticket.getJalon().getId();

        return position;
    }

    /**
     * Retourne la date d'échéance en chaîne de charactères
     * @return la date en chaîne de charactètres ou null s'il y en a pas
     */
    @Override
    public String getDateEcheance() {
        String dateEcheanceString = null;

        if (_ticket.getDateEcheance() != null)
            dateEcheanceString = _ticket.getDateEcheance().toString();

        return dateEcheanceString;
    }

    /**
     * Retourne tous les titres des étiquettes disponible lors de l'édition du ticket
     * @return la liste des titres des étiquettes
     */
    @Override
    public List<String> getTitresEtiquettes() {
        ModeleEtiquette.MEtiquette modeleEtiquette = new ModeleEtiquette.MEtiquette();
        List<String> toutesEtiquettes = new ArrayList<>();

        toutesEtiquettes.add(_activite.getResources().getString(R.string.toast_etiquette_ajouter_retirer));

        for (int i=0; i < modeleEtiquette.getEtiquettes().size(); i++)
            toutesEtiquettes.add(modeleEtiquette.getEtiquettes().get(i).getTitre());

        return toutesEtiquettes;
    }

    /**
     * Retourne les étiquettes selectionnées par l'utilisateur
     * @return la liste de étiquettes
     */
    @Override
    public ArrayList<Etiquette> getEtiquettes() { return _etiquettes; }

    // Actions
    /**
     * Ajoute l'étiquette, séléctionnée par sa position entré en paramètre, à la liste des étiquettes choisie et la retire si elle est déjà présente.
     * @param positionEtiquette
     */
    @Override
    public void ajouterEtiquette(int positionEtiquette) {
        ModeleEtiquette.MEtiquette modeleEtiquette = new ModeleEtiquette.MEtiquette();
        Boolean contient = false;

        for (int i=0; i < _etiquettes.size(); i++) {
            if (_etiquettes.get(i).getId() == modeleEtiquette.obtenir(positionEtiquette).getId()) {
                _etiquettes.remove(i);
                contient = true;
            }
        }

        if (!contient)
            _etiquettes.add(modeleEtiquette.obtenir(positionEtiquette));

        _vue.rafraichir();
    }

    /**
     * Initialise ticket à partir de l'identifant entré en paramètre
     * @param unId
     */
    @Override
    public void setTicket(int unId) { _ticket = _modele.obtenir(unId); }

    /** Intialise la liste des etiquettes déjà assingé au ticket */
    @Override
    public void setEtiquettes() {
        if (_ticket.getEtiquettes().size() > 0)
            _etiquettes = _ticket.getEtiquettes();
    }

    /** Initalise la liste de jalons */
    @Override
    public void setJalons() {
        try {
            _jalons = new ModeleJalon.MJalon().getJalons();
        } catch(DAOException e) {
            Log.println(Log.ERROR, "PresenteurModifier", e.getMessage());
        }
    }

    /**
     * Débute la modification du ticket en initalisant les varaibles essentielles
     * @param unId
     */
    @Override
    public void commencerModification(int unId) {
        setTicket(unId);
        setEtiquettes();
        setJalons();
    }

    /**
     * Modifie le ticket avec les nouvelles informations
     *
     * @param unTitre
     * @param uneDescription
     * @param pseudoMembre
     * @param positionJalon
     * @param uneDateEcheance
     */
    @Override
    public void modifier(String unTitre, String uneDescription, String pseudoMembre, int positionJalon, String uneDateEcheance) {
        LocalDate dateEcheance = null;
        Jalon unJalon = null;
        Membre unMembre = null;

        if (!uneDateEcheance.equals(""))
            dateEcheance = LocalDate.parse(uneDateEcheance);

        if (positionJalon > 0)
            unJalon = _jalons.get(positionJalon-1);

        if (!pseudoMembre.equals(""))
            unMembre = new Membre(pseudoMembre);

        _modele.remplacer(new Ticket(_ticket.getId(), unTitre, _etiquettes, uneDescription, unMembre, unJalon, dateEcheance));
        terminerModification();
    }

    /** Retourne à l'affichage du ticket */
    @Override
    public void terminerModification() {
        Intent donneesRetour;

        if (!_activite.getIntent().getBooleanExtra(EXTRA_AFFICHAGE_LISTE, false))
            donneesRetour = new Intent(_activite, ActiviteAfficherTicket.class);
        else
            donneesRetour = new Intent(_activite, ActiviteListeTickets.class);

        donneesRetour.putExtra(EXTRA_ID, _ticket.getId());

        _activite.startActivity(donneesRetour);
        _activite.finish();
    }
}
