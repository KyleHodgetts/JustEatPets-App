package jkfj.brumhack.justeatpetsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
public class ProductsAdapter extends ArrayAdapter<Product> {
    private LayoutInflater inflater;
    private View productRow;
    private Product product;
    private TextView productName;
    private TextView productPrice;
    private TextView productRestaurant;

    public ProductsAdapter(Context context, ArrayList<Product> products) {
        super(context, R.layout.product_row, products);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        inflater = LayoutInflater.from(super.getContext());
        productRow = inflater.inflate(R.layout.product_row, parent, false);

        product = getItem(position);

        //TODO convert image URL to put in image view

        productName = (TextView) productRow.findViewById(R.id.txtProductName);
        productName.setText(product.getName());

        productPrice = (TextView) productRow.findViewById(R.id.txtProductPrice);
        productPrice.setText(Double.toString(product.getPrice()));

        productRestaurant = (TextView) productRow.findViewById(R.id.txtProductRestaurant);
        RestaurantParseTask restaurantParseTask = new RestaurantParseTask();
        restaurantParseTask.execute("http://api.justeatpets.com/restaurants/" + product.getRestaurantId());

        return productRow;
    }

    private class RestaurantParseTask extends AsyncTask<String, Restaurant, Void> {

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
                JSONObject object = new JSONObject(readData(params[0]));
                int id = object.getInt("id");
                String name = object.getString("name");
                String postcode = object.getString("postcode");
                publishProgress(new Restaurant(id, name, postcode));

            }
            catch(IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Restaurant... restaurants) {
            productRestaurant.setText(restaurants[0].getName());
        }
    }
}
