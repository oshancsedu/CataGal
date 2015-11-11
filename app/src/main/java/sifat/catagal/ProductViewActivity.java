package sifat.catagal;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;
import sifat.Fragments.ProductViewFragment;

/**
 * Created by sifat on 11/11/2015.
 */
public class ProductViewActivity extends ActionBarActivity {

    ScrollerViewPager viewPager;
    private ImageView ivBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iteminfo);

        init();

        viewPager = (ScrollerViewPager) findViewById(R.id.view_pager);
        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        PagerModelManager manager = new PagerModelManager();
        manager.addCommonFragment(ProductViewFragment.class, getBgRes(), getTitles());
        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        viewPager.setAdapter(adapter);
        viewPager.fixScrollSpeed();

        // just set viewPager
        springIndicator.setViewPager(viewPager);

    }


    private void init()
    {
        ivBanner= (ImageView) findViewById(R.id.ivBanner);
        InputStream ims = null;
        try {
            ims = getAssets().open("banner.jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            ivBanner.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3");
    }

    private List<Integer> getBgRes(){
        return Lists.newArrayList(1, 2, 3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_about) {
            //Intent intent = new Intent(this, AboutActivity.class);
            //startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
