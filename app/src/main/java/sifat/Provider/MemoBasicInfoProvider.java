package sifat.Provider;

import java.util.ArrayList;

/**
 * Created by sifat on 11/16/2015.
 */
public class MemoBasicInfoProvider {

    private volatile static MemoBasicInfoProvider memoBasicInfoProvider;
    private static ArrayList<String> areaCodes = new ArrayList<>();
    private static ArrayList<String> areaNames = new ArrayList<>();
    private static ArrayList<String> distributorNames = new ArrayList<>();

    private MemoBasicInfoProvider() {
    }

    public static MemoBasicInfoProvider getProvider() {
        if (memoBasicInfoProvider == null) {
            synchronized (MemoBasicInfoProvider.class) {
                if (memoBasicInfoProvider == null)
                    setAreaCode();
                setAreaName();
                setDistributorName();
                memoBasicInfoProvider = new MemoBasicInfoProvider();
            }
        }
        return memoBasicInfoProvider;
    }

    private static void setDistributorName() {
        distributorNames.add("-Distributor Name-");
        distributorNames.add("Juber Store");
        distributorNames.add("Ma-Babar Dowya");
        distributorNames.add("Siyam Enterprise");
        distributorNames.add("Juber Store");
        distributorNames.add("Jakir Store");
    }

    private static void setAreaName() {
        areaNames.add("-Area Name-");
        areaNames.add("Dinajpur");
        areaNames.add("Parbatipur");
        areaNames.add("Birampur");
        areaNames.add("Birol");
    }

    private static void setAreaCode() {
        areaCodes.add("-Area Code-");
        areaCodes.add("1");
        areaCodes.add("14");
        areaCodes.add("16");
        areaCodes.add("17");
        areaCodes.add("0");
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


