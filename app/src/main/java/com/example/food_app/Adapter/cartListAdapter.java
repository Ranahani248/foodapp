package com.example.food_app.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_app.Domain.FoodDomain;
import com.example.food_app.R;
import com.example.food_app.helpers.ManagementCart;
import com.example.food_app.Interface.ChangeNumberItemsListner;

import java.util.ArrayList;
public class cartListAdapter extends  RecyclerView.Adapter<cartListAdapter.ViewHolder> {
        private ArrayList<FoodDomain> foodDomains;
        private ManagementCart managementCart;
        private ChangeNumberItemsListner changeNumberItemsListner;
        public cartListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemsListner changeNumberItemsListner) {
                this.foodDomains=foodDomains;
                this.managementCart=new ManagementCart(context);
                this.changeNumberItemsListner=changeNumberItemsListner;
        }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new cartListAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.num.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart()*foodDomains.get(position).getFee())*100)/100));
       int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),
               "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             managementCart.plusNumberFood(foodDomains, position,new ChangeNumberItemsListner(){
                @Override
                public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListner.changed();
                }

                });


            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(foodDomains, position,new ChangeNumberItemsListner(){
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListner.changed();
                    }

                });


            }
        });
        }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView num,totalEachItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            feeEachItem=itemView.findViewById(R.id.feeEachItem);
            pic=itemView.findViewById(R.id.picCart);
            plusItem=itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
            num=itemView.findViewById(R.id.numberItemTxt);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);

        }
    }
}
