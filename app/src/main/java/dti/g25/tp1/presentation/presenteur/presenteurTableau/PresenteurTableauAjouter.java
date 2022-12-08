package dti.g25.tp1.presentation.presenteur.presenteurTableau;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauAjouter;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOProjet;
import dti.g25.tp1.presentation.modele.ModeleTableau;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;
import dti.g25.tp1.ui.activite.ActiviteAjouterTableau;

public class PresenteurTableauAjouter extends PresenteurBase implements ContratVuePresenteurTableauAjouter.IPresenteurTableauAjouter {
    private ModeleTableau _modele;
    private Activity _activite;
    private ContratVuePresenteurTableauAjouter.IVueTableauAjouter _vue;

    public PresenteurTableauAjouter(ModeleTableau _modele, Activity _activite, ContratVuePresenteurTableauAjouter.IVueTableauAjouter _vue){
        super(_activite);

        this._modele = _modele;
        this._activite = _activite;
        this._vue = _vue;
    }

    @Override
    public void ajouter(EditText titre) {
        String nouveauTitre = titre.getText().toString();
        if (nouveauTitre.equals("")) {
            Toast toast=Toast.makeText(_activite,"Vous devez ajouter un titre au nouveau Tableau",Toast.LENGTH_SHORT);
            toast.show();
        } else {
            _modele.creerTableau(new Tableau(0,nouveauTitre, "","", new ArrayList<Liste>(), _modele.getProjetID()));
            Intent intent = new Intent(_activite.getApplicationContext(), ActiviteAfficherTableaux.class);
            intent.putExtra("dti.g25.ticket.projetid", _modele.getProjetID());
            _activite.startActivity(intent);
            _activite.finish();
        }
    }

    @Override
    public String getProjetTitre() {
        String titre = _activite.getResources().getString(R.string.ajout_dans_projet);
        try {
            titre += new DAOProjet().lire(_modele.getProjetID()).getTitre();
        } catch (DAOException e){
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return titre;
    }

    @Override
    public void annuler() {
        Intent intent = new Intent(_activite.getApplicationContext(), ActiviteAfficherTableaux.class);
        intent.putExtra("dti.g25.ticket.projetid", _modele.getProjetID());
        _activite.startActivity(intent);
        _activite.finish();
    }
}
