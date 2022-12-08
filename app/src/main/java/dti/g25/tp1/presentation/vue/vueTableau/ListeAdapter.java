package dti.g25.tp1.presentation.vue.vueTableau;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauListe;

public class ListeAdapter extends RecyclerView.Adapter<ListeAdapter.ViewHolder>{
    private ContratVuePresenteurTableauListe.IPresenteurTableauMenu _presenteur;
    private TicketAdapter ticketAdapter;
    Context context;

    public ListeAdapter(ContratVuePresenteurTableauListe.IPresenteurTableauMenu _presenteur) {
        this._presenteur = _presenteur;;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_liste_tableau, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListeAdapter.ViewHolder holder, final int position) {
        if (_presenteur.getNbTableaux() == 0) {
            holder.liste.setVisibility(View.GONE);
            holder.listeCacher.setVisibility(View.GONE);
            holder.listeVideMessage.setVisibility(View.VISIBLE);
        } else {

            if (_presenteur.getEtat(position)) {
                holder.tableau.setLayoutParams(_presenteur.changementTaille(holder.tableau.getLayoutParams(), true));
                holder.liste.setVisibility(View.VISIBLE);
                holder.listeCacher.setVisibility(View.GONE);
            } else {
                holder.tableau.setLayoutParams(_presenteur.changementTaille(holder.tableau.getLayoutParams(), false));
                holder.liste.setVisibility(View.GONE);
                holder.listeCacher.setVisibility(View.VISIBLE);
            }

            holder.listeVideMessage.setVisibility(View.GONE);

            if (position == 0 || position == _presenteur.getNbListes() - 1) {
                holder.listeOption.setVisibility(View.GONE);
                holder.listeTitre.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
            }

            if (position == 0) {
                ticketAdapter = new TicketAdapter(_presenteur.getTicketsProjet("ouvert",_presenteur.getListe(position)),_presenteur,_presenteur.getListe(position).getId());
                holder.listeTitre.setText(_presenteur.internationalisation(R.string.ouverts));
                holder.titreCacher.setText(_presenteur.titreVertical(_presenteur.internationalisation(R.string.ouvert)));
            } else if (position == _presenteur.getNbListes() - 1) {
                ticketAdapter = new TicketAdapter(_presenteur.getTicketsProjet("fermer",_presenteur.getListe(position)),_presenteur,_presenteur.getListe(position).getId());
                holder.listeTitre.setText(_presenteur.internationalisation(R.string.fermes));
                holder.titreCacher.setText(_presenteur.titreVertical(_presenteur.internationalisation(R.string.ferme)));
            } else {
                ticketAdapter = new TicketAdapter(_presenteur.getTicketsProjet("milieu",_presenteur.getListe(position)),_presenteur,_presenteur.getListe(position).getId());
                holder.listeTitre.setText(_presenteur.getListeTitre(position));
                holder.titreCacher.setText(_presenteur.titreVertical(_presenteur.getListeTitre(position)));
            }

            holder.listeTickets.setAdapter(ticketAdapter);
            Integer[] tagsEnvoyer = new Integer[2];
            tagsEnvoyer[0] = _presenteur.getListe(position).getId();
            tagsEnvoyer[1] = position;
            holder.tableau.setTag(tagsEnvoyer);
            holder.listeCacher.setTag(tagsEnvoyer);

            holder.listeTickets.setLayoutManager(new LinearLayoutManager(context));
            holder.listeTitre.setBackgroundColor(Color.parseColor(_presenteur.getListeCouleur(position)));
            holder.listeOption.setBackgroundColor(Color.parseColor(_presenteur.getListeCouleur(position)));
            holder.cacher.setBackgroundColor(Color.parseColor(_presenteur.getListeCouleur(position)));

            holder.titreCacher.setBackgroundColor(Color.parseColor(_presenteur.getListeCouleur(position)));
            holder.montrer.setBackgroundColor(Color.parseColor(_presenteur.getListeCouleur(position)));

            holder.listeOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu options = new PopupMenu(context, holder.listeOption);
                    options.inflate(R.menu.liste_option_menu);
                    options.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.supprimerListe:
                                    _presenteur.supprimerListe(_presenteur.getListe(position));
                                    break;
                            }
                            return false;
                        }
                    });
                    options.show();
                }
            });

            holder.cacher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _presenteur.changerEtat(position, false);
                    _presenteur.testEtat();
                    holder.liste.setVisibility(View.GONE);
                    holder.listeCacher.setVisibility(View.VISIBLE);
                    holder.tableau.setLayoutParams(_presenteur.changementTaille(holder.tableau.getLayoutParams(), false));
                }
            });

            holder.montrer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _presenteur.changerEtat(position, true);
                    _presenteur.testEtat();
                    holder.liste.setVisibility(View.VISIBLE);
                    holder.listeCacher.setVisibility(View.GONE);
                    holder.tableau.setLayoutParams(_presenteur.changementTaille(holder.tableau.getLayoutParams(), true));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (_presenteur.getNbTableaux() == 0) return 1;
        return _presenteur.getNbListes();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout liste;
        LinearLayout titreGroupe;
        LinearLayout listeCacher;
        TextView listeTitre;
        RecyclerView listeTickets;
        RelativeLayout tableau;
        TextView listeVideMessage;
        TextView listeOption;
        TextView cacher;
        TextView montrer;
        TextView titreCacher;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            liste = itemView.findViewById(R.id.liste);
            titreGroupe = itemView.findViewById(R.id.titreGroupe);
            listeTitre = itemView.findViewById(R.id.listeTitre);
            listeTickets = itemView.findViewById(R.id.listeTickets);
            tableau = itemView.findViewById(R.id.tableau);
            listeVideMessage = itemView.findViewById(R.id.listeVideMessage);
            listeOption = itemView.findViewById(R.id.listeOption);
            cacher = itemView.findViewById(R.id.cacher);
            listeCacher = itemView.findViewById(R.id.listeCacher);
            montrer = itemView.findViewById(R.id.montrer);
            titreCacher = itemView.findViewById(R.id.titreCacher);
        }
    }
}
