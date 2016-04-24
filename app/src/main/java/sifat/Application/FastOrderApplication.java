package sifat.Application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

/**
 * Created by Md. Sifat-Ul Haque on 4/23/2016.
 */
public class FastOrderApplication extends MultiDexApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(FastOrderApplication.this);
    }
}
