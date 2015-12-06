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
 * Created by sifat on 11/14/2015.
 */
public class CandyInfoProvider extends BasicProvider {

    private volatile static CandyInfoProvider candyInfoProvider;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static List<Integer> product_images = new ArrayList<>();
    private static ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private static ArrayList<IntegratedProductInfo> integratedProductInfos = new ArrayList<>();
    private static int totalItemAdded, totalCost;
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
        String query = "Select * from " + TABLE_PRODUCT_COMMON_INFO + " where " + COL_PRODUCT_ID + " > 200";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            Log.i(LOG_TAG_DATABASE, "count not 0");
            commonInfos = setCommonInfo(c);
        } else {
            Log.i(LOG_TAG_DATABASE, "count 0");
            ProductCommonInfo productCommonInfo = new ProductCommonInfo(201, "Bingo Milk Candy", "milk_candy_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(202, "Bingo Tamarind Candy", "tamarind_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(203, "Winnie Green Mango Candy", "tamarind_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            productCommonInfo = new ProductCommonInfo(204, "Winnie Lychee Candy", "tamarind_banner.jpg", "ingredients");
            commonInfos.add(productCommonInfo);
            dbOperator.updateProductCommonInfo(commonInfos);
        }
    }

    private static void fetchProductInfos() {

        ProductInfo productInfo;
        MemoProductInfo memoProductInfo;
        IntegratedProductInfo integratedProductInfo;

        Cursor c;
        String query = "Select * from " + TABLE_PRODUCT_DETAIL_INFO + " where " + COL_PRODUCT_ID + " > 2000";
        c = sqlDatabase.rawQuery(query, null);
        if (c.getCount() > 0) {
            //Log.i(LOG_TAG_DATABASE, "count not 0");
            integratedProductInfos = setDetailInfo(c);
        } else {

            /***
             * Bingo Milk
             * **/
            integratedProductInfo = new IntegratedProductInfo(2011, "Bingo Milk Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 1, "1 Boyam", "Boyam", 250, "11811,11812,11813");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2012, "Bingo Milk Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 1, "1 Boyam", "Boyam", 200, "11811,11812,11813");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2013, "Bingo Milk Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Boyam MRP", 40,
                    "Per Piece MRP", 1, 1, "12 Pack/Carton", "Carton", 480, "11831,11812,11813");
            integratedProductInfos.add(integratedProductInfo);

            /***
             * Bingo Tamarind
             * **/
            integratedProductInfo = new IntegratedProductInfo(2021, "Bingo Tamarind Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 2, "1 Boyam", "Boyam", 250, "11711,11712,11713");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2022, "Bingo Tamarind Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 2, "1 Boyam", "Boyam", 200, "11711,11712,11713");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2023, "Bingo Tamarind Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Boyam MRP", 40,
                    "Per Piece MRP", 1, 2, "12 Pack/Carton", "Carton", 480, "11731,11712,11713");
            integratedProductInfos.add(integratedProductInfo);

            /****
             *Winnie Mango Candy
             * ***/
            integratedProductInfo = new IntegratedProductInfo(2031, "Winnie Mango Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 3, "1 Boyam", "Boyam", 250, "11511,11512,11513");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2032, "Winnie Mango Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 3, "1 Boyam", "Boyam", 200, "11811,11812,11813");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2033, "Winnie Mango Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Boyam MRP", 40,
                    "Per Piece MRP", 1, 3, "12 Pack/Carton", "Carton", 480, "11531,11512,11513");
            integratedProductInfos.add(integratedProductInfo);

            /****
             *Winnie Lychee Candy
             * ***/
            integratedProductInfo = new IntegratedProductInfo(2041, "Winnie Lychee Candy", "Boyam (250)", "1 Boyam", "250 Piece", "Minimum 6 months", "Boyam MRP", 250,
                    "Per Piece MRP", 1, 4, "1 Boyam", "Boyam", 250, "11611,11612,11613");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2042, "Winnie Lychee Candy", "Boyam (200)", "1 Boyam", "200 Piece", "Minimum 6 months", "Boyam MRP", 200,
                    "Per Piece MRP", 1, 4, "1 Boyam", "Boyam", 200, "11611,11612,11613");
            integratedProductInfos.add(integratedProductInfo);
            integratedProductInfo = new IntegratedProductInfo(2043, "Winnie Lychee Candy", "Consumer Pack", "1 Pack", "50 Piece", "Minimum 6 months", "Boyam MRP", 40,
                    "Per Piece MRP", 1, 4, "12 Pack/Carton", "Carton", 480, "11631,11612,11613");
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


    /*private static void setDetailInfo(Cursor c) {
        int product_id, mrp1, mrp2, costPerUnit, header;
        String name, size, container, quantity, validity, mrp1Title, mrp2Title, packing, sellingUnit, images;
        IntegratedProductInfo integratedProductInfo;
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            product_id = c.getInt(c.getColumnIndex(COL_PRODUCT_ID));
            mrp1 = c.getInt(c.getColumnIndex(COL_MRP1));
            mrp2 = c.getInt(c.getColumnIndex(COL_MRP2));
            costPerUnit = c.getInt(c.getColumnIndex(COL_COST_PER_UNIT));
            header = c.getInt(c.getColumnIndex(COL_PRODUCT_HEADER));
            name = c.getString(c.getColumnIndex(COL_PRODUCT_NAME));
            size = c.getString(c.getColumnIndex(COL_PRODUCT_SIZE));
            container = c.getString(c.getColumnIndex(COL_CONTAINER));
            quantity = c.getString(c.getColumnIndex(COL_QUANTITY));
            validity = c.getString(c.getColumnIndex(COL_VALIDITY));
            mrp1Title = c.getString(c.getColumnIndex(COL_MRP1TITLE));
            mrp2Title = c.getString(c.getColumnIndex(COL_MRP2TITLE));
            packing = c.getString(c.getColumnIndex(COL_PACKING));
            sellingUnit = c.getString(c.getColumnIndex(COL_SELLINGUNIT));
            images = c.getString(c.getColumnIndex(COL_IMAGES));

            integratedProductInfo = new IntegratedProductInfo(product_id, name, size, container, quantity, validity, mrp1Title, mrp1, mrp2Title, mrp2, header, packing, sellingUnit, costPerUnit, images);
            integratedProductInfos.add(integratedProductInfo);
            c.moveToNext();
        }
    }*/


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
        return totalItemAdded;
    }

    public void setTotalItemAdded(int totalItemAdded) {
        this.totalItemAdded = totalItemAdded;
    }

    @Override
    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public ArrayList<MemoProductInfo> getAddedProduct() {
        return addedProduct;
    }

    public void setAddedProduct(ArrayList<MemoProductInfo> addedProduct) {
        this.addedProduct = addedProduct;
    }
}