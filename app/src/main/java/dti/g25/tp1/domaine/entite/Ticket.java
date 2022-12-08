package dti.g25.tp1.domaine.entite;

import android.os.Build;

import java.time.*;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.O;

/**
 Contient les methodes et les Attributs pour un Jalon
 @author Philippe
 @since 10/02/2020
 @version 1
 */


public class Ticket {

    protected int id;
    protected String titre;
    protected ArrayList<Etiquette> etiquettes = new ArrayList<>();
    protected String description;
    protected Membre affectation;
    protected Jalon jalon;
    protected LocalDate dateEcheance;
    protected boolean ouvert;

    /**
     Initialise un ticket avec son titre
     * @param unTitre : String
     */

    public Ticket(String unTitre){

        assert unTitre != null && unTitre != "": "Le titre ne peut etre nulle ou vide";

        id = -1;
        titre = unTitre;
    }

    /**
     Initialise un ticket avec son id, son titre, son étiquette, sa description, son affectation, son jalon et sa date d'échéance
     @param unId : Int
     @param unTitre : String
     @param desEtiquette : Etiquette
     @param uneDescription : String
     @param uneAffectation : Membre
     @param unJalon : Jalon
     @param uneDateEcheance : LocalDateTime
     */

    public Ticket(int unId, String unTitre, ArrayList<Etiquette> desEtiquette, String uneDescription, Membre uneAffectation, Jalon unJalon, LocalDate uneDateEcheance){

        assert  unId > 0 : "Le id d'un ticket ne peut pas être null ou être moindre que 0";
        assert unTitre != null && unTitre != "": "Le titre ne peut etre nulle ou vide";

        if (Build.VERSION.SDK_INT >= O)
            assert uneDateEcheance.compareTo(unJalon.getDateDebut()) > 0  : "La date d'échéance du ticket ne peut pas être avant la date de début du jalon";


        id = unId;
        titre = unTitre;
        etiquettes = desEtiquette;
        description = uneDescription;
        affectation = uneAffectation;
        jalon = unJalon;
        dateEcheance = uneDateEcheance;
        ouvert = true;
    }

    /**
     Retourne l'id du ticket
     @return titre : int
     */

    public int getId(){ return id; }

    /**
     Retourne le titre du ticket
     @return titre : String
     */

    public String getTitre(){ return titre; }

    /**
     Retourne l'étiquette du ticket
     @return étiquette : Étiquette
     */

    public ArrayList<Etiquette> getEtiquettes(){ return etiquettes; }

    /**
     Retourne la description du ticket
     @return description : String
     */

    public String getDescription(){ return description; }

    /**
     Retourne le membre affecté au ticket
     @return affectation : Membre
     */

    public Membre getAffectation(){ return affectation; }

    /**
     Retourne le jalon du ticket
     @return jalon : Jalon
     */

    public Jalon getJalon(){ return jalon; }

    /**
     Retourne la date d'échéance du ticket
     @return dateEcheance : LocalDateTime
     */

    public LocalDate getDateEcheance(){ return dateEcheance; }

    /**
     Émet un titre au ticket
     @param nouveauTitre : String
     */

    public void setTitre(String nouveauTitre){ titre = nouveauTitre; }

    /**
     Émet un étiquette au ticket
     @param nouvellesEtiquettes : Etiquette
     */

    public void setEtiquettes(ArrayList<Etiquette> nouvellesEtiquettes){ etiquettes = nouvellesEtiquettes; }

    /**
     Émet une description au ticket
     @param nouvelleDescription : String
     */

    public void setDescription(String nouvelleDescription){ description = nouvelleDescription; }

    /**
     Émet une affectation au ticket
     @param nouvelleAffectation : Membre
     */

    public void setAffectation(Membre nouvelleAffectation){ affectation = nouvelleAffectation; }

    /**
     Émet un jalon au ticket
     @param nouveauJalon : Jalon
     */

    public void setJalon(Jalon nouveauJalon){ jalon = nouveauJalon; }

    /**
     Émet une date d'échéance au ticket
     @param nouvelleDateEcheance : LocalDateTime
     */

    public void setDateEcheance(LocalDate nouvelleDateEcheance){ dateEcheance = nouvelleDateEcheance; }

    public boolean estOuvert() { return ouvert; }

    public void ouvrir() { ouvert = true; }

    public void fermer() { ouvert = false; }
}
