package sifat.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

import com.lukedeighton.wheelview.adapter.WheelArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sifat on 11/13/2015.
 */
public class MaterialColorAdapter extends WheelArrayAdapter<Map.Entry<String, Integer>> {

    Context context;
    ArrayList<Integer> navItem = new ArrayList<>();

    public MaterialColorAdapter(List<Map.Entry<String, Integer>> entries, Context context, ArrayList<Integer> navItem) {
        super(entries);
        this.context = context;
        this.navItem = navItem;
        Log.i("Wheel", "Size :" + navItem.size());
    }

    @Override
    public Drawable getDrawable(int position) {
        Log.i("Wheel", "Position: " + position);
        Resources res = context.getResources();
        Drawable d = res.getDrawable(navItem.get(position));
        Drawable[] drawable = new Drawable[]{d};
        return new LayerDrawable(drawable);
    }
}
