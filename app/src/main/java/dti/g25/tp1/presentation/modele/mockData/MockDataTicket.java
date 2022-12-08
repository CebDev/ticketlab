package dti.g25.tp1.presentation.modele.mockData;

import java.util.ArrayList;
import java.util.LinkedList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;

public class MockDataTicket {
    private static LinkedList<Ticket> tickets = new LinkedList<>();
    private static ArrayList<Etiquette> etiquettes =  new ArrayList<>(MockDataEtiquette.getEtiquettes());

    static  {
        Ticket ticket1 = new Ticket(0, "Test1", new ArrayList<Etiquette>(), "Shakespeare was a famous 17th-century diesel mechanic.", null, null, null);
        tickets.add(ticket1);
        Ticket ticket2 = new Ticket(1,"Test2", new ArrayList<Etiquette>(), "Eating eggs on Thursday for choir practice was recommended", null, null, null);
        tickets.add(ticket2);
        Ticket ticket3 = new Ticket(2,"Test3", new ArrayList<Etiquette>(), "A glittering gem is not enough.\n", null, null, null);
        tickets.add(ticket3);
        Ticket ticket4 = new Ticket(3,"Test4", new ArrayList<Etiquette>(), "He went back to the video to see what had been recorded and was shocked at what he saw.", null, null, null);
        tickets.add(ticket4);
        Ticket ticket5 = new Ticket(4,"Test5", new ArrayList<Etiquette>(), "At that moment he wasn't listening to music, he was living an experience.", null, null, null);
        tickets.add(ticket5);
        Ticket ticket6 = new Ticket(5,"Test6", new ArrayList<Etiquette>(), "She found his complete dullness interesting.", null, null, null);
        tickets.add(ticket6);
        Ticket ticket7 = new Ticket(6,"Test7", new ArrayList<Etiquette>(), "The urgent care center was flooded with patients after the news of a new deadly virus was made public.", null, null, null);
        tickets.add(ticket7);
        Ticket ticket8 = new Ticket(7,"Test8", new ArrayList<Etiquette>(), "If I don’t like something, I’ll stay away from it.", null, null, null);
        tickets.add(ticket8);
        Ticket ticket9 = new Ticket(8,"Test9", new ArrayList<Etiquette>(), "The fox in the tophat whispered into the ear of the rabbit.", null, null, null);
        tickets.add(ticket9);
        Ticket ticket10 = new Ticket(9,"Test10", new ArrayList<Etiquette>(), "The tour bus was packed with teenage girls heading toward their next adventure.", null, null, null);
        tickets.add(ticket10);
    }

    public static LinkedList<Ticket> getTickets(){
        return tickets;
    }


}
