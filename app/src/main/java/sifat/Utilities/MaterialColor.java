package sifat.Utilities;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import sifat.catagal.R;

public class MaterialColor {
    private static Random sRandom = new Random();
    private static HashMap<String, Integer> sMaterialHashMap;
    private static Pattern sColorPattern = Pattern.compile("_[aA]?+\\d+");

    private static HashMap<String, Integer> getMaterialColors(Context context) {
        Field[] fields = R.color.class.getFields();
        HashMap<String, Integer> materialHashMap = new HashMap<String, Integer>(fields.length);
        for (Field field : fields) {
            String fieldName = field.getName(); //prone to errors but okay for a sample!
            if (fieldName.startsWith("abc") || fieldName.startsWith("material")) continue;

            try {
                int resId = field.getInt(null);
                //Log.i("Wheel","get Color "+fieldName+"-"+context.getResources().getColor(resId));
                materialHashMap.put(fieldName, context.getResources().getColor(resId));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        /*materialHashMap.put("green_900",-15903998);
        materialHashMap.put("green_900",-15903998);
        materialHashMap.put("green_900",-15903998);
        materialHashMap.put("green_900",-15903998);*/

        Log.i("Wheel", "Size : " + materialHashMap.size());

        /*for(int i=0;i<materialHashMap.size();i++)
        {
            Log.i("Wheel","Test Color: "+materialHashMap.get(i).toString());
        }*/
        return materialHashMap;
    }

    public static Map.Entry<String, Integer> random(Context context, String regex) {
        if (sMaterialHashMap == null) {
            sMaterialHashMap = getMaterialColors(context);
        }

        Log.i("Wheel", "Size2 : " + sMaterialHashMap.size());

        Pattern pattern = Pattern.compile(regex);
        List<Map.Entry<String, Integer>> materialColors = new ArrayList<Map.Entry<String, Integer>>();
        for (Map.Entry<String, Integer> entry : sMaterialHashMap.entrySet()) {
            if (!pattern.matcher(entry.getKey()).matches()) continue;
            Log.i("Wheel", "get Color " + entry.getKey() + "-" + entry.getValue());
            materialColors.add(entry);
        }

        //int rndIndex = sRandom.nextInt(materialColors.size());
        //"green_900",-15903998
        //Log.i("Wheel",)

        return materialColors.get(0);
    }

    /*public static int getContrastColor(String colourName) {
        return sMaterialHashMap.get(colourName + "_200");
    }*/

    /*public static String getColorName(Map.Entry<String, Integer> entry) {
        String color = entry.getKey();
        Matcher matcher = sColorPattern.matcher(color);
        if (matcher.find()) {
            return color.substring(0, matcher.start());
        }
        return null;
    }*/
}
