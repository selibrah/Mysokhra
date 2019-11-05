package com.my_sokhra.selibrah.mysokhra;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;


public class KindAdapter extends RecyclerView.Adapter<KindAdapter.KindHolder>{
    List<Kind> kind;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    public KindAdapter(List<Kind> menu)
    {
        this.kind = menu;
    }

    @Override
    public KindAdapter.KindHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.cmdlist,parent,false);
        KindAdapter.KindHolder holder = new KindAdapter.KindHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final KindAdapter.KindHolder holder, int position) {
        final Kind kindi = kind.get(position);

        Picasso mPicasso = Picasso.get();

        mPicasso.setIndicatorsEnabled(true);
        mPicasso
                .load(kindi.imgurl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.logo1) //this is optional the image to display while the url image is downloading
                .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(holder.kindImg);
        holder.kindname.setText(kindi.name);
        holder.kindprice.setText(kindi.prix+" Dh");
        if(kindi.nbr == 0) {
            holder.plus.setImageResource(R.drawable.add_to_shopping);
        }

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usersData");
        final FirebaseUser user = mAuth.getCurrentUser();

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(kindi.nbr == 0)
                {
                    mDatabase.child(user.getUid()).child("Cartitem").child("items").child(kindi.name).setValue(kindi);
                    mDatabase.child(user.getUid()).child("Cartitem").child("items").child(kindi.name).child("resto").setValue(Mycmd.restoname);
                    mDatabase.child(user.getUid()).child("Cartitem").child("items").child(kindi.name).child("menu").setValue(Mycmd.menuname);

                }
                holder.plus.setImageResource(R.drawable.checked);
                kindi.nbr = 1;
                mDatabase.child(user.getUid()).child("Cartitem").child("items").child(kindi.name).child("nbr").setValue(1);
                notifyDataSetChanged();

            }
        });



    }

    @Override
    public int getItemCount() {
        return kind.size();
    }
    class KindHolder extends RecyclerView.ViewHolder
    {
        ImageView kindImg;
        ImageView plus;
        TextView kindname;
        TextView kindprice;

        public KindHolder(@NonNull View itemView) {
            super(itemView);
            kindImg = (ImageView) itemView.findViewById(R.id.kindimg);
            plus = (ImageView) itemView.findViewById(R.id.plus);
            kindname = (TextView) itemView.findViewById(R.id.kindtxt);
            kindprice = (TextView) itemView.findViewById(R.id.kindprice);
        }
    }

}
