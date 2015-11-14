package sifat.Provider;

/**
 * Created by sifat on 11/14/2015.
 */
public class ProviderSelector {

    public static ProductInfoProvider getMyProvider(String productName) {
        ProductInfoProvider productInfoProvider = null;
        if (productName.equalsIgnoreCase("Biscuit"))
            productInfoProvider = BiscuitInfoProvider.getProvider();
        else if (productName.equalsIgnoreCase("Candey"))
            productInfoProvider = CandyInfoProvider.getProvider();
        else if (productName.equalsIgnoreCase("Snacks"))
            productInfoProvider = SnacksInfoProvider.getProvider();
        else if (productName.equalsIgnoreCase("Flour"))
            productInfoProvider = FlourInfoProvider.getProvider();
        return productInfoProvider;
    }
}
