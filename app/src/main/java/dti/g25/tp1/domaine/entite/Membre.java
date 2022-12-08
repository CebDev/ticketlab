package dti.g25.tp1.domaine.entite;


/**
 Contient les methodes et les Attributs pour un Membre
 @author Philippe
 @since 10/02/2020
 @version 1
 */

public class Membre {

        protected String pseudo;
        protected String prenom;
        protected String nom;
        protected Role role;

        /**
         Initialise un membre avec son pseudo, son prénom, son nom et son rôle
         @param unPseudo : String
         @param unPrenom : String
         @param unNom : String
         @param unRole : String
         */

        public Membre(String unPseudo, String unPrenom, String unNom, Role unRole){
                assert unPseudo != null && unPseudo != "": "Le pseudo ne peut etre nulle ou vide";

                pseudo = unPseudo;
                prenom = unPrenom;
                nom = unNom;
                role = unRole;
        }

        /**
         Constructeur minimal d'un membre
         @param unPseudo : String
         */
        public Membre(String unPseudo) {
                assert unPseudo != null && unPseudo != "": "Le pseudo ne peut etre nulle ou vide";

                pseudo = unPseudo;
        }

        /**
         Retourne le pseudo du membre
         @return pseudo : String
         */

        public String getPseudo(){ return pseudo; }

        /**
         Retourne le prenom du membre
         @return prenom : String
         */

        public String getPrenom(){ return prenom; }

        /**
         Retourne le nom du membre
         @return nom : String
         */

        public String getNom(){ return nom; }

        /**
         Retourne le rôle du membre
         @return role : String
         */

        public Role getRole(){ return role; }

        /**
         Émet un prenom au membre
         @param nouveauPrenom : String
         */

        public void setPrenom(String nouveauPrenom){ prenom=nouveauPrenom; }

        /**
         Émet un nom au membre
         @param nouveauNom : String
         */

        public void setNom(String nouveauNom){ nom=nouveauNom; }

        /**
         Émet un rôle au membre
         @param nouveauRole : String
         */

        public void setRole(Role nouveauRole){ role=nouveauRole; }


}
