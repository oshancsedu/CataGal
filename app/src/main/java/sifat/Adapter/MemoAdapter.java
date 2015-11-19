package sifat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.iangclifton.android.floatlabel.FloatLabel;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import sifat.Domain.MemoProductInfo;
import sifat.Provider.BiscuitInfoProvider;
import sifat.catagal.R;

import static sifat.Utilities.CommonUtilities.showToast;

/**
 * Created by sifat on 11/17/2015.
 */
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> implements
        StickyHeaderAdapter<MemoAdapter.HeaderHolder> {

    static Context context;
    private static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    private static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private static ArrayList<String> headers = new ArrayList<>();
    private static TextView tvTotalCost, tvItemAdded;
    private static int totalItemAdded, totalCost;
    private LayoutInflater mInflater;
    private BiscuitInfoProvider provider;

    public MemoAdapter(Context context, TextView tvTotalCost, TextView tvItemAdded) {
        provider = BiscuitInfoProvider.getProvider();
        memoProductInfos = provider.getProductMemoInfo();
        headers = provider.getHeader();
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.tvItemAdded = tvItemAdded;
        this.tvTotalCost = tvTotalCost;
        totalCost = 0;
        totalItemAdded = 0;
    }

    public static void setCosttv(TextView tvPrice, int i) {
        if (memoProductInfos.get(i).getCost() == 0)
            tvPrice.setText("1 " + memoProductInfos.get(i).getSellingUnit() + " : " + memoProductInfos.get(i).getCostPerUnit() + " tk");
        else
            tvPrice.setText("Cost: " + memoProductInfos.get(i).getCost() + " tk");
    }

    public static void btnToggle(boolean flag, Button btAddItem, Button btRemoveItem) {
        if (flag) {
            btRemoveItem.setVisibility(View.GONE);
            btAddItem.setVisibility(View.VISIBLE);
        } else {
            btRemoveItem.setVisibility(View.VISIBLE);
            btAddItem.setVisibility(View.GONE);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.memo_gen_single_product, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.i("recycler", "onBindViewHolder");
        btnToggle(!memoProductInfos.get(i).isAdded(), viewHolder.btAddItem, viewHolder.btRemoveItem);
        viewHolder.tvPackSize.setText(memoProductInfos.get(i).getProductSize());
        viewHolder.tvContainer.setText("Unit: " + memoProductInfos.get(i).getSellingUnit());
        viewHolder.tvQuantity.setText("Packing: " + memoProductInfos.get(i).getPacking());
        setCosttv(viewHolder.tvPrice, i);
        if (memoProductInfos.get(i).getQuantity() == 0)
        viewHolder.etAmount.setText("");
        else
            viewHolder.etAmount.setText("" + memoProductInfos.get(i).getQuantity());
    }

    @Override
    public int getItemCount() {
        return memoProductInfos.size();
    }

    @Override
    public long getHeaderId(int position) {

        Log.i("recycler", "getHeaderid");
        return (long) memoProductInfos.get(position).getHeader();
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

        public TextView tvPackSize, tvContainer, tvQuantity, tvPrice;
        public CircularImageView circularImageView;
        public FloatLabel etAmount;
        public Button btCalculate, btAddItem, btRemoveItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPackSize = (TextView) itemView.findViewById(R.id.tvPackSize);
            tvContainer = (TextView) itemView.findViewById(R.id.tvContainer);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            etAmount = (FloatLabel) itemView.findViewById(R.id.etAmount);
            btCalculate = (Button) itemView.findViewById(R.id.btCalculate);
            btCalculate.setOnClickListener(this);
            btAddItem = (Button) itemView.findViewById(R.id.btAddItem);
            btAddItem.setOnClickListener(this);
            btRemoveItem = (Button) itemView.findViewById(R.id.btRemoveItem);
            btRemoveItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btAddItem)
                addToBucket();
            else if (v.getId() == R.id.btCalculate)
                calculatePrice();
            else if (v.getId() == R.id.btRemoveItem)
                removeFromBucket();
        }


        public void calculatePrice() {
            String temp_quantity = etAmount.getEditText().getText().toString();
            //showToast(context,"q :"+temp_quantity );
            int i = getPosition();
            if (temp_quantity == null || temp_quantity.equalsIgnoreCase("")) {
                if (memoProductInfos.get(i).getQuantity() == 0)
                showToast(context, "Enter Amount");
                memoProductInfos.get(i).setQuantity(0);
                memoProductInfos.get(i).setCost(0);
            } else {
                int quantity = Integer.parseInt(temp_quantity);
                memoProductInfos.get(i).setQuantity(quantity);
                memoProductInfos.get(i).setCost(memoProductInfos.get(i).getCostPerUnit() * quantity);
            }
            MemoAdapter.setCosttv(tvPrice, i);
        }

        public void addToBucket() {
            int i = getPosition();
            showToast(context, "" + addedProduct.indexOf(memoProductInfos.get(i)));
            if (addedProduct.indexOf(memoProductInfos.get(i)) == -1) {
                memoProductInfos.get(i).setIsAdded(true);
                addedProduct.add(memoProductInfos.get(i));
                tvItemAdded.setText("Total Item Ordered " + addedProduct.size());
                btnToggle(!memoProductInfos.get(i).isAdded(), btAddItem, btRemoveItem);
            }
        }

        public void removeFromBucket() {
            int i = getPosition();
            showToast(context, "" + addedProduct.indexOf(memoProductInfos.get(i)));
            if (addedProduct.indexOf(memoProductInfos.get(i)) != -1) {
                memoProductInfos.get(i).setIsAdded(false);
                addedProduct.remove(memoProductInfos.get(i));
                tvItemAdded.setText("Total Item Ordered " + addedProduct.size());
                btnToggle(!memoProductInfos.get(i).isAdded(), btAddItem, btRemoveItem);
            }
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
