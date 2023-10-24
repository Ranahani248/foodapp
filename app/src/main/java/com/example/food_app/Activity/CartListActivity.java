package com.example.food_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.food_app.Adapter.cartListAdapter;
import com.example.food_app.Interface.ChangeNumberItemsListner;
import com.example.food_app.R;
import com.example.food_app.helpers.ManagementCart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);
        managementCart=new ManagementCart(  this);
        initView();
        initList();
        CalculateCart();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn= findViewById(R.id.homeBtn);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void initView() {
        recyclerViewList=findViewById(R.id.recyclerView5);
        totalFeeTxt=findViewById(R.id.totalFeetxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.delievryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        emptyTxt=findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
        recyclerViewList = findViewById(R.id.recyclerView5);
         }

         public void initList(){
             LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
             recyclerViewList.setLayoutManager(linearLayoutManager);
             adapter = new cartListAdapter(managementCart.getListCart(), this, new ChangeNumberItemsListner(){

                 @Override
                 public void changed() {
                    CalculateCart();
                 }
             });
             recyclerViewList.setAdapter(adapter);
             if (managementCart.getListCart().isEmpty()) {
                 scrollView.setVisibility(View.GONE);
                 emptyTxt.setVisibility(View.VISIBLE);

             }else {
                 scrollView.setVisibility(View.VISIBLE);
                 emptyTxt.setVisibility(View.GONE);
             }
         }
         private void CalculateCart(){
            double percentTax = 0.02;
            double delivery = 10;
             double itemTotal = Math.round(managementCart.getTotalFee()*100)/100;
            tax = Math.round(managementCart.getTotalFee()*percentTax*100)/100;
            double total = Math.round((managementCart.getTotalFee()+ tax +delivery)*100)/100;


            totalFeeTxt.setText("$"+itemTotal);
            taxTxt.setText("$"+tax);
            deliveryTxt.setText("$"+delivery);
            totalTxt.setText("$"+total);








         }
}