package dti.g25.tp1.presentation.modele;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOJalon;

public class ModeleJalon {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static class MJalon {
        //LinkedList<Jalon> jalons = MockDataJalon.getJalons();
        ArrayList<Jalon> listeJalons;

        /**
         * Méthode qui initialise la liste des jalons avec tous les jalons de l'unité de stockage
         */
        private void setListeJalons () throws DAOException {
            DAOJalon daoJalon = new DAOJalon();
            listeJalons = daoJalon.trouverTout();
        }

        /**
         * Méthode qui retourne la liste des jalons avec tous les jalons de l'unité de stockage
         * ou qui initialise celle ci si la liste n'existe pas
         * @return array la liste de tous les jalons
         */
        public ArrayList<Jalon> getJalons() throws DAOException {
            if(listeJalons == null)
                setListeJalons();
            return listeJalons;
        }

        /**
         * Méthode qui ajoute un jalon dans l'unité de stockage
         * @param Jalon un jalon
         * @return Jalon le jalon insérer
         */
        public int ajouter(Jalon unJalon) throws DAOException {
            DAOJalon daoJalon = new DAOJalon();
            return daoJalon.ajouter(unJalon).getId();
        }

        /**
         * Méthode qui supprime un jalon dans l'unité de stockage
         * @param int l'index du jalon dans le tableau
         */
        public  void retirer(int unIndex) {listeJalons.remove(unIndex); }

        /**
         * Méthode qui remplace un jalon dans le tableau
         * @param int l'index du jalon dans le tableau
         * @param Jalon le nouveau jalon
         */
        public void remplacer(int unIndex, Jalon unJalon) {listeJalons.set(unIndex, unJalon); }

        /**
         * Méthode qui modifie un jalon dans l'unité de stockage
         * @param Jalon le jalon modifié
         * @return Jalon le jalon tel qu'il est enregistré
         */
        public int modifier(Jalon unJalon) throws DAOException {
            DAOJalon daoJalon = new DAOJalon();
            return daoJalon.modifier(unJalon).getId();
        }

        /**
         * Méthode qui retourne un jalon de l'unité de stockage par son id
         * @param int l'id du jalon
         * @return Jalon le jalon recherché
         */
        public Jalon obtenirParId (int idJalon) throws DAOException {
            DAOJalon daoJalon = new DAOJalon();
            return daoJalon.lire(idJalon);
        }

        /**
         * Méthode qui retourne une liste des jalons avec tous les jalons de l'unité de stockage
         * @return array la liste de tous les jalons
         */
        public ArrayList<Jalon> obtenirJalons () throws DAOException {
            DAOJalon daoJalon = new DAOJalon();
            ArrayList<Jalon> listeJalons = new ArrayList<>();
            listeJalons = daoJalon.trouverTout();
            return  listeJalons;
        }

        // ************** METHODES POUR ADAPTER **************

        /**
         * Méthode qui retourne la taille du tableau de jalons
         * @return int la nombre de jalons dans le tableau
         */
        public int taille() throws DAOException {
            return getJalons().size();
        }

        /**
         * Méthode qui retourne un jalon par son index du tableau de jalons
         * @return Jalon le jalon recherché
         */
        public Jalon obtenir(int unIndex) {
            return listeJalons.get(unIndex);
        }

    }
}
