package dti.g25.tp1.presentation.vue.vueTicket;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurAfficherTicket;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurAfficherTicket;
import dti.g25.tp1.presentation.vue.VueBase;

/**
 * Vue qui permet l'entré et la sortie de données pour l'affichage d'un ticket
 * @author Simon Roy
 * @since 06/03/2020
 */
public class VueAfficherTicket extends VueBase implements ContratVuePresenteurAfficherTicket.IVueAfficherTicket {
    public static final String EXTRA_POSITION="dti.g25.nombres.position";

    private View _racine;
    private Boolean _estOuvert = true;
    private ContratVuePresenteurAfficherTicket.IPresenteurAfficherTicket _presenteur;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        _racine = inflater.inflate(R.layout.fragment_afficher_ticket, container, false);

        super.onCreateView(inflater, container, savedInstanceState, _racine, (PresenteurBase) _presenteur);

        if (_presenteur != null) {
            Button boutonFermerOuvrir = _racine.findViewById(R.id.btnFermetOuvrirTicket);

            boutonFermerOuvrir.setText(getResources().getText(R.string.fermer));
            boutonFermerOuvrir.setBackgroundResource(R.drawable.boutton_rouge);

            if (_presenteur.estExpire())
                ((TextView) _racine.findViewById(R.id.tvEcheance)).setTextColor(Color.parseColor("#FF0000"));

            ((TextView) _racine.findViewById(R.id.tvStatus)).setText(_presenteur.getStatus());
            ((TextView) _racine.findViewById(R.id.tvTitre)).setText(_presenteur.getTitre());
            ((TextView) _racine.findViewById(R.id.tvTicketDescription)).setText(_presenteur.getDescription());
            ((TextView) _racine.findViewById(R.id.tvAffectation)).setText(_presenteur.getPseudoMembre());
            ((TextView) _racine.findViewById(R.id.tvEcheance)).setText(_presenteur.getDateEcheance());
            ((TextView) _racine.findViewById(R.id.tvJalon)).setText(_presenteur.getTitreJalon());

            // Ajoute le RecyclerView des etiquettes
            EtiquetteAdapter adapter = new EtiquetteAdapter(_presenteur.getEtiquettes());
            RecyclerView recyclerViewEtiquettes = _racine.findViewById(R.id.tvEtiquettes);
            recyclerViewEtiquettes.setAdapter(adapter);
            recyclerViewEtiquettes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        }


        _racine.findViewById(R.id.btnFermetOuvrirTicket).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (_estOuvert) {
                    _presenteur.fermer();
                    afficherToast(getResources().getString(R.string.toast_ticket_ferme));
                } else {
                    _presenteur.rouvrir();
                    afficherToast(getResources().getString(R.string.toast_ticket_rouvert));
                }
            }
        });

        _racine.findViewById(R.id.btnModifierTicket).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                _presenteur.modifier();
            }
        });

        return _racine;
    }

    /**
     * Initalise le présenteur
     * @param unPresenteur
     */
    @Override
    public void setPresenteur(PresenteurAfficherTicket unPresenteur) { _presenteur = unPresenteur; }

    /** Change le ticket pour un ticket fermé */
    @Override
    public void fermer() {
        Button boutonFermerOuvrir =  _racine.findViewById(R.id.btnFermetOuvrirTicket);

        boutonFermerOuvrir.setText(getResources().getText(R.string.rouvrir));
        boutonFermerOuvrir.setBackgroundResource(R.drawable.boutton_vert);
        _estOuvert = false;
    }
}
