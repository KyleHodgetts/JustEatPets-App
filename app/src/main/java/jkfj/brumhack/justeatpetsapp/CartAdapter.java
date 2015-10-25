package jkfj.brumhack.justeatpetsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import models.CartItem;
import models.Product;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class CartAdapter extends ArrayAdapter<CartItem> {
    public CartAdapter(Context context, ArrayList<CartItem> products) {
        super(context, R.layout.product_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CartItem c = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(super.getContext());
        View productRow = inflater.inflate(R.layout.product_row, parent, false);
        ImageView image = (ImageView) productRow.findViewById(R.id.imgProduct);
        TextView name = (TextView) productRow.findViewById(R.id.txtProductName);
        TextView price = (TextView) productRow.findViewById(R.id.txtProductPrice);

        Picasso.with(getContext()).load(c.getUrl()).into(image);
        name.setText(c.getName());
        price.setText("£"+Double.toString(c.getPrice()));


        return productRow;
    }
}
