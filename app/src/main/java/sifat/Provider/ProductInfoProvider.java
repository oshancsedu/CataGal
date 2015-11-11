package sifat.Provider;

import java.util.ArrayList;

import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/11/2015.
 */
public class ProductInfoProvider {

    private volatile static ProductInfoProvider productInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();

    private ProductInfoProvider() {
    }

    public static ProductInfoProvider getProvider() {
        if (productInfoProvider == null) {
            synchronized (ProductInfoProvider.class) {
                if (productInfoProvider == null)
                    setProductInfos();
                    productInfoProvider = new ProductInfoProvider();
            }
        }
        return productInfoProvider;
    }

    public ArrayList<ProductInfo> getProductInfos()
    {
        return productInfos;
    }

    public static void setProductInfos()
    {
        ProductInfo productInfo = new ProductInfo("Master Slices (Family)","6 packet","Minimum 6 months","210 tk","35 tk","banner");
        productInfos.add(productInfo);
        productInfo = new ProductInfo("Master Slices (Regular)","24 packet","Minimum 6 months","240 tk","10 tk","banner");
        productInfos.add(productInfo);
        productInfo = new ProductInfo("Master Slices (Mini)","40 packet","Minimum 6 months","200 tk","5 tk","banner");
        productInfos.add(productInfo);
        productInfo = new ProductInfo("Pine-Apple Cream (Family)","8 packet","Minimum 6 months","200 tk","25 tk","banner");
        productInfos.add(productInfo);
        productInfo = new ProductInfo("Pine-Apple Cream (Regular)","20 packet","Minimum 6 months","200 tk","10 tk","banner");
        productInfos.add(productInfo);
        productInfo = new ProductInfo("Pine-Apple Cream (Mini)","40 packet","Minimum 6 months","200 tk","5 tk","banner");
        productInfos.add(productInfo);
    }

}
