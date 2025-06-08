package android.bignerdranch.cofee;

import java.io.Serializable;

public class ItemsModel implements Serializable {
    private String title;
    private String description;
    public String picUrl;
    private double price;
    private double rating;
    public int numberInCart;
    private String extra;





    public ItemsModel(String title, String description, String picUrl, double price, double rating, int numberInCart, String extra) {
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.price = price;
        this.rating = rating;
        this.numberInCart = numberInCart;
        this.extra = extra;
    }

    public ItemsModel() {}

    public String getTitle() {return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

}
