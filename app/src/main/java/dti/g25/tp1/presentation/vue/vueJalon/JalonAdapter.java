package dti.g25.tp1.presentation.vue.vueJalon;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dti.g25.tp1.R;
import dti.g25.tp1.presentation.contratJalon.ContratVuePresenteurJalonListe;
import dti.g25.tp1.presentation.modele.DAO.DAOException;
import dti.g25.tp1.presentation.vue.vueEtiquette.EtiquetteAdapter;

/**
 * Activité adapter Jalon
 * @author Philippe Joubarne
 * @since 06/03/2020
 * @version 1
 */

public class JalonAdapter extends RecyclerView.Adapter<JalonAdapter.HDHolder>{
    private ContratVuePresenteurJalonListe.IPresenteurAfficherJalon _presenteur;

    public JalonAdapter(ContratVuePresenteurJalonListe.IPresenteurAfficherJalon presenteur) {
        _presenteur = presenteur;
    }

    @NonNull
    @Override
    public HDHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vue = inflater.inflate(R.layout.fragment_jalon, parent, false);

        return new HDHolder(vue){};

    }

    public void onBindViewHolder(@NonNull HDHolder holder, final int unIndex) {

        holder.titreJalon.setText(_presenteur.getJalonTitre(unIndex));


        //écouteur sur les cartes jalons
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.modifier(unIndex);
            }
        });

    }

    @Override
    public int getItemCount() {

        int itemCount = 0;
        try {
            itemCount =  _presenteur.getNbJalon();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return itemCount;
    }


    public class HDHolder extends RecyclerView.ViewHolder{

        TextView titreJalon;
        View mView;

        public HDHolder(@NonNull View itemView) {
            super(itemView);
            titreJalon = itemView.findViewById(R.id.titreJalon);
            mView = itemView;
        }
    }


}
