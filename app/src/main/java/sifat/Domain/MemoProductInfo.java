package sifat.Domain;

/**
 * Created by sifat on 11/18/2015.
 */
public class MemoProductInfo {

    String productName, productSize, sellingUnit, packing, comment, calculatedCost;
    int cost, quantity, costPerUnit, costPerPack, header;
    boolean isAdded;

    public MemoProductInfo() {
    }

    public MemoProductInfo(String productName, String productSize, String packing, String sellingUnit, int costPerUnit, int header) {
        this.productName = productName;
        this.productSize = productSize;
        this.packing = packing;
        this.sellingUnit = sellingUnit;
        this.costPerUnit = costPerUnit;
        this.costPerPack = costPerPack;
        this.header = header;
        this.cost = 0;
        this.quantity = 0;
        this.comment = "";
        this.isAdded = false;
    }

    public boolean isAdded() {
        return isAdded;
    }

    public void setIsAdded(boolean isAdded) {
        this.isAdded = isAdded;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getSellingUnit() {
        return sellingUnit;
    }

    public void setSellingUnit(String sellingUnit) {
        sellingUnit = sellingUnit;
    }

    public String getPacking() {
        return packing;
    }

    public void setPacking(String packing) {
        this.packing = packing;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCostPerUnit() {
        return costPerUnit;
    }

    public void setCostPerUnit(int costPerUnit) {
        this.costPerUnit = costPerUnit;
    }

    public int getHeader() {
        return header;
    }

    public void setCostPerPack(int costPerPack) {
        this.costPerPack = costPerPack;
    }
}
