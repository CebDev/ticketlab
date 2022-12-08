package dti.g25.tp1.presentation.modele.mockData;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.LinkedList;
import dti.g25.tp1.domaine.entite.Jalon;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MockDataJalon {
    private static LinkedList<Jalon> jalons = new LinkedList<>();


    static {
        jalons.add(new Jalon(1,"Jalon de Simon", "Premier jalon de la semaine 1", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"),1));
        jalons.add(new Jalon(2,"Jalon de Philippe","Deuxieme jalon de la semaine 2", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"),1));
        jalons.add(new Jalon(3,"Jalon de Sebastien", "Troisieme jalon de la semaine 3", LocalDate.parse("2020-02-10"), LocalDate.parse("2020-03-10"),1));

    }

    public static LinkedList<Jalon> getJalons() {return jalons;}

}
