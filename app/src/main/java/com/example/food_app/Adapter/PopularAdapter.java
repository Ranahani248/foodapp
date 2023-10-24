package com.example.food_app.Adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_app.Activity.ShowDetailsActivity;
import com.example.food_app.Domain.FoodDomain;
import com.example.food_app.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<FoodDomain> popularFood;

        public PopularAdapter(ArrayList<FoodDomain> categoryFood) {
        this.popularFood = categoryFood;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewHolder holder, int position) {
    holder.title.setText(popularFood.get(position).getTitle());
    holder.fee.setText(String.valueOf(popularFood.get(position).getFee()));

    int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (holder.itemView.getContext(), ShowDetailsActivity.class);
                intent.putExtra( "object", popularFood.get(position));
                holder.itemView.getContext().startActivity (intent);
            }
            });


        }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        ImageView pic;
        TextView addbtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title1);
            fee = itemView.findViewById(R.id.pop_fee);
            pic = itemView.findViewById(R.id.pop_img);
            addbtn = itemView.findViewById(R.id.addbtn);
        }
}}
