package sifat.Provider;

import android.database.Cursor;

import java.util.ArrayList;

import sifat.Domain.IntegratedProductInfo;
import sifat.Domain.ProductCommonInfo;

import static sifat.Utilities.CommonUtilities.COL_CONTAINER;
import static sifat.Utilities.CommonUtilities.COL_COST_PER_UNIT;
import static sifat.Utilities.CommonUtilities.COL_IMAGES;
import static sifat.Utilities.CommonUtilities.COL_MRP1;
import static sifat.Utilities.CommonUtilities.COL_MRP1TITLE;
import static sifat.Utilities.CommonUtilities.COL_MRP2;
import static sifat.Utilities.CommonUtilities.COL_MRP2TITLE;
import static sifat.Utilities.CommonUtilities.COL_PACKING;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_BANNER;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_HEADER;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_ID;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_INGREDIENT;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_NAME;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_SIZE;
import static sifat.Utilities.CommonUtilities.COL_QUANTITY;
import static sifat.Utilities.CommonUtilities.COL_SELLINGUNIT;
import static sifat.Utilities.CommonUtilities.COL_VALIDITY;

/**
 * Created by sifat on 12/6/2015.
 */
public abstract class BaseProvider implements ProductInfoProvider {



    public static ArrayList<ProductCommonInfo> setCommonInfo(Cursor c) {

        ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
        int count = 0, productId;
        String header, banner, ingredient;
        ProductCommonInfo productCommonInfo;
        c.moveToFirst();
        while (!c.isAfterLast()) {
            productId = c.getInt(c.getColumnIndex(COL_PRODUCT_ID));
            header = c.getString(c.getColumnIndex(COL_PRODUCT_HEADER));
            banner = c.getString(c.getColumnIndex(COL_PRODUCT_BANNER));
            ingredient = c.getString(c.getColumnIndex(COL_PRODUCT_INGREDIENT));
            productCommonInfo = new ProductCommonInfo(productId, header, banner, ingredient);
            commonInfos.add(productCommonInfo);
            c.moveToNext();
        }
        return commonInfos;
    }


    public static ArrayList<IntegratedProductInfo> setDetailInfo(Cursor c) {
        ArrayList<IntegratedProductInfo> integratedProductInfos = new ArrayList<>();
        int product_id, mrp1, mrp2, costPerUnit, header;
        String name, size, container, quantity, validity, mrp1Title, mrp2Title, packing, sellingUnit, images;
        IntegratedProductInfo integratedProductInfo;
        c.moveToFirst();
        while (!c.isAfterLast()) {
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
        return integratedProductInfos;
    }
}
