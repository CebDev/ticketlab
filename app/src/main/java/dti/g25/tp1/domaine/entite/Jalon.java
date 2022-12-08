package dti.g25.tp1.domaine.entite;


import android.os.Build;

import java.time.LocalDate;

/**
 Contient les methodes et les Attributs pour un Jalon
 @author Philippe
 @since 10/02/2020
 @version 1
 */


public class Jalon {

    protected int id;
    protected String titre;
    protected String description;
    protected LocalDate dateDebut;
    protected LocalDate dateFin;
    protected int idProjet;

    /**
     Initialise un jalon avec son titre,sa description,sa date de début et sa date de fin
     @param unId : Int
     @param unTitre : String
     @param uneDescription : String
     @param uneDateDebut : LocalDateTime
     @param uneDateFin : LocalDateTime
     @param unIdProjet : int
     */

    public Jalon(int unId, String unTitre, String uneDescription, LocalDate uneDateDebut, LocalDate uneDateFin, int unIdProjet){

        assert unTitre != null && unTitre != "": "Le titre ne peut etre nulle ou vide";
        assert uneDateDebut != null : "La Date de début ne peut être nulle ou vide";

        // Le "if" permet de vérifier la version de SDK pour que le temps puisse être comparé correctement
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            assert uneDateFin.compareTo(uneDateDebut) > 0  : "La date de début du jalon ne peut pas être après la date de fin";

        id = unId;
        titre = unTitre;
        description = uneDescription;
        dateDebut = uneDateDebut;
        dateFin = uneDateFin;
        idProjet = unIdProjet;

    }

    /**
     Retourne le l'id du jalon
     @return titre : Int
     */

    public int getId(){ return id; }

    /**
     Retourne le titre du jalon
     @return titre : String
     */

    public String getTitre(){ return titre; }

    /**
     Retourne la description du jalon
     @return description : String
     */

    public String getDescription(){ return description; }

    /**
     Retourne la date de début du jalon
     @return dateDebut : LocalDateTime
     */

    public LocalDate getDateDebut(){ return dateDebut; }

    /**
     Retourne la date de fin du jalon
     @return datefin : LocalDateTime
     */

    public LocalDate getDateFin(){ return dateFin; }

    /**
     Émet une date de début au jalon
     @param nouvelleDateDebut : LocalDateTime
     */

    public void setDateDebut(LocalDate nouvelleDateDebut){ dateDebut=nouvelleDateDebut; }

    /**
     Émet une date de fin au jalon
     @param nouvelleDateFin : LocalDateTime
     */

    public void setDateFin(LocalDate nouvelleDateFin){ dateFin=nouvelleDateFin; }

    public int getIdProjet() {
        return idProjet;
    }

}
