package dti.g25.tp1.ui.activite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.sql.SQLException;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.modele.DAO.DAOEtiquette;
import dti.g25.tp1.presentation.modele.DAO.DAOEtiquetteTicket;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.modele.DAO.DAOJalon;
import dti.g25.tp1.presentation.modele.DAO.DAOListe;
import dti.g25.tp1.presentation.modele.DAO.DAOProjet;
import dti.g25.tp1.presentation.modele.DAO.DAOTableau;
import dti.g25.tp1.presentation.modele.DAO.DAOTicket;
import dti.g25.tp1.presentation.modele.DAO.SQLiteConnexion;


/**
 Activité principale

 @author Simon Roy
 @since 10/02/2020
 @version 1
 */
public class  MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";

    /**
     * S'assure que toutes les tables sont créés dans la base de données
     */
    private void creerTables() {
        try {
            DAOProjet.creerTable();
            DAOTicket.creerTable();
            DAOTableau.creerTable();
            DAOListe.creerTable();
            DAOEtiquette.creerTable();
            DAOEtiquetteTicket.creerTable();
//            MembreDAO.creerTable();
            DAOJalon.creerTable();
        } catch (DAOException e) {
            System.out.println("Impossible de créer les base de données: " + e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide(); // Cacher la barre de titre par défaut
        SQLiteConnexion.setContext(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // On créer les tables
                creerTables();

                Intent intent = new Intent(MainActivity.this, ActiviteAfficherProjets.class);
                intent.putExtra(EXTRA_ID, 1);

                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
