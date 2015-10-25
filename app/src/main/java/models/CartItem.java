package models;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class CartItem {
    private String name;
    private double price;

    public CartItem(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
