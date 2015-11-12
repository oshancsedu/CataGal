package sifat.Provider;

import java.util.ArrayList;
import java.util.List;

import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/11/2015.
 */
public class ProductInfoProvider {

    private volatile static ProductInfoProvider productInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images =  new ArrayList<>();

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
        ProductInfo productInfo;

        /*******
         * Family Pack
         *******/
        productInfo = new ProductInfo("Master Slices ","Family Pack","1 Carton","6 packet","Minimum 6 months","Carton MRP","210 tk","Pack MRP","35 tk","banner.jpg",1);
        product_images.add(100011);
        product_images.add(100012);
        product_images.add(100013);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images=  new ArrayList<>();

        productInfo = new ProductInfo("Pine-Apple Cream","Family Pack","1 Carton","8 packet","Minimum 6 months","Carton MRP","200 tk","Pack MRP","25 tk","banner.jpg",1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images=  new ArrayList<>();


        /*******
         * Regular Pack
         * ******/
        productInfo = new ProductInfo("Master Slices ","Regular Pack","1 Carton","24 packet","Minimum 6 months","Carton MRP","240 tk","Pack MRP","10 tk","banner.jpg",2);
        product_images.add(100031);
        product_images.add(100032);
        product_images.add(100033);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images=  new ArrayList<>();

        productInfo = new ProductInfo("Pine-Apple Cream ","Regular Pack","1 Carton","20 packet","Minimum 6 months","Carton MRP","200 tk","Pack MRP","10 tk","banner.jpg",2);
        product_images.add(100041);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images=  new ArrayList<>();

        /*****
         * Mini Pack
         * *****/
        productInfo = new ProductInfo("Master Slices ","Mini Pack","1 Carton","40 packet","Minimum 6 months","Carton MRP","200 tk","Pack MRP","5 tk","banner.jpg",3);
        product_images.add(100051);
        product_images.add(100052);
        product_images.add(100053);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images =  new ArrayList<>();

        productInfo = new ProductInfo("Pine-Apple Cream ","Mini Pack","1 Carton","40 packet","Minimum 6 months","Carton MRP","200 tk","Pack MRP","5 tk","banner.jpg",3);
        product_images.add(100061);
        product_images.add(100062);
        product_images.add(100063);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images =  new ArrayList<>();
    }
}