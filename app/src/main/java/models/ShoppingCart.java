package models;

import java.util.ArrayList;

/**
 * Created by kylehodgetts on 25/10/2015.
 */
public class ShoppingCart {
    private static ArrayList<CartItem> products = new ArrayList<>();
    private static double totalPrice = 0.0;

    public static void addProduct(String product, double price, String url) {
        products.add(new CartItem(product, price, url));
        calculatePrice();
    }

    public static void removeProduct(String p) {
        products.remove(p);
        calculatePrice();
    }

    public static void calculatePrice() {
        for(CartItem c : products) {
            totalPrice += c.getPrice();
        }
    }

    public static ArrayList<CartItem> getShoppingCart() {
        return products;
    }

    public static double getTotalPrice() {
        return totalPrice;
    }
}
