package dti.g25.tp1.presentation.presenteur.presenteurJalon;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.presentation.contratJalon.ContratVuePresenteurJalonListe;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleJalon;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAjouterJalon;
import dti.g25.tp1.ui.activite.ActiviteModifierJalon;

public class PresenteurAfficherJalon extends PresenteurBase implements ContratVuePresenteurJalonListe.IPresenteurAfficherJalon {
    public static final String EXTRA_STRING = "dti.g25.jalon.string";
    public static final String EXTRA_POSITION = "dti.g25.jalon.position";
    public static final String PROJET_ID = "dti.g25.ticket.projetid";

    private int idProjet;
    private int _position = 0;
    private Activity _activite;
    private ContratVuePresenteurJalonListe.IVueAfficherJalon _vue;
    private ModeleJalon.MJalon _modele;

    public PresenteurAfficherJalon(Activity activite, ContratVuePresenteurJalonListe.IVueAfficherJalon vue, ModeleJalon.MJalon modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    public void setIdProjet(int idProjet){
        this.idProjet = idProjet;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String getJalonTitre(int unIndex) { return _modele.obtenir(unIndex).getTitre(); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public String getJalonDescription(int unIndex){
        return _modele.obtenir(unIndex).getDescription();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public LocalDate getJalonDateDebut(int unIndex) {return _modele.obtenir(unIndex).getDateDebut(); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public LocalDate getJalonDateFin(int unIndex) {return _modele.obtenir(unIndex).getDateFin(); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int getNbJalon() throws DAOException { return _modele.taille(); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void supprimer() { _modele.retirer(_position); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void modifier(int unIndex) {
        Intent intent = new Intent(_activite, ActiviteModifierJalon.class);
        intent.putExtra(EXTRA_STRING, _modele.obtenir(unIndex).getId());
        intent.putExtra(EXTRA_POSITION, unIndex);
        intent.putExtra(PROJET_ID, idProjet);
        _activite.startActivity(intent);
        _activite.finish();
    }

    public void ajouter() {
        Intent intent = new Intent(_activite, ActiviteAjouterJalon.class);
        intent.putExtra(PROJET_ID, idProjet);
        _activite.startActivity(intent);
        _activite.finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public ArrayList<Jalon> getJalons() throws DAOException {return _modele.getJalons();}

}
