package jkfj.brumhack.justeatpetsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import models.Product;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class ProductsAdapter extends ArrayAdapter<Product> {
    public ProductsAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.product_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(super.getContext());
        View productRow = inflater.inflate(R.layout.product_row, parent, false);

        Product product = getItem(position);

        //TODO convert image URL to put in image view

        TextView productName = (TextView) productRow.findViewById(R.id.txtProductName);
        productName.setText(product.getName());

        TextView productPrice = (TextView) productRow.findViewById(R.id.txtProductPrice);
        productPrice.setText(Double.toString(product.getPrice()));

        TextView productRestaurant = (TextView) productRow.findViewById(R.id.txtProductRestaurant);
        productRestaurant.setText(product.getRestaurant().getName());

        return productRow;
    }
}
