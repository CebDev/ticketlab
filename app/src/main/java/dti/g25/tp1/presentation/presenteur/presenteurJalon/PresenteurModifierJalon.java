package dti.g25.tp1.presentation.presenteur.presenteurJalon;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;

import java.time.LocalDate;

import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.presentation.contratJalon.ContratVuePresenteurJalonModifier;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleJalon;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.vueJalon.VueModifierJalon;
import dti.g25.tp1.ui.activite.ActiviteAfficherJalon;

public class PresenteurModifierJalon extends PresenteurBase implements ContratVuePresenteurJalonModifier.IPresenteurModifierJalon {
    public static final String EXTRA_ID = "dti.g25.jalon.string";
    public static final String EXTRA_POSITION = "dti.g25.jalon.position";

    private Activity _activite;
    private ContratVuePresenteurJalonModifier.IVueModifierJalon _vue;
    private ModeleJalon.MJalon _modele;

    private int idProjet;
    private int _id;
    private int _position;

    public PresenteurModifierJalon(Activity activite, VueModifierJalon vue, ModeleJalon.MJalon modele ){
        super(activite);

        _activite = activite;
        _vue = vue;
        _modele = modele;
    }

    public void setIdProjet(int idProjet){
        this.idProjet = idProjet;
    }

    public void commencerModification(int id,  int position) {
        _id = id;
        _position = position;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)

    public void modifier( String unTitre, String uneDescription, String uneDateDebut, String uneDateFin, int idProjet) {

        LocalDate dateDebut = LocalDate.parse(uneDateDebut);
        LocalDate dateFin = LocalDate.parse(uneDateFin);
        _modele.remplacer(_position, new Jalon(_id, unTitre, uneDescription, dateDebut, dateFin, idProjet));

        terminerModification();
    }

    // ************ METHODES SPECIFIQUES A L'ADAPTEUR ********************

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void modifier(String unTitre, String uneDescription, String uneDateDebut, String uneDateFin) {
        Jalon unJalon = new Jalon(_id, unTitre, uneDescription, LocalDate.parse(uneDateDebut), LocalDate.parse(uneDateFin) ,idProjet);
        try {
            _modele.modifier(unJalon);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void terminerModification(){
        Intent donneesRetour = new Intent(_activite, ActiviteAfficherJalon.class);
        donneesRetour.putExtra(EXTRA_ID, _id);
        donneesRetour.putExtra(EXTRA_POSITION, _position);
        _activite.startActivity(donneesRetour);
        _activite.finish();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public Jalon getJalon() {
        Jalon unJalon = null;
        try {
            unJalon = _modele.obtenirParId(_id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return unJalon;
    }

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
