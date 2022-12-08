package dti.g25.tp1.presentation.contratListe;

import android.widget.EditText;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Tableau;

public interface ContratVuePresenteurListeNouveau {
    public interface IVueListeNouveau {
    }

    public interface IPresenteurListeNouveau {
        public void ajouterListe();
        public void redirectionVueTableauListe();
        public String getTableauTitre();
        public ArrayList<Etiquette> toutEtiquette();
        public Etiquette getEtiquette(int position);
        public void ajouterLienEtiquetteTableau (boolean lien, int etiquette_id);
    }
}
