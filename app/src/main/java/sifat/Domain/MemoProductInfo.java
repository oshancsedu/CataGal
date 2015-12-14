package sifat.Domain;

import java.util.StringTokenizer;

import static sifat.Utilities.CommonUtilities.getTwoDecimal;

/**
 * Created by sifat on 11/18/2015.
 */
public class MemoProductInfo {

    String productName, productSize, sellingUnit, packing, comment, calculatedCost;
    int carton, packet, costPerUnit, header;
    double cost, costPerPack;
    boolean isAdded;

    public MemoProductInfo() {
    }

    public MemoProductInfo(String productName, String productSize, String packing, String sellingUnit, int costPerUnit, int header) {
        this.productName = productName;
        this.productSize = productSize;
        this.packing = packing;
        this.sellingUnit = sellingUnit;
        this.costPerUnit = costPerUnit;
        this.costPerPack = setCostPerPack(packing, costPerUnit);
        this.header = header;
        this.cost = 0.0;
        this.carton = 0;
        this.packet = 0;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getCarton() {
        return carton;
    }

    public void setCarton(int carton) {
        this.carton = carton;
    }

    public int getPacket() {
        return packet;
    }

    public void setPacket(int packet) {
        this.packet = packet;
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

    public double getCostPerPack() {
        return costPerPack;
    }

    public double setCostPerPack(String packing, int costPerUnit) {
        int pack;
        double cost;
        StringTokenizer tokenizer = new StringTokenizer(packing, " ");
        pack = Integer.parseInt(tokenizer.nextToken());
        cost = (costPerUnit * 1.0) / (pack * 1.0);
        return getTwoDecimal(cost);
    }
}
