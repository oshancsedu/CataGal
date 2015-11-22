package sifat.Provider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.StringTokenizer;

import sifat.Database.DbOperator;

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
            String areaname = c.getString(c.getColumnIndex(COL_AREA_NAME));
            setAreaName(areaname);
            String areacode = c.getString(c.getColumnIndex(COL_AREA_CODE));
            setAreaCode(areacode);
            String distributorname = c.getString(c.getColumnIndex(COL_DISTRIBUTOR_NAME));
            setDistributorName(distributorname);
        } else {
            setAreaName(DEFULT_AREA_NAME);
            setAreaCode(DEFULT_AREA_CODE);
            setDistributorName(DEFULT_DISTRIBUTOR_NAME);
        }
    }

    public void setDistributorName(String distributorName) {
        StringTokenizer tokenizer = new StringTokenizer(distributorName, ",");
        while (tokenizer.hasMoreTokens())
            distributorNames.add(tokenizer.nextToken());
    }

    public void setAreaName(String areaName) {
        StringTokenizer tokenizer = new StringTokenizer(areaName, ",");

        while (tokenizer.hasMoreTokens())
            areaNames.add(tokenizer.nextToken());
    }

    public void setAreaCode(String areaCode) {
        StringTokenizer tokenizer = new StringTokenizer(areaCode, ",");

        while (tokenizer.hasMoreTokens())
            areaCodes.add(tokenizer.nextToken());

        //"-Area Code-,1,12,16"
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
}