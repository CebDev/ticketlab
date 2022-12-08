package dti.g25.tp1.presentation.modele.mockData;

import java.util.ArrayList;
import java.util.LinkedList;

import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.domaine.entite.Ticket;

public class MockDataEtiquette {
    private static LinkedList<Etiquette> etiquettes = new LinkedList<>();


    static {
        etiquettes.add(new Etiquette(1,"Ma deuxieme etiquette", "Etiquette qui sert a moins que rien", "#FF00FF", false));
        etiquettes.add(new Etiquette(2,"Ma premiere etiquette","Etiquette qui sert a rien","#FF0000", true));
        etiquettes.add(new Etiquette(3,"Ma Troisième etiquette", "Etiquette qui sert a moins que rien", "#0000FF", false));
    }

    public static LinkedList<Etiquette> getEtiquettes() {return etiquettes;}
}
