package dti.g25.tp1.presentation.presenteur.presenteurJalon;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;

import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.presentation.contratJalon.ContratVuePresenteurJalonAjouter;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleJalon;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherJalon;

public class PresenteurAjouterJalon extends PresenteurBase implements ContratVuePresenteurJalonAjouter.IPresenteurAjouterJalon {
    public static final String EXTRA_POSITION="dti.g25.jalon.position";


    private Activity _activite;
    private ContratVuePresenteurJalonAjouter.IVueAjouterJalon _vue;
    private ModeleJalon.MJalon _modele;
    private int idProjet;

    private int _position;

    public PresenteurAjouterJalon(Activity activite, ContratVuePresenteurJalonAjouter.IVueAjouterJalon vue, ModeleJalon.MJalon modele) {
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    public void setIdProjet(int idProjet){
        this.idProjet = idProjet;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ajouter(int unId, String unTitre, String uneDescription, String uneDateDebut, String uneDateFin) {
         LocalDate dateDebut = null;
         LocalDate dateFin = null;

         if (!uneDateDebut.equals(""))
             dateDebut = LocalDate.parse(uneDateDebut);

         if (!uneDateFin.equals(""))
             dateFin = LocalDate.parse(uneDateFin);

        try {
            Log.d("ID PROJET", "ajouter: " + idProjet);
            _modele.ajouter(new Jalon(-1, unTitre, uneDescription, dateDebut, dateFin, idProjet));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        terminerAjout();
    }

    @Override
    public void terminerAjout() {
        Intent donneesRetour = new Intent(_activite, ActiviteAfficherJalon.class);
        _activite.startActivity(donneesRetour);
        _activite.finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Jalon getJalon() { return _modele.obtenir(_position); }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int getNbJalons() {
        int nbrJalons = 0;
        try {
            nbrJalons =  _modele.taille();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return nbrJalons;
    }

    public FragmentManager getSupportFragmentManager() {
        return getSupportFragmentManager();
    }
}
