package dti.g25.tp1.presentation.modele;

import android.util.Log;

import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.modele.DAO.DAOEtiquette;
import dti.g25.tp1.presentation.modele.DAO.DAOException;


public class ModeleEtiquette {
    public static  class MEtiquette {
        Etiquette etiquette;
        DAOEtiquette daoEtiquette = new DAOEtiquette();
        ArrayList<Etiquette> etiquettes = getEtiquettes();

        public ArrayList<Etiquette> getEtiquettes() {
            try {
                etiquettes = daoEtiquette.trouverTout();
            } catch (DAOException e) {
                Log.println(Log.ERROR,"Err.DAO",e.toString());
            }

            return etiquettes;
        }

        public Etiquette ajouter(Etiquette unEtiquette) {
            try {
                etiquette = daoEtiquette.ajouter(unEtiquette);
            } catch (DAOException e) {
                Log.println(Log.ERROR,"Err.DAO", e.toString());
            }

            return etiquette;
        }

        public void retirer(Etiquette unEtiquette) {
            try {
                daoEtiquette.supprimer(unEtiquette);
            } catch (DAOException e) {
                Log.println(Log.ERROR,"Err.DAO",e.toString());
            }
        }

        public Etiquette remplacer(int _position, Etiquette unEtiquette) {
            try {
                etiquette = daoEtiquette.modifier(unEtiquette);
            } catch (DAOException e) {
                Log.println(Log.ERROR,"Err.DAO", e.toString());
            }

            return etiquette;
        }

        public Etiquette obtenir(int unIndex){
            try {
                etiquette = daoEtiquette.lire(unIndex);
            } catch (DAOException e) {
                Log.println(Log.ERROR,"Err.DAO", e.toString());
            }

            return etiquette;
        }

        public int taille(){ return etiquettes.size(); }


    }

}
