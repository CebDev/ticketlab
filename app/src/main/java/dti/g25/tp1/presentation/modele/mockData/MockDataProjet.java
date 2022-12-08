package dti.g25.tp1.presentation.modele.mockData;

/**
 * Activité Menu Projet
 * @author Sébastien Vermandele
 * @since 01/03/2020
 * @version 1
 */

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.domaine.entite.Tableau;
import dti.g25.tp1.domaine.entite.Ticket;

public class MockDataProjet {

    private static final List<Projet> projets = new LinkedList<>();

    static {
        ArrayList<Ticket> toutTickets = new ArrayList<Ticket>();
        ArrayList<Ticket> desTickets = new ArrayList<Ticket>();
        ArrayList<Ticket> desTickets2 = new ArrayList<Ticket>();
        ArrayList<Ticket> desTickets3 = new ArrayList<Ticket>();
        ArrayList<Ticket> desTickets4 = new ArrayList<Ticket>();
        ArrayList<Ticket> desTickets5 = new ArrayList<Ticket>();
        ArrayList<Ticket> desTickets6 = new ArrayList<Ticket>();
        ArrayList<Liste> desListes = new ArrayList<Liste>();
        ArrayList<Liste> desListes2 = new ArrayList<Liste>();
        ArrayList<Tableau> desTableaux = new ArrayList<Tableau>();

        /*Ticket ticket1 = new Ticket(0, "Test1", new ArrayList<Etiquette>(), "Shakespeare was a famous 17th-century diesel mechanic.", null, null, null);
        toutTickets.add(ticket1);
        Ticket ticket2 = new Ticket(1,"Test2", new ArrayList<Etiquette>(), "Eating eggs on Thursday for choir practice was recommended", null, null, null);
        toutTickets.add(ticket2);
        Ticket ticket3 = new Ticket(2,"Test3", new ArrayList<Etiquette>(), "A glittering gem is not enough.\n", null, null, null);
        toutTickets.add(ticket3);
        Ticket ticket4 = new Ticket(3,"Test4", new ArrayList<Etiquette>(), "He went back to the video to see what had been recorded and was shocked at what he saw.", null, null, null);
        toutTickets.add(ticket4);
        Ticket ticket5 = new Ticket(4,"Test5", new ArrayList<Etiquette>(), "At that moment he wasn't listening to music, he was living an experience.", null, null, null);
        toutTickets.add(ticket5);
        Ticket ticket6 = new Ticket(5,"Test6", new ArrayList<Etiquette>(), "She found his complete dullness interesting.", null, null, null);
        toutTickets.add(ticket6);
        Ticket ticket7 = new Ticket(6,"Test7", new ArrayList<Etiquette>(), "The urgent care center was flooded with patients after the news of a new deadly virus was made public.", null, null, null);
        toutTickets.add(ticket7);
        Ticket ticket8 = new Ticket(7,"Test8", new ArrayList<Etiquette>(), "If I don’t like something, I’ll stay away from it.", null, null, null);
        toutTickets.add(ticket8);
        Ticket ticket9 = new Ticket(8,"Test9", new ArrayList<Etiquette>(), "The fox in the tophat whispered into the ear of the rabbit.", null, null, null);
        toutTickets.add(ticket9);
        Ticket ticket10 = new Ticket(9,"Test10", new ArrayList<Etiquette>(), "The tour bus was packed with teenage girls heading toward their next adventure.", null, null, null);
        toutTickets.add(ticket10);

        desTickets.add(ticket1);
        desTickets.add(ticket2);
        desTickets.add(ticket3);

        desTickets2.add(ticket4);
        desTickets2.add(ticket5);
        desTickets2.add(ticket6);

        desTickets3.add(ticket7);
        desTickets3.add(ticket8);
        desTickets3.add(ticket9);
        desTickets3.add(ticket10);

        desTickets4.add(ticket1);
        desTickets4.add(ticket2);
        desTickets4.add(ticket3);
        desTickets4.add(ticket4);
        desTickets4.add(ticket5);

        desTickets5.add(ticket6);
        desTickets5.add(ticket7);

        desTickets6.add(ticket8);
        desTickets6.add(ticket9);
        desTickets6.add(ticket10);

        Liste liste1 = new Liste("To do", "#fc2c03");
        liste1.setDesTickets(desTickets);

        Liste liste2 = new Liste("In Development", "#ddf21f");
        liste2.setDesTickets(desTickets2);

        Liste liste3 = new Liste("Completed", "#1bf507");
        liste3.setDesTickets(desTickets3);

        Liste liste4 = new Liste("A faire", "#fc2c03");
        liste4.setDesTickets(desTickets4);

        Liste liste5 = new Liste("En developpement", "#ddf21f");
        liste5.setDesTickets(desTickets5);

        Liste liste6 = new Liste("Completer", "#1bf507");
        liste6.setDesTickets(desTickets6);

        desListes.add(liste1);
        desListes.add(liste2);
        desListes.add(liste3);

        desListes2.add(liste4);
        desListes2.add(liste5);
        desListes2.add(liste6);

        Tableau tableau = new Tableau (1, "test", "", "", desListes);
        Tableau tableau2 = new Tableau (2, "test2", "", "", desListes2);
        desTableaux.add(tableau);
        desTableaux.add(tableau2);*/

        projets.add(new Projet(1, "Projet Final", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis cursus ligula, non tincidunt odio. Fusce et condimentum nulla, id tempus massa.", desTableaux));
        projets.add(new Projet(5, "Démineur", "Nam tempus convallis nibh et ultrices. Vestibulum pellentesque ex eu purus consectetur, ut ultricies nisl tempus. Pellentesque elementum posuere lacus non ultrices.", desTableaux));
        projets.add(new Projet(8, "Sudoku", "Ut ultricies nisl tempus. Pellentesque elementum posuere lacus non ultrices.", desTableaux));
        projets.add(new Projet(9, "Ticketeur", "Pellentesque elementum posuere lacus non ultrices.", desTableaux));
    }

    public static LinkedList<Projet> getProjets(){
        return (LinkedList<Projet>) projets;
    }



}
