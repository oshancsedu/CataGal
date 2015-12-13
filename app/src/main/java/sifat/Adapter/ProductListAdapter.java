package sifat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import sifat.Domain.ProductCommonInfo;
import sifat.Domain.ProductInfo;
import sifat.Provider.ProductInfoProvider;
import sifat.catagal.ProductViewActivity;
import sifat.catagal.R;

import static sifat.Provider.ProviderSelector.getMyProvider;
import static sifat.Utilities.CommonUtilities.SINGLE_PRODUCT_COMMON_INFO;
import static sifat.Utilities.CommonUtilities.SINGLE_PRODUCT_DETAIL;

/**
 * Created by sifat on 11/12/2015.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements
        StickyHeaderAdapter<ProductListAdapter.HeaderHolder> {

    static Context context;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
    private LayoutInflater mInflater;
    private ProductInfoProvider provider;

    public ProductListAdapter(Context context) {
        provider = getMyProvider(context);
        productInfos = provider.getProductInfos();
        commonInfos = provider.getCommonInfo();
        this.context=context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.product_list_single_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.i("recycler", "onBindViewHolder");
        viewHolder.item.setText(productInfos.get(i).getSize());

        /*InputStream ims = null;
        try {
            ims = context.getAssets().open(productInfos.get(i).getProduct_images().get(1) + ".jpg");
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            viewHolder.circularImageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        viewHolder.circularImageView.setImageDrawable(getDrawable("p" + productInfos.get(i).getProduct_images().get(1)));
    }

    public Drawable getDrawable(String name) {
        Log.e("Tag", name);
        int resourceId = context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        return context.getResources().getDrawable(resourceId);
    }

    @Override
    public int getItemCount() {
        return productInfos.size();
    }

    @Override
    public long getHeaderId(int position) {

        Log.i("recycler", "getHeaderid");
        return (long) productInfos.get(position).getHeader();
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        Log.i("recycler","onCreateHeaderViewHolder");
        final View view = mInflater.inflate(R.layout.product_list_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        String header=getHeaderName(getHeaderId(position));
        viewholder.header.setText(header);
    }

    private String getHeaderName(long headerId) {
        return commonInfos.get((int) headerId - 1).getHeader();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, RippleView.OnRippleCompleteListener {
        public TextView item;
        public RippleView rippleView;
        public CircularImageView circularImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.tvProductName);
            rippleView = (RippleView) itemView.findViewById(R.id.rvSingleItem);
            circularImageView = (CircularImageView) itemView.findViewById(R.id.civProductImage);
            rippleView.setOnRippleCompleteListener(this);
        }

        @Override
        public void onClick(View v) {
            changeActivity();
        }

        @Override
        public void onComplete(RippleView rippleView) {
            Log.i("Ripple", "Position " + getPosition());
            changeActivity();
        }

        public void changeActivity() {
            Intent intent = new Intent(context, ProductViewActivity.class);
            Bundle product = new Bundle();
            product.putSerializable(SINGLE_PRODUCT_DETAIL, productInfos.get(getPosition()));
            product.putSerializable(SINGLE_PRODUCT_COMMON_INFO, commonInfos.get(productInfos.get(getPosition()).getHeader() - 1));
            intent.putExtras(product);
            context.startActivity(intent);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView.findViewById(R.id.tvListHeader);
        }
    }
}
