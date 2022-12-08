package dti.g25.tp1.presentation.modele;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.LinkedList;

import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOTicket;
import dti.g25.tp1.presentation.modele.mockData.MockDataTicket;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ModeleTicket {
    Ticket ticket;
    DAOTicket daoTicket = new DAOTicket();
    ArrayList<Ticket> tickets = getTickets();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Ticket> getTickets(){
        try {
            tickets = daoTicket.trouverTout();
        } catch (DAOException e) {
            Log.println(Log.ERROR,"Err.DAO", e.toString());
        }

        return tickets;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Ticket ajouter(Ticket unTicket, int idProjet) {
        try {
            ticket = daoTicket.setProjet(daoTicket.ajouter(unTicket), idProjet);
        } catch (DAOException e) {
            Log.println(Log.ERROR,"Err.DAO", e.toString());
        }

        return ticket;
    }

    public void remplacer(Ticket unTicket) {
        try {
            ticket = daoTicket.modifier(unTicket);
        } catch(DAOException e) {
            Log.println(Log.ERROR,"Err.DAO", e.toString());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Ticket obtenir(int id) {
        try {
            ticket = daoTicket.lire(id);
        } catch (DAOException e) {
            Log.println(Log.ERROR,"Err.DAO", e.toString());
        }

        return ticket;
    }

    public void setTicket (Ticket ticket) {
        this.ticket = ticket;
    }

    public ArrayList<Ticket> getTicketsParProjet(int projetId) {
        ArrayList<Ticket> desTickets = new ArrayList<>();

        try {
            desTickets = daoTicket.trouverParProjet(projetId);
        } catch (DAOException e) {
            Log.println(Log.ERROR,"Err.DAO", e.toString());
        }

        return desTickets;
    }
}

