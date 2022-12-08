package dti.g25.tp1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.domaine.entite.Tableau;

import static org.junit.Assert.*;

/**
 Contient les tests  des methodes et des Attributs pour un Projet
 @author Philippe
 @since 11/02/2020
 @version 1
 */

public class ProjetTest {

    /**
     Test le constructeur complet de l'objet Projet et la méthode getTitre.
     */

    @Test
    public void constructeurEtGetId() {

        int varObtenue;
        int varAttendue = -1;

        //Mise en place
        Projet projetTest = new Projet(-1, "Appeler Maman", "Appeler ma mere", new ArrayList<Tableau>());
        varObtenue = projetTest.getId();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getTitre.
     */

    @Test
    public void constructeurEtGetTitre() {

        String varObtenue;
        String varAttendue = "Appeler Maman";

        //Mise en place
        Projet projetTest = new Projet(-1, "Appeler Maman", "Appeler ma mere", new ArrayList<Tableau>());
        varObtenue = projetTest.getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getDescription.
     */

    @Test
    public void constructeurEtGetDescription() {

        String varObtenue;
        String varAttendue = "Appeler ma mere";

        //Mise en place
        Projet projetTest = new Projet(-1, "Appeler Maman", "Appeler ma mere", new ArrayList<Tableau>());
        varObtenue = projetTest.getDescription();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    @Test
    public void constructeurEtGetTableau() {

        String varObtenue;
        String varAttendue = "Tableau Test";

        //Mise en place
        Projet projetTest = new Projet(-1, "Appeler Maman", "Appeler ma mere", new ArrayList<Tableau>(Collections.singletonList(new Tableau(1, "Tableau Test", "Description du tableau test", "rouge", new ArrayList<Liste>(), 1) )));
        varObtenue = projetTest.getTableaux().get(0).getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

}
