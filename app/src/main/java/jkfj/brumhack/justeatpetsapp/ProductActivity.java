package jkfj.brumhack.justeatpetsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class ProductActivity extends Activity{
    private TextView txtProductName;
    private ImageView imgImage;
    private TextView txtPrice;
    private TextView txtRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        txtProductName = (TextView) findViewById(R.id.txtProductName);
        imgImage = (ImageView) findViewById(R.id.imgProduct);
        txtPrice = (TextView) findViewById(R.id.txtProductPrice);
        txtRestaurant = (TextView) findViewById(R.id.txtProductRestaurant);

        final Intent i = getIntent();
        txtProductName.setText(i.getStringExtra(ProductsActivity.PRODUCT + "name"));
        DecimalFormat df = new DecimalFormat("##.##");
        String price = df.format(i.getDoubleExtra(ProductsActivity.PRODUCT + "price", 0.00));
        txtPrice.setText("£"+price);
        txtRestaurant.setText(i.getStringExtra(ProductsActivity.PRODUCT + "restaurant"));
    }
}
