package sifat.Provider;

import java.util.ArrayList;

import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/14/2015.
 */
public class SnacksInfoProvider implements ProductInfoProvider {

    private volatile static SnacksInfoProvider snacksInfoProvider;

    private SnacksInfoProvider() {
    }

    public static SnacksInfoProvider getProvider() {
        if (snacksInfoProvider == null) {
            synchronized (SnacksInfoProvider.class) {
                if (snacksInfoProvider == null)
                    //setProductInfos();
                    snacksInfoProvider = new SnacksInfoProvider();
            }
        }
        return snacksInfoProvider;
    }

    @Override
    public ArrayList<ProductInfo> getProductInfos() {
        return null;
    }

    @Override
    public ArrayList<String> getHeader() {
        return null;
    }


}
