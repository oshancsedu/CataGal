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
}
