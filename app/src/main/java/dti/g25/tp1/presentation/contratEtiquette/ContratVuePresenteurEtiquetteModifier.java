package dti.g25.tp1.presentation.contratEtiquette;

import dti.g25.tp1.domaine.entite.Etiquette;

public interface ContratVuePresenteurEtiquetteModifier {
    public interface IVueModifierEtiquette {

    }

    public interface IPresenteurModifierEtiquette {
        void commencerModification(String unTitre, int unIndex);
        void modifier(int unId, String unTitre, String uneDescription, String uneCouleur);
        void terminerModification();

        Etiquette getEtiquette();
        
    }
}
