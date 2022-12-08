package dti.g25.tp1;

import org.junit.Test;
import dti.g25.tp1.domaine.entite.Membre;
import dti.g25.tp1.domaine.entite.Role;

import static org.junit.Assert.*;

/**
 Contient les tests  des methodes et des Attributs pour un Membre
 @author Philippe
 @since 10/02/2020
 @version 1
 */

public class MembreTest {

    /**
     Test le constructeur complet de l'objet Membre et la méthode getPseudo.
     */


    @Test
    public void constructeurEtGetPseudo() {

        String varObtenue;
        String varAttendue = "Yo99";

        //Mise en place
        Membre membreTest = new Membre("Yo99", "Rock", "LaPierre", Role.DEVELOPPEUR);
        varObtenue = membreTest.getPseudo();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }


    /**
     Test la méthode getPrenom.
     */


    @Test
    public void constructeurEtGetPrenom() {

        String varObtenue;
        String varAttendue = "Rock";

        //Mise en place
        Membre membreTest = new Membre("Yo99", "Rock", "LaPierre", Role.DEVELOPPEUR);
        varObtenue = membreTest.getPrenom();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getPrenom.
     */


    @Test
    public void constructeurEtGetNom() {

        String varObtenue;
        String varAttendue = "LaPierre";

        //Mise en place
        Membre membreTest = new Membre("Yo99", "Rock", "LaPierre", Role.DEVELOPPEUR);
        varObtenue = membreTest.getNom();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getRole.
     */


    @Test
    public void constructeurEtGetRole() {

        Role varObtenue;
        Role varAttendue = Role.JOURNALISTE;

        //Mise en place
        Membre membreTest = new Membre("Yo99", "Rock", "LaPierre", Role.JOURNALISTE);
        varObtenue = membreTest.getRole();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode setPrenom.
     */


    @Test
    public void constructeurEtSetPrenom() {

        String varObtenue;
        String varAttendue = "Marguerite";

        //Mise en place
        Membre membreTest = new Membre("Yo99", "Rock", "LaPierre", Role.DEVELOPPEUR);
        membreTest.setPrenom("Marguerite");
        varObtenue = membreTest.getPrenom();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode setNom.
     */

    @Test
    public void constructeurEtSetNom() {

        String varObtenue;
        String varAttendue = "Lafleur";

        //Mise en place
        Membre membreTest = new Membre("Yo99", "Rock", "LaPierre", Role.DEVELOPPEUR);
        membreTest.setNom("Lafleur");
        varObtenue = membreTest.getNom();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode setRole.
     */

    @Test
    public void constructeurEtSetRole() {

        Role varObtenue;
        Role varAttendue = Role.MAINTENEUR;

        //Mise en place
        Membre membreTest = new Membre("Yo99", "Rock", "LaPierre", Role.DEVELOPPEUR);
        membreTest.setRole(Role.MAINTENEUR);
        varObtenue = membreTest.getRole();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }
}


