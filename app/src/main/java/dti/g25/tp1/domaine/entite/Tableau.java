package dti.g25.tp1.domaine.entite;

import java.util.ArrayList;
/**
 Contient les méthodes et attributs d'un Tableau
 @author Bryan
 */
public class Tableau {
    private int id;
    private int projetID;
    private String titre;
    private String description;
    private String couleur;
    private ArrayList<Liste> uneListe;

    /**
     * Initialise un Tableau avec un ID, un titre, une description, une couleur, une liste de Listes et un ID de projet
     * @param id : int
     * @param titre : String
     * @param description : String
     * @param couleur : String
     * @param uneListe : ArrayList<Liste>
     * @param projetID : int
     */
    public Tableau(int id, String titre, String description, String couleur, ArrayList<Liste> uneListe, int projetID) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.couleur = couleur;
        this.uneListe = uneListe;
        this.projetID = projetID;
    }

    //***************************** Getters *******************************
    /**
     * Retourne le ID du tableau
     * @return id : int
     */
    public int getId() {
        return id;
    }

    /**
     * Retourne la couleur du Tableau
     * @return couleur : String
     */
    public String getCouleur() {
        return couleur;
    }

    /**
     * Retourne la description du Tableau
     * @return description : String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le titre du Tableau
     * @return titre : String
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Retourne la liste de Liste du Tableau
     * @return uneListe : ArrayList<Liste>
     */
    public ArrayList<Liste> getUneListe() {
        return uneListe;
    }

    /**
     * Retourne le ID du projet auquel le Tableau appartient
     * @return projetID : int
     */
    public int getProjetID() {
        return projetID;
    }

    //***************************** Setters *******************************

    /**
     * Défini la liste de Liste du Tableau
     * @param uneListe : ArrayList<Liste>
     */
    public void setUneListe(ArrayList<Liste> uneListe) {
        this.uneListe = uneListe;
    }

    /**
     * Défini le titre du Tableau
     * @param titreModifier : String
     */
    public void setTitre(String titreModifier) {
        this.titre = titreModifier;
    }
}
