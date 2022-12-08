package dti.g25.tp1.presentation.vue.vueJalon;


import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratJalon.ContratVuePresenteurJalonAjouter;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurJalon.PresenteurAjouterJalon;
import dti.g25.tp1.presentation.vue.DatePicker;
import dti.g25.tp1.presentation.vue.VueBase;

/**
 * Vue qui permet l'entré et la sortie de données pour l'ajout d'un jalon
 * @author Philippe Joubarne
 * @since 06/03/2020
 */

public class VueAjouterJalon extends VueBase implements ContratVuePresenteurJalonAjouter.IVueAjouterJalon {

    private ContratVuePresenteurJalonAjouter.IPresenteurAjouterJalon _presenteur;
    private View racine;

    private Boolean _changerDateDebut = false;
    private Boolean _changerDateFin = false;


    public void setPresenteur(PresenteurAjouterJalon presenteur) {_presenteur = presenteur; }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //On crée la racine et ensuite on passe le on createView personalisé a la vue de base (VueBase) pour intégrer les fonctions partagé par les vues
        racine = inflater.inflate(R.layout.fragment_jalon_formulaire, container, false);
        super.onCreateView(inflater, container, savedInstanceState, racine, (PresenteurBase) _presenteur);

        //Écoute et réagit au bouton << terminer >> de la vue
        racine.findViewById(R.id.btnTerminer).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int id = ((EditText) racine.findViewById(R.id.jalonId)).getId();
                String titre = ((EditText) racine.findViewById(R.id.jalonTitre)).getText().toString();
                String description = ((EditText) racine.findViewById(R.id.jalonDescription)).getText().toString();
                String dateDebut = ((TextView) racine.findViewById(R.id.textDateDebut)).getText().toString();
                String dateFin = ((TextView) racine.findViewById(R.id.textDateFin)).getText().toString();

                if (!titre.equals("") &&
                        dateDebut.matches("^\\d{4}-\\d{2}-\\d{2}$") &&
                        dateFin.matches("^\\d{4}-\\d{2}-\\d{2}$") &&
                        LocalDate.parse(dateDebut).compareTo(LocalDate.parse(dateFin)) < 0) {
                    _presenteur.ajouter(id,titre,description,dateDebut,dateFin);
                    afficherToast(getResources().getString(R.string.toast_jalon_ajoute));
                } else {
                    afficherToast(getResources().getString(R.string.toast_jalon_erreur));
                }
            }
        });

        Button btnDateDebut = (Button) racine.findViewById(R.id.jalonDateDebut);
        btnDateDebut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _changerDateDebut = true;

                DialogFragment datePicker = new DatePicker();
                datePicker.show(getFragmentManager(), "Date de Debut");
            }
        });

        Button btnDateFin = (Button)racine.findViewById(R.id.jalonDateFin);
        btnDateFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _changerDateFin = true;

                DialogFragment datePicker = new DatePicker();
                datePicker.show(getFragmentManager(), "Date de fin");
            }
        });

        return racine;
    }

        public void onDateSet( int annee, int mois, int jour) {

            String date = String.format("%04d-%02d-%02d", annee, mois+1, jour);

            if (_changerDateDebut) {
                TextView textDateDebut = (TextView) racine.findViewById(R.id.textDateDebut);
                textDateDebut.setText(date);
                _changerDateDebut = false;
            } else if (_changerDateFin) {
                TextView textDateFin = (TextView) racine.findViewById(R.id.textDateFin);
                textDateFin.setText(date);
                _changerDateFin = false;
            }
        }

}
