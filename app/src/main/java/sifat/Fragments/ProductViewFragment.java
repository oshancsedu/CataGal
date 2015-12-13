package sifat.Fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import sifat.catagal.R;

public class ProductViewFragment extends Fragment {

    private int bgRes;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bgRes = getArguments().getInt("data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guide, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = (ImageView) getView().findViewById(R.id.image);
        /*InputStream ims = null;
        try {
            ims = getActivity().getAssets().open(bgRes+".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        imageView.setImageDrawable(getDrawable("p" + bgRes));
    }

    public Drawable getDrawable(String name) {
        Log.e("Tag", name);
        int resourceId = getActivity().getResources().getIdentifier(name, "drawable", getActivity().getPackageName());
        return this.getResources().getDrawable(resourceId);
    }
}
