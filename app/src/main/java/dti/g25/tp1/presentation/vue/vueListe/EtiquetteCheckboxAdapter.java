package dti.g25.tp1.presentation.vue.vueListe;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratListe.ContratVuePresenteurListeNouveau;
import dti.g25.tp1.presentation.vue.vueTableau.ListeAdapter;

public class EtiquetteCheckboxAdapter extends RecyclerView.Adapter<EtiquetteCheckboxAdapter.ViewHolder>{
    ContratVuePresenteurListeNouveau.IPresenteurListeNouveau _presenteur;
    public EtiquetteCheckboxAdapter (ContratVuePresenteurListeNouveau.IPresenteurListeNouveau _presenteur) {
        this._presenteur = _presenteur;
    }
    @NonNull
    @Override
    public EtiquetteCheckboxAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_liste_checkbox, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        RelativeLayout.LayoutParams layoutParams;
        if (_presenteur.toutEtiquette() == null || _presenteur.toutEtiquette().size() == 0) {
            layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            holder.etiquetteCheckbox.setLayoutParams(layoutParams);
            holder.titreEtiquette.setVisibility(View.GONE);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            holder.etiquetteCheckbox.setLayoutParams(layoutParams);
            holder.etiquetteCheckbox.setTag(_presenteur.getEtiquette(position).getId());
            holder.etiquettesVide.setVisibility(View.GONE);
            holder.titreEtiquette.setText(_presenteur.getEtiquette(position).getTitre());
            holder.titreEtiquette.setBackgroundColor(Color.parseColor(_presenteur.getEtiquette(position).getCouleur()));
            if (_presenteur.getEtiquette(position).estListe())
                holder.titreEtiquette.setChecked(true);
            holder.titreEtiquette.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    _presenteur.ajouterLienEtiquetteTableau(buttonView.isChecked(), _presenteur.getEtiquette(position).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (_presenteur.toutEtiquette() == null || _presenteur.toutEtiquette().size() == 0) return 1;
        return _presenteur.toutEtiquette().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox titreEtiquette;
        TextView etiquettesVide;
        RelativeLayout etiquetteCheckbox;

        public ViewHolder (View itemView) {
            super(itemView);
            titreEtiquette = itemView.findViewById(R.id.titreEtiquetteCheckbox);
            etiquettesVide = itemView.findViewById(R.id.etiquettesVide);
            etiquetteCheckbox = itemView.findViewById(R.id.etiquetteCheckbox);
        }
    }
}
