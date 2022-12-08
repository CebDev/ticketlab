package dti.g25.tp1.presentation.vue.vueTicket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurAjouterTicket;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurAjouterTicket;
import dti.g25.tp1.presentation.vue.DatePicker;
import dti.g25.tp1.presentation.vue.VueBase;

/**
 * Vue qui permet l'entré et la sortie de données pour l'ajout d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class VueAjouterTicket extends VueBase implements ContratVuePresenteurAjouterTicket.IVueAjouterTicket {
    public static final String EXTRA_POSITION="dti.g25.nombres.position";

    private ContratVuePresenteurAjouterTicket.IPresenteurAjouterTicket _presenteur;
    private View _racine;
    private EtiquetteAdapter _etiquettesAdapter;
    private Spinner _spinnerJalon;
    private Spinner _spinnerEtiquette;
    private Button _btnDateEcheance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // On créer la racine et ensuite on passe le on creatView personalisé à la vue de base (VueBase) pour intégrer les fonctions partagé par les vues
        _racine = inflater.inflate(R.layout.fragment_formulaire_ticket, container, false);
        super.onCreateView(inflater, container, savedInstanceState, _racine, (PresenteurBase) _presenteur);

        _etiquettesAdapter = new EtiquetteAdapter(_presenteur.getEtiquettes());
        _spinnerEtiquette = setSpinner((Spinner) _racine.findViewById(R.id.spEtiquettes), _presenteur.getTitresEtiquettes());
        _spinnerJalon = setSpinner((Spinner) _racine.findViewById(R.id.jalon), _presenteur.getTitresJalons());
        _btnDateEcheance = _racine.findViewById(R.id.btnDateEcheance);

        // Affiche la liste des etiquettes du ticket
        RecyclerView recyclerViewEtiquettes = _racine.findViewById(R.id.tvEtiquettes);
        recyclerViewEtiquettes.setAdapter(_etiquettesAdapter);
        recyclerViewEtiquettes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Spinner des étiquettes
        _spinnerEtiquette.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) {
                    _presenteur.ajouterEtiquette(position);
                    _spinnerEtiquette.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Bouton pour retirer l'affectation
        _racine.findViewById(R.id.btnAffectation).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                ((EditText) _racine.findViewById(R.id.etAffectation)).setText("");
            }
        });

        // Bouton pour séléctionner une dates d'échéance
        _btnDateEcheance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePicker();
                datePicker.show(getFragmentManager(), getResources().getString(R.string.date_echeance));
            }
        });

        // Bouton pour retirer une date d'échéance
        _racine.findViewById(R.id.btnRetirerDateEcheance).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _btnDateEcheance.setText(null);
            }
        });

        // Bouton terminer
        _racine.findViewById(R.id.btnTerminer).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String titre = ((EditText) _racine.findViewById(R.id.etTitre)).getText().toString();
                String description = ((EditText) _racine.findViewById(R.id.etDescription)).getText().toString();
                String pseudoAffectation = ((EditText) _racine.findViewById(R.id.etAffectation)).getText().toString();
                String dateEcheance = _btnDateEcheance.getText().toString();
                int positionJalon = _spinnerJalon.getSelectedItemPosition();


                if (!titre.equals("")) {
                    _presenteur.ajouter(titre, description, pseudoAffectation, positionJalon, dateEcheance);
                    afficherToast(getResources().getString(R.string.toast_ticket_ajoute));
                } else {
                    afficherToast(getResources().getString(R.string.toast_titre));
                }
            }
        });

        return _racine;
    }

    /**
     * Intialise le présenteur par celui passé en paramètre
     * @param unPresenteur
     */
    @Override
    public void setPresenteur(PresenteurAjouterTicket unPresenteur) { _presenteur = unPresenteur; }

    /**
     * Intialise et retourne un spinner
     * @param unSpinner
     * @param uneListe
     * @return le spinner initalisé
     */
    @Override
    public Spinner setSpinner(Spinner unSpinner, List uneListe) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, uneListe);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unSpinner.setAdapter(adapter);

        return unSpinner;
    }

    /** Rafraichie la liste d'étiquettes */
    @Override
    public void rafraichir() {
        if(_etiquettesAdapter !=null)
            _etiquettesAdapter.notifyDataSetChanged();
    }

    /**
     * Formate la date choisie et L'écrit sur le bouton de date d'échéance
     *
     * @param annee
     * @param mois
     * @param jour
     */
    @Override
    public void onDateSet(int annee, int mois, int jour) {
        _btnDateEcheance.setText(String.format("%04d-%02d-%02d", annee, mois+1, jour));
    }
}
