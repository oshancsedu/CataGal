package sifat.Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import sifat.Database.DbOperator;
import sifat.Domain.MemoProductInfo;

import static sifat.Utilities.CommonUtilities.COL_AREA_CODE;
import static sifat.Utilities.CommonUtilities.COL_AREA_NAME;
import static sifat.Utilities.CommonUtilities.COL_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.DEFULT_AREA_CODE;
import static sifat.Utilities.CommonUtilities.DEFULT_AREA_NAME;
import static sifat.Utilities.CommonUtilities.DEFULT_DISTRIBUTOR_NAME;
import static sifat.Utilities.CommonUtilities.TABLE_MEMO_BASIC_INFO;

/**
 * Created by sifat on 11/16/2015.
 */
public class MemoBasicInfoProvider {

    private volatile static MemoBasicInfoProvider memoBasicInfoProvider;
    private static ArrayList<String> areaCodes = new ArrayList<>();
    private static ArrayList<String> areaNames = new ArrayList<>();
    private static ArrayList<String> distributorNames = new ArrayList<>();
    private String areaCode, areaName, distributorName;
    private DbOperator dbOperator;
    private Context context;
    private SQLiteDatabase sqlDatabase;
    private static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static int totalItemAdded;
    private static double totalCost;

    private MemoBasicInfoProvider(Context context) {

        this.context = context;
        dbOperator = DbOperator.getDbOperator(this.context);
        dbOperator.open();
        sqlDatabase = dbOperator.getDatabase();
        //"-Distributor Name-,Juber Store,Ma-Babar Dowya,Siyam Enterprise"
        //"-Area Name-,Dinajpur,Birol,Birampur"
        /*dbOperator.updateMemoBasicInfo("-Distributor Name-,Juber Store,Ma-Babar Dowya,Siyam Enterprise,Jakir Store",
                "-Area Name-,Dinajpur,Birol,Birampur,Parbatipur,New Town","-Area Code-,1,12,16,17,0,11");*/
        //dbOperator.setMemoBasicInfo();
        setMemoBasicInfo();

        dbOperator.close();
    }

    public static MemoBasicInfoProvider getProvider(Context context) {
        if (memoBasicInfoProvider == null) {
            synchronized (MemoBasicInfoProvider.class) {
                if (memoBasicInfoProvider == null)
                    memoBasicInfoProvider = new MemoBasicInfoProvider(context);
            }
        }
        return memoBasicInfoProvider;
    }


    public void setMemoBasicInfo() {
        Cursor c;
        String query = "Select * from " + TABLE_MEMO_BASIC_INFO;
        c = sqlDatabase.rawQuery(query, null);

        if (c.getCount() > 0) {
            c.moveToFirst();

            while (!c.isAfterLast()) {
                String areaname = c.getString(c.getColumnIndex(COL_AREA_NAME));
                areaNames.add(areaname);
                String areacode = c.getString(c.getColumnIndex(COL_AREA_CODE));
                areaCodes.add(areacode);
                String distributorname = c.getString(c.getColumnIndex(COL_DISTRIBUTOR_NAME));
                distributorNames.add(distributorname);
                c.moveToNext();
            }

        } else {
            areaCodes.add(DEFULT_AREA_CODE);
            areaNames.add(DEFULT_AREA_NAME);
            distributorNames.add(DEFULT_DISTRIBUTOR_NAME);
        }
    }

    public ArrayList<String> getAreaCodes() {
        return areaCodes;
    }

    public ArrayList<String> getAreaNames() {
        return areaNames;
    }

    public ArrayList<String> getDistributorNames() {
        return distributorNames;
    }

    public static ArrayList<MemoProductInfo> getMemoProductInfos() {
        return memoProductInfos;
    }

    public static ArrayList<MemoProductInfo> getAddedProduct() {
        return addedProduct;
    }

    public static int getTotalItemAdded() {
        return totalItemAdded;
    }

    public static double getTotalCost() {
        return totalCost;
    }
}