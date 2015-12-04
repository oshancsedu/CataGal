package sifat.Provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import sifat.Database.DbOperator;
import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductCommonInfo;
import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/11/2015.
 */
public class BiscuitInfoProvider implements ProductInfoProvider {

    private volatile static BiscuitInfoProvider biscuitInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images =  new ArrayList<>();
    private static ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private static int totalItemAdded, totalCost;
    private DbOperator dbOperator;
    private SQLiteDatabase sqlDatabase;
    private Context context;

    private BiscuitInfoProvider(Context context) {
        this.context = context;
        dbOperator = DbOperator.getDbOperator(this.context);
        dbOperator.open();
        sqlDatabase = dbOperator.getDatabase();
        setProductInfos();
        setCommonInfo();
        dbOperator.close();
    }

    public static BiscuitInfoProvider getProvider(Context context) {
        if (biscuitInfoProvider == null) {
            synchronized (BiscuitInfoProvider.class) {
                if (biscuitInfoProvider == null)
                    biscuitInfoProvider = new BiscuitInfoProvider(context);
            }
        }
        return biscuitInfoProvider;
    }

    private static void setCommonInfo() {

        ProductCommonInfo productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);
        productCommonInfo = new ProductCommonInfo("Master Slices", "master_slice_banner.jpg", "ingredients");
        commonInfos.add(productCommonInfo);

