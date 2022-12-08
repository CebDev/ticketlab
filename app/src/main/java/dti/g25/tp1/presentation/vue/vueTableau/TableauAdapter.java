package dti.g25.tp1.presentation.vue.vueTableau;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Liste;
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauListe;
import dti.g25.tp1.ui.activite.ActiviteAfficherTableaux;
import dti.g25.tp1.ui.activite.ActiviteAjouterListe;

public class TableauAdapter extends RecyclerView.Adapter<TableauAdapter.ViewHolder>{
    private ContratVuePresenteurTableauListe.IPresenteurTableauMenu _presenteur;
    Context context;

    public TableauAdapter(ContratVuePresenteurTableauListe.IPresenteurTableauMenu _presenteur) {
        this._presenteur = _presenteur;
    }

    @NonNull
    @Override
    public TableauAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_liste_liste, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final TableauAdapter.ViewHolder holder, final int position) {
        RelativeLayout.LayoutParams layoutParams;
        holder.addTableau.setVisibility(View.VISIBLE);
        if (_presenteur.getNbTableaux() == 0) {
            layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            holder.tableauOnglet.setLayoutParams(layoutParams);
            holder.tableauTitre.setVisibility(View.GONE);
            holder.addTableau.setVisibility(View.GONE);
            holder.tableauOption.setVisibility(View.GONE);
            holder.addTableauVide.setVisibility(View.VISIBLE);
            holder.addTableauVide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _presenteur.creationTableau();
                }
            });
        }

        else {
            layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            holder.tableauOnglet.setLayoutParams(layoutParams);
            holder.tableauTitre.setVisibility(View.VISIBLE);
            holder.tableauOption.setVisibility(View.VISIBLE);
            holder.addTableauVide.setVisibility(View.GONE);
            final int unePosition = position;
            if (position != _presenteur.getNbTableaux() - 1) {
                holder.addTableau.setVisibility(View.GONE);
            }

            if (_presenteur.getTableauIDAfficher() == _presenteur.getTableau(position).getId()) {
                SpannableString titre = new SpannableString(_presenteur.getTableauxTitre(position));
                titre.setSpan(new UnderlineSpan(), 0, _presenteur.getTableauxTitre(position).length(), 0);
                holder.tableauTitre.setText(titre);
                holder.tableauTitre.setTextSize(18);
                holder.tableauTitre.setTypeface(null, Typeface.BOLD);
            } else {
                holder.tableauTitre.setText(_presenteur.getTableauxTitre(position));
                holder.tableauTitre.setTypeface(null, Typeface.NORMAL);
                holder.tableauTitre.setTextSize(14);
            }

            holder.tableauTitre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _presenteur.setNouvelleListe(position);
                    _presenteur.listesEtat();
                    _presenteur.testEtat();
                }
            });

            holder.addTableau.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _presenteur.creationTableau();
                }
            });

            holder.tableauOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu options = new PopupMenu(context, holder.tableauOption);
                    options.inflate(R.menu.tableau_option_menu);
                    options.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.gestionListe:
                                    _presenteur.gestionListe(_presenteur.getTableau(position));
                                    break;
                                case R.id.supprimerTableau:
                                    _presenteur.supprimerTableau(_presenteur.getTableau(position));
                                    break;
                                case R.id.modifierTableau:
                                    _presenteur.modiferTableau(_presenteur.getTableau(position));
                                    break;
                            }
                            return false;
                        }
                    });
                    options.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (_presenteur.getNbTableaux() == 0) return 1;
        return _presenteur.getNbTableaux();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tableauTitre;
        TextView addTableau;
        TextView tableauOption;
        TextView addTableauVide;
        RelativeLayout tableauOnglet;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            tableauOnglet = itemView.findViewById(R.id.tableauOnglet);
            tableauTitre = itemView.findViewById(R.id.tableau);
            addTableau = itemView.findViewById(R.id.addTableau);
            tableauOption = itemView.findViewById(R.id.tableauOption);
            addTableauVide = itemView.findViewById(R.id.addTableauVide);
        }
    }
}
