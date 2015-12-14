package sifat.Domain;

/**
 * Created by sifat on 12/14/2015.
 */
public class MemoBasicInfo {
    String areaName, distributorName, areaCode;

    public MemoBasicInfo(String areaName, String distributorName, String areaCode) {
        this.areaCode = areaCode;
        this.areaName = areaName;
        this.distributorName = distributorName;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {

        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
