package sifat.Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sifat.Database.DbOperator;
import sifat.Domain.IntegratedProductInfo;
import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductCommonInfo;
import sifat.Domain.ProductInfo;

import static sifat.Utilities.CommonUtilities.COL_PRODUCT_ID;
import static sifat.Utilities.CommonUtilities.TABLE_PRODUCT_COMMON_INFO;
import static sifat.Utilities.CommonUtilities.TABLE_PRODUCT_DETAIL_INFO;

/**
 * Created by sifat on 11/14/2015.
 */
public class CandyInfoProvider extends BaseProvider {

    private volatile static CandyInfoProvider candyInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images = new ArrayList<>();
    private static ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedCandy = new ArrayList<>();
    private static ArrayList<IntegratedProductInfo> integratedProductInfos = new ArrayList<>();
    private static int totalCandyAdded;
    private static double totalCandyCost;
    private static DbOperator dbOperator;
    private static SQLiteDatabase sqlDatabase;
    private static Context context;

    private CandyInfoProvider(Context context) {
        this.context = context;
        dbOperator = DbOperator.getDbOperator(this.context);
        dbOperator.open();
        sqlDatabase = dbOperator.getDatabase();
        fetchProductInfos();
        fetchCommonInfo();
        dbOperator.close();
    }

    public static CandyInfoProvider getProvider(Context context) {
        if (candyInfoProvider == null) {
            synchronized (CandyInfoProvider.class) {
                if (candyInfoProvider == null)
                    candyInfoProvider = new CandyInfoProvider(context);
            }
        }
        return candyInfoProvider;
    }

    private static void fetchCommonInfo() {

        Cursor c;
        String query = "Select * from " + TABLE_PRODUCT_COMMON_INFO + " WHERE " + COL_PRODUCT_ID + " BETWEEN  200 AND 300";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            commonInfos = setCommonInfo(c);
        } else {
            ProductCommonInfo productCommonInfo;
            productCommonInfo = new ProductCommonInfo(201, "Winnie Green Mango Candy", "green_mango_banner", "Sugar, liquid glucose,citric acid, salt, water and food grade flavour etc.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(202, "Winnie Lychee Candy", "lychee_banner", "Sugar, liquid glucose, Citric Acid, Salt, water & food grade flavor etc.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(203, "Bingo Tamarind Candy", "tamarind_banner", "Sugar, liquid glucose, salt, water & food grade flavor etc.");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(204, "Bingo Milk Candy", "milk_candy_banner", "Sugar, liquid glucose, milk powder, vegetable fat, salt, water and food grade flavour.");
            commonInfos.add(productCommonInfo);
            dbOperator.updateProductCommonInfo(commonInfos, false);
        }
    }

    private static void fetchProductInfos() {

        ProductInfo productInfo;
        MemoProductInfo memoProductInfo;
        IntegratedProductInfo integratedProductInfo;
        Cursor c;
        String query = "Select * from " + TABLE_PRODUCT_DETAIL_INFO + " where " + COL_PRODUCT_ID + " BETWEEN  2000 AND 3000";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            integratedProductInfos = setDetailInfo(c);
        } else {

            /***
             * Bingo Milk
             * **/
            integratedProductInfo = new IntegratedProductInfo(2041, "Bingo Milk Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 4, "1 Boyam", "Boyam", 172, "20411,20412,20413");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2042, "Bingo Milk Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 4, "1 Boyam", "Boyam", 135, "20411,20412,20413");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2043, "Bingo Milk Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP", 40,
                    "Per Piece MRP", 1, 4, "12 Pack/Carton", "Carton", 375, "20431,20412,20413");
            integratedProductInfos.add(integratedProductInfo);

            /***
             * Bingo Tamarind
             * **/
            integratedProductInfo = new IntegratedProductInfo(2031, "Bingo Tamarind Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 3, "1 Boyam", "Boyam", 172, "20311,20312,20313");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2032, "Bingo Tamarind Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 3, "1 Boyam", "Boyam", 135, "20311,20312,20313");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2033, "Bingo Tamarind Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP", 40,
                    "Per Piece MRP", 1, 3, "12 Pack/Carton", "Carton", 375, "20331,20312,20313");
            integratedProductInfos.add(integratedProductInfo);

            /****
             *Winnie Mango Candy
             * ***/
            integratedProductInfo = new IntegratedProductInfo(2011, "Winnie Mango Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 1, "1 Boyam", "Boyam", 172, "20111,20112,20113");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2012, "Winnie Mango Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 1, "1 Boyam", "Boyam", 135, "20111,20112,20113");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2013, "Winnie Mango Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP", 40,
                    "Per Piece MRP", 1, 1, "12 Pack/Carton", "Carton", 375, "20131,20112,20113");
            integratedProductInfos.add(integratedProductInfo);

            /****
             *Winnie Lychee Candy
             * ***/
            integratedProductInfo = new IntegratedProductInfo(2021, "Winnie Lychee Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 2, "1 Boyam", "Boyam", 172, "20211,20212,20213");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2022, "Winnie Lychee Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 2, "1 Boyam", "Boyam", 135, "20211,20212,20213");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2023, "Winnie Lychee Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Packet MRP", 40,
                    "Per Piece MRP", 1, 2, "12 Pack/Carton", "Carton", 375, "20231,20212,20213");
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

    @Override
    public ArrayList<ProductInfo> getProductInfos() {
        return productInfos;
    }

    @Override
    public ArrayList<ProductCommonInfo> getCommonInfo() {
        return commonInfos;
    }



    @Override
    public ArrayList<MemoProductInfo> getProductMemoInfo() {
        return memoProductInfos;
    }

    public void setProductMemoInfo(ArrayList<MemoProductInfo> memoProductInfos) {
        this.memoProductInfos = memoProductInfos;
    }

    @Override
    public int getTotalItemAdded() {
        return totalCandyAdded;
    }

    public void setTotalItemAdded(int totalItemAdded) {
        this.totalCandyAdded = totalItemAdded;
    }

    @Override
    public double getTotalCost() {
        return totalCandyCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCandyCost = totalCost;
    }

    @Override
    public ArrayList<MemoProductInfo> getAddedProduct() {
        return addedCandy;
    }

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct) {
        this.addedCandy = addedProduct;
    }
}