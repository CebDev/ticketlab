package dti.g25.tp1.presentation.modele;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.modele.DAO.DAOEtiquette;
import dti.g25.tp1.presentation.modele.DAO.DAOEtiquetteTicket;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOListe;
import dti.g25.tp1.presentation.modele.DAO.DAOTableau;
/**
 * Modèle pour les Listes
 * @author Bryan
 */
public class ModeleListe {
    DAOListe dao = new DAOListe();
    private ArrayList<Liste> listes;
    private ArrayList<Boolean> listeEtat;
    private int tableauID;
    private Liste uneListe;

    /**
     * Défini le ID du Tableau affichée dans la Vue et défini les Listes à affiché dans la Vue selon
     * le ID de tableau donnée.
     * @param tableauID : int
     */
    public void setListes(int tableauID) {
        this.tableauID = tableauID;
        try {
            this.listes = dao.chercherTout(tableauID);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    /**
     * Défini la Liste à modifier dans la Vue
     * @param listeID : int
     */
    public void setUneListe(int listeID) {
        try {
            uneListe = dao.lire(listeID);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    /**
     * Retourne la Liste affiché dans la Vue Modifier
     * @return uneListe : Liste
     */
    public Liste getUneListe(){
        return uneListe;
    }

    /**
     * Retourne les Listes associées à l'id du Tableau défini.
     * @return listes : ArrayList<Liste>
     */
    public ArrayList<Liste> Listes () {
        try {
            listes = dao.chercherTout(tableauID);
            return listes;
        } catch (DAOException e){
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return listes;
    }

    /**
     * Retourne la quantité de Liste associée au Tableau montrée dans la Vue
     * @return Listes().size() : int
     */
    public int NbListe () {
        return Listes().size();
    }

    /**
     * Retourne la Liste affichée dans la vue qui se trouve dans la position donnée en paramètre
     * @param position : int
     * @return Listes().get(position) : Liste
     */
    public Liste getListe (int position) {
        return Listes().get(position);
    }

    /**
     * Retourne le ID du tableau affichée dans la Vue.
     * @return tableauID : int
     */
    public int getTableauID (){
        return tableauID;
    }

    /**
     * Retourne le Tableau dans lequel la Liste à modifier se trouve.
     * @return tableau : Tableau
     */
    public Tableau getTableau() {
        Tableau tableau = null;
        try {
            tableau = new DAOTableau().lire(tableauID);
        } catch (DAOException e) {

        }

        return tableau;
    }

    /**
     * Ajoute la Liste donnée en paramètre.
     * @param liste : Liste
     */
    public void ajouterListe (Liste liste) {
        try {
            dao.ajouter(liste);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    /**
     * Supprime la Liste donnée en paramètre
     * @param liste : Liste
     */
    public void supprimerListe (Liste liste) {
        try {
            dao.supprimer(liste);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    /**
     * Modifie la Liste donnée en paramètre.
     * @param listeModifier : Liste
     */
    public void modifier(Liste listeModifier) {
        try {
            dao.modifier(listeModifier);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    /**
     * Retourne les tickets associés à la Liste donnée en paramètre
     * @param liste : Liste
     * @return tickets : ArrayList<Ticket>
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Ticket> lienListeTicket(Liste liste) {
        ArrayList<Ticket> tickets = null;
        try {
            tickets = new DAOEtiquetteTicket().trouverTicketParListe(liste.getId());
            for (int i = 0; i<tickets.size() ; i++) {
                if (tickets.get(i).estOuvert() == false)
                    tickets.remove(i);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return tickets;
    }

    /**
     * Trie la liste de tickets donnée en paramètre et retourne les tickets ouvert.
     * @param projetTickets : ArrayList<Ticket>
     * @return tickets : ArrayList<Ticket>
     */
    public ArrayList<Ticket> estOuvert(ArrayList<Ticket> projetTickets) {
        ArrayList<Ticket> ticketsOuvert = new ArrayList<Ticket>();
        for (int i = 0; i < projetTickets.size() ; i++) {
            if (projetTickets.get(i).estOuvert() == true) {
                ticketsOuvert.add(projetTickets.get(i));
            }
        }
        return ticketsOuvert;
    }

    /**
     * Trie la liste de tickets donnée en paramètre et retourne les tickets fermé.
     * @param projetTickets : ArrayList<Ticket>
     * @return tickets : ArrayList<Ticket>
     */
    public ArrayList<Ticket> estFermer(ArrayList<Ticket> projetTickets) {
        ArrayList<Ticket> ticketsFermer = new ArrayList<Ticket>();
        for (int i = 0; i < projetTickets.size() ; i++) {
            if (projetTickets.get(i).estOuvert() == false) {
                ticketsFermer.add(projetTickets.get(i));
            }
        }
        return ticketsFermer;
    }

    public ArrayList<Etiquette> toutEtiquette() {
        ArrayList<Etiquette> etiquettes = null;
        try {
            etiquettes = new DAOEtiquette().trouverTout();
            for (int i = 0; i < etiquettes.size(); i++) {
                if (dao.tableauListeExiste(etiquettes.get(i).getId(), tableauID))
                    etiquettes.get(i).setEstListe(true);
            }
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return etiquettes;
    }

    public Etiquette getEtiquette(int position) {
        return toutEtiquette().get(position);
    }

    public void lienTableauEtiquette(boolean lien, int etiquette) {
        try {
            if (lien)
                dao.ajouterLien(etiquette, tableauID);
            else
                dao.supprimerLien(etiquette,tableauID);
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
    }

    public void setListeEtat(ArrayList<Boolean> etat) {
        listeEtat = etat;
    }

    public String testEtat() {
        String booleanString = "";
        for (int i = 0 ; i < listeEtat.size() ; i++)
            booleanString+= listeEtat.get(i)+", ";
        return booleanString;
    }

    public void changerEtat(int position, boolean etat) {
        listeEtat.set(position, etat);
    }

    public boolean getEtat(int position) {
        return listeEtat.get(position);
    }
}
