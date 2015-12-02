package sifat.Provider;

import java.util.ArrayList;

import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/14/2015.
 */
public class SnacksInfoProvider implements ProductInfoProvider {

    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private static int totalItemAdded, totalCost;
    private volatile static SnacksInfoProvider snacksInfoProvider;

    private SnacksInfoProvider() {
    }

    public static SnacksInfoProvider getProvider() {
        if (snacksInfoProvider == null) {
            synchronized (SnacksInfoProvider.class) {
                if (snacksInfoProvider == null)
                    //setProductInfos();
                    snacksInfoProvider = new SnacksInfoProvider();
            }
        }
        return snacksInfoProvider;
    }

    @Override
    public ArrayList<ProductInfo> getProductInfos() {
        return null;
    }

    @Override
    public ArrayList<String> getHeader() {
        return null;
    }

    @Override
    public ArrayList<MemoProductInfo> getProductMemoInfo() {
        return null;
    }

    public void setProductMemoInfo(ArrayList<MemoProductInfo> memoProductInfos) {
        this.memoProductInfos = memoProductInfos;
    }

    @Override
    public int getTotalItemAdded() {
        return totalItemAdded;
    }

    public void setTotalItemAdded(int totalItemAdded) {
        this.totalItemAdded = totalItemAdded;
    }

    @Override
    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public ArrayList<MemoProductInfo> getAddedProduct() {
        return addedProduct;
    }

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct) {
        this.addedProduct = addedProduct;
    }
}
