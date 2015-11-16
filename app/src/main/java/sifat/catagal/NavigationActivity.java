package sifat.catagal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lukedeighton.wheelview.WheelView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import sifat.Adapter.MaterialColorAdapter;
import sifat.Utilities.MaterialColor;

/**
 * Created by sifat on 11/13/2015.
 */


public class NavigationActivity extends Activity {

    private static final int ITEM_COUNT = 4;
    static ArrayList<Integer> navItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        final WheelView wheelView = (WheelView) findViewById(R.id.wheelview);
        navItems.add(R.drawable.biscuit);
        navItems.add(R.drawable.candey);
        navItems.add(R.drawable.shanks);
        navItems.add(R.drawable.flour);

        //create data for the adapter
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(ITEM_COUNT);
        for (int i = 0; i < ITEM_COUNT; i++) {
            Map.Entry<String, Integer> entry = MaterialColor.random(this, "\\D*_500$");
            //Log.i("Wheel",entry.toString());
            entries.add(entry);
        }

        //populate the adapter, that knows how to draw each item (as you would do with a ListAdapter)
        wheelView.setAdapter(new MaterialColorAdapter(entries, this, navItems));

        //a listener for receiving a callback for when the item closest to the selection angle changes
        /*wheelView.setOnWheelItemSelectedListener(new WheelView.OnWheelItemSelectListener() {
            @Override
            public void onWheelItemSelected(WheelView parent, Drawable itemDrawable, int position) {
                //get the item at this position
                Map.Entry<String, Integer> selectedEntry = ((MaterialColorAdapter) parent.getAdapter()).getItem(position);
                parent.setSelectionColor(getContrastColor(selectedEntry));
            }
        });*/

        wheelView.setOnWheelItemClickListener(new WheelView.OnWheelItemClickListener() {
            @Override
            public void onWheelItemClick(WheelView parent, int position, boolean isSelected) {
                String msg = String.valueOf(position) + " " + isSelected;
                //Toast.makeText(NavigationActivity.this, msg, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NavigationActivity.this, ProductList.class);
                startActivity(intent);
            }
        });

        //initialise the selection drawable with the first contrast color
        //wheelView.setSelectionColor(getContrastColor(entries.get(0)));

        /*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //wheelView.setSelectionAngle(-wheelView.getAngleForPosition(5));
                wheelView.setMidSelected();
            }
        }, 3000); */
    }

    //get the materials darker contrast
    /*private int getContrastColor(Map.Entry<String, Integer> entry) {
        String colorName = MaterialColor.getColorName(entry);
        return MaterialColor.getContrastColor(colorName);
    }*/
}