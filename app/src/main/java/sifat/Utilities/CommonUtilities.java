package sifat.Utilities;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by sifat on 11/11/2015.
 */
public class CommonUtilities {

    public static final String SINGLE_PRODUCT_DETAIL="singleProductDetail";

    public static void showToast(Context context,String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
