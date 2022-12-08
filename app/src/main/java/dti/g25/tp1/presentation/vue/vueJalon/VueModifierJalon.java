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

import java.time.LocalDate;
import java.util.Calendar;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratJalon.ContratVuePresenteurJalonModifier;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurJalon.PresenteurModifierJalon;
import dti.g25.tp1.presentation.vue.DatePicker;
import dti.g25.tp1.presentation.vue.VueBase;


/**
 * Vue qui permet l'entrée et la sortie de données pour modifier d'un jalon
 * @author Philippe Joubarne
 * @since 06/03/2020
 */


public class VueModifierJalon extends VueBase implements ContratVuePresenteurJalonModifier.IVueModifierJalon {


    private ContratVuePresenteurJalonModifier.IPresenteurModifierJalon _presenteur;
    private View racine;


    private Boolean _changerDateDebut = false;
    private Boolean _changerDateFin = false;

    public void setPresenteur(PresenteurModifierJalon presenteur) {_presenteur = presenteur; }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         racine = inflater.inflate(R.layout.fragment_jalon_formulaire, container, false);
        super.onCreateView(inflater, container, savedInstanceState, racine, (PresenteurBase) _presenteur);


        ((EditText)racine.findViewById(R.id.jalonTitre)).setText(_presenteur.getJalon().getTitre());
        ((EditText)racine.findViewById(R.id.jalonDescription)).setText(_presenteur.getJalon().getDescription());
        ((TextView)racine.findViewById(R.id.textDateDebut)).setText(_presenteur.getJalon().getDateDebut().toString());
        ((TextView)racine.findViewById(R.id.textDateFin)).setText(_presenteur.getJalon().getDateFin().toString());

        racine.findViewById(R.id.btnTerminer).setOnClickListener((new View.OnClickListener() {
            public void onClick(View v) {

                String titre = ((EditText)racine.findViewById(R.id.jalonTitre)).getText().toString().trim();
                String description = ((EditText)racine.findViewById(R.id.jalonDescription)).getText().toString().trim();
                String dateDebut = ((TextView) racine.findViewById(R.id.textDateDebut)).getText().toString();
                String dateFin = ((TextView) racine.findViewById(R.id.textDateFin)).getText().toString();

                if (!titre.equals("") &&
                        dateDebut.matches("^\\d{4}-\\d{2}-\\d{2}$") &&
                        dateFin.matches("^\\d{4}-\\d{2}-\\d{2}$") &&
                        LocalDate.parse(dateDebut).compareTo(LocalDate.parse(dateFin)) < 0) {
                    _presenteur.modifier(titre,description, dateDebut, dateFin);
                    _presenteur.terminerModification();
                    afficherToast(getResources().getString(R.string.toast_jalon_modifie));
                } else {
                    afficherToast(getResources().getString(R.string.toast_jalon_erreur));
                }

            }

        }));

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
        btnDateFin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _changerDateFin = true;

                DialogFragment datePicker = new DatePicker();
                datePicker.show(getFragmentManager(), "Date de fin");

            }
        });

        return racine;
    }

    public void onDateSet(int annee, int mois, int jour) {

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
