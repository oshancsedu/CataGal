package sifat.Provider;

import android.content.Context;
import android.content.SharedPreferences;

import static sifat.Utilities.CommonUtilities.SHAREDPREF_TAG_SELECTED_ITEM;
import static sifat.Utilities.CommonUtilities.getPref;

/**
 * Created by sifat on 11/14/2015.
 */
public class ProviderSelector {

    private static SharedPreferences sharedPreferences;

    public static ProductInfoProvider getMyProvider(Context context) {
        ProductInfoProvider productInfoProvider = null;
        sharedPreferences = getPref(context);
        String productName = sharedPreferences.getString(SHAREDPREF_TAG_SELECTED_ITEM, "0");
        if (productName.equalsIgnoreCase("0"))
            productInfoProvider = BiscuitInfoProvider.getProvider(context);
        else if (productName.equalsIgnoreCase("1"))
            productInfoProvider = CandyInfoProvider.getProvider(context);
        /*else if (productName.equalsIgnoreCase("Snacks"))
            productInfoProvider = SnacksInfoProvider.getProvider();
        else if (productName.equalsIgnoreCase("Flour"))
            productInfoProvider = FlourInfoProvider.getProvider();*/
        return productInfoProvider;
    }
}
