package com.my_sokhra.selibrah.mysokhra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder>{
    List<itemCart> menu;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private DatabaseReference jDatabase;
    private int nbr;

    public CartAdapter(List<itemCart> menu)
    {
        this.menu = menu;
    }
    @Override
    public CartAdapter.CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartview,parent,false);
        CartAdapter.CartHolder holder = new CartAdapter.CartHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartHolder holder, final int position) {
        final itemCart menui = menu.get(position);

        Picasso mPicasso = Picasso.get();

        mPicasso.setIndicatorsEnabled(true);
        mPicasso
                .load(menui.imgurl)
                .placeholder(R.drawable.logo1) //this is optional the image to display while the url image is downloading
                .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(holder.kindImg);
        holder.kindname.setText(menui.name);
        holder.kindprice.setText(menui.prix+" Dh");
        holder.plus.setImageResource(R.drawable.close);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("usersData");
        final FirebaseUser user = mAuth.getCurrentUser();
        jDatabase = mDatabase.child(user.getUid()).child("Cartitem").child("items").child(menui.name);
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jDatabase.removeValue();
            }
        });
        holder.kindnbr.setText(String.valueOf(menui.nbr));
        holder.itplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child(user.getUid()).child("Cartitem").child("items").child(menui.name).child("nbr").setValue(++menui.nbr);
            }
        });
        holder.itminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child(user.getUid()).child("Cartitem").child("items").child(menui.name).child("nbr").setValue(--menui.nbr);
                if(menui.nbr<=0)
                {
                    mDatabase.child(user.getUid()).child("Cartitem").child("items").child(menui.name).removeValue();
                }
            }

        });

    }

    @Override
    public int getItemCount() {
        return menu.size();
    }
    class CartHolder extends RecyclerView.ViewHolder
    {
        ImageView kindImg;
        ImageView plus;
        TextView kindname;
        TextView kindprice;
        TextView kindnbr;
        TextView itplus;
        TextView itminus;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            kindImg = (ImageView) itemView.findViewById(R.id.itemimg);
            plus = (ImageView) itemView.findViewById(R.id.close);
            kindname = (TextView) itemView.findViewById(R.id.itemtxt);
            kindprice = (TextView) itemView.findViewById(R.id.itemprice);
            kindnbr = (TextView) itemView.findViewById(R.id.nbr);
            itplus = (TextView) itemView.findViewById(R.id.itplus);
            itminus = (TextView) itemView.findViewById(R.id.itminus);
        }
    }
    public void deletitem(int position)
    {

    }
}

