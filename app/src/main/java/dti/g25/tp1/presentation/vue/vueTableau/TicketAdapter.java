package dti.g25.tp1.presentation.vue.vueTableau;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import dti.g25.tp1.presentation.contratTableau.ContratVuePresenteurTableauListe;
import dti.g25.tp1.presentation.vue.vueTicket.EtiquetteAdapter;

/**
 * Adapteur ticket pour l'affichage des tickets dans un Recycle View
 * @author Simon Roy & Bryan Valdiviezo (Drag and Drop)
 * @since 06/03/2020
 */
public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.HDHolder> implements View.OnTouchListener{
    public static final String EXTRA_ID="dti.g25.ticket.id";
    private int listeID;
    private ArrayList<Ticket> tickets;
    Context context;
    private ContratVuePresenteurTableauListe.IPresenteurTableauMenu _presenteur;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TICKET_ID= "ticketID";
    public static final String LISTE_PRESENT_ID="listeID";

    public TicketAdapter(ArrayList<Ticket> tickets, ContratVuePresenteurTableauListe.IPresenteurTableauMenu presenteur, int listeID) {
        this.tickets = tickets;
        _presenteur = presenteur;
        this.listeID = listeID;
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
        holder.tvTitreTicket.setText(tickets.get(position).getTitre());
        holder.tvDescriptionTicket.setText(tickets.get(position).getDescription());
        holder.tvExpire.setText(_presenteur.getDate(tickets.get(position)));

        // Cache les textes s'ils sont vide
        if (holder.tvDescriptionTicket.getText().equals(""))
            holder.tvDescriptionTicket.setVisibility(View.GONE);

        if (holder.tvExpire.getText().equals(""))
            holder.tvExpire.setVisibility(View.GONE);

        EtiquetteAdapter adapter = new EtiquetteAdapter(_presenteur.getEtiquettes(tickets.get(position)));
        holder.tvEtiquettes.setAdapter(adapter);
        holder.tvEtiquettes.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        if (holder.tvEtiquettes.getAdapter().getItemCount() <= 0)
            holder.tvEtiquettes.setVisibility(View.GONE);

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS,context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(TICKET_ID);
                editor.remove(LISTE_PRESENT_ID);
                editor.apply();
                editor.putInt(TICKET_ID, tickets.get(position).getId());
                editor.putInt(LISTE_PRESENT_ID, listeID);
                editor.apply();
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, shadowBuilder, v, 0);
                } else {
                    v.startDrag(data, shadowBuilder, v, 0);
                }
                return false;
            }
        });

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _presenteur.afficherTicket(tickets.get(position).getId());
            }
        });


    }

    @Override
    public int getItemCount() {
        if (tickets == null) return 0;
        return tickets.size();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x = Math.round(event.getRawX());
        int y = Math.round(event.getRawY());
        Log.i("positionX", "Position on release = : "+x+"/"+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("positionX", "Position on touch = : "+x+"/"+y);
                break;
            case MotionEvent.ACTION_MOVE:
                _presenteur.derouleRV(x,y);
                break;
            case MotionEvent.ACTION_UP:
                Log.i("positionX", "Position on release = : "+x+"/"+y);
                break;
        }
        return false;
    }

    public class HDHolder extends RecyclerView.ViewHolder{

        TextView tvTitreTicket, tvDescriptionTicket, tvExpire;
        RecyclerView tvEtiquettes;
        LinearLayout ticket;
        View mView;

        public HDHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            tvTitreTicket = itemView.findViewById(R.id.tvTitreTicket);
            tvDescriptionTicket = itemView.findViewById(R.id.tvDescritpionTicket);
            tvEtiquettes = itemView.findViewById(R.id.tvEtiquettes);
            tvExpire = itemView.findViewById(R.id.tvExpire);
            ticket = itemView.findViewById(R.id.ticket);
            mView = itemView;
        }
    }

}