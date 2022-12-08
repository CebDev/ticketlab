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
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurModifierTicket;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurModifierTicket;
import dti.g25.tp1.presentation.vue.DatePicker;
import dti.g25.tp1.presentation.vue.VueBase;

/**
 * Vue qui permet l'entré et la sortie de données pour la modification d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class VueModifierTicket extends VueBase implements ContratVuePresenteurModifierTicket.IVueModifierTicket {
    public static final String EXTRA_ID = "dti.g25.ticket.id";
    public static final String EXTRA_POSITION = "dti.g25.ticket.position";

    private ContratVuePresenteurModifierTicket.IPresenteurModifierTicket _presenteur;
    private View _racine;
    private EtiquetteAdapter _etiquettesAdapter;
    private Spinner _spinnerEtiquettes;
    private Spinner _spinnerJalons;
    private Button _btnDateEcheance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _racine = inflater.inflate(R.layout.fragment_formulaire_ticket, container, false);
        super.onCreateView(inflater, container, savedInstanceState, _racine, (PresenteurBase) _presenteur);

        _spinnerJalons = setSpinner((Spinner) _racine.findViewById(R.id.jalon), _presenteur.getTitresJalons());
        _spinnerEtiquettes = setSpinner((Spinner) _racine.findViewById(R.id.spEtiquettes), _presenteur.getTitresEtiquettes());
        _btnDateEcheance = _racine.findViewById(R.id.btnDateEcheance);

        ((EditText) _racine.findViewById(R.id.etTitre)).setText(_presenteur.getTitre());
        ((EditText) _racine.findViewById(R.id.etDescription)).setText(_presenteur.getDescription());
        ((EditText) _racine.findViewById(R.id.etAffectation)).setText(_presenteur.getPseudoMembre());
        ((Button) _racine.findViewById(R.id.btnDateEcheance)).setText(_presenteur.getDateEcheance());

        // Ajoute le RecyclerView des etiquettes
        RecyclerView recyclerViewEtiquettes = _racine.findViewById(R.id.tvEtiquettes);
        _etiquettesAdapter = new EtiquetteAdapter(_presenteur.getEtiquettes());
        recyclerViewEtiquettes.setAdapter(_etiquettesAdapter);
        recyclerViewEtiquettes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        _spinnerJalons.setSelection(_presenteur.getPositionJalon());


        // Spinner des etiquettes
        _spinnerEtiquettes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position > 0) {
                    _presenteur.ajouterEtiquette(position);
                    _spinnerEtiquettes.setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                afficherToast(getResources().getString(R.string.toast_ticket_selectionne));
            }
        });

        // Boton pour retirer une affectation
        _racine.findViewById(R.id.btnAffectation).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                ((EditText) _racine.findViewById(R.id.etAffectation)).setText("");
            }
        });

        // Bouton pour selectionner une date d'échéance
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
                _btnDateEcheance.setText("");
            }
        });

        // Bouton terminer
        _racine.findViewById(R.id.btnTerminer).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String titre = ((EditText) _racine.findViewById(R.id.etTitre)).getText().toString();
                String description = ((EditText) _racine.findViewById(R.id.etDescription)).getText().toString();
                String pseudoAffectation = ((EditText) _racine.findViewById(R.id.etAffectation)).getText().toString();
                int positionJalon = _spinnerJalons.getSelectedItemPosition();


                String dateEcheance = _btnDateEcheance.getText().toString();

                if (!titre.equals(null)) {
                    _presenteur.modifier(titre, description, pseudoAffectation, positionJalon, dateEcheance);
                    afficherToast(getResources().getString(R.string.toast_ticket_modifie));
                } else {
                    afficherToast(getResources().getString(R.string.toast_titre));
                }
            }
        });

        return _racine;
    }

    /**
     * Intialise le présenteur
     * @param presenteur
     */
    public void setPresenteur(PresenteurModifierTicket presenteur) { _presenteur = presenteur; }

    /**
     * Créer et retourne un nouveau spinner
     *
     * @param spinner
     * @param uneListe
     * @return un spinner
     */
    public Spinner setSpinner(Spinner spinner, List uneListe) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, uneListe);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        return spinner;
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
        ((Button) _racine.findViewById(R.id.btnDateEcheance)).setText(String.format("%04d-%02d-%02d", annee, mois+1, jour));
    }
}
