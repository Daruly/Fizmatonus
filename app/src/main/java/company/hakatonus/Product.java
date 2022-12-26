package company.hakatonus;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Product implements Serializable {
    private String price;
    private String title;
    private int img;

    public int getImg() {
        return img;
    }

    public Product(String title, String price, int img) {
        this.title = title;
        this.price = price;
        this.img = img;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return title;
    }

    public void setName(String name) {
        this.title = name;
    }


    public void setImg(int img) {
        this.img = img;
    }
}
