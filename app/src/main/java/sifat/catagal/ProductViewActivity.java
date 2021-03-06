package sifat.catagal;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Lists;

import java.util.List;

import github.chenupt.dragtoplayout.DragTopLayout;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerModelManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;
import sifat.Controller.ServerCommunicator;
import sifat.Domain.ProductCommonInfo;
import sifat.Domain.ProductInfo;
import sifat.Fragments.ProductViewFragment;

import static sifat.Utilities.CommonUtilities.SINGLE_PRODUCT_COMMON_INFO;
import static sifat.Utilities.CommonUtilities.SINGLE_PRODUCT_DETAIL;

/**
 * Created by sifat on 11/11/2015.
 */
public class ProductViewActivity extends ActionBarActivity {

    ScrollerViewPager viewPager;
    private ImageView ivBanner;
    private ProductInfo productInfo;
    private ProductCommonInfo productCommonInfo;
    private TextView tvProductName, tvSize, tvUnit, tvValidity, tvMrp1, tvMrp2, tvUnitTitle, tvMrp1Title, tvMrp2Title, tvIngredient;
    private DragTopLayout dragTopLayout;

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
        Intent intent = this.getIntent();
        Bundle product=intent.getExtras();
        productInfo = (ProductInfo) product.getSerializable(SINGLE_PRODUCT_DETAIL);
        productCommonInfo = (ProductCommonInfo) product.getSerializable(SINGLE_PRODUCT_COMMON_INFO);
        dragTopLayout = (DragTopLayout) findViewById(R.id.drag_layout);
        //dragTopLayout.setCollapseOffset(60);
        //dragTopLayout.closeTopView(true);

        tvSize=(TextView)findViewById(R.id.tvSize);
        tvSize.setText(productInfo.getSize());

        tvProductName=(TextView)findViewById(R.id.tvHeader);
        tvProductName.setText("Product Info");
        //tvProductName.setVisibility(View.GONE);

        tvUnit=(TextView)findViewById(R.id.tvUnit);
        tvUnit.setText(productInfo.getQuantity());
        tvValidity=(TextView)findViewById(R.id.tvValidity);
        tvValidity.setText(productInfo.getValidity());
        tvMrp1=(TextView)findViewById(R.id.tvMRP1);
        tvMrp1.setText(productInfo.getMrp1() + " tk");
        tvMrp2=(TextView)findViewById(R.id.tvMRP2);
        tvMrp2.setText(productInfo.getMrp2() + " tk");
        tvUnitTitle=(TextView)findViewById(R.id.tvUnitTitle);
        tvUnitTitle.setText(productInfo.getConatiner());
        tvMrp1Title=(TextView)findViewById(R.id.tvMRP1Title);
        tvMrp1Title.setText(productInfo.getMrp1Title());
        tvMrp2Title=(TextView)findViewById(R.id.tvMRP2Title);
        tvMrp2Title.setText(productInfo.getMrp2Title());
        tvIngredient = (TextView) findViewById(R.id.tvIngredient);
        tvIngredient.setText(productCommonInfo.getIngredient());

        /*InputStream ims = null;
        try {
            ims = getAssets().open(productCommonInfo.getBanner()+".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            ivBanner.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ivBanner.setImageDrawable(getDrawable(productCommonInfo.getBanner()));
    }

    private List<String> getTitles(){
        return Lists.newArrayList("1", "2", "3");
    }

    private List<Integer> getBgRes(){
        return productInfo.getProduct_images();
    }

    public Drawable getDrawable(String name) {
        Log.i("RES", name);
        int resourceId = this.getResources().getIdentifier(name, "drawable", this.getPackageName());
        return this.getResources().getDrawable(resourceId);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_memo) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_update) {
            ServerCommunicator serverCommunicator = new ServerCommunicator(this);
            serverCommunicator.getProductInfo();
        }

        return super.onOptionsItemSelected(item);
    }
}
