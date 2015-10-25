package jkfj.brumhack.justeatpetsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import models.CartItem;
import models.Product;
import models.Restaurant;
import models.ShoppingCart;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class CartActivity extends Activity {
    private static final String PRODUCTS_URL = "http://api.justeatpets.com/products/";
    public static final String PRODUCT = "product ";

    private ListView listProducts;
    private CartAdapter productsAdapter;
    private ArrayList<CartItem> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        listProducts = (ListView)findViewById(R.id.listProducts);
        products = ShoppingCart.getShoppingCart();
        productsAdapter = new CartAdapter(this, products);
        listProducts.setAdapter(productsAdapter);
    }
}
