package sifat.Domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sifat on 11/11/2015.
 */
public class ProductInfo implements Serializable{
    int product_id,header;
    String name,unit,validity,mrp1,mrp2,banner,unitTitle,mrp1Title,mrp2Title,size;
    List<Integer> product_images =  new ArrayList<>();

    public ProductInfo(String name,String size,String unitTitle,String unit,String validity,String mrp1Title,String mrp1,String mrp2Title,String mrp2,String banner,int header)
    {
        this.size=size;
        this.name=name;
        this.unit=unit;
        this.validity=validity;
        this.mrp1=mrp1;
        this.mrp2=mrp2;
        this.banner=banner;
        this.header=header;
        this.unitTitle=unitTitle;
        this.mrp1Title=mrp1Title;
        this.mrp2Title=mrp2Title;
    }
    public ProductInfo()
    {

    }

    public String getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public String getUnitTitle() {
        return unitTitle;
    }

    public String getMrp1Title() {
        return mrp1Title;
    }

    public String getMrp2Title() {
        return mrp2Title;
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

    public void setProduct_images(List<Integer> product_images) {
        this.product_images = product_images;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getHeader() {
        return header;
    }
}
