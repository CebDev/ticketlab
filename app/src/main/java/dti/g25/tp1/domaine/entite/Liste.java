package dti.g25.tp1.domaine.entite;

import java.util.ArrayList;

/**
 Contient les méthodes et attributs d'une Liste
 Liste : Étiquettes qui agissent comme des rangés qui contiennent les tickets dans un tableau
 @author Bryan
 */
public class Liste {
    private int id;
    private String titre;
    private String couleur;
    private int tableauID;
    private ArrayList<Ticket> desTickets;

    /**
     * Initialise une Liste avec un titre et une couleur.
     * @param titre : String
     * @param couleur : String
     */
    public Liste (String titre, String couleur) {
        this.titre = titre;
        this.couleur = couleur;
    }
//***************************** Getters *******************************

    /**
     * Retourne le titre de la Liste
     * @return titre : String
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Retourne la couleur de la Liste
     * @return couleur : String
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Retourne le ID de la Liste
     * @return id : int
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne le ID du tableau dans lequel se trouve la Liste
     * @return tableauID : int
     */
    public int getTableauID() {
        return tableauID;
    }

    //***************************** Setters *******************************

    /**
     * Défini le ID de la Liste
     * @param id : int
     */
    public void setId(int id){
        this.id = id;
    }

    /**
     * Défini le ID du tableau ou se trouve la Liste
     * @param tableauID : int
     */
    public void setTableauID(int tableauID) {
        this.tableauID = tableauID;
    }

    /**
     * Défini le titre de la Liste
     * @param titre : String
     */
    public void setTitre(String titre){
        this.titre = titre;
    }

    /**
     * Défini la couleur de la Liste
     * @param couleur : String
     */
    public void setCouleur(String couleur){
        this.couleur = couleur;
    }

}
