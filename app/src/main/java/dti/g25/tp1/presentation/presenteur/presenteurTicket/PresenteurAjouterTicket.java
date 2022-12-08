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
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurAjouterTicket;
import dti.g25.tp1.presentation.modele.ModeleTicket;
import dti.g25.tp1.ui.activite.ActiviteListeTickets;

/**
 * Présenteur pour l'ajout d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class PresenteurAjouterTicket extends PresenteurBase implements ContratVuePresenteurAjouterTicket.IPresenteurAjouterTicket {
    public static final String EXTRA_POSITION="dti.g25.ticket.position";
    public static final String EXTRA_ID="dti.g25.ticket.id";
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    public static final String EXTRA_AFFICHAGE_LISTE = "dti.g25.ticket.position";

    private Activity _activite;
    private ContratVuePresenteurAjouterTicket.IVueAjouterTicket _vue;
    private ModeleTicket _modele;

    private ArrayList<Jalon> _jalons = new ArrayList<>();
    private ArrayList<Etiquette> _etiquettes = new ArrayList<Etiquette>();
    private int _idProjet;

    public PresenteurAjouterTicket(Activity activite, ContratVuePresenteurAjouterTicket.IVueAjouterTicket vue, ModeleTicket modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    // Accesseur
    /**
     * Retourne tous les titres des jalons disponnible et initialise la liste de jalons
     * @return
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
     * Retourne tous les titres des etiquettes.
     * @return
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
     * Retourne toutes les etiquettes choisies
     * @return les etiquettes choisies
     */
    @Override
    public ArrayList<Etiquette> getEtiquettes() { return _etiquettes; }

    // Mutateur
    /**
     * Initialise l'identifiant du projet
     * @param unIdProjet
     */
    @Override
    public void setIdProjet(int unIdProjet) { _idProjet = unIdProjet; }

    /** Initialise la liste de jalons disponnible au moment de la création */
    @Override
    public void setJalons() {
        try {
            _jalons = new ModeleJalon.MJalon().getJalons();
        } catch(DAOException e) {
            Log.println(Log.ERROR, "PresenteurModifier", e.getMessage());
        }
    }

    // Actions
    /**
     * Ajoute une etiquette à la liste des étiquettes choisies pour le ticket et rafraichie la liste des etiquettes
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
     * Commence l'ajout du ticket en initalisant les variables éssentieles
     * @param unIdProjet
     */
    public void commencerAjout(int unIdProjet) {
        setIdProjet(unIdProjet);
        setJalons();
    }

    /**
     * Ajoute le ticket
     *
     * @param titre
     * @param description
     * @param pseudoMembre
     * @param positionJalon
     * @param uneDateEcheance
     */
    @Override
    public void ajouter(String titre, String description, String pseudoMembre, int positionJalon, String uneDateEcheance) {
        LocalDate dateEcheance = null;
        Jalon unJalon = null;
        Membre unMembre = null;

        Log.println(Log.ERROR, "MODIFIER", uneDateEcheance);
        if (!uneDateEcheance.equals(""))
            dateEcheance = LocalDate.parse(uneDateEcheance);

        if (positionJalon > 0)
            unJalon = _jalons.get(positionJalon-1);

        if (!pseudoMembre.equals(""))
            unMembre = new Membre(pseudoMembre);

        _modele.ajouter(new Ticket(-1, titre, _etiquettes, description, unMembre, unJalon, dateEcheance), _idProjet);

        terminerAjout();
    }

    /**
     * Retourne à l'activité du projet
     */
    @Override
    public void terminerAjout() {
        Intent activite;

        if (!_activite.getIntent().getBooleanExtra(EXTRA_AFFICHAGE_LISTE, false))
            activite = new Intent(_activite, ActiviteAfficherTableaux.class);
        else
            activite = new Intent(_activite, ActiviteListeTickets.class);

        activite.putExtra(PROJET_ID, _idProjet);

        _activite.startActivity(activite);
        _activite.finish();
    }
}
