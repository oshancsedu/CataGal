package sifat.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import java.util.List;
import java.util.Map;

/**
 * Created by sifat on 11/13/2015.
 */
class MaterialColorAdapter extends WheelArrayAdapter<Map.Entry<String, Integer>> {

    Context context;

    MaterialColorAdapter(List<Map.Entry<String, Integer>> entries, Context context) {
        super(entries);
        this.context = context;
    }

    @Override
    public Drawable getDrawable(int position) {

        Resources res = context.getResources();
        Drawable d = res.getDrawable(navItem[position]);
        Drawable[] drawable = new Drawable[]{d};
        return new LayerDrawable(drawable);
    }
}
