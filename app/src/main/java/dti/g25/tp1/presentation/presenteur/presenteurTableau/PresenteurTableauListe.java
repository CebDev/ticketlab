package dti.g25.tp1.presentation.presenteur.presenteurTableau;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauListe;
import dti.g25.tp1.presentation.modele.DAO.DAOEtiquetteTicket;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOListe;
import dti.g25.tp1.presentation.modele.DAO.DAOTicket;
import dti.g25.tp1.presentation.modele.ModeleListe;
import dti.g25.tp1.presentation.modele.ModeleTableau;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherEtiquette;
import dti.g25.tp1.ui.activite.ActiviteAfficherJalon;
import dti.g25.tp1.ui.activite.ActiviteAfficherTicket;
import dti.g25.tp1.ui.activite.ActiviteAjouterListe;
import dti.g25.tp1.ui.activite.ActiviteAjouterTableau;
import dti.g25.tp1.ui.activite.ActiviteAjouterTicket;
import dti.g25.tp1.ui.activite.ActiviteListeTickets;
import dti.g25.tp1.ui.activite.ActiviteModifierTableau;

/**
 * Présenteur pour la Gestion des Tableaux, Listes et Tickets affichées.
 * @author Bryan
 */
public class PresenteurTableauListe extends PresenteurBase implements ContratVuePresenteurTableauListe.IPresenteurTableauMenu {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    public static final String LISTE_ID = "dti.g25.ticket.listeid";
    public static final String TABLEAU_ID = "dti.g25.ticket.tableauid";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TICKET_ID= "ticketID";
    public static final String LISTE_PRESENT_ID="listeID";
    private ContratVuePresenteurTableauListe.IVueTableauMenu _vue;
    private ModeleTableau _modele;
    private ModeleListe _modeleListe;
    private Activity _activite;
    private int idProjet;

    /**
     * Initialise le présenteur avec une vue, un modèle de Tableau et une activité
     * @param _vue : ContratVuePresenteurTableauListe.IVueTableauMenu
     * @param _modele : ModeleTableau
     * @param _actitive : Activity
     */
    public PresenteurTableauListe(ContratVuePresenteurTableauListe.IVueTableauMenu _vue, ModeleTableau _modele, Activity _actitive) {
        super(_actitive);

        this._vue = _vue;
        this._modele = _modele;
        this._activite = _actitive;
        this._modeleListe = new ModeleListe();
        if (_modele.NbTableaux() != 0) {
            this._modeleListe.setListes(this._modele.getTableau(0).getId());
            this.listesEtat();
        }
    }

    /**
     * Défini le ID du Projet affiché.
     * @param idProjet : int
     */
    @Override
    public void setIdProjet(int idProjet){
        this.idProjet = idProjet;
    }


// ---------------------------- Gestion des Tableaux afficher --------------------------------------

    /**
     * Retourne le ID du Tableau affiché
     * @return _modeleListe.getTableauID() : int
     */
    @Override
    public int getTableauIDAfficher() {
        return _modeleListe.getTableauID();
    }

    /**
     * Retourne le nombre de Tableaux dans le Projet affiché
     * @return _modele.NbTableaux();
     */
    @Override
    public int getNbTableaux() {
        return _modele.NbTableaux();
    }

    /**
     * Retourne le Tableau situé à la position donnée en paramètre.
     * @param position : int
     * @return _modele.getTableau(position) : Tableau
     */
    @Override
    public Tableau getTableau(int position) {
        return _modele.getTableau(position);
    }

    /**
     * Retourne le titre du Tableau situé à la position donnée en paramètre.
     * @param position : int
     * @return _modele.getTableau(position).getTitre() : String
     */
    @Override
    public String getTableauxTitre(int position) {
        return _modele.getTableau(position).getTitre();
    }

