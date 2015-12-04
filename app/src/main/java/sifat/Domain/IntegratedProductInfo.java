package sifat.Domain;

/**
 * Created by sifat on 12/4/2015.
 */
public class IntegratedProductInfo {

    int product_id, header, mrp1, mrp2, costPerUnit;
    String name, quantity, validity, conatiner, mrp1Title, mrp2Title, size, sellingUnit, packing, images;

    public IntegratedProductInfo(int product_id, String name, String size, String container, String quantity,
                                 String validity, String mrp1Title, int mrp1, String mrp2Title,
                                 int mrp2, int header, String packing, String sellingUnit, int costPerUnit, String images) {
        this.product_id = product_id;
        this.size = size;
        this.name = name;
        this.quantity = quantity;
        this.validity = validity;
        this.mrp1 = mrp1;
        this.mrp2 = mrp2;
        this.header = header;
        this.conatiner = container;
        this.mrp1Title = mrp1Title;
        this.mrp2Title = mrp2Title;
        this.packing = packing;
        this.sellingUnit = sellingUnit;
        this.costPerUnit = costPerUnit;
        this.images = images;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getHeader() {
        return header;
    }

    public int getMrp1() {
        return mrp1;
    }

    public int getMrp2() {
        return mrp2;
    }

    public int getCostPerUnit() {
        return costPerUnit;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getValidity() {
        return validity;
    }

    public String getConatiner() {
        return conatiner;
    }

    public String getMrp1Title() {
        return mrp1Title;
    }

    public String getMrp2Title() {
        return mrp2Title;
    }

    public String getSize() {
        return size;
    }

    public String getSellingUnit() {
        return sellingUnit;
    }

    public String getPacking() {
        return packing;
    }

    public String getProduct_image() {
        return images;
    }
}
