package com.example.food_app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.food_app.Domain.FoodDomain;
import com.example.food_app.R;
import com.example.food_app.helpers.ManagementCart;

public class ShowDetailsActivity extends AppCompatActivity {
    private TextView addToCartBtn, feeTxt, descriptionTxt, numberOrderTxt, titleTxt;
    private int numberOrder = 1 ;
    private ImageView plusBtn, minusBtn, picFood;
    private FoodDomain object;

    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        managementCart=new ManagementCart (  this);
        initView();
        getBundle();
    }
    private void getBundle(){
        object =(FoodDomain)getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getApplicationContext().getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                  .into(picFood);
          titleTxt.setText(object.getTitle());
          feeTxt.setText("$"+object.getFee());
          descriptionTxt.setText(object.getDescription());

          numberOrderTxt.setText(String.valueOf(numberOrder));
          plusBtn.setOnClickListener(v -> {
              numberOrder += 1;
              numberOrderTxt.setText(String.valueOf(numberOrder));
          });
          minusBtn.setOnClickListener(v -> {
                      if (numberOrder > 1) {
                          numberOrder -= 1;
                      }
                      ;
                      numberOrderTxt.setText(String.valueOf(numberOrder));
                  });

          addToCartBtn.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      object.setNumberInCart(numberOrder);
                      managementCart.insertFood(object);

                  }
          });
    }
    private void initView(){
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.fee);
        addToCartBtn = findViewById(R.id.addToCartBtn);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        picFood = findViewById(R.id.picFood);

    }
}