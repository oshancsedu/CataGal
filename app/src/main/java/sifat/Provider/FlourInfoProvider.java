package sifat.Provider;

import java.util.ArrayList;

import sifat.Domain.ProductInfo;

/**
 * Created by sifat on 11/14/2015.
 */
public class FlourInfoProvider implements ProductInfoProvider {

    private volatile static FlourInfoProvider flourInfoProvider;

    private FlourInfoProvider() {
    }

    public static FlourInfoProvider getProvider() {
        if (flourInfoProvider == null) {
            synchronized (FlourInfoProvider.class) {
                if (flourInfoProvider == null)
                    //setProductInfos();
                    flourInfoProvider = new FlourInfoProvider();
            }
        }
        return flourInfoProvider;
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
