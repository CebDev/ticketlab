package dti.g25.tp1.presentation.presenteur.presenteurTableau;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauModifier;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOProjet;
import dti.g25.tp1.presentation.modele.ModeleTableau;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;

public class PresenteurTableauModifier extends PresenteurBase implements ContratVuePresenteurTableauModifier.IPresenteurTableauModifier {
    public static final String PROJET_ID = "dti.g25.ticket.projetid";
    private ContratVuePresenteurTableauModifier.IVueTableauModifier _vue;
    private Activity _activite;
    private ModeleTableau _modele;

    public PresenteurTableauModifier(ContratVuePresenteurTableauModifier.IVueTableauModifier _vue, Activity _activite, ModeleTableau _modele){
        super(_activite);

        this._vue = _vue;
        this._activite = _activite;
        this._modele = _modele;
    }

    @Override
    public void modifier(EditText titre) {
        String titreModifier = titre.getText().toString();
        if (titreModifier.equals("")) {
            Toast toast=Toast.makeText(_activite,"Vous devez ajouter un nouveau titre au tableau à modifier",Toast.LENGTH_SHORT);
            toast.show();
        } else {
            Tableau tableauModifier = _modele.getUnTableau();
            tableauModifier.setTitre(titreModifier);
            _modele.modifierTableau(tableauModifier);
            Intent intent = new Intent(_activite.getApplicationContext(), ActiviteAfficherTableaux.class);
            intent.putExtra(PROJET_ID, _modele.getUnTableau().getProjetID());
            _activite.startActivity(intent);
            _activite.finish();
        }
    }

    @Override
    public void annuler() {
        Intent intent = new Intent(_activite.getApplicationContext(), ActiviteAfficherTableaux.class);
        intent.putExtra(PROJET_ID, _modele.getUnTableau().getProjetID());
        _activite.startActivity(intent);
        _activite.finish();
    }

    @Override
    public String TableauTitreModifier() {
        return _activite.getResources().getString(R.string.modification_tableau_titre)+_modele.getUnTableau().getTitre();
    }

    @Override
    public String getProjetTitre() {
        String titre = _activite.getResources().getString(R.string.modification_tableau_projet_titre);
        try {
            titre += new DAOProjet().lire(_modele.getUnTableau().getProjetID()).getTitre();
        } catch (DAOException e){
            e.printStackTrace();
            Log.e("Erreur DAO", e.getMessage());
        }
        return titre;
    }
}
