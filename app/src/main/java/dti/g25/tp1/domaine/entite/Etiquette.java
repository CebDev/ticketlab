package dti.g25.tp1.domaine.entite;

/**
 Contient les methodes et les Attributs pour une Etiquette
 @author Philippe
 @since 10/02/2020
 @version 1
 */
public class Etiquette {

        protected int id;
        protected String titre;
        protected String description;
        protected String couleur;
        protected boolean estListe;

        /**
         Initialise une etiquette avec son titre, sa description et sa couleur
         @param unId : int
         @param unTitre : String
         @param uneDescription : String
         @param uneCouleur : String
         @param estListe : Boolean
         */

        public Etiquette(int unId, String unTitre, String uneDescription, String uneCouleur, boolean estListe){

                assert unTitre != null && unTitre != "": "Le titre ne peut etre nulle ou vide";

                id = unId;
                titre = unTitre;
                description = uneDescription;
                couleur = uneCouleur;
                this.estListe = estListe;
        }

        /**
         Retourne l'id de l'étiquette
         @return id : int
         */

        public int getId(){ return id; }


        /**
         Retourne le titre de l'étiquette
         @return titre : String
         */

        public String getTitre(){ return titre; }

        /**
         Retourne la description de l'étiquette
         @return description : String
         */

        public String getDescription(){ return description; }

        /**
         Retourne la couleur de l'étiquette
         @return couleur : String
         */

        public String getCouleur(){ return couleur; }

        /**
         Retourne si l'étiquette est dans une liste
         @return couleur : Boolean
         */

        public Boolean estListe(){ return estListe; }

        /**
         Émet un titre à l'étiquette
         @param nouveauTitre : String
         */

        public void setTitre(String nouveauTitre){ titre=nouveauTitre; }

        /**
         Émet une couleure à l'étiquette
         @param nouvelleCouleur : String
         */

        public void setCouleur(String nouvelleCouleur){couleur=nouvelleCouleur;}

        /**
         Émet une description à l'étiquette
         @param nouvelleDescription : String
         */

        public void setDescription(String nouvelleDescription){ description=nouvelleDescription; }

        /**
         Émet la condition de l'étiquette dans la liste
         @param nouvelleConditionListe : Boolean
         */

        public void setEstListe(Boolean nouvelleConditionListe){ estListe=nouvelleConditionListe; }
}
