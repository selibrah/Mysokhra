package com.my_sokhra.selibrah.mysokhra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.DatabaseReference;


import java.util.List;

public class CommandesAdapter extends RecyclerView.Adapter<CommandesAdapter.CommandesHolder> {
    List<Commandes> commandes;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    public CommandesAdapter(List<Commandes> commandes)
    {
        this.commandes = commandes;
    }

    @Override
    public CommandesAdapter.CommandesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmdlist,parent,false);
        CommandesAdapter.CommandesHolder holder = new CommandesAdapter.CommandesHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommandesAdapter.CommandesHolder holder, int position) {
        final Commandes commandesi = commandes.get(position);

        holder.commandesImg.setImageResource(R.drawable.foo5);
        holder.commandesname.setText(commandesi.name);
        holder.commandestotal.setText(commandesi.total+" Dh");
        holder.commandestime.setText(commandesi.tmtdlvry + " min");

    }

    @Override
    public int getItemCount() {
        return commandes.size();
    }
    class CommandesHolder extends RecyclerView.ViewHolder
    {
        ImageView commandesImg;
        TextView commandesname;
        TextView commandestotal;
        TextView commandestime;

        public CommandesHolder(@NonNull View itemView) {
            super(itemView);
            commandesImg = (ImageView) itemView.findViewById(R.id.commandesimg);
            commandesname = (TextView) itemView.findViewById(R.id.commandesname);
            commandestotal = (TextView) itemView.findViewById(R.id.commandestotal);
            commandestotal = (TextView) itemView.findViewById(R.id.commandestime);

        }
    }

}

