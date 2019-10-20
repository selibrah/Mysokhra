package com.my_sokhra.selibrah.mysokhra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RestoAdapter extends RecyclerView.Adapter<RestoAdapter.RestoHolder> {


    List<Resto> restos;

    public RestoAdapter(List<Resto> restos)
    {
        this.restos = restos;
    }
    @Override
    public RestoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview,parent,false);
        RestoHolder holder = new RestoHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestoHolder holder, int position) {
            final Resto rest = restos.get(position);

        Picasso mPicasso = Picasso.get();

        mPicasso.setIndicatorsEnabled(true);
        mPicasso
                .load(rest.imgurl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.logo1) //this is optional the image to display while the url image is downloading
                .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(holder.restoImg);
        holder.restoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(v.getContext());
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Rname", rest.name); //InputString: from the EditText
                editor.commit();
                Intent intent = new Intent(v.getContext(), MenuList.class);
                intent.putExtra("Rname",rest.name);
                v.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return restos.size();
    }
    class RestoHolder extends RecyclerView.ViewHolder
    {
        ImageView restoImg;

        public RestoHolder(@NonNull View itemView) {
            super(itemView);
            restoImg = (ImageView) itemView.findViewById(R.id.restimg);
        }
    }


}
