package sifat.Application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Md. Sifat-Ul Haque on 4/23/2016.
 */
public class FastOrderApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(FastOrderApplication.this);
    }
}
