package models;

/**
 * Created by faresalaboud on 24/10/2015.
 */
public class Product {
    private int id;
    private String name;
    private Double price;
    private Pet pet;
    private Restaurant restaurant;
    private String photoURL;

    public Product(int id, String name, Double price, String pet, Restaurant restaurant, String photoURL) {
        this.id = id;
        this.name = name;
        this.price = price;
        if (pet.toLowerCase().equals("cat")) {
            this.pet = Pet.CAT;
        } else if (pet.toLowerCase().equals("dog")) {
            this.pet = Pet.DOG;
        } else {
            this.pet = Pet.FISH;
        }
        this.restaurant = restaurant;
        this.photoURL = photoURL;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Pet getPet() {
        return pet;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public String getPhotoURL() {
        return photoURL;
    }
}
