package jkfj.brumhack.justeatpetsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

import models.Pet;
import models.Product;
import models.ShoppingCart;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class ProductActivity extends Activity{
    private TextView txtProductName;
    private ImageView imgImage;
    private TextView txtPrice;
    private TextView txtRestaurant;
    private ImageButton btnAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        txtProductName = (TextView) findViewById(R.id.txtProductName);
        imgImage = (ImageView) findViewById(R.id.imgProduct);
        txtPrice = (TextView) findViewById(R.id.txtProductPrice);
        txtRestaurant = (TextView) findViewById(R.id.txtProductRestaurant);
        btnAddToCart = (ImageButton) findViewById(R.id.btnAddToCart);


        final Intent i = getIntent();
        txtProductName.setText(i.getStringExtra(ProductsActivity.PRODUCT + "name"));
        Picasso.with(this).load(i.getStringExtra(ProductsActivity.PRODUCT + "image")).into(imgImage);
        DecimalFormat df = new DecimalFormat("##.##");
        String price = df.format(i.getDoubleExtra(ProductsActivity.PRODUCT + "price", 0.00));
        txtPrice.setText("£"+price);
        txtRestaurant.setText(i.getStringExtra(ProductsActivity.PRODUCT + "restaurant"));

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShoppingCart.addProduct(txtProductName.getText().toString(), Double.parseDouble(txtPrice.getText().toString().substring(1)), i.getStringExtra(ProductsActivity.PRODUCT + "image"));
                Toast.makeText(ProductActivity.this, "Product added to basket", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
