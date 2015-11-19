package sifat.Utilities;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sifat on 11/11/2015.
 */
public class CommonUtilities {

    public static final String SINGLE_PRODUCT_DETAIL="singleProductDetail";


    public static final String DATABASE_NAME = "Bengal";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_MEMO_BASIC_INFO = "basic_memo_info";
    public static final String COL_AREA_NAME = "areaName";
    public static final String COL_AREA_CODE = "areaCode";
    public static final String COL_DISTRIBUTOR_NAME = "distributorName";
    public static final String CONFIRM_FRAG_TAG = "confirm_frag_tag";

    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