    /**
     * Supprime le Tableau donnée en paramètre avec un message d'avertissement
     * @param tableau : Tableau
     */
    @Override
    public void supprimerTableau(final Tableau tableau) {
        AlertDialog.Builder constructeur = new AlertDialog.Builder(_activite);
        View view = LayoutInflater.from(_activite).inflate(
                R.layout.avertissement_tableau,
                (ViewGroup) _activite.findViewById(R.id.conteneurAvertissement)
        );
        constructeur.setView(view);
        final AlertDialog alertDialog = constructeur.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        view.findViewById(R.id.procede).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _modele.supprimerTableau(tableau);
                alertDialog.dismiss();
                _vue.rafraichir();
            }
        });

        view.findViewById(R.id.annuler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    /**
     * Envoi vers l'activité d'ajout de Tableau.
     */
    @Override
    public void creationTableau() {
        Intent intent = new Intent(_activite.getApplicationContext(), ActiviteAjouterTableau.class);
        intent.putExtra(PROJET_ID, _modele.getProjetID());
        _activite.startActivity(intent);
        _activite.finish();
    }

    /**
     * Envoi vers l'activité de modification de Tableau en envoyant le tableau mis en paramètre à
     * modifier
     * @param tableau : Tableau
     */
    @Override
    public void modiferTableau(Tableau tableau) {
        Intent intent = new Intent(_activite.getApplicationContext(), ActiviteModifierTableau.class);
        intent.putExtra(TABLEAU_ID, tableau.getId());
        _activite.startActivity(intent);
        _activite.finish();
    }


// ---------------------------- Gestion des Listes afficher --------------------------------------

    /**
     * Affiche les Listes du Tableau se trouvant à la position donnée en paramètre.
     * @param position : int
     */
    @Override
    public void setNouvelleListe(int position) {
        _modeleListe.setListes(_modele.getTableau(position).getId());
        _vue.rafraichir();
    }

    /**
     * Vérifie si le id mis en paramètre appartient à une Liste ouverte, fermer ou autre et retourne
     * la liste trouver.
     * @param id : int
     * @return liste : Liste
     */
    @Override
    public Liste estOuvertFermer(int id){
        Liste liste = null;
        if (id == -1) {
            liste = new Liste("Ouvert", "");
            liste.setId(-1);
        } else if (id == -2) {
            liste = new Liste("Fermé","");
            liste.setId(-2);
        } else {
            try {
                liste = new DAOListe().lire(id);
            } catch (DAOException e){
                e.printStackTrace();
                Log.e("Erreur DAO", e.getMessage());
            }
        }
        return liste;
    }

    /**
     * Retourne le nombre de Liste afficher.
     * @return _modeleListe.NbListe()
     */
    @Override
    public int getNbListes() {
        return _modeleListe.NbListe();
    }

    /**
     * Retourne la Liste qui se trouve dans la position donnée en paramètre
     * @param position : int
     * @return _modeleListe.getListe(position)
     */
    @Override
    public Liste getListe(int position) {
        return _modeleListe.getListe(position);
    }

    /**
     * Retourne le titre de la Liste qui se trouve dans la position donnée en paramètre
     * @param position : int
     * @return _modeleListe.getListe(position).getTitre() : String
     */
    @Override
    public String getListeTitre(int position) {
        return _modeleListe.getListe(position).getTitre();
    }

    /**
     * Retourne la couleur de la Liste qui se trouve dans la position donnée en paramètre
     * @param position : int
     * @return _modeleListe.getListe(position).getCouleur() : String
     */
    @Override
    public String getListeCouleur(int position) {
        return _modeleListe.getListe(position).getCouleur();
    }

    /**
     * Envoi vers l'activité d'ajout de Liste en envoyant le Tableau dans lequel celui se trouve.
     * @param tableau : Tableau
     */
    @Override
    public void gestionListe(Tableau tableau) {
        Intent intent = new Intent(_activite.getApplicationContext(), ActiviteAjouterListe.class);
        intent.putExtra(PROJET_ID, idProjet);
        intent.putExtra(TABLEAU_ID, tableau.getId());
        _activite.startActivity(intent);
        _activite.finish();
    }

    /**
     * Supprime la Liste donnée en paramètre en envoyant un avertissement.
     * @param liste : Liste
     */
    @Override
    public void supprimerListe(final Liste liste) {
        AlertDialog.Builder constructeur = new AlertDialog.Builder(_activite);
        View view = LayoutInflater.from(_activite).inflate(
                R.layout.avertissement_liste,
                (ViewGroup) _activite.findViewById(R.id.conteneurAvertissement)
        );
        constructeur.setView(view);
        final AlertDialog alertDialog = constructeur.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        view.findViewById(R.id.procede).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _modeleListe.lienTableauEtiquette(false, liste.getId());
                alertDialog.dismiss();
                _vue.rafraichir();
            }
        });

        view.findViewById(R.id.annuler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    @Override
    public String titreVertical(String titre) {
        String titreVertical = "";
        for (int i = 0; i < titre.length() ; i++)
            titreVertical += titre.charAt(i)+"\n";
        return titreVertical;
    }

    @Override
    public void listesEtat() {
        ArrayList<Boolean> etat = new ArrayList<Boolean>();
        for (int i = 0; i <_modeleListe.Listes().size() ; i++)
            etat.add(true);
        _modeleListe.setListeEtat(etat);
    }

    @Override
    public void testEtat() {
        Log.e("Test", _modeleListe.testEtat());
    }

    @Override
    public void changerEtat(int position, boolean etat) {
        _modeleListe.changerEtat(position, etat);
    }

    @Override
    public boolean getEtat(int position) {
        return _modeleListe.getEtat(position);
    }

    @Override
    public ViewGroup.LayoutParams changementTaille (ViewGroup.LayoutParams lp, boolean etat) {
        if (etat) {
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        } else {
            lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        }
        return lp;
    }


// ---------------------------- Gestion des Tickets afficher --------------------------------------
    /**
     * Retourne les Tickets associés au type de Liste donnée en paramètre ou la Liste avec celle que
     * les Tickets sont associés
     *
     * @param typeListe : String
     * @param liste : Liste
     * @return tickets : ArrayList<Ticket>
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<Ticket> getTicketsProjet(String typeListe, Liste liste) {
        ArrayList<Ticket> tickets;
        try {
            if (typeListe.equals("ouvert")){
               return  _modeleListe.estOuvert(new DAOTicket().trouverParProjet(_modele.getProjetID()));
            } else if (typeListe.equals("fermer")) {
                return _modeleListe.estFermer(new DAOTicket().trouverParProjet(_modele.getProjetID()));
            } else {
                return _modeleListe.lienListeTicket(liste);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return null;
    }


    /**
     * @author : Simon Roy
     */
    @Override
    public void commencerAjouterTicket() {
        Intent intent = new Intent(_activite, ActiviteAjouterTicket.class);
        intent.putExtra(PROJET_ID, _modele.getProjetID());

        _activite.startActivity(intent);
        _activite.finish();
    }

    /**
     * @author : Simon Roy
     */
    @Override
    public void afficherTicket(int id) {
        Intent donneesRetour = new Intent(_activite, ActiviteAfficherTicket.class);
        donneesRetour.putExtra("dti.g25.ticket.id", id);
        donneesRetour.putExtra(PROJET_ID, _modele.getProjetID());

        _activite.startActivity(donneesRetour);
        _activite.finish();
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

    /**
     * Bouge un Ticket à la Liste qui correspond au ID donnée en paramètre.
     * -1 : Liste Ouvert
     * -2 : Liste Fermer
     * Default : Liste autre.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void dragChangement(int liste) {
        Ticket ticket = null;
        Liste listeDestination = null;
        Liste listePresente = null;
        int ticketID;
        int listePresenteID;
        SharedPreferences sharedPreferences = _activite.getSharedPreferences(SHARED_PREFS, _activite.MODE_PRIVATE);
        ticketID = sharedPreferences.getInt(TICKET_ID,0);
        listePresenteID = sharedPreferences.getInt(LISTE_PRESENT_ID,0);
        try {
            listePresente = estOuvertFermer(listePresenteID);
            ticket = new DAOTicket().lire(ticketID);
            listeDestination = estOuvertFermer(liste);
            switch (listePresente.getId()){
                case -1:
                    if (listeDestination.getId() == -1)
                        Log.e("AnnulerDrag", "Drag Annuler");
                    else if (listeDestination.getId() == -2) {
                        ticket.fermer();
                        supprimerToutEtiquetteListe(ticket);
                        new DAOTicket().modifier(ticket);
                    } else {
                        if (new DAOEtiquetteTicket().TicketEtiquetteExiste(listeDestination.getId(), ticket.getId()))
                            Log.e("AnnulerDrag", "Ticket dest deja dans cette liste");
                        else
                            new DAOEtiquetteTicket().ajouterTicketEtiquette(listeDestination.getId(), ticket.getId());
                    }
                    break;

                case -2:
                    if (listeDestination.getId() == -2)
                        Log.e("AnnulerDrag", "Drag Annuler");
                    else if (listeDestination.getId() == -1){
                        ticket.ouvrir();
                        new DAOTicket().modifier(ticket);
                    } else {
                        ticket.ouvrir();
                        new DAOTicket().modifier(ticket);
                        if (!new DAOEtiquetteTicket().TicketEtiquetteExiste(listeDestination.getId(), ticket.getId()))
                            new DAOEtiquetteTicket().ajouterTicketEtiquette(listeDestination.getId(),ticket.getId());
                    }

                    break;
                default:
                    if (listeDestination.getId() == -1)
                        new DAOEtiquetteTicket().supprimerTicketEtiquette(listePresente.getId(), ticket.getId());
                    else if (listeDestination.getId() == -2) {
                        ticket.fermer();
                        new DAOTicket().modifier(ticket);
                        supprimerToutEtiquetteListe(ticket);
                    } else {
                        new DAOEtiquetteTicket().supprimerTicketEtiquette(listePresente.getId(), ticket.getId());
                        new DAOEtiquetteTicket().ajouterTicketEtiquette(listeDestination.getId(), ticket.getId());
                    }
                    break;
            }
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        raffraichirRV();
    }

    /**
     * Supprime tous les liens du Ticket donnée en paramètre avec une étiquettes de type Liste si le
     * Ticket est bougé dans une Liste fermé.
     * @param ticket : Ticket
     */
    @Override
    public void supprimerToutEtiquetteListe(Ticket ticket) {
        try {
            ArrayList<Etiquette> listes = new ArrayList<Etiquette>();
            ArrayList<Etiquette> etiquettes = new DAOEtiquetteTicket().trouverToutParTicket(ticket.getId());
            for (int i = 0; i < etiquettes.size(); i++) {
                if (etiquettes.get(i).estListe())
                    listes.add(etiquettes.get(i));
            }
            if (listes.size() != 0) {
                for (int i = 0; i < listes.size() ; i ++) {
                    new DAOEtiquetteTicket().supprimerTicketEtiquette(listes.get(i).getId(), ticket.getId());
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

// ---------------------------- Gestion des Options afficher --------------------------------------
    /**
     * @author : Simon Roy
     */
    @Override
    public void afficherEtiquettes() {
        Intent intent = new Intent(_activite, ActiviteAfficherEtiquette.class);

        _activite.startActivity(intent);
    }

    /**
     * @author : Simon Roy
     */
    @Override
    public void afficherJalons() {
        Intent intent = new Intent(_activite, ActiviteAfficherJalon.class);

        _activite.startActivity(intent);
    }

    @Override
    public void afficherListeTickets() {
        Intent intent = new Intent(_activite, ActiviteListeTickets.class);
        intent.putExtra(PROJET_ID, idProjet);

        _activite.startActivity(intent);
    }

    /**
     * @author : Simon Roy
     */
    @Override
    public View afficherMenuPlus() {
        View vue = LayoutInflater.from(this._activite).inflate(
                R.layout.fragment_menu_plus,
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
// ---------------------------- Gestion de la Vue --------------------------------------
    /**
     * Raffraichi les liste de Tableaux et de Listes.
     */
    @Override
    public void raffraichirRV() {
        _vue.rafraichir();
    }

    @Override
    public void derouleRV(int x, int y) {
        _vue.derouleRV(x, y);
    }

    @Override
    public String internationalisation(int id){
        return _activite.getResources().getString(id);
    }
}