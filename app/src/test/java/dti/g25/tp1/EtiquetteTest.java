package dti.g25.tp1;

import org.junit.Test;
import dti.g25.tp1.domaine.entite.Etiquette;
import static org.junit.Assert.*;

/**
 Contient les tests  des methodes et des Attributs pour une Etiquette
 @author Philippe
 @since 11/02/2020
 @version 1
 */

public class EtiquetteTest {


    /**
     Test la méthode getDescription.
     */

    @Test
    public void constructeurEtGetDescription() {

        String varObtenue;
        String varAttendue = "Ne pas oublier d'appeler mom";

        //Mise en place
        Etiquette etiquetteTest = new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom" ,"Rouge" ,false);
        varObtenue = etiquetteTest.getDescription();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode getDescription.
     */

    @Test
    public void constructeurEtGetCouleur() {

        String varObtenue;
        String varAttendue = "Rouge";

        //Mise en place
        Etiquette etiquetteTest = new Etiquette(1, "Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false);
        varObtenue = etiquetteTest.getCouleur();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode setTitre.
     */
    @Test
    public void constructeurEtSetTitre() {

        String varObtenue;
        String varAttendue = "Repondre au cellulaire";

        //Mise en place
        Etiquette etiquetteTest = new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false);
        etiquetteTest.setTitre("Repondre au cellulaire");
        varObtenue = etiquetteTest.getTitre();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }

    /**
     Test la méthode setDescription.
     */

    @Test
    public void constructeurEtSetDescription() {

        String varObtenue;
        String varAttendue = "Allo";

        //Mise en place
        Etiquette etiquetteTest = new Etiquette(1,"Appeler Maman", "Ne pas oublier d'appeler mom","Rouge" , false);
        etiquetteTest.setDescription( "Allo");
        varObtenue = etiquetteTest.getDescription();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }


    /**
     Test la méthode setCouleur.
     */
    @Test
    public void constructeurEtSetCouleur() {

        String varObtenue;
        String varAttendue = "Bleu";

        //Mise en place
        Etiquette etiquetteTest = new Etiquette(1, "Appeler Maman", "Ne pas oublier d'appeler mom", "Rouge", false);
        etiquetteTest.setCouleur( "Bleu");
        varObtenue = etiquetteTest.getCouleur();

        //Vérification
        assertEquals(varAttendue, varObtenue);
    }




}
