package sifat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import sifat.catagal.ProductViewActivity;
import sifat.catagal.R;

/**
 * Created by sifat on 11/12/2015.
 */
public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements
        StickyHeaderAdapter<ProductListAdapter.HeaderHolder> {

    private LayoutInflater mInflater;
    static Context context;

    public ProductListAdapter(Context context) {
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
        viewHolder.item.setText("Item " + i);
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    @Override
    public long getHeaderId(int position) {

        Log.i("recycler","getHeaderid");
        if(position>10 && position<22)
            return 10;
        else if (position>=22 && position<=30)
            return 22;
        else if(position>30)
            return 30;
        else return 0;
    }

    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        Log.i("recycler","onCreateHeaderViewHolder");
        final View view = mInflater.inflate(R.layout.product_list_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        Log.i("recycler","onBindHeaderViewHolder");
        viewholder.header.setText("Header " + getHeaderId(position));
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
            //Toast(this,"",Toast.LENGTH_SHORT).show();
            //Log.i("recycler","Item Clicked at "+getPosition());
            Toast.makeText(context, "Clicked at :"+getPosition(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ProductViewActivity.class);
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
