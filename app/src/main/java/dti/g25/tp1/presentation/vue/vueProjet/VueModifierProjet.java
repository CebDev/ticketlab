package dti.g25.tp1.presentation.vue.vueProjet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratProjet.ContratVuePresenteurProjetModifier;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

public class VueModifierProjet extends VueBase implements ContratVuePresenteurProjetModifier.IVueProjetModifier {

    private ContratVuePresenteurProjetModifier.IPresenteurProjetModifier _presenteur;
    private EditText etTitreProjet;
    private EditText etDescriptionProjet;
    private Button btnEnregistrerProjet;

    public void set_presenteur(ContratVuePresenteurProjetModifier.IPresenteurProjetModifier presenteurProjetModifier){
        _presenteur = presenteurProjetModifier;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vueProjetModifier = inflater.inflate(R.layout.fragment_projet_modifier, container, false);
        super.onCreateView(inflater, container, savedInstanceState, vueProjetModifier, (PresenteurBase) _presenteur);

        etTitreProjet = (EditText) vueProjetModifier.findViewById(R.id.etTitreProjetModifier);
        etDescriptionProjet = (EditText) vueProjetModifier.findViewById(R.id.etDescriptionProjetModifier);

        try {
            etTitreProjet.setText(_presenteur.getTitreProjet());
            etDescriptionProjet.setText(_presenteur.getDescriptionProjet());
        } catch (DAOException e) {
            e.printStackTrace();
            afficherToast(getResources().getString(R.string.toast_projet_probleme));
        }

        btnEnregistrerProjet = vueProjetModifier.findViewById(R.id.btnEnregistrerModificationProjet);
        btnEnregistrerProjet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    _presenteur.EnregistrerModification(etTitreProjet.getText().toString().trim(), etDescriptionProjet.getText().toString().trim());
                    afficherToast(getResources().getString(R.string.toast_projet_modifie));
                } catch (DAOException e) {
                    afficherToast(getResources().getString(R.string.toast_projet_probleme));
                    e.printStackTrace();
                }
                _presenteur.lancerActiviteAfficherProjets();
            }
        });
        return vueProjetModifier;
    }

    @Override
    public void afficherToast(String message) {
        super.afficherToast(message);
    }
}
