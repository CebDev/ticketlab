package dti.g25.tp1.presentation.contratEtiquette;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;

public interface  ContratVuePresenteurEtiquetteListe {

    public interface IVueAfficherEtiquette {
        void rafraichir();
    }

    public interface IPresenteurAfficherEtiquette {
        ArrayList<Etiquette> getEtiquettes();
        String getEtiquetteTitre(int unIndex);
        String getEtiquetteDescription(int unIndex);
        String getEtiquetteCouleur(int unIndex);
        int getNbEtiquette();
        void modifier(int unIndex);
        void ajouter();
    }
}
