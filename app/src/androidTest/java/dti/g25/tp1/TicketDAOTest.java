package dti.g25.tp1;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.modele.DAO.DAOEtiquetteTicket;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOTicket;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;

import static org.junit.Assert.assertEquals;

public class TicketDAOTest {

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("dti.g25.tp1", appContext.getPackageName());
    }

    @Test
    public void ajouterLireTicket () {

        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
            DAOTicket.creerTable();
            DAOEtiquetteTicket.creerTable();
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
        DAOTicket daoTicket = new DAOTicket();

        String varObtenue;
        String varAttendue = "Ticket test 1";

        //Mise en place
        Ticket ticketTest = new Ticket("Ticket test 1");
        Ticket ticketObtenu = null;
        try {
            ticketObtenu = daoTicket.ajouter(ticketTest);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        varObtenue = ticketObtenu.getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    @Test
    public void ModifierTicket () {

        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DAOTicket daoTicket = new DAOTicket();

        String varObtenue;
        String varAttendue = "Ticket test 1 mofifié";

        //Mise en place
        Ticket ticketTest = new Ticket("Ticket test 1 mofifié");
        Ticket ticketObtenu = null;
        try {
            ticketTest = daoTicket.lire(1);
            ticketTest.setTitre("Ticket test 1 mofifié");
            ticketObtenu = daoTicket.modifier(ticketTest);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        varObtenue = ticketObtenu.getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    @Test
    public void trouverToutTickets () {

        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DAOTicket daoTicket = new DAOTicket();

        int varObtenue = 0;
        int varAttendue = 0;

        //Mise en place
        ArrayList<Ticket> listeTickets = new ArrayList<>();

        Ticket ticketTest = new Ticket("Ticket test lireTout");
        try {
            listeTickets = daoTicket.trouverTout();
            varAttendue = listeTickets.size() + 1;
            daoTicket.ajouter(ticketTest);
            listeTickets = daoTicket.trouverTout();
            varObtenue = listeTickets.size();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }


    @Test
    public void supprimerTicket () {

        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DAOTicket daoTicket = new DAOTicket();

        int varObtenue;
        int varAttendue = 0;
        
        //Mise en place
        Ticket ticketASupprimer = new Ticket("Ticket à supprimer");
        ArrayList<Ticket> listeTickets = null;
        
        try {
            ticketASupprimer = daoTicket.ajouter(ticketASupprimer);
            listeTickets = daoTicket.trouverTout();
            varAttendue = listeTickets.size() - 1;
            daoTicket.supprimer(ticketASupprimer);
            listeTickets = daoTicket.trouverTout();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        varObtenue = listeTickets.size();
                

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

}
