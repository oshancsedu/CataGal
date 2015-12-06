package sifat.Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sifat.Database.DbOperator;
import sifat.Domain.IntegratedProductInfo;
import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductCommonInfo;
import sifat.Domain.ProductInfo;

import static sifat.Utilities.CommonUtilities.COL_PRODUCT_ID;
import static sifat.Utilities.CommonUtilities.LOG_TAG_DATABASE;
import static sifat.Utilities.CommonUtilities.TABLE_PRODUCT_COMMON_INFO;
import static sifat.Utilities.CommonUtilities.TABLE_PRODUCT_DETAIL_INFO;

/**
 * Created by sifat on 11/11/2015.
 */
public class BiscuitInfoProvider extends BaseProvider {

    private volatile static BiscuitInfoProvider biscuitInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images =  new ArrayList<>();
    private static ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private static ArrayList<IntegratedProductInfo> integratedProductInfos = new ArrayList<>();
    private static int totalItemAdded, totalCost;
    private static DbOperator dbOperator;
    private static SQLiteDatabase sqlDatabase;
    private static Context context;

    private BiscuitInfoProvider(Context context) {
        this.context = context;
        dbOperator = DbOperator.getDbOperator(this.context);
        dbOperator.open();
        sqlDatabase = dbOperator.getDatabase();
        fetchProductInfos();
        fetchCommonInfo();
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


    private static void fetchCommonInfo() {

        Cursor c;
        String query = "Select * from " + TABLE_PRODUCT_COMMON_INFO + " where " + COL_PRODUCT_ID + " > 100 and " + COL_PRODUCT_ID + " < 200";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            Log.i(LOG_TAG_DATABASE, "count not 0");
            commonInfos = setCommonInfo(c);
        } else {
            Log.i(LOG_TAG_DATABASE, "count 0");

            ProductCommonInfo productCommonInfo;
            productCommonInfo = new ProductCommonInfo(101, "Bengal Pine-Apple Cream", "pine_apple_cream_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(102, "Bengal Master Slices", "master_slice_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(103, "NutriBake Digestive Biscuit", "digestives_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(106, "Bengal Orange Cake Biscuit", "orange_cake_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(108, "Bengal Hit Cream Biscuit", "hit_cream_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(109, "Bengal B-vita Biscuit", "b_vita_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(112, "Bengal Lemon Biscuit", "master_slice_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(113, "Bengal Bingo Toast Biscuit", "master_slice_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(114, "Kingdom Kheer Cookies", "kingdom_kheer_cookies.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(107, "Bengal Choco Twin", "choco_twin_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(110, "Bengal Protein Plus", "master_slice_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(111, "Bengal Glucose Biscuit", "master_slice_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(104, "Bengal All Time (Orange)", "alltime_orange_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(105, "Bengal All Time (Milk+Coconut)", "alltime_coco_milk_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            dbOperator.updateProductCommonInfo(commonInfos);
        }
    }

    private static void fetchProductInfos() {

        ProductInfo productInfo;
        MemoProductInfo memoProductInfo;
        IntegratedProductInfo integratedProductInfo;

        Cursor c;
        String query = "Select * from " + TABLE_PRODUCT_DETAIL_INFO + " where " + COL_PRODUCT_ID + " > 1000 and " + COL_PRODUCT_ID + " < 2000";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            integratedProductInfos = setDetailInfo(c);
        } else {

            /********
             * Pine-Apple Cream
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1011, "Bengal Pine-Apple Cream", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 25,
                    1, "8 pack/carton", "Carton", 200, "10121,10112,10113");
            integratedProductInfos.add(integratedProductInfo);


            integratedProductInfo = new IntegratedProductInfo(1012, "Bengal Pine-Apple Cream", "Regular Pack", "1 Carton", "20 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 10,
                    1, "20 pack/carton", "Carton", 200, "10121,10122,10113");
            integratedProductInfos.add(integratedProductInfo);


            integratedProductInfo = new IntegratedProductInfo(1013, "Pine-Apple Cream ", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 5,
                    1, "40 pack/carton", "Carton", 200, "10131,10132,10113");
            integratedProductInfos.add(integratedProductInfo);

            /*******
             * Master Slices
             *******/
            integratedProductInfo = new IntegratedProductInfo(1021, "Bengal Master Slice", "Family Pack", "1 Carton", "6 packet", "Minimum 6 months", "Carton MRP", 210, "Packet MRP", 35,
                    2, "6 pack/carton", "Carton", 210, "10221,10212,10213");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1022, "Bengal Master Slices ", "Regular Pack", "1 Carton", "24 Packet", "Minimum 6 months", "Carton MRP", 240, "Packet MRP", 10,
                    2, "24 pack/carton", "Carton", 240, "10221,10222,10213");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1023, "Bengal Master Slices ", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 5,
                    2, "40 pack/carton", "Carton", 200, "10231,10232,10213");
            integratedProductInfos.add(integratedProductInfo);


            /********
             * Digestives Biscuit
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1031, "Nutribake Digestives Biscuit", "Family Pack", "1 Carton", "8 Box", "Minimum 6 months", "Carton MRP", 480, "Packet MRP", 60,
                    3, "8 box/carton", "Carton", 480, "10312,10312,10313");
            integratedProductInfos.add(integratedProductInfo);


            integratedProductInfo = new IntegratedProductInfo(1033, "Digestives Biscuit", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 192, "Packet MRP", 8,
                    3, "40 pack/carton", "Carton", 192, "10332,10332,10313");
            integratedProductInfos.add(integratedProductInfo);


            /********
             * Orange Cake Biscuit
             * *****/
            integratedProductInfo = new IntegratedProductInfo(1061, "Bengal Orange Cake Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 40,
                    4, "5 pack/carton", "Carton", 200, "10612,10612,10613");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1062, "Bengal Orange Cake Biscuit ", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 288, "Packet MRP", 12,
                    4, "24 pack/carton", "Carton", 288, "10622,10622,10613");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1063, "Orange Cake Biscuit ", "Tin", "1 Carton", "4 Tin", "Minimum 9 months", "Carton MRP", 800, "Tin MRP", 200,
                    4, "1 tin", "Tin", 200, "10632,10632,10613");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Hit Cream Biscuit
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1081, "Bengal Hit Cream Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 175, "Packet MRP", 35,
                    5, "5 pack/carton", "Carton", 175, "1081,10812,10812");
            integratedProductInfos.add(integratedProductInfo);

            /*******
             * B-vita Biscuit
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1091, "Bengal B-vita Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 150, "Packet MRP", 30,
                    6, "5 pack/carton", "Carton", 150, "1091,10912,10913");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Lemon Biscuit
             * *******/
            integratedProductInfo = new IntegratedProductInfo(1121, "Bengal Lemon Biscuit", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", 176, "Packet MRP", 22,
                    7, "8 pack/carton", "Carton", 176, "11211,11212,11212");
            integratedProductInfos.add(integratedProductInfo);


            /******
             * Bingo Toast Biscuit
             * *****/
            integratedProductInfo = new IntegratedProductInfo(1131, "Bingo Toast Biscuit", "Family Pack", "1 Carton", "6 Packet", "Minimum 6 months", "Carton MRP", 300, "Packet MRP", 50,
                    8, "6 pack/carton", "Carton", 300, "11312,11312,11313");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Kingdom Kheer Cookies
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1141, "Kingdom Kheer Cookies", "Family Pack", "1 Carton", "5 Packet", "Minimum 6 months", "Carton MRP", 250, "Packet MRP", 50,
                    9, "5 pack/carton", "Carton", 250, "11412,11412,11413");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Choco Twin
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1072, "Choco Twin", "Regular Pack", "1 Carton", "12 packet", "Minimum 6 months", "Carton MRP", 480, "Packet MRP", 40,
                    10, "12 pack/carton", "Carton", 480, "10731,10722,10723");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1073, "Choco Twin", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 400, "Packet MRP", 10,
                    10, "40 pack/carton", "Carton", 400, "10731,10732,10723");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Protein Plus Biscuit
             * *******/
            integratedProductInfo = new IntegratedProductInfo(1102, "Protein Plus Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 312, "Packet MRP", 13,
                    11, "24 pack/carton", "Carton", 312, "11021,11022,11023");
            integratedProductInfos.add(integratedProductInfo);

