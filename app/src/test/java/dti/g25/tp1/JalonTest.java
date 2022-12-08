package dti.g25.tp1;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dti.g25.tp1.domaine.entite.Jalon;
import static org.junit.Assert.*;

/**
 Contient les tests  des methodes et des Attributs pour un Jalon
 @author Philippe
 @since 10/02/2020
 @version 1
 */

public class JalonTest {

    /**
     Test le constructeur complet de l'objet Jalon et la méthode getTitre.
     */


    @Test
    public void constructeurEtGetTitre() {

        String varObtenue;
        String varAttendue = "Allo";

        //Mise en place
        Jalon jalonTest = new Jalon(1, "Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"), 1);
        varObtenue = jalonTest.getTitre();

        //Vérification
        assertEquals(varAttendue,varObtenue);
    }


    /**
     Test la méthode getDescription.
     */


    @Test
    public void constructeurEtGetDescription() {

        String varObtenue;
        String varAttendue = "une salutation";

        //Mise en place
        Jalon jalonTest = new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"), 1);
        varObtenue = jalonTest.getDescription();

        //Vérification
        assertEquals(varAttendue,varObtenue);
    }

    /**
     Test la méthode getDateDebut.
     */


    @Test
    public void constructeurEtGetDateDebut() {

        LocalDate varObtenue;
        LocalDate varAttendue = LocalDate.parse("2020-02-10");

        //Mise en place
        Jalon jalonTest = new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"),1);
        varObtenue = jalonTest.getDateDebut();

        //Vérification
        assertEquals(varAttendue,varObtenue);
    }

    /**
     Test la méthode getDateFin.
     */


    @Test
    public void constructeurEtGetDateFin() {

        LocalDate varObtenue;
        LocalDate varAttendue = LocalDate.parse("2020-03-10");

        //Mise en place
        Jalon jalonTest = new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"), 1);
        varObtenue = jalonTest.getDateFin();

        //Vérification
        assertEquals(varAttendue,varObtenue);
    }

    /**
     Test la méthode setDateDebut.
     */


    @Test
    public void constructeurEtSetDateDebut() {

        LocalDate varObtenue;
        LocalDate varAttendue = LocalDate.parse("2020-02-10");

        //Mise en place
        Jalon jalonTest = new Jalon(1, "Allo", "une salutation", LocalDate.parse("2020-01-10"), LocalDate.parse("2020-03-10"), 1);
        jalonTest.setDateDebut(LocalDate.parse("2020-02-10"));
        varObtenue = jalonTest.getDateDebut();

        //Vérification
        assertEquals(varAttendue,varObtenue);
    }

    /**
     Test la méthode setDateFin.
     */


    @Test
    public void constructeurEtSetDateFin() {

        LocalDate varObtenue;
        LocalDate varAttendue = LocalDate.parse("2020-03-10");

        //Mise en place
        Jalon jalonTest = new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-01-10"), LocalDate.parse("2020-02-10"), 1);
        jalonTest.setDateFin(LocalDate.parse("2020-03-10"));
        varObtenue = jalonTest.getDateFin();

        //Vérification
        assertEquals(varAttendue,varObtenue);
    }
}