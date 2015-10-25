package models;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class CartItem {
    private String name;
    private double price;
    private String url;

    public CartItem(String name, double price, String url){
        this.name = name;
        this.price = price;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }
}
