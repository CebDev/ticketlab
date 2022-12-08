package dti.g25.tp1.presentation.contratTableau;

import android.widget.EditText;

/**
 * Contrat Vue Présenteur pour la modification d'un Tableau
 * @author Bryan Valdiviezo
 */
public interface ContratVuePresenteurTableauModifier {
    public interface IVueTableauModifier{}

    public interface IPresenteurTableauModifier{
        public void modifier(EditText titre);
        public void annuler();
        public String TableauTitreModifier();
        public String getProjetTitre();
    }
}
