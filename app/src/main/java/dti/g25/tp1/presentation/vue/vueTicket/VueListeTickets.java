package dti.g25.tp1.presentation.vue.vueTicket;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurListeTickets;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.presenteur.presenteurTicket.PresenteurListeTickets;
import dti.g25.tp1.presentation.vue.VueBase;

/**
 * Vue qui permet l'entré et la sortie de données pour l'affichage de la liste de tickets
 * @author Simon Roy
 * @since 06/03/2020
 */
public class VueListeTickets extends VueBase implements ContratVuePresenteurListeTickets.IVueListeTicket {
    private RecyclerView _rvListe;
    private RecyclerView.Adapter _adapter;
    private ContratVuePresenteurListeTickets.IPresenteurListeTicket _presenteur = null;
    private View _racine;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _racine = inflater.inflate(R.layout.fragment_liste_ticket, container, false);
        super.onCreateView(inflater, container, savedInstanceState, _racine, (PresenteurBase) _presenteur);


        // création et paramétrage du RecyclerView inclus dans fragment_projet_menu_principal
        _rvListe = (RecyclerView) _racine.findViewById(R.id.rvListeTickets);
        _rvListe.setLayoutManager(new LinearLayoutManager(getContext()));

        _adapter = new TicketAdapter(_presenteur.getTickets(), _presenteur);
        _rvListe.setAdapter(_adapter);


        final TabLayout tabLayout = (TabLayout) _racine.findViewById(R.id.tabTicket); // get the reference of TabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                _presenteur.changerTypeTicket(tabLayout.getSelectedTabPosition());
                _adapter = new TicketAdapter(_presenteur.getTickets(), _presenteur);
                _rvListe.setAdapter(_adapter);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });


        // Bouton ajouter un ticket
        _racine.findViewById(R.id.btnAjouterTicket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.ajouterTicket();
            }
        });

        // Bouton plus pour afficher les options suplémentaire
        _racine.findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final AlertDialog.Builder constructeur = _presenteur.creerAlertMenuPlus();
                final View vue = _presenteur.afficherMenuPlus();

                constructeur.setView(vue);
                final AlertDialog alertDialog = constructeur.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // Bouton pour afficher les etiquettes
                vue.findViewById(R.id.btnAfficherEtiquette).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _presenteur.afficherEtiquettes();
                        alertDialog.dismiss();
                    }
                });
                // Bouton pour afficher les jalons
                vue.findViewById(R.id.btnAfficherJalon).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _presenteur.afficherJalons();
                        alertDialog.dismiss();
                    }
                });
                // Bouton pour afficher les tickets en mode tableaux
                vue.findViewById(R.id.btnAfficherTableaux).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _presenteur.afficherTableaux();
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        return _racine;
    }

    @Override
    public void setPresenteur(PresenteurListeTickets unPresenteur){ _presenteur = unPresenteur; }

    @Override
    public void rafraichir() {
        if(_adapter !=null)
            _adapter.notifyDataSetChanged();
    }
}
