package dti.g25.tp1.presentation.presenteur.presenteurProjet;

/**
 * Activité Menu Projet
 * @author Sébastien Vermandele
 * @since 02/03/2020
 * @version 1
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.ModeleProjet;
import dti.g25.tp1.presentation.contratProjet.ContratVuePresenteurProjetListe;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;
import dti.g25.tp1.ui.activite.ActiviteAjouterProjet;
import dti.g25.tp1.ui.activite.ActiviteModifierProjet;

public class PresenteurProjetAfficher extends PresenteurBase implements ContratVuePresenteurProjetListe.IPresenteurProjetMenu {
    public static final String PROJET_ID = "idProjet";

    private ContratVuePresenteurProjetListe.IVueProjetMenu _vue;
    private ModeleProjet _modeleProjet;
    private Activity _activite;

    public PresenteurProjetAfficher(ContratVuePresenteurProjetListe.IVueProjetMenu _vue, ModeleProjet _modeleProjet, Activity _activite) {
        super(_activite);

        this._vue = _vue;
        this._modeleProjet = _modeleProjet;
        this._activite = _activite;
    }

    @Override
    public void setList (CharSequence termeRecherche) {
        try{
            _modeleProjet.setListeProjet(termeRecherche.toString());
            _vue.rafraichir();
        } catch (DAOException e){
            e.getStackTrace();
            this.afficherToast(_activite.getResources().getString(R.string.connexion_base_donne_erreur));
        }
    }

    /**
     * Méthode qui lance l'activité AjouterProjet
     */
    @Override
    public void lancerActiviteAjouterProjet (){
        Intent intent = new Intent(_activite, ActiviteAjouterProjet.class);
        _activite.startActivity(intent);
        _activite.finish();
    }

    /**
     * Méthode qui lance l'activité AfficherTableaux
     */
    @Override
    public void lancerActiviteAfficherTableaux(int positionProjet){
        Intent intent = new Intent(_activite, ActiviteAfficherTableaux.class);
        intent.putExtra("dti.g25.ticket.projetid", getProjetId(positionProjet));
        _activite.startActivity(intent);
    }

    /**
     * Méthode qui lance l'activité ModifierProjet
     */
    @Override
    public void lancerActiviteModifierProjet(int positionProjet){
        Log.d("modifier", "onSwiped: " + positionProjet);
        Log.d("idProjet", "lancerActiviteModifierProjet: " + getProjetId(positionProjet));
        Intent intent = new Intent(_activite, ActiviteModifierProjet.class);
        intent.putExtra(PROJET_ID, getProjetId(positionProjet));
        _activite.startActivity(intent);
        _activite.finish();
    }

    /**
     * Méthode qui lance un fragment alert pour valider la suppression
     * @param positionProjet : int
     */
    @Override
    public void lancerFragmentSuppression(final int positionProjet){

        AlertDialog.Builder constructeur = new AlertDialog.Builder(this._activite);
        final View view = LayoutInflater.from(this._activite).inflate(
                R.layout.fragment__supprimer__projet,
                (ViewGroup) this._activite.findViewById(R.id.fragmentMenuPlus)
        );
        constructeur.setView(view);
        final AlertDialog alertDialog = constructeur.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        view.findViewById(R.id.btnAfficherEtiquette).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etTitreProjetSupprimer = (EditText) view.findViewById(R.id.etTitreProjetSupprimer);
                String titreProjetSupprimer = etTitreProjetSupprimer.getText().toString();

                if (!titreProjetSupprimer.equals(getProjetTitre(positionProjet))){
                    afficherToast(_activite.getString(R.string.erreur_validation));
                } else {
                    try {
                        _modeleProjet.supprimerProjetParId(getProjetId(positionProjet));
                        supprimerProjet(positionProjet);
                        afficherToast(_activite.getString(R.string.supprimer));
                    } catch (DAOException e) {
                        e.printStackTrace();
                        afficherToast(_activite.getString(R.string.toast_erreur_suppresion));
                    }
                }
                _vue.rafraichir();
                alertDialog.dismiss();
            }
        });
        view.findViewById(R.id.btnAfficherJalon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _vue.rafraichir();
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }



    // ************* METHODE SPECIFIQUE A L'ADAPTEUR ******************

    /**
     * Méthode qui retourne le nombre de projet à afficher par l'adapter
     * @return le nombre de Projet à afficher : int
     */
    @Override
    public int getNbProjetAfficher() {
        int nbrProjetAfficher = 0;
        int nbrProjetTotal = 0;
        try{
            nbrProjetAfficher = _modeleProjet.getNbProjetAfficher();
            nbrProjetTotal = _modeleProjet.recupererNbrEnregistrement();
        } catch (DAOException e){
            e.getStackTrace();
            this.afficherToast(_activite.getString(R.string.connexion_base_donne_erreur));
        }
         if (nbrProjetAfficher == 0 && nbrProjetTotal != 0 ){
             this.afficherToast(_activite.getResources().getString(R.string.toast_aucun_projet_correspondant));
         }
        return nbrProjetAfficher;
    }

    /**
     * Méthode qui retourne le titre d'un projet à partir de sa position dans l'adapter
     * @param position dans l'adapter : int
     * @return le titre du projet : String
     */
    @Override
    public String getProjetTitre(int position) {
        String titreProjet = null;
        try{
            titreProjet =  _modeleProjet.getProjet(position).getTitre();
        } catch (DAOException e){
            e.getStackTrace();
            this.afficherToast(_activite.getString(R.string.toast_aucun_projet_correspondant));
        }
        return titreProjet;
    }

    /**
     * Méthode qui retourne la description d'un projet à partir de sa position dans l'adapter
     * @param position dans l'adapter : int
     * @return la description du projet : String
     */
    @Override
    public String getProjetDescription(int position) {
        String descriptionProjet = null;
        try{
            descriptionProjet =  _modeleProjet.getProjet(position).getDescription();
        } catch (DAOException e){
            e.getStackTrace();
            this.afficherToast(_activite.getString(R.string.connexion_base_donne_erreur));
        }
        return descriptionProjet;
    }

    /**
     * Méthode qui retourne l'id d'un projet à partir de sa position dans l'adapter
     * @param position dans l'adapter : int
     * @return l'id du Projet : int
     */
    @Override
    public int getProjetId(int position) {
        int idProjet = 0;
        try{
            idProjet = _modeleProjet.getProjet(position).getId();
        } catch (DAOException e){
            e.getStackTrace();
            this.afficherToast(_activite.getString(R.string.connexion_base_donne_erreur));
        }
        return idProjet;
    }

    /**
     * Méthode qui supprime un projet de l'adapter et rafraichit l'adapter
     * @param position dans l'adapter : int
     */
    public void supprimerProjet(int position){
        setList("");
        _vue.rafraichirSuppression(position);
    }

    /**
     * Méthode qui affiche un toast en haut de l'ecran
     * @param message : String
     */
    private void afficherToast(String message){
        Toast t = Toast.makeText(_activite, message, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 60);
        t.show();
    }

}
