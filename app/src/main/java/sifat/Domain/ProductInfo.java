package sifat.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sifat on 11/11/2015.
 */
public class ProductInfo implements Serializable{
    int product_id;
    String name,unit,validity,mrp1,mrp2,banner;
    List<Integer> product_images =  new ArrayList<>();

    public ProductInfo(String name,String unit,String validity,String mrp1,String mrp2,String banner)
    {
        this.name=name;
        this.unit=unit;
        this.validity=validity;
        this.mrp1=mrp1;
        this.mrp2=mrp2;
        this.banner=banner;
    }
    public ProductInfo()
    {

    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getValidity() {
        return validity;
    }

    public String getMrp1() {
        return mrp1;
    }

    public String getMrp2() {
        return mrp2;
    }

    public String getBanner() {
        return banner;
    }

    public List<Integer> getProduct_images() {
        return product_images;
    }
}
