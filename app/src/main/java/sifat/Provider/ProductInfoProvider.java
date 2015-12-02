package sifat.Provider;

import java.util.ArrayList;

import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/13/2015.
 */
public interface ProductInfoProvider {

    public ArrayList<ProductInfo> getProductInfos();

    public ArrayList<String> getHeader();

    public ArrayList<MemoProductInfo> getProductMemoInfo();

    public void setProductMemoInfo(ArrayList<MemoProductInfo> memoProductInfos);

    public int getTotalCost();

    public void setTotalCost(int totalCost);

    public int getTotalItemAdded();

    public void setTotalItemAdded(int totalItemAdded);

    public ArrayList<MemoProductInfo> getAddedProduct();

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct);
}
