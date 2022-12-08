package dti.g25.tp1.domaine.entite;

import java.util.ArrayList;

/**
 Contient les methodes et les Attributs pour un Projet
 @author Sébastien
 @since 25/02/2020
 @version 1
 */

public class Projet {

    protected int id;
    protected String titre;
    protected String description;
    protected ArrayList<Tableau> tableaux = new ArrayList<>();

    /**
     *Initialise un projet avec son titre
     * @param unTitre : String
     */
    public Projet(String unTitre){

        assert unTitre != null && !unTitre.equals(""): "Le titre ne peut etre nulle ou vide";

        id = -1;
        titre = unTitre;

    }

    /**
     * Initialise un projet avec son id, son titre, sa description
     * @param unId : Int
     * @param unTitre : String
     * @param uneDescription : String
     * @param unTableau : ArrayList<Tableau>
     */
    public Projet(int unId, String unTitre, String uneDescription, ArrayList<Tableau> tableaux){

        assert  unId < 0 : "Le id d'un ticket ne peut pas être null ou être moindre que 0";
        assert unTitre != null && !unTitre.equals(""): "Le titre ne peut etre nulle ou vide";

        id = unId;
        titre = unTitre;
        description = uneDescription;
        this.tableaux = tableaux;
    }

    /**
     * Retourne l'id du projet
     * @return id : int
     */
    public int getId() { return id; }

    /**
     * Retourne le titre du projet
     * @return titre : String
     */
    public String getTitre() { return titre; }

    /**
     * Retourne la description du projet
     * @return description : String
     */
    public String getDescription() { return description; }

    /**
     * Retourne un tableau contenant les tableaux de tickets
     * @return tableaux : ArrayList<Tableau>
     */
    public ArrayList<Tableau> getTableaux() { return tableaux; }

    /**
     * Définit l'id du projet
     * @param id : int
     */
    public void setId(int id) { this.id = id; }

    /**
     * Définit le titre du projet
     * @param titre : String
     */
    public void setTitre(String titre) { this.titre = titre; }

    /**
     * Définit la description du projet
     * @param description : String
     */
    public void setDescription(String description) { this.description = description; }

}
