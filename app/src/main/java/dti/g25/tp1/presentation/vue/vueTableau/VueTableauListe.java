package dti.g25.tp1.presentation.vue.vueTableau;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauListe;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

public class VueTableauListe extends VueBase implements ContratVuePresenteurTableauListe.IVueTableauMenu {

    private  ContratVuePresenteurTableauListe.IPresenteurTableauMenu _presenteur;
    private RecyclerView listeTableau;
    private RecyclerView listeListe;
    private TableauAdapter adapterTableau;
    private ListeAdapter adapterListe;
    private Button ajouterTicket;
    private View view;

    public void set_presenteur(ContratVuePresenteurTableauListe.IPresenteurTableauMenu _presenteur) {
        this._presenteur = _presenteur;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_afficher_tableau_liste, container, false);
        super.onCreateView(inflater, container, savedInstanceState, view, (PresenteurBase) _presenteur);
        listeTableau = view.findViewById(R.id.listeTableau);
        listeListe = view.findViewById(R.id.listeListe);
        ajouterTicket = view.findViewById(R.id.btnAjouterTicket);
        ajouterTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.commencerAjouterTicket();
            }
        });
        initRecyclerView();

        view.findViewById(R.id.btnPlus).setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                final AlertDialog.Builder constructeur = _presenteur.creerAlertMenuPlus();
                final View vue = _presenteur.afficherMenuPlus();

                constructeur.setView(vue);
                final AlertDialog alertDialog = constructeur.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                vue.findViewById(R.id.btnAfficherEtiquette).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _presenteur.afficherEtiquettes();
                        alertDialog.dismiss();
                    }
                });
                vue.findViewById(R.id.btnAfficherJalon).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _presenteur.afficherJalons();
                        alertDialog.dismiss();
                    }
                });
                vue.findViewById(R.id.btnAfficherListe).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        _presenteur.afficherListeTickets();
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        return view;
    }

    private void initRecyclerView(){
        adapterTableau = new TableauAdapter(_presenteur);
        adapterListe = new ListeAdapter(_presenteur);
        listeTableau.setAdapter(adapterTableau);
        listeListe.setAdapter(adapterListe);
        listeListe.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()){
                    case DragEvent.ACTION_DRAG_STARTED:
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        derouleRV(Math.round(event.getX()), Math.round(event.getY()));
                        break;
                    case DragEvent.ACTION_DROP:
                        View view = listeListe.findChildViewUnder(event.getX(),event.getY());
                        Integer[] tagsRecu = (Integer[]) view.getTag();
                        _presenteur.dragChangement(tagsRecu[0]);
                        _presenteur.changerEtat(tagsRecu[1], true);
                        break;
                }
                return true;
            }
        });
        listeTableau.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        listeListe.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void rafraichir() {
        if(adapterTableau!=null && adapterListe!=null) {
            adapterTableau.notifyDataSetChanged();
            adapterListe.notifyDataSetChanged();
        }
    }

    @Override
    public void derouleRV(int x, int y) {
        if (x > 900)
            listeListe.scrollBy(30,0);
        else if (x < 300)
            listeListe.scrollBy(-30,0);
    }
}
