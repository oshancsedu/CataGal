package sifat.Provider;

import java.util.ArrayList;

import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductCommonInfo;
import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/13/2015.
 */
public interface ProductInfoProvider {

    public ArrayList<ProductInfo> getProductInfos();

    public ArrayList<ProductCommonInfo> getCommonInfo();

    public ArrayList<MemoProductInfo> getProductMemoInfo();

    public void setProductMemoInfo(ArrayList<MemoProductInfo> memoProductInfos);

    public double getTotalCost();

    public void setTotalCost(double totalCost);

    public int getTotalItemAdded();

    public void setTotalItemAdded(int totalItemAdded);

    public ArrayList<MemoProductInfo> getAddedProduct();

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct);
}
