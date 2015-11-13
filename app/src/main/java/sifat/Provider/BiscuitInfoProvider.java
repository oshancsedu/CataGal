package sifat.Provider;

import java.util.ArrayList;
import java.util.List;

import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/11/2015.
 */
public class BiscuitInfoProvider {

    private volatile static BiscuitInfoProvider biscuitInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images =  new ArrayList<>();

    private BiscuitInfoProvider() {
    }

    public static BiscuitInfoProvider getProvider() {
        if (biscuitInfoProvider == null) {
            synchronized (BiscuitInfoProvider.class) {
                if (biscuitInfoProvider == null)
                    setProductInfos();
                biscuitInfoProvider = new BiscuitInfoProvider();
            }
        }
        return biscuitInfoProvider;
    }

    public static void setProductInfos() {
        ProductInfo productInfo;

        /*******
         * Family Pack
         *******/
        productInfo = new ProductInfo("Master Slices", "Family Pack", "1 Carton", "6 packet", "Minimum 6 months", "Carton MRP", "210 tk", "Pack MRP", "35 tk", "master_slice_banner.jpg", 1);
        product_images.add(100011);
        product_images.add(100012);
        product_images.add(100013);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Pine-Apple Cream", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", "200 tk", "Packet MRP", "25 tk", "pine_apple_cream_banner.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Digestives Biscuit", "Family Pack", "1 Carton", "8 Box", "Minimum 6 months", "Carton MRP", "480 tk", "Packet MRP", "60 tk", "banner.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Orange Cake Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", "200 tk", "Packet MRP", "40 tk", "orange_cake_banner.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Hit Cream Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", "175 tk", "Packet MRP", "35 tk", "banner.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("B-vita Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", "150 tk", "Packet MRP", "30 tk", "banner.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Lemon Biscuit", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", "176 tk", "Packet MRP", "22 tk", "banner.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Bingo Toast Biscuit", "Family Pack", "1 Carton", "6 Packet", "Minimum 6 months", "Carton MRP", "300 tk", "Packet MRP", "50 tk", "banner.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Kingdom Kheer Cookies", "Family Pack", "1 Carton", "5 Packet", "Minimum 6 months", "Carton MRP", "250 tk", "Packet MRP", "50 tk", "kingdom_kheer_cookies.jpg", 1);
        product_images.add(100021);
        product_images.add(100022);
        product_images.add(100023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();


        /*******
         * Regular Pack
         * ******/
        productInfo = new ProductInfo("Master Slices ", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", "240 tk", "Pack MRP", "10 tk", "master_slice_banner.jpg", 2);
        product_images.add(100031);
        product_images.add(100032);
        product_images.add(100033);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Pine-Apple Cream ", "Regular Pack", "1 Carton", "20 packet", "Minimum 6 months", "Carton MRP", "200 tk", "Pack MRP", "10 tk", "pine_apple_cream_banner.jpg", 2);
        product_images.add(100041);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Orange Cake Biscuit ", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", "288 tk", "Packet MRP", "12 tk", "orange_cake_banner.jpg", 2);
        product_images.add(100041);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Choco Twin", "Regular Pack", "1 Carton", "12 packet", "Minimum 6 months", "Carton MRP", "480 tk", "Packet MRP", "40 tk", "choco_twin_banner.jpg", 2);
        product_images.add(100041);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Protein Plus Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", "312 tk", "Packet MRP", "13 tk", "banner.jpg", 2);
        product_images.add(100041);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Glucose Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", "120 tk", "Packet MRP", "05 tk", "banner.jpg", 2);
        product_images.add(100041);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        /*****
         * Mini Pack
         * *****/
        productInfo = new ProductInfo("Master Slices ", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", "200 tk", "Pack MRP", "5 tk", "master_slice_banner.jpg", 3);
        product_images.add(100051);
        product_images.add(100052);
        product_images.add(100053);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Pine-Apple Cream ", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", "200 tk", "Pack MRP", "5 tk", "pine_apple_cream_banner.jpg", 3);
        product_images.add(100061);
        product_images.add(100062);
        product_images.add(100063);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Digestives Biscuit", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", "192 tk", "Pack MRP", "8 tk", "banner.jpg", 3);
        product_images.add(100061);
        product_images.add(100062);
        product_images.add(100063);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("All Time (Orange) Biscuit", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", "200 tk", "Pack MRP", "2 tk", "alltime_orange_banner.jpg", 3);
        product_images.add(100061);
        product_images.add(100062);
        product_images.add(100063);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("All Time (Milk+Coconut)", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", "200 tk", "Pack MRP", "2 tk", "banner.jpg", 3);
        product_images.add(100061);
        product_images.add(100062);
        product_images.add(100063);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        productInfo = new ProductInfo("Choco Twin", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", "400 tk", "Pack MRP", "10 tk", "choco_twin_banner.jpg", 3);
        product_images.add(100061);
        product_images.add(100062);
        product_images.add(100063);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        /*******
         * Tin
         * *******/
        productInfo = new ProductInfo("Orange Cake Biscuit ", "Tin", "1 Carton", "4 Tin", "Minimum 9 months", "Carton MRP", "800 tk", "Tin MRP", "200 tk", "orange_cake_banner.jpg", 4);
        product_images.add(100041);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

    }

    public ArrayList<ProductInfo> getProductInfos()
    {
        return productInfos;
    }
}