        /*headers.add("Master Slices");
        headers.add("Pine-Apple Cream");
        headers.add("Digestives Biscuit");
        headers.add("Orange Cake Biscuit");
        headers.add("Hit Cream Biscuit");
        headers.add("B-vita Biscuit");
        headers.add("Lemon Biscuit");
        headers.add("Bingo Toast Biscuit");
        headers.add("Kingdom Kheer Cookies");
        headers.add("Choco Twin");
        headers.add("Protein Plus Biscuit");
        headers.add("Glucose Biscuit");
        headers.add("All Time (Orange) Biscuit");
        headers.add("All Time (Milk+Coconut)");*/
    }

    public static void setProductInfos() {
        ProductInfo productInfo;
        MemoProductInfo memoProductInfo;

        /*******
         * Master Slices
         *******/
        productInfo = new ProductInfo("Bengal Master Slice", "Family Pack", "1 Carton", "6 packet", "Minimum 6 months", "Carton MRP", 210, "Packet MRP", 35,
                "master_slice_banner.jpg", 1);
        /*productInfo.setUnit("Carton");
        productInfo.setPacking("6 pack/carton");
        productInfo.setPricePerUnit("210 tk");*/
        product_images.add(10231);
        product_images.add(100012);
        product_images.add(10213);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Master Slice", "Family Pack", "6 pack/carton", "Carton", 210, 35, 1);
        memoProductInfos.add(memoProductInfo);


        productInfo = new ProductInfo("Bengal Master Slices ", "Regular Pack", "1 Carton", "24 Packet", "Minimum 6 months", "Carton MRP", 240, "Packet MRP", 10,
                "master_slice_banner.jpg", 1);
        /*productInfo.setUnit("Carton");
        productInfo.setPacking("24 pack/carton");
        productInfo.setPricePerUnit("240 tk");*/
        product_images.add(10221);
        product_images.add(100022);
        product_images.add(10213);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Master Slice", "Regular Pack", "24 pack/carton", "Carton", 240, 10, 1);
        memoProductInfos.add(memoProductInfo);


        productInfo = new ProductInfo("Bengal Master Slices ", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 5,
                "master_slice_banner.jpg", 1);
        /*productInfo.setUnit("Carton");
        productInfo.setPacking("40 pack/carton");
        productInfo.setPricePerUnit("200 tk");*/
        product_images.add(10231);
        product_images.add(100032);
        product_images.add(10213);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Master Slice", "Mini Pack", "40 pack/carton", "Carton", 200, 5, 1);
        memoProductInfos.add(memoProductInfo);


        /********
         * Pine-Apple Cream
         * ******/

        productInfo = new ProductInfo("Bengal Pine-Apple Cream", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 25,
                "pine_apple_cream_banner.jpg", 2);
        /*productInfo.setUnit("Carton");
        productInfo.setPacking("8 pack/carton");
        productInfo.setPricePerUnit("200 tk");*/
        product_images.add(10131);
        product_images.add(100042);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Pine-Apple Cream", "Family Pack", "8 pack/carton", "Carton", 200, 25, 2);
        memoProductInfos.add(memoProductInfo);


        productInfo = new ProductInfo("Bengal Pine-Apple Cream", "Regular Pack", "1 Carton", "20 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 10,
                "pine_apple_cream_banner.jpg", 2);
        productInfo.setUnit("Carton");
        productInfo.setPacking("6 pack/carton");
        productInfo.setPricePerUnit("210 tk");
        product_images.add(10121);
        product_images.add(100052);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Pine-Apple Cream", "Regular Pack", "20 pack/carton", "Carton", 200, 10, 2);
        memoProductInfos.add(memoProductInfo);


        productInfo = new ProductInfo("Pine-Apple Cream ", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 5,
                "pine_apple_cream_banner.jpg", 2);
        productInfo.setUnit("Carton");
        productInfo.setPacking("6 pack/carton");
        productInfo.setPricePerUnit("210 tk");
        product_images.add(10131);
        product_images.add(100062);
        product_images.add(100043);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Pine-Apple Cream", "Mini Pack", "40 pack/carton", "Carton", 200, 5, 2);
        memoProductInfos.add(memoProductInfo);

        /********
         * Digestives Biscuit
         * ******/

        productInfo = new ProductInfo("Nutribake Digestives Biscuit", "Family Pack", "1 Carton", "8 Box", "Minimum 6 months", "Carton MRP", 480, "Packet MRP", 60,
                "digestives_banner.jpg", 3);
        product_images.add(100132);
        product_images.add(100132);
        product_images.add(100133);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Nutribake Digestives Biscuit", "Family Pack", "8 box/carton", "Carton", 480, 60, 3);
        memoProductInfos.add(memoProductInfo);


        productInfo = new ProductInfo("Digestives Biscuit", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 192, "Packet MRP", 8,
                "digestives_banner.jpg", 3);
        product_images.add(100142);
        product_images.add(100142);
        product_images.add(100133);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Nutribake Digestives Biscuit", "Mini Pack", "40 pack/carton", "Carton", 192, 8, 3);
        memoProductInfos.add(memoProductInfo);


        /********
         * Orange Cake Biscuit
         * *****/
        productInfo = new ProductInfo("Bengal Orange Cake Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 40,
                "orange_cake_banner.jpg", 4);
        product_images.add(100072);
        product_images.add(100072);
        product_images.add(10613);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Orange Cake Biscuit", "Family Pack", "5 pack/carton", "Carton", 200, 40, 4);
        memoProductInfos.add(memoProductInfo);

        productInfo = new ProductInfo("Bengal Orange Cake Biscuit ", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 288, "Packet MRP", 12,
                "orange_cake_banner.jpg", 4);
        product_images.add(100092);
        product_images.add(100092);
        product_images.add(10613);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Orange Cake Biscuit", "Regular Pack", "24 pack/carton", "Carton", 288, 12, 4);
        memoProductInfos.add(memoProductInfo);


        productInfo = new ProductInfo("Orange Cake Biscuit ", "Tin", "1 Carton", "4 Tin", "Minimum 9 months", "Carton MRP", 800, "Tin MRP", 200,
                "orange_cake_banner.jpg", 4);
        product_images.add(100082);
        product_images.add(100082);
        product_images.add(10613);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Orange Cake Biscuit", "Tin", "1 tin", "Tin", 200, 200, 4);
        memoProductInfos.add(memoProductInfo);


        /*******
         * Hit Cream Biscuit
         * ******/
        productInfo = new ProductInfo("Bengal Hit Cream Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 175, "Packet MRP", 35,
                "hit_cream_banner.jpg", 5);
        product_images.add(1081);
        product_images.add(10812);
        product_images.add(10812);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Hit Cream Biscuit", "Family Pack", "5 pack/carton", "Carton", 175, 35, 5);
        memoProductInfos.add(memoProductInfo);


        /*******
         * B-vita Biscuit
         * ******/
        productInfo = new ProductInfo("Bengal B-vita Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 150, "Packet MRP", 30,
                "b_vita_banner.jpg", 6);
        product_images.add(1091);
        product_images.add(10912);
        product_images.add(10913);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal B-vita Biscuit", "Family Pack", "5 pack/carton", "Carton", 150, 30, 6);
        memoProductInfos.add(memoProductInfo);

        /*******
         * Lemon Biscuit
         * *******/
        productInfo = new ProductInfo("Bengal Lemon Biscuit", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", 176, "Packet MRP", 22,
                "banner.jpg", 7);
        product_images.add(11211);
        product_images.add(11212);
        product_images.add(11212);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bengal Lemon Biscuit", "Family Pack", "8 pack/carton", "Carton", 176, 22, 7);
        memoProductInfos.add(memoProductInfo);


        /******
         * Bingo Toast Biscuit
         * *****/
        productInfo = new ProductInfo("Bingo Toast Biscuit", "Family Pack", "1 Carton", "6 Packet", "Minimum 6 months", "Carton MRP", 300, "Packet MRP", 50,
                "banner.jpg", 8);
        product_images.add(100152);
        product_images.add(100152);
        product_images.add(11313);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Bingo Toast Biscuit", "Family Pack", "6 pack/carton", "Carton", 300, 50, 8);
        memoProductInfos.add(memoProductInfo);


        /*******
         * Kingdom Kheer Cookies
         * ******/
        productInfo = new ProductInfo("Kingdom Kheer Cookies", "Family Pack", "1 Carton", "5 Packet", "Minimum 6 months", "Carton MRP", 250, "Packet MRP", 50,
                "kingdom_kheer_cookies.jpg", 9);
        product_images.add(100122);
        product_images.add(100122);
        product_images.add(100123);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        memoProductInfo = new MemoProductInfo("Kingdom Kheer Cookies", "Family Pack", "5 pack/carton", "Carton", 250, 50, 9);
        memoProductInfos.add(memoProductInfo);


        /*******
         * Choco Twin
         * ******/
        productInfo = new ProductInfo("Choco Twin", "Regular Pack", "1 Carton", "12 packet", "Minimum 6 months", "Carton MRP", 480, "Packet MRP", 40,
                "choco_twin_banner.jpg", 10);
        product_images.add(10731);
        product_images.add(100102);
        product_images.add(100103);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();


        productInfo = new ProductInfo("Choco Twin", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 400, "Packet MRP", 10,
                "choco_twin_banner.jpg", 10);
        product_images.add(10731);
        product_images.add(100112);
        product_images.add(100103);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        /*******
         * Protein Plus Biscuit
         * *******/
        productInfo = new ProductInfo("Protein Plus Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 312, "Packet MRP", 13,
                "banner.jpg", 11);
        product_images.add(11021);
        product_images.add(11022);
        product_images.add(11023);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        /*********
         * Glucose Biscuit
         * ********/
        productInfo = new ProductInfo("Bengal Glucose Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 120, "Packet MRP", 5,
                "banner.jpg", 12);
        product_images.add(11121);
        product_images.add(11122);
        product_images.add(11122);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

        /*****
         * All Time (Orange) Biscuit
         * *****/

        productInfo = new ProductInfo("All Time (Orange) Biscuit", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 2,
                "alltime_orange_banner.jpg", 13);
        product_images.add(1041);
        product_images.add(10432);
        product_images.add(10433);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();


        /*******
         * All Time (Milk+Coconut)
         * *******/
        productInfo = new ProductInfo("All Time (Milk+Coconut)", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 2,
                "alltime_coco_milk_banner.jpg", 14);
        product_images.add(10532);
        product_images.add(10532);
        product_images.add(10433);
        productInfo.setProduct_images(product_images);
        productInfos.add(productInfo);
        product_images = new ArrayList<>();

    }

    @Override
    public ArrayList<MemoProductInfo> getProductMemoInfo() {
        return memoProductInfos;
    }

    public void setProductMemoInfo(ArrayList<MemoProductInfo> memoProductInfos) {
        this.memoProductInfos = memoProductInfos;
    }

    public int getTotalItemAdded() {
        return totalItemAdded;
    }

    public void setTotalItemAdded(int totalItemAdded) {
        this.totalItemAdded = totalItemAdded;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public ArrayList<MemoProductInfo> getAddedProduct() {
        return addedProduct;
    }

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct) {
        this.addedProduct = addedProduct;
    }

    public ArrayList<ProductCommonInfo> getCommonInfo() {
        return commonInfos;
    }

    @Override
    public ArrayList<ProductInfo> getProductInfos()
    {
        return productInfos;
    }
}