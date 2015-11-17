package sifat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import sifat.Domain.ProductInfo;
import sifat.Provider.BiscuitInfoProvider;
import sifat.catagal.R;

/**
 * Created by sifat on 11/17/2015.
 */
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> implements
        StickyHeaderAdapter<MemoAdapter.HeaderHolder> {

    static Context context;
    private static ArrayList<ProductInfo> productInfos = new ArrayList<>();
    private static ArrayList<String> headers = new ArrayList<>();
    private LayoutInflater mInflater;
    private BiscuitInfoProvider provider;

    public MemoAdapter(Context context) {
        provider = BiscuitInfoProvider.getProvider();
        productInfos = provider.getProductInfos();
        headers = provider.getHeader();
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.memo_gen_single_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.i("recycler", "onBindViewHolder");
        viewHolder.tvPackSize.setText(productInfos.get(i).getSize());


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
        Log.i("recycler", "onCreateHeaderViewHolder");
        final View view = mInflater.inflate(R.layout.product_list_header, parent, false);
        return new HeaderHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        String header = getHeaderName(getHeaderId(position));
        viewholder.header.setText(header);
    }

    private String getHeaderName(long headerId) {
        return headers.get((int) headerId - 1);
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvPackSize;

        public CircularImageView circularImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            tvPackSize = (TextView) itemView.findViewById(R.id.tvPackSize);

        }

        @Override
        public void onClick(View v) {
            sendMemoInfo();
            calculatePrice();
        }


        public void calculatePrice() {

        }

        public void sendMemoInfo() {

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
