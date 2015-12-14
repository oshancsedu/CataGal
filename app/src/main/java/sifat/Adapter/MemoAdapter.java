package sifat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.iangclifton.android.floatlabel.FloatLabel;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import ca.barrenechea.widget.recyclerview.decoration.StickyHeaderAdapter;
import sifat.Domain.MemoProductInfo;
import sifat.Domain.ProductCommonInfo;
import sifat.Provider.ProductInfoProvider;
import sifat.catagal.R;

import static sifat.Provider.ProviderSelector.getMyProvider;
import static sifat.Utilities.CommonUtilities.getTwoDecimal;

/**
 * Created by sifat on 11/17/2015.
 */
public class MemoAdapter extends RecyclerView.Adapter<MemoAdapter.ViewHolder> implements
        StickyHeaderAdapter<MemoAdapter.HeaderHolder> {

    public static ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    public static int totalItemAdded;
    public static double totalCost;
    public static ArrayList<MemoProductInfo> memoProductInfos = new ArrayList<>();
    static Context context;
    private static ArrayList<ProductCommonInfo> commonInfos = new ArrayList<>();
    private static TextView tvTotalCost, tvItemAdded;
    private LayoutInflater mInflater;
    private ProductInfoProvider provider;

    public MemoAdapter(Context context, TextView tvTotalCost, TextView tvItemAdded) {
        provider = getMyProvider(context);
        memoProductInfos = provider.getProductMemoInfo();
        commonInfos = provider.getCommonInfo();
        addedProduct = provider.getAddedProduct();
        totalCost = provider.getTotalCost();
        totalItemAdded = provider.getTotalItemAdded();
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.tvItemAdded = tvItemAdded;
        this.tvTotalCost = tvTotalCost;
        updateItemOrdered();
        updateTotalCost();
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

    private static void updateTotalCost() {
        totalCost = getTwoDecimal(totalCost);
        tvTotalCost.setText("Total Order: " + totalCost + " tk");
    }

    private static void updateItemOrdered() {
        tvItemAdded.setText("Total Item Ordered " + totalItemAdded);
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
        viewHolder.etCarton.setLabel(memoProductInfos.get(i).getSellingUnit());
        viewHolder.etCarton.getEditText().setHint(memoProductInfos.get(i).getSellingUnit());
        if (memoProductInfos.get(i).getCostPerPack() == memoProductInfos.get(i).getCostPerUnit()) {
            viewHolder.etPacket.setVisibility(View.GONE);
        } else
            viewHolder.etPacket.setVisibility(View.VISIBLE);

        viewHolder.etComment.setText(memoProductInfos.get(i).getComment());

        if (memoProductInfos.get(i).getCarton() == 0)
            viewHolder.etCarton.setText("");
        else
            viewHolder.etCarton.setText("" + memoProductInfos.get(i).getCarton());

        if (memoProductInfos.get(i).getPacket() == 0)
            viewHolder.etPacket.setText("");
        else
            viewHolder.etPacket.setText("" + memoProductInfos.get(i).getPacket());


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
        return commonInfos.get((int) headerId - 1).getHeader();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, TextWatcher {

        public TextView tvPackSize, tvContainer, tvQuantity, tvPrice;
        public CircularImageView circularImageView;
        public FloatLabel etCarton, etComment, etPacket;
        public Button btCalculate, btAddItem, btRemoveItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPackSize = (TextView) itemView.findViewById(R.id.tvPackSize);
            tvContainer = (TextView) itemView.findViewById(R.id.tvContainer);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            etCarton = (FloatLabel) itemView.findViewById(R.id.etCarton);
            etPacket = (FloatLabel) itemView.findViewById(R.id.etPacket);
            etComment = (FloatLabel) itemView.findViewById(R.id.etComment);
            etComment.getEditText().addTextChangedListener(this);
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

            try {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etCarton.getEditText().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }

            try {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etPacket.getEditText().getWindowToken(), 0);
            } catch (Exception e) {
                // TODO: handle exception
            }

            String temp_Carton = etCarton.getEditText().getText().toString();
            String temp_Packet = etPacket.getEditText().getText().toString();
            int i = getPosition();
            boolean flag = memoProductInfos.get(i).isAdded();
            //IF The product has already been added.Subtract it's price from the total price
            if (flag)
                totalCost = totalCost - memoProductInfos.get(i).getCost();

            if (temp_Carton == null || temp_Carton.equalsIgnoreCase("")) {
                if (memoProductInfos.get(i).getCarton() == 0)
                    memoProductInfos.get(i).setCarton(0);
                memoProductInfos.get(i).setCost(0.0);
            } else {
                int carton = Integer.parseInt(temp_Carton);
                memoProductInfos.get(i).setCarton(carton);
                memoProductInfos.get(i).setCost(memoProductInfos.get(i).getCostPerUnit() * carton);
            }

            if (temp_Packet == null || temp_Packet.equalsIgnoreCase("")) {
                if (memoProductInfos.get(i).getPacket() == 0)
                    memoProductInfos.get(i).setPacket(0);
            } else {
                int packet = Integer.parseInt(temp_Packet);
                memoProductInfos.get(i).setPacket(packet);
                memoProductInfos.get(i).setCost(memoProductInfos.get(i).getCost() + (memoProductInfos.get(i).getCostPerPack() * packet * 1.0));
            }

            //IF The product has already been added.Add it's price with the total price
            if (flag) {
                totalCost = totalCost + memoProductInfos.get(i).getCost();
                updateTotalCost();
            }
            MemoAdapter.setCosttv(tvPrice, i);
        }

        public void addToBucket() {
            int i = getPosition();
            //showToast(context, "" + addedProduct.indexOf(memoProductInfos.get(i)));
            if (addedProduct.indexOf(memoProductInfos.get(i)) == -1) {
                totalCost = totalCost + memoProductInfos.get(i).getCost();
                updateTotalCost();
                memoProductInfos.get(i).setIsAdded(true);
                addedProduct.add(memoProductInfos.get(i));
                totalItemAdded++;
                updateItemOrdered();
                btnToggle(!memoProductInfos.get(i).isAdded(), btAddItem, btRemoveItem);
            }
        }

        public void removeFromBucket() {
            int i = getPosition();
            //showToast(context, "" + addedProduct.indexOf(memoProductInfos.get(i)));
            if (addedProduct.indexOf(memoProductInfos.get(i)) != -1) {
                totalCost = totalCost - memoProductInfos.get(i).getCost();
                updateTotalCost();
                memoProductInfos.get(i).setIsAdded(false);
                addedProduct.remove(memoProductInfos.get(i));
                totalItemAdded--;
                updateItemOrdered();
                btnToggle(!memoProductInfos.get(i).isAdded(), btAddItem, btRemoveItem);
            }
        }


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int i = getPosition();
            memoProductInfos.get(i).setComment(s.toString());
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
