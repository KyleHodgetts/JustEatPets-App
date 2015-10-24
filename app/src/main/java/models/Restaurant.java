package models;

import java.util.ArrayList;

/**
 * Created by faresalaboud on 24/10/2015.
 */
public class Restaurant {

    private int id;
    private String name;
    private String postcode;
    private ArrayList<Product> products;

    public Restaurant(int id, String name, String postcode) {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
        products = new ArrayList<Product>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPostcode() {
        return postcode;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
