package dti.g25.tp1.presentation.vue.vueProjet;

/**
 * Activité Menu Projet
 * @author Sébastien Vermandele
 * @since 01/03/2020
 * @version 1
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratProjet.ContratVuePresenteurProjetListe;
import dti.g25.tp1.presentation.presenteur.PresenteurBase;
import dti.g25.tp1.presentation.vue.VueBase;

public class VueListeProjet extends VueBase implements ContratVuePresenteurProjetListe.IVueProjetMenu {



    private ContratVuePresenteurProjetListe.IPresenteurProjetMenu _presenteur;
    private RecyclerView rvListe;
    private RecyclerView.Adapter adapter;
    private Button btnNouveauProjet;
    private EditText termeRecherche;
    private View vueProjetMenuPrincipale;

    public void set_presenteur(ContratVuePresenteurProjetListe.IPresenteurProjetMenu presenteurProjetMenu){
        _presenteur = presenteurProjetMenu;
    }

    /**
     * Méthode qui rafraichit l'adapter dans le cas de l'ajout d'un projet
     */
    @Override
    public void rafraichir() {
        if(adapter!=null)
            adapter.notifyDataSetChanged();
    }

    /**
     * Méthode qui rafraichit l'adapter dans le cas de la suppression d'un projet
     * @param position : int la position du projet dans l'adapter
     */
    @Override
    public void rafraichirSuppression(int position) {
        if(adapter!=null)
            adapter.notifyItemRemoved(position);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vueProjetMenuPrincipale = inflater.inflate(R.layout.fragment_projet_menu_principal, container, false);
        super.onCreateView(inflater, container, savedInstanceState, vueProjetMenuPrincipale, (PresenteurBase) _presenteur);

        // Recherche de projet par saisie de l'utilisateur
        termeRecherche = (EditText) vueProjetMenuPrincipale.findViewById(R.id.eTextRecherche);
        termeRecherche.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    _presenteur.setList(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // création et paramétrage du RecyclerView inclus dans fragment_projet_menu_principal
        rvListe = (RecyclerView) vueProjetMenuPrincipale.findViewById(R.id.rvListeProjets);
        rvListe.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ProjetAdapter(_presenteur);
        rvListe.setAdapter(adapter);
        // Insertion d'un écouteur pour le swipe
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(rvListe);

        btnNouveauProjet = vueProjetMenuPrincipale.findViewById(R.id.btnNouveauProjet);
        btnNouveauProjet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.lancerActiviteAjouterProjet();
            }
        });
        return vueProjetMenuPrincipale;
    }

    // Gestion des fonctions swipe gauche et droit pour lancer d'autres activités
    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                    _presenteur.lancerFragmentSuppression(position);
                    rafraichir();
                    break;
                case ItemTouchHelper.RIGHT:
                    _presenteur.lancerActiviteModifierProjet(position);
                    break;
            }
        }

        // Override de la méthode qui permet de dessiner un arrière plan au bouton qui swipe avec
        // différenciations entre édition et suppression
        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            View itemView = viewHolder.itemView;

            final ColorDrawable background;
            Drawable icon;

            if (dX > 0) {
                icon = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.ic_edit_black_24dp);
                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                background = new ColorDrawable(Color.argb(255, 35, 52, 85));
                background.setBounds(0, itemView.getTop(), (int) (itemView.getLeft() + dX), itemView.getBottom());
                icon.setBounds(itemView.getLeft() + iconMargin, itemView.getTop() + iconMargin, itemView.getLeft() + iconMargin + icon.getIntrinsicWidth(), itemView.getBottom() - iconMargin);
            } else {
                icon = ContextCompat.getDrawable(recyclerView.getContext(), R.drawable.ic_delete_black_24dp);
                int iconMargin = (itemView.getHeight() - icon.getIntrinsicHeight()) / 2;
                background = new ColorDrawable(Color.argb(255, 83, 52, 52));
                background.setBounds((int) (itemView.getRight() + dX), itemView.getTop(), itemView.getRight(), itemView.getBottom());
                icon.setBounds(itemView.getRight() - iconMargin - icon.getIntrinsicWidth(), itemView.getTop() + iconMargin, itemView.getRight() - iconMargin , itemView.getBottom() - iconMargin);
            }

            background.draw(c);
            icon.draw(c);
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    @Override
    public void retourListeProjet() {
        afficherToast(getResources().getString(R.string.toast_back_home));
    }
}
