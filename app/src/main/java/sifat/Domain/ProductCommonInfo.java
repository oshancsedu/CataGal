package sifat.Domain;

import java.io.Serializable;

/**
 * Created by sifat on 12/4/2015.
 */
public class ProductCommonInfo implements Serializable {

    String header, ingredient, banner;
    int productID;

    public ProductCommonInfo(int productID, String header, String headerImage, String ingredient) {
        this.productID = productID;
        this.header = header;
        this.ingredient = ingredient;
        this.banner = headerImage;
    }

    public String getHeader() {
        return header;
    }

    public String getIngredient() {
        return ingredient;
    }

    public String getBanner() {
        return banner;
    }

    public int getProductID() {
        return productID;
    }
}
