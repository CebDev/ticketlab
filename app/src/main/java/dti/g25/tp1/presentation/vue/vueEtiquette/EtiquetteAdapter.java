package dti.g25.tp1.presentation.vue.vueEtiquette;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratEtiquette.ContratVuePresenteurEtiquetteListe;


/**
 * Activité Menu Etiquette
 * @author Philippe Joubarne
 * @since 06/03/2020
 * @version 1
 */

public class EtiquetteAdapter extends RecyclerView.Adapter<EtiquetteAdapter.HDHolder>{
    private ContratVuePresenteurEtiquetteListe.IPresenteurAfficherEtiquette _presenteur;

    public EtiquetteAdapter(ContratVuePresenteurEtiquetteListe.IPresenteurAfficherEtiquette presenteur){
        _presenteur = presenteur;
    }

    @NonNull
    @Override
    public HDHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vue = inflater.inflate(R.layout.fragment_etiquette, parent, false);

        return new HDHolder(vue){};
    }

    public void onBindViewHolder(@NonNull HDHolder holder, final int unIndex) {
        final int id = unIndex+1;

        holder.titreEtiquette.setText(_presenteur.getEtiquetteTitre(id));
        holder.titreEtiquette.setBackgroundColor(Color.parseColor(_presenteur.getEtiquetteCouleur(id)));

        //écouteur sur les cartes etiquettes
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.modifier(id);
            }
        });
    }

    @Override
    public int getItemCount() { return _presenteur.getNbEtiquette(); }


    public class HDHolder extends RecyclerView.ViewHolder{

        TextView titreEtiquette;
        View mView;

        public HDHolder(@NonNull View itemView) {
            super(itemView);
            titreEtiquette = itemView.findViewById(R.id.titreEtiquette);
            mView = itemView;
        }
    }




}
