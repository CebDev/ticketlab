package dti.g25.tp1.presentation.vue.vueProjet;

/**
 * Activité Menu Projet
 * @author Sébastien Vermandele
 * @since 01/03/2020
 * @version 1
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratProjet.ContratVuePresenteurProjetListe;

class ProjetAdapter extends RecyclerView.Adapter<ProjetAdapter.HDHolder>{

    private ContratVuePresenteurProjetListe.IPresenteurProjetMenu _presenteur;
    Context context;

    ProjetAdapter(ContratVuePresenteurProjetListe.IPresenteurProjetMenu presenteur){
        _presenteur = presenteur;
    }

    @NonNull
    @Override
    public HDHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vuePresentationProjet = inflater.inflate(R.layout.rv_presentation_projet, parent, false);
        return new HDHolder(vuePresentationProjet){};
    }

    public void onBindViewHolder(@NonNull HDHolder holder, final int position) {

        holder.tvTitreProjet.setText(_presenteur.getProjetTitre(position));
        holder.tvDescriptionProjet.setText(_presenteur.getProjetDescription(position));

        // écouteur sur les cartes projets
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.lancerActiviteAfficherTableaux(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return _presenteur.getNbProjetAfficher();
    }

    public class HDHolder extends RecyclerView.ViewHolder{

        TextView tvTitreProjet, tvDescriptionProjet;
        View mView;

        public HDHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvTitreProjet = itemView.findViewById(R.id.tvTitreProjet);
            tvDescriptionProjet = itemView.findViewById(R.id.tvDescriptionProjet);
            mView = itemView;
        }
    }


}
