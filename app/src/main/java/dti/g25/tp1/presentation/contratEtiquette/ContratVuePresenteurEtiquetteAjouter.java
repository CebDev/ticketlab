package dti.g25.tp1.presentation.contratEtiquette;

import dti.g25.tp1.domaine.entite.Etiquette;

public interface ContratVuePresenteurEtiquetteAjouter {

    public interface IVueAjouterEtiquette{
    }

    public interface IPresenteurAjouterEtiquette{
        Etiquette ajouter(String unTitre, String uneDescription, String uneCouleur);
        void terminerAjout();
        int getNbEtiquettes();

    }
}
