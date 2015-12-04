package sifat.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import sifat.Domain.ProductCommonInfo;
import sifat.Provider.MemoBasicInfoProvider;

import static sifat.Utilities.CommonUtilities.COL_AREA_CODE;
import static sifat.Utilities.CommonUtilities.COL_AREA_NAME;
import static sifat.Utilities.CommonUtilities.COL_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_BANNER;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_HEADER;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_ID;
import static sifat.Utilities.CommonUtilities.COL_PRODUCT_INGREDIENT;
import static sifat.Utilities.CommonUtilities.DATABASE_NAME;
import static sifat.Utilities.CommonUtilities.DATABASE_VERSION;
import static sifat.Utilities.CommonUtilities.TABLE_MEMO_BASIC_INFO;
import static sifat.Utilities.CommonUtilities.TABLE_PRODUCT_COMMON_INFO;
import static sifat.Utilities.CommonUtilities.showToast;

/**
 * Created by sifat on 11/17/2015.
 */
public class DbOperator {

    private volatile static DbOperator dbOperator;
    private final Context context;
    private DbInit dbInit;
    private SQLiteDatabase sqlDatabase;

    private DbOperator(Context context) {
        this.context = context;
    }

    public static DbOperator getDbOperator(Context context) {
        if (dbOperator == null) {
            synchronized (DbOperator.class) {
                if (dbOperator == null)
                    dbOperator = new DbOperator(context);
            }
        }
        return dbOperator;
    }

    public DbOperator open() {
        dbInit = new DbInit(context);
        sqlDatabase = dbInit.getWritableDatabase();
        Log.i("Clear", "Open");
        return this;
    }

    public SQLiteDatabase getDatabase() {
        return sqlDatabase;
    }

    public void close() {
        dbInit.close();
    }

    public void updateMemoBasicInfo(String distributorName, String areaName, String areaCode) {
        String query = "delete from " + TABLE_MEMO_BASIC_INFO;
        sqlDatabase.execSQL(query);
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_AREA_CODE, areaCode);
        contentValues.put(COL_AREA_NAME, areaName);
        contentValues.put(COL_DISTRIBUTOR_NAME, distributorName);
        sqlDatabase.insert(TABLE_MEMO_BASIC_INFO, null, contentValues);
        showToast(context, "Updated!");
    }

    public void updateProductCommonInfo(ArrayList<ProductCommonInfo> commonInfos) {
        String query = "delete from " + TABLE_PRODUCT_COMMON_INFO;
        sqlDatabase.execSQL(query);
        int size = commonInfos.size();
        for (int i = 0; i < size; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(COL_PRODUCT_ID, commonInfos.get(i).getProductID());
            contentValues.put(COL_PRODUCT_HEADER, commonInfos.get(i).getHeader());
            contentValues.put(COL_PRODUCT_BANNER, commonInfos.get(i).getBanner());
            contentValues.put(COL_PRODUCT_INGREDIENT, commonInfos.get(i).getIngredient());
            sqlDatabase.insert(TABLE_PRODUCT_COMMON_INFO, null, contentValues);
        }
        showToast(context, "Updated!");
    }

    public void setMemoBasicInfo() {
        MemoBasicInfoProvider memoBasicInfoProvider = MemoBasicInfoProvider.getProvider(context);

        Cursor c;
        String query = "Select * from " + TABLE_MEMO_BASIC_INFO;
        c = sqlDatabase.rawQuery(query, null);
        c.moveToFirst();

        String areaname = c.getString(c.getColumnIndex(COL_AREA_NAME));
        memoBasicInfoProvider.setAreaName(areaname);
        String areacode = c.getString(c.getColumnIndex(COL_AREA_CODE));
        memoBasicInfoProvider.setAreaCode(areacode);
        String distributorname = c.getString(c.getColumnIndex(COL_DISTRIBUTOR_NAME));
        memoBasicInfoProvider.setDistributorName(distributorname);
    }


    private class DbInit extends SQLiteOpenHelper {
        private String query;

        public DbInit(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            query = "CREATE TABLE " + TABLE_MEMO_BASIC_INFO + " (" +
                    COL_AREA_NAME + " varchar(500) NOT NULL," +
                    COL_AREA_CODE + " varchar(500) NOT NULL," +
                    COL_DISTRIBUTOR_NAME + " varchar(400) NOT NULL" +
                    ");";
            db.execSQL(query);

            query = "CREATE TABLE " + TABLE_PRODUCT_COMMON_INFO + " (" +
                    COL_PRODUCT_ID + " int(7) NOT NULL," +
                    COL_PRODUCT_HEADER + " varchar(100) NOT NULL," +
                    COL_PRODUCT_BANNER + " varchar(50) NOT NULL," +
                    COL_PRODUCT_INGREDIENT + " varchar(300) NOT NULL" +
                    ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + TABLE_MEMO_BASIC_INFO);
            db.execSQL("drop table if exists " + TABLE_PRODUCT_COMMON_INFO);
            onCreate(db);
        }
    }
}
