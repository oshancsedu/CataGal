package sifat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Provider;
import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import sifat.Domain.ProductInfo;
import sifat.Provider.ProductInfoProvider;
import sifat.catagal.ProductViewActivity;
import sifat.catagal.R;

import static sifat.Utilities.CommonUtilities.*;

/**
 * Created by sifat on 11/12/2015.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements
        StickyHeaderAdapter<ProductListAdapter.HeaderHolder> {

    private LayoutInflater mInflater;
    static Context context;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private ProductInfoProvider provider;

    public ProductListAdapter(Context context) {
        provider=ProductInfoProvider.getProvider();
        productInfos = provider.getProductInfos();
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
        viewHolder.item.setText(productInfos.get(i).getName());
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
        Log.i("recycler", "onBindHeaderViewHolder");

        String header=getHeaderName(getHeaderId(position));

        viewholder.header.setText(header);
    }

    private String getHeaderName(long headerId) {

        if(headerId==1)
            return "Family Pack";
        else if(headerId==2)
            return "Regular Pack";
        else if(headerId==3)
            return "Mini Pack";
        else
            return "Tin";
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.tvProductName);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, ProductViewActivity.class);
            Bundle product=new Bundle();
            product.putSerializable(SINGLE_PRODUCT_DETAIL,productInfos.get(getPosition()));
            intent.putExtras(product);
            context.startActivity(intent);
        }
    }

    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;

        public HeaderHolder(View itemView) {
            super(itemView);
            header = (TextView) itemView;
        }
    }
}
