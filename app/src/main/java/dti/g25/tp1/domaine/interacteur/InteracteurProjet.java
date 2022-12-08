package dti.g25.tp1.domaine.interacteur;

import java.sql.SQLException;
import java.util.LinkedList;

import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.presentation.modele.DAO.DAOException;

public class InteracteurProjet {

    public Projet creerProjet (String unTitre, String uneDescription) {
        Projet nouveauProjet = new Projet(unTitre);
        nouveauProjet.setDescription(uneDescription);
        return nouveauProjet;
    }

    public LinkedList<Projet> filtrerProjets (LinkedList<Projet> listeProjets, String termeRecherche){
        LinkedList<Projet> listeProjetsFiltrés = new LinkedList<>();
        for (Projet unProjet : listeProjets) {
            if (unProjet.getTitre().toLowerCase().contains(termeRecherche.toLowerCase()))
                listeProjetsFiltrés.add(unProjet);
        }
        return listeProjetsFiltrés;
    }

    public LinkedList<Projet> trierLesProjets (LinkedList<Projet> liste){
        // A implementer
        return liste;
    }
}
