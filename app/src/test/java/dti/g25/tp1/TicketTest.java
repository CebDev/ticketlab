package dti.g25.tp1;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Jalon;
import dti.g25.tp1.domaine.entite.Membre;
import dti.g25.tp1.domaine.entite.Role;
import dti.g25.tp1.domaine.entite.Ticket;
import static org.junit.Assert.assertEquals;

/**
 Contient les tests des methodes et des Attributs pour un Ticket
 @author Philippe
 @since 10/02/2020
 @version 1
 */

public class TicketTest {

    /**
     Test le constructeur partiel de l'objet Ticket et la méthode getTitre.
     */


    @Test
    public void constructeurPartielEtGetTitre() {

        String varObtenue;
        String varAttendue = "Test Junit";

        //Mise en place
        Ticket ticketTest = new Ticket("Test Junit");
        varObtenue = ticketTest.getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test le constructeur partiel de l'objet Ticket et la méthode getId.
     */


    @Test
    public void constructeurEtGetId() {

        int varObtenue;
        int varAttendue = 1;

        //Mise en place
        Ticket ticketTest = new Ticket(
                1,
                "Junit",
                new ArrayList<Etiquette>(Collections.singletonList(new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom","Rouge", false))),
                "ticketTest",
                new Membre("Phil", "Philippe", "Joubarne", Role.DEVELOPPEUR),
                new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"), 1), LocalDate.parse("2020-02-10"));
        varObtenue = ticketTest.getId();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getEtiquette.
     */


    @Test
    public void constructeurEtGetEtiquette() {

        String varAttendue = "Appeler Maman";
        String varObtenue;

        //Mise en place
        Ticket ticketTest = new Ticket(
                10,
                "Junit",
                new ArrayList<Etiquette>(Collections.singletonList(new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false))),
                "ticketTest",
                new Membre("Phil", "Philippe", "Joubarne", Role.DEVELOPPEUR),
                new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-01-10"), LocalDate.parse("2020-02-10"), 1),
                LocalDate.parse("2020-01-10"));
        varObtenue = ticketTest.getEtiquettes().get(0).getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getDescription.
     */


    @Test
    public void constructeurEtGetDescription() {

        String varAttendue = "ticketTest";
        String varObtenue;

        //Mise en place
        Ticket ticketTest = new Ticket(
                1,
                "Junit",
                new ArrayList<Etiquette>(Collections.singletonList(new Etiquette(1, "Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false))),
                "ticketTest",
                new Membre("Phil", "Philippe", "Joubarne", Role.DEVELOPPEUR),
                new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-01-10"), LocalDate.parse("2020-02-10"), 1),
                LocalDate.parse("2020-02-10"));
        varObtenue = ticketTest.getDescription();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getAffectation.
     */


    @Test
    public void constructeurEtGetAffectation() {

        Membre varAttendue = new Membre("Phil", "Philippe", "Joubarne", Role.DEVELOPPEUR);
        Membre varObtenue;

        //Mise en place
        Ticket ticketTest = new Ticket(
                1,
                "Junit",
                new ArrayList<Etiquette>(Collections.singletonList(new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false))),
                "ticketTest", new Membre("Phil", "Philippe", "Joubarne", Role.DEVELOPPEUR),
                new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"),1), LocalDate.parse("2020-02-10"));
        varObtenue = ticketTest.getAffectation();

        //Vérification
        assertEquals(varAttendue.getPseudo(), varObtenue.getPseudo());
    }

    /**
     Test la méthode getJalon.
     */


    @Test
    public void constructeurEtGetJalon() {

        String varAttendue = "Allo";
        String varObtenue;

        //Mise en place
        Ticket ticketTest = new Ticket(
                1,
                "Junit",
                new ArrayList<Etiquette>(Collections.singletonList(new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false))),
                "ticketTest", new Membre("Phil", "Philippe", "Joubarne", Role.DEVELOPPEUR),
                new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"),1), LocalDate.parse("2020-02-10"));
        varObtenue = ticketTest.getJalon().getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getJalon.
     */


    @Test
    public void constructeurEtGetDateEcheance() {

        LocalDate varAttendue = LocalDate.parse("2020-02-10");
        LocalDate varObtenue;

        //Mise en place
        Ticket ticketTest = new Ticket(
                1,
                "Junit",
                new ArrayList<Etiquette>(Collections.singletonList(new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false))),
                "ticketTest", new Membre("Phil", "Philippe", "Joubarne", Role.DEVELOPPEUR),
                new Jalon(1,"Allo", "une salutation", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"),1), LocalDate.parse("2020-02-10"));
        varObtenue = ticketTest.getDateEcheance();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

}
