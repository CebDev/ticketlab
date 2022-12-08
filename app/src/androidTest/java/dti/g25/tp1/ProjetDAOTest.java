package dti.g25.tp1;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;

import dti.g25.tp1.domaine.entite.Projet;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOProjet;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;

import static org.junit.Assert.assertEquals;

public class ProjetDAOTest {


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("dti.g25.tp1", appContext.getPackageName());
    }

    @Test
    public void ajouterLireProjet () {


        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
            DAOProjet.creerTable();
        } catch (SQLException | DAOException e) {
            e.printStackTrace();
        }
        DAOProjet daoProjet = new DAOProjet();

        String varObtenue;
        String varAttendue = "Projet test 1";

        //Mise en place
        Projet projetTest = new Projet("Projet test 1");
        Projet projetObtenu = null;
        try {
            projetObtenu = daoProjet.ajouter(projetTest);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        varObtenue = projetObtenu.getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    @Test
    public void ModifierProjet () {

        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DAOProjet daoProjet = new DAOProjet();

        String varObtenue;
        String varAttendue = "Projet test 1 mofifié";

        //Mise en place
        Projet projetTest = new Projet("Projet test 1");

        Projet projetObtenu = null;
        try {
            projetTest = daoProjet.ajouter(projetTest);
            projetTest.setTitre("Projet test 1 mofifié");
            projetObtenu = daoProjet.modifier(projetTest);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        varObtenue = projetObtenu.getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    @Test
    public void lireTout () {

        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DAOProjet daoProjet = new DAOProjet();

        int varObtenue = 0;
        int varAttendue = 0;

        //Mise en place
        LinkedList<Projet> listeProjet = new LinkedList<>();

        Projet projetTest = new Projet("Projet pour test liste");
        try {
            listeProjet = daoProjet.lireTout();
            varAttendue = listeProjet.size() + 1;
            daoProjet.ajouter(projetTest);
            listeProjet = daoProjet.lireTout();
            varObtenue = listeProjet.size();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    @Test
    public void supprimerProjet () {

        try {
            SQLiteConnexion.setContext(InstrumentationRegistry.getInstrumentation().getTargetContext());
            SQLiteConnexion.getConnexion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DAOProjet daoProjet = new DAOProjet();

        int varObtenue;
        int varAttendue = 0;
        
        //Mise en place
        Projet projetASupprimer = new Projet("Projet test à supprimer");
        LinkedList<Projet> listeProjets = null;
        
        try {
            projetASupprimer = daoProjet.ajouter(projetASupprimer);
            listeProjets = daoProjet.lireTout();
            varAttendue = listeProjets.size() - 1;
            daoProjet.supprimer(projetASupprimer);
            listeProjets = daoProjet.lireTout();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        varObtenue = listeProjets.size();
                

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }









}
