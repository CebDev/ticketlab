package dti.g25.tp1.presentation.contratProjet;

public interface ContratVuePresenteurProjetListe {

    interface IVueProjetMenu{
        void rafraichir();
        void rafraichirSuppression(int position);
    }

    interface IPresenteurProjetMenu{
        int getNbProjetAfficher();
        int getProjetId(int position);
        String getProjetTitre(int position);
        String getProjetDescription(int position);
        void setList (CharSequence termeRecherche);
        void lancerActiviteAjouterProjet();
        void lancerActiviteAfficherTableaux(int positionProjet);
        void lancerActiviteModifierProjet(int positionProjet);
        void lancerFragmentSuppression(int positionProjet);
    }

}
