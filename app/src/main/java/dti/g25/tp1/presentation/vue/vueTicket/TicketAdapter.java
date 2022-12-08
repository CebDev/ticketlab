package dti.g25.tp1.presentation.vue.vueTicket;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dti.g25.tp1.R;
import dti.g25.tp1.domaine.entite.Ticket;
import dti.g25.tp1.presentation.contratTicket.ContratVuePresenteurListeTickets;

/**
 * Adapteur ticket pour l'affichage des tickets dans un Recycle View
 * @author Simon Roy & Bryan Valdiviezo (Drag and Drop)
 * @since 06/03/2020
 */
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.HDHolder> {
    private ArrayList<Ticket> _tickets;
    private Context _context;
    private ContratVuePresenteurListeTickets.IPresenteurListeTicket _presenteur;

    public TicketAdapter(ArrayList<Ticket> desTickets, ContratVuePresenteurListeTickets.IPresenteurListeTicket presenteur) {
        _tickets = desTickets;
        _presenteur = presenteur;
    }

    @NonNull
    @Override
    public HDHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View vue = inflater.inflate(R.layout.fragement_ticket, parent, false);

        return new HDHolder(vue){};
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onBindViewHolder(@NonNull TicketAdapter.HDHolder holder, final int position) {
        holder.tvTitreTicket.setText(_tickets.get(position).getTitre());
        holder.tvDescriptionTicket.setText(_tickets.get(position).getDescription());
        holder.tvExpire.setText(_presenteur.getDate(_tickets.get(position)));

        // Cache les textes s'ils sont vide
        if (holder.tvDescriptionTicket.getText().equals(""))
            holder.tvDescriptionTicket.setVisibility(View.GONE);

        if (holder.tvExpire.getText().equals(""))
            holder.tvExpire.setVisibility(View.GONE);

        EtiquetteAdapter adapter = new EtiquetteAdapter(_presenteur.getEtiquettes(_tickets.get(position)));
        holder.tvEtiquettes.setAdapter(adapter);
        holder.tvEtiquettes.setLayoutManager(new LinearLayoutManager(_context, LinearLayoutManager.HORIZONTAL, false));
        if (holder.tvEtiquettes.getAdapter().getItemCount() <= 0)
            holder.tvEtiquettes.setVisibility(View.GONE);


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.afficherTicket(_tickets.get(position).getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        if (_tickets == null) return 0;
        return _tickets.size();
    }

    public class HDHolder extends RecyclerView.ViewHolder{
        TextView tvTitreTicket, tvDescriptionTicket, tvExpire;
        RecyclerView tvEtiquettes;
        LinearLayout ticket;
        View mView;

        public HDHolder(@NonNull View itemView) {
            super(itemView);
            _context = itemView.getContext();
            tvTitreTicket = itemView.findViewById(R.id.tvTitreTicket);
            tvDescriptionTicket = itemView.findViewById(R.id.tvDescritpionTicket);
            tvEtiquettes = itemView.findViewById(R.id.tvEtiquettes);
            tvExpire = itemView.findViewById(R.id.tvExpire);
            ticket = itemView.findViewById(R.id.ticket);
            mView = itemView;
        }
    }

}