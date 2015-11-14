package sifat.Provider;

import java.util.ArrayList;

import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/14/2015.
 */
public class CandyInfoProvider implements ProductInfoProvider {

    private volatile static CandyInfoProvider candyInfoProvider;

    private CandyInfoProvider() {
    }

    public static CandyInfoProvider getProvider() {
        if (candyInfoProvider == null) {
            synchronized (CandyInfoProvider.class) {
                if (candyInfoProvider == null)
                    //setProductInfos();
                    candyInfoProvider = new CandyInfoProvider();
            }
        }
        return candyInfoProvider;
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
