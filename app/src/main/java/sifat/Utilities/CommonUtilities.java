package sifat.Utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by sifat on 11/11/2015.
 */
public class CommonUtilities {

    public static final String SINGLE_PRODUCT_DETAIL="singleProductDetail";
    public static final String SINGLE_PRODUCT_COMMON_INFO = "singleProductCommonInfo";

    public static final String DATABASE_NAME = "Bengal";
    public static final int DATABASE_VERSION = 3;

    public static final String TABLE_MEMO_BASIC_INFO = "basic_memo_info";
    public static final String COL_AREA_NAME = "areaName";
    public static final String COL_AREA_CODE = "areaCode";
    public static final String COL_DISTRIBUTOR_NAME = "distributorName";

    public static final String TABLE_PRODUCT_COMMON_INFO = "product_common_info";
    public static final String COL_PRODUCT_ID = "product_id";
    public static final String COL_PRODUCT_BANNER = "product_banner";
    public static final String COL_PRODUCT_INGREDIENT = "product_ingredient";
    public static final String COL_PRODUCT_HEADER = "product_header";

    public static final String TABLE_PRODUCT_DETAIL_INFO = "product_detail_info";
    public static final String COL_PRODUCT_SIZE = "size";
    public static final String COL_PRODUCT_NAME = "product_name";
    public static final String COL_CONTAINER = "container";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_VALIDITY = "validity";
    public static final String COL_MRP1TITLE = "mrp1Title";
    public static final String COL_MRP1 = "mrp1";
    public static final String COL_MRP2TITLE = "mrp2Title";
    public static final String COL_MRP2 = "mrp2";
    public static final String COL_IMAGES = "images";
    public static final String COL_PACKING = "packing";
    public static final String COL_SELLINGUNIT = "sellingUnit";
    public static final String COL_COST_PER_UNIT = "costPerUnit";


    public static final String CONFIRM_FRAG_TAG = "confirm_frag_tag";
    public static final String DIALOG_HEADER_SUPPLY_DATE = "dialog_header_supply_date";
    public static final String DIALOG_HEADER_AREA_NAME = "dialog_header_area_name";
    public static final String DIALOG_HEADER_AREA_CODE = "dialog_header_area_code";
    public static final String DIALOG_HEADER_DISTRIBUTOR_NAME = "dialog_header_distributor_name";
    public static final String DEFULT_AREA_NAME = "-Area Name-";
    public static final String DEFULT_AREA_CODE = "-Area Code-";
    public static final String DEFULT_DISTRIBUTOR_NAME = "-Distributor Name-";

    public static final String MEMO_BASIC_INFO_URL = "http://inspireitl.com/bengal/memoinfo.php";
    public static final String LOGIN_URL = "http://inspireitl.com/bengal/login.php";
    public static final String MEMO_RECEIVE_URL = "http://inspireitl.com/bengal/memoreceive.php";
    public static final String PRODUCT_INFO_RECEIVE_URL = "http://inspireitl.com/bengal/product.php";

    public static final String LOG_TAG_WEB = "webLogTag";
    public static final String LOG_TAG_MEMO = "memoLogTag";
    public static final String LOG_TAG_DATABASE = "databaseLogTag";

    public static final String JSON_TAG_AREA_NAME = "area_name";
    public static final String JSON_TAG_AREA_CODE = "area_code";
    public static final String JSON_TAG_DISTRIBUTOR_NAME = "distributor_name";
    public static final String JSON_TAG_PRODUCT_COMMON_INFO_ARRAY = "product_common_info";
    public static final String JSON_TAG_PRODUCT_ID = "product_id";
    public static final String JSON_TAG_HEADER = "header";
    public static final String JSON_TAG_BANNER = "banner";
    public static final String JSON_TAG_INGREDIENT = "ingredient";

    public static final String SHAREPREF_TAG = "sifat.mySharedPref";
    public static final String SHAREDPREF_TAG_SELECTED_ITEM = "selectedItem";
    public static final String SHAREDPREF_TAG_USERID = "userId";

    public static final String SERVER_REQUEST_USERID = "userIDRequestParams";
    public static final String SERVER_REQUEST_PASSWORD = "passwordRequestParams";
    public static final String SERVER_REQUEST_AREA_NAME = "areaName";
    public static final String SERVER_REQUEST_AREA_CODE = "areaCode";
    public static final String SERVER_REQUEST_DISTRIBUTOR_NAME = "distributorName";
    public static final String SERVER_REQUEST_SUPPLY_DATE = "supplyDate";
    public static final String SERVER_REQUEST_PRODUCT_NAME = "productName";
    public static final String SERVER_REQUEST_PRODUCT_SIZE = "productSize";
    public static final String SERVER_REQUEST_COMMENT = "commentOnProduct";
    public static final String SERVER_REQUEST_PRODUCT_COST = "productCost";
    public static final String SERVER_REQUEST_PRODUCT_QUANTITY = "productQuantity";
    public static final String SERVER_REQUEST_PRODUCT_UNIT = "productUnit";

    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public static SharedPreferences getPref(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPREF_TAG, Context.MODE_PRIVATE);
        return sharedPreferences;
    }


    public static void changeActivity(Context context, Class toClass) {
        Intent intent = new Intent(context, toClass);
        context.startActivity(intent);
    }
}