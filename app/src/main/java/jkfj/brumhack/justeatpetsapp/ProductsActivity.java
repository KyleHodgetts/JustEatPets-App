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

import models.Product;
import models.Restaurant;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class ProductsActivity extends Activity {
    private static final String PRODUCTS_URL = "http://api.justeatpets.com/products/";
    public static final String PRODUCT = "product ";

    private ListView listProducts;
    private ProductsAdapter productsAdapter;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        listProducts = (ListView)findViewById(R.id.listProducts);
        listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product p = (Product) parent.getItemAtPosition(position);
                Intent i = new Intent(ProductsActivity.this, ProductActivity.class);
                i.putExtra(PRODUCT + "name", p.getName());
                i.putExtra(PRODUCT + "price", p.getPrice());
                i.putExtra(PRODUCT + "restaurant", "Restaurant X");
                startActivity(i);
            }
        });
        products = new ArrayList<Product>();
        productsAdapter = new ProductsAdapter(this, products);
        listProducts.setAdapter(productsAdapter);
        ParseTask parseTask = new ParseTask();
        String filter = getIntent().getStringExtra(MainActivity.FILTER);
        String url = PRODUCTS_URL;
        if(filter != null) {
            url += "pet/" + filter;
        }
        parseTask.execute(url);

    }

    private class ParseTask extends AsyncTask<String, Product, Void> {

        private String readData(String urlName) throws IOException {
            StringBuffer buffer = new StringBuffer();
            URL url = new URL(urlName);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.connect();
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine = in.readLine();
            while (inputLine != null) {
                buffer.append(inputLine);
                inputLine = in.readLine();
            }
            in.close();
            connection.disconnect();
            return(buffer.toString());
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                JSONArray array = new JSONArray(readData(params[0]));
                for(int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    int id = row.getInt("id");
                    String name = row.getString("name");
                    double price = row.getDouble("price");
                    String pet = row.getString("pet");
                    int restaurantId = row.getInt("restaurant_id");
                    String photoUrl = row.getString("photo");
                    publishProgress(new Product(id, name, price, pet, restaurantId, photoUrl));
                }

            }
            catch(IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Product... product) {
            products.add(product[0]);
            productsAdapter.notifyDataSetChanged();
        }
    }


}
