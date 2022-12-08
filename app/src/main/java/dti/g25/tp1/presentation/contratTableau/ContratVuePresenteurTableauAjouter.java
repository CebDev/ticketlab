package dti.g25.tp1.presentation.contratTableau;

import android.widget.EditText;

/**
 * Contrat Vue Présenteur pour l'ajout d'un Tableau
 * @author Bryan Valdiviezo
 */
public interface ContratVuePresenteurTableauAjouter {
    public interface IVueTableauAjouter{}

    public interface IPresenteurTableauAjouter{
        public void ajouter(EditText titre);
        public String getProjetTitre();
        public void annuler();
    }
}
