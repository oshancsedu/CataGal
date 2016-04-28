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

    public static int totalBiscuitAdded;
    public static double totalBiscuitCost;
    private volatile static BiscuitInfoProvider biscuitInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images =  new ArrayList<>();
    private static ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedBiscuit = new ArrayList<>();
    private static ArrayList<IntegratedProductInfo> integratedProductInfos = new ArrayList<>();
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
        String query = "Select * from " + TABLE_PRODUCT_COMMON_INFO + " where " + COL_PRODUCT_ID + " BETWEEN  100 AND 200";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            Log.i(LOG_TAG_DATABASE, "count not 0");
            commonInfos = setCommonInfo(c);
        } else {
            Log.i(LOG_TAG_DATABASE, "count 0");

            ProductCommonInfo productCommonInfo;
            productCommonInfo = new ProductCommonInfo(101, "Bengal Pine-Apple Cream", "pine_apple_cream_banner", "Wheat flour, sugar, glucose, milk powder, dextrose, egg, vegetable shortening, salt, water, food grade flavour etc.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(102, "Bengal Master Slice", "master_slice_banner", "Wheat flour, sugar, soda, vanilin powder, liquid glucose, amonia, salt, cream of tarter, dalda, coconut powder, citric acid, milk and milk products, water, egg, food grade flavor etc.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(103, "NutriBake Digestive Biscuit", "digestives_banner", "Wheat flour, bran, peanut, sugar, vegetable oil, liquid glucose, milk and milk products, egg, salt, vanilin powder, sodium bicarbonate, ammonium bicarbonate, citric acid  and food grade flavor etc.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(104, "Bengal All Time (Orange)", "alltime_orange_banner", "wheat flour, sugar, vegetable fat, full cream milk powder, glucose, salt, water, eggs, leavening agent, Permitted flavors & preservative.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(105, "Bengal All Time (Milk+Coconut)", "alltime_coco_milk_banner", "Wheat flour, sugar, vegetable fat, full cream milk powder,Glucose, salt, water, eggs, leavening agent, natural coconut, permitted flavors and preservative.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(106, "Bengal Orange Cake Biscuit", "orange_cake_banner", "Wheat flour, sugar, vegetable shortening, pour ghee, glucose,  milk powder, salt, ammonium bi carbonate, sodium bi carbonate, citric acid & food grade flavor.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(107, "Bengal Choco Twin", "choco_twin_banner", "Wheat flour, sugar, vegetable oi, liquid glucose, milk and milk products, cocoa powder, cocoa liquor, egg, salt, vanillin powder, sodium bi carbonate, ammonium bi carbonate, citric acid, foo grade flavor and water.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(108, "Bengal Hit Cream Biscuit", "hit_cream_banner", "Wheat Flour, hydrogenated vegetable oil, sugar, corn starch, full cream milk powder, invert suger syrup, vanilla cream powder, whey powder, table salt, leavening agent, permitted flavors.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(109, "Bengal B-vita Biscuit", "b_vita_banner", "Wheat flour, hydrogenated vegetable oil, sugar, full cream milk powder, invert sugar syrup, salt, water, egg, levening agent & food grade flavor");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(110, "Bengal Protein Plus", "protein_plus_banner", "Wheat flour, full cream milk powder, soya flour, sugar, liquid glucose, vegetable oil, butter oil, salt, egg, leavening agent, food grade flavor.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(111, "Bengal Glucose Biscuit", "glucose_banner", "Wheat flour, glucose, egg, sugar, shortening agent, milk powder, starch, salt, food grade flavor.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(112, "Bengal Lemon Biscuit", "lemon_banner", "Wheat flour, sugar, vegetable shortening, glucose, egg, full cream milk powder, salt, ammonium bi carbonate, sodium bi carbonate, citric acid & food grade flavor.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(113, "Bengal Bingo Toast Biscuit", "bingo_toast_banner", "Wheat flour, sugar, edible vegetable fat, black caraway, yeast, water and salt.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(114, "Kingdom Kheer Cookies", "kingdom_kheer_cookies", "Wheat flour, sugar, vegetable oil, egg, butter oil, milk powder, nut, salt, ammonium bicarbonate, sodium bicarbonate, food grade flavor and water etc.");
            commonInfos.add(productCommonInfo);
            dbOperator.updateProductCommonInfo(commonInfos, false);
        }
    }

    private static void fetchProductInfos() {

        ProductInfo productInfo;
        MemoProductInfo memoProductInfo;
        IntegratedProductInfo integratedProductInfo;

        Cursor c;
        String query = "Select * from " + TABLE_PRODUCT_DETAIL_INFO + " where " + COL_PRODUCT_ID + " BETWEEN  1000 AND 2000";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            integratedProductInfos = setDetailInfo(c);
        } else {

            /********
             * Pine-Apple Cream
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1011, "Bengal Pine-Apple Cream", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 25,
                    1, "8 pack/carton", "Carton", 140, "10121,10112,10113");
            integratedProductInfos.add(integratedProductInfo);


            integratedProductInfo = new IntegratedProductInfo(1012, "Bengal Pine-Apple Cream", "Regular Pack", "1 Carton", "20 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 10,
                    1, "20 pack/carton", "Carton", 160, "10121,10122,10113");
            integratedProductInfos.add(integratedProductInfo);


            integratedProductInfo = new IntegratedProductInfo(1013, "Pine-Apple Cream", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 5,
                    1, "40 pack/carton", "Carton", 155, "10131,10132,10113");
            integratedProductInfos.add(integratedProductInfo);

            /*******
             * Master Slices
             *******/
            integratedProductInfo = new IntegratedProductInfo(1021, "Bengal Master Slice", "Family Pack", "1 Carton", "6 packet", "Minimum 6 months", "Carton MRP", 210, "Packet MRP", 35,
                    2, "6 pack/carton", "Carton", 152, "10221,10212,10213");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1022, "Bengal Master Slice", "Regular Pack", "1 Carton", "24 Packet", "Minimum 6 months", "Carton MRP", 240, "Packet MRP", 10,
                    2, "24 pack/carton", "Carton", 190, "10221,10222,10213");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1023, "Bengal Master Slice", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 5,
                    2, "40 pack/carton", "Carton", 162, "10231,10232,10213");
            integratedProductInfos.add(integratedProductInfo);


            /********
             * Digestives Biscuit
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1031, "Nutribake Digestives Biscuit", "Family Pack", "1 Carton", "8 Box", "Minimum 6 months", "Carton MRP", 480, "Packet MRP", 60,
                    3, "8 box/carton", "Carton", 375, "10311,10312,10313");
            integratedProductInfos.add(integratedProductInfo);


            integratedProductInfo = new IntegratedProductInfo(1033, "Digestives Biscuit", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 192, "Packet MRP", 8,
                    3, "40 pack/carton", "Carton", 134, "10331,10332,10313");
            integratedProductInfos.add(integratedProductInfo);


            /********
             * Orange Cake Biscuit
             * *****/
            integratedProductInfo = new IntegratedProductInfo(1061, "Bengal Orange Cake Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 40,
                    6, "5 pack/carton", "Carton", 160, "10611,10612,10613");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1062, "Bengal Orange Cake Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 288, "Packet MRP", 12,
                    6, "24 pack/carton", "Carton", 208, "10621,10622,10613");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1063, "Bengal Orange Cake Biscuit", "Tin", "1 Carton", "4 Tin", "Minimum 9 months", "Carton MRP", 800, "Tin MRP", 200,
                    6, "1 tin", "Tin", 165, "10631,10632,10613");
            integratedProductInfos.add(integratedProductInfo);

            /*******
             * Hit Cream Biscuit
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1081, "Bengal Hit Cream Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 175, "Packet MRP", 35,
                    8, "5 pack/carton", "Carton", 135, "1081,10812,10813");
            integratedProductInfos.add(integratedProductInfo);

            /*******
             * B-vita Biscuit
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1091, "Bengal B-vita Biscuit", "Family Pack", "1 Carton", "5 packet", "Minimum 6 months", "Carton MRP", 150, "Packet MRP", 30,
                    9, "5 pack/carton", "Carton", 112, "1091,10912,10913");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Lemon Biscuit
             * *******/
            integratedProductInfo = new IntegratedProductInfo(1121, "Bengal Lemon Biscuit", "Family Pack", "1 Carton", "8 Packet", "Minimum 6 months", "Carton MRP", 176, "Packet MRP", 22,
                    12, "8 pack/carton", "Carton", 128, "11211,11212,11213");
            integratedProductInfos.add(integratedProductInfo);


            /******
             * Bingo Toast Biscuit
             * *****/
            integratedProductInfo = new IntegratedProductInfo(1131, "Bingo Toast Biscuit", "Family Pack", "1 Carton", "6 Packet", "Minimum 6 months", "Carton MRP", 300, "Packet MRP", 50,
                    13, "6 pack/carton", "Carton", 245, "11311,11312,11313");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Kingdom Kheer Cookies
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1141, "Kingdom Kheer Cookies", "Family Pack", "1 Carton", "5 Packet", "Minimum 6 months", "Carton MRP", 250, "Packet MRP", 50,
                    14, "5 pack/carton", "Carton", 186, "11411,11412,11413");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Choco Twin
             * ******/
            integratedProductInfo = new IntegratedProductInfo(1072, "Choco Twin", "Regular Pack", "1 Carton", "12 packet", "Minimum 6 months", "Carton MRP", 480, "Packet MRP", 40,
                    7, "12 pack/carton", "Carton", 360, "10731,10722,10723");
            integratedProductInfos.add(integratedProductInfo);

            integratedProductInfo = new IntegratedProductInfo(1073, "Choco Twin", "Mini Pack", "1 Carton", "40 packet", "Minimum 6 months", "Carton MRP", 400, "Packet MRP", 10,
                    7, "40 pack/carton", "Carton", 300, "10731,10732,10723");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * Protein Plus Biscuit
             * *******/
            integratedProductInfo = new IntegratedProductInfo(1102, "Protein Plus Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 312, "Packet MRP", 13,
                    10, "24 pack/carton", "Carton", 260, "11021,11022,11023");
            integratedProductInfos.add(integratedProductInfo);

            /*********
             * Glucose Biscuit
             * ********/
            integratedProductInfo = new IntegratedProductInfo(1112, "Bengal Glucose Biscuit", "Regular Pack", "1 Carton", "24 packet", "Minimum 6 months", "Carton MRP", 120, "Packet MRP", 5,
                    11, "24 pack/carton", "Carton", 95, "11121,11122,11123");
            integratedProductInfos.add(integratedProductInfo);

            /*****
             * All Time (Orange) Biscuit
             * *****/
            integratedProductInfo = new IntegratedProductInfo(1043, "All Time (Orange) Biscuit", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 2,
                    4, "100 pack/carton", "Carton", 140, "1041,10432,10433");
            integratedProductInfos.add(integratedProductInfo);


            /*******
             * All Time (Milk+Coconut)
             * *******/
            integratedProductInfo = new IntegratedProductInfo(1053, "All Time (Milk+Coconut)", "Mini Pack", "1 Carton", "100 packet", "Minimum 6 months", "Carton MRP", 200, "Packet MRP", 2,
                    5, "100 pack/carton", "Carton", 140, "10531,10532,10433");
            integratedProductInfos.add(integratedProductInfo);
            dbOperator.updateProductDetailInfo(integratedProductInfos, false);
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
        return totalBiscuitAdded;
    }

    public void setTotalItemAdded(int totalItemAdded) {
        this.totalBiscuitAdded = totalItemAdded;
    }

    @Override
    public double getTotalCost() {
        return totalBiscuitCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalBiscuitCost = totalCost;
    }

    public ArrayList<MemoProductInfo> getAddedProduct() {
        return addedBiscuit;
    }

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct) {
        this.addedBiscuit = addedProduct;
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