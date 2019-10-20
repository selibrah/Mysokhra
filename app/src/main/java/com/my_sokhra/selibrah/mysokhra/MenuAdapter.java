package com.my_sokhra.selibrah.mysokhra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuHolder>{
    List<Menuitem> menu;

    public MenuAdapter(List<Menuitem> menu)
    {
        this.menu = menu;
    }
    @Override
    public MenuAdapter.MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_menu_list,parent,false);
        MenuAdapter.MenuHolder holder = new MenuAdapter.MenuHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuHolder holder, int position) {
        final Menuitem menui = menu.get(position);


        Picasso mPicasso = Picasso.get();

        mPicasso.setIndicatorsEnabled(true);
        mPicasso
                .load(menui.imgurl)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .placeholder(R.drawable.logo1) //this is optional the image to display while the url image is downloading
                .error(R.drawable.ic_launcher_background)         //this is also optional if some error has occurred in downloading the image this image would be displayed
                .into(holder.menuImg);
        holder.menuname.setText(menui.name);
        holder.menuImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Mycmd.class);

                intent.putExtra("Mname",menui.name);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menu.size();
    }
    class MenuHolder extends RecyclerView.ViewHolder
    {
        ImageView menuImg;
        TextView menuname;

        public MenuHolder(@NonNull View itemView) {
            super(itemView);
            menuImg = (ImageView) itemView.findViewById(R.id.menimg);
            menuname = (TextView) itemView.findViewById(R.id.mename);
        }
    }
}
