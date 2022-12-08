package dti.g25.tp1.presentation.presenteur.presenteurListe;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.presentation.contratListe.ContratVuePresenteurListeNouveau;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOTableau;
import dti.g25.tp1.presentation.modele.ModeleListe;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;
import dti.g25.tp1.ui.activite.ActiviteAjouterListe;

public class PresenteurListeNouveau extends PresenteurBase implements ContratVuePresenteurListeNouveau.IPresenteurListeNouveau {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    ModeleListe _modele;
    Activity _activity;
    ContratVuePresenteurListeNouveau.IVueListeNouveau _vue;

    public PresenteurListeNouveau(ModeleListe _modele, Activity _activity, ContratVuePresenteurListeNouveau.IVueListeNouveau _vue) {
        super(_activity);

        this._activity = _activity;
        this._modele = _modele;
        this._vue = _vue;
    }

    @Override
    public void ajouterListe() {

    }

    @Override
    public void redirectionVueTableauListe() {
        Intent intent = new Intent(_activity, ActiviteAfficherTableaux.class);
        intent.putExtra(PROJET_ID, _modele.getTableau().getProjetID());
        _activity.startActivity(intent);
        _activity.finish();
    }

    @Override
    public String getTableauTitre() {
        String tableauID = "";
        try {
            tableauID+=new DAOTableau().lire(_modele.getTableauID()).getTitre();
        } catch (DAOException e) {
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return tableauID;
    }

    @Override
    public ArrayList<Etiquette> toutEtiquette() {
        return _modele.toutEtiquette();
    }

    @Override
    public Etiquette getEtiquette(int position) {
        return _modele.getEtiquette(position);
    }

    @Override
    public void ajouterLienEtiquetteTableau (boolean lien, int etiquette_id) {
        _modele.lienTableauEtiquette(lien, etiquette_id);
    }
}
