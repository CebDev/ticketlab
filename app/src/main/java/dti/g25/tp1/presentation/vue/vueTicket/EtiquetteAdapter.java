package dti.g25.tp1.presentation.vue.vueTicket;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Etiquette;
import dti.g25.tp1.presentation.vue.vueTableau.ListeAdapter;

/**
 * Adapteur ticket pour l'affichage des tickets dans un Recycle View
 * @author Bryan Valdiviezo & Simon Roy (affichage/styles)
 * @since 06/03/2020
 */
public class EtiquetteAdapter extends RecyclerView.Adapter<EtiquetteAdapter.ViewHolder> {
    ArrayList<Etiquette> etiquettes;

    public EtiquetteAdapter (ArrayList<Etiquette> etiquettes){
        this.etiquettes = etiquettes;
    }
    @NonNull
    @Override
    public EtiquetteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_liste_etiquette, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull EtiquetteAdapter.ViewHolder holder, int position) {
        holder.ettiquetteTitre.setText(etiquettes.get(position).getTitre());
        holder.ettiquetteTitre.setBackgroundResource(R.drawable.arrondie);

        GradientDrawable drawable = (GradientDrawable) holder.ettiquetteTitre.getBackground();
        drawable.setColor(Color.parseColor(etiquettes.get(position).getCouleur()));
    }

    @Override
    public int getItemCount() {
        if (etiquettes == null) return 0;
        return etiquettes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ettiquetteTitre;
        RelativeLayout ettiquette;

        public ViewHolder(View itemView) {
            super(itemView);
            ettiquette = itemView.findViewById(R.id.etiquette);
            ettiquetteTitre = itemView.findViewById(R.id.etiquetteTitre);
        }
    }
}
