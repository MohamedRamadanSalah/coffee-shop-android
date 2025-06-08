package android.bignerdranch.cofee;

public class Category {
    private   String extra;
    private String description ;
    private  int numberInCart;
    private String name;
    private String imageUrl;
    double rating;
    double prices;
    private String title;

    public Category() {

    }

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }
    public Category(String title , String Pict , float rate , double price)
    {
        this.name = title;
        this.imageUrl = Pict;
        this.rating = rate;
        this.prices = price;
    }
    public Category(String title, String description, String picUrl, double price, double rating, int numberInCart, String extra) {
        this.name = title;
        this.description = description;
        this.imageUrl = picUrl;
        this.prices = price;
        this.rating = rating;
        this.numberInCart = numberInCart;
        this.extra = extra;
    }


    public String getName() {
        return name;
    }
    public double getPrice() {
        return prices;
    }
public double getRating()
{
    return rating;
}

    public String getImageUrl() {
        return imageUrl;
    }
    public String getTitle() {
        return name;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}