            /*********
             * Glucose Biscuit
             * ********/
            integratedProductInfo = new IntegratedProductInfo(1112, "Bengal Glucose Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 120, "Packet MRP", 5,
                    12, "24 pack/carton", "Carton", 120, "11121,11122,11123");
            integratedProductInfos.add(integratedProductInfo);

            /*****
             * All Time (Orange) Biscuit
             * *****/
            integratedProductInfo = new IntegratedProductInfo(1043, "All Time (Orange) Biscuit", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 2,
                    13, "100 pack/carton", "Carton", 200, "1041,10432,10433");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * All Time (Milk+Coconut)
             * *******/
            integratedProductInfo = new IntegratedProductInfo(1053, "All Time (Milk+Coconut)", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 2,
                    14, "100 pack/carton", "Carton", 200, "10532,10532,10433");
            integratedProductInfos.add(integratedProductInfo);

            dbOperator.updateProductDetailInfo(integratedProductInfos);
        }

        int size = integratedProductInfos.size();

        for (int i = 0; i < size; i++) {
            integratedProductInfo = integratedProductInfos.get(i);
            productInfo = new ProductInfo(integratedProductInfo.getName(), integratedProductInfo.getSize(), integratedProductInfo.getConatiner(), integratedProductInfo.getQuantity(),
                    integratedProductInfo.getValidity(), integratedProductInfo.getMrp1Title(), integratedProductInfo.getMrp1(), integratedProductInfo.getMrp2Title(),
                    integratedProductInfo.getMrp2(), integratedProductInfo.getHeader());
            StringTokenizer tokenizer = new StringTokenizer(integratedProductInfo.getProduct_image(), ",");
            while (tokenizer.hasMoreTokens()) {
                product_images.add(Integer.parseInt(tokenizer.nextToken()));
            }
            productInfo.setProduct_images(product_images);
            productInfos.add(productInfo);
            product_images = new ArrayList<>();

            memoProductInfo = new MemoProductInfo(integratedProductInfo.getName(), integratedProductInfo.getSize(),
                    integratedProductInfo.getPacking(), integratedProductInfo.getSellingUnit(),
                    integratedProductInfo.getCostPerUnit(), integratedProductInfo.getHeader());
            memoProductInfos.add(memoProductInfo);
        }
    }


    public static void setProductInfos() {
        ProductInfo productInfo;
        MemoProductInfo memoProductInfo;
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