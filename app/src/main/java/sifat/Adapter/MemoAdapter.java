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
import sifat.Domain.ProductInfo;
import sifat.Provider.BiscuitInfoProvider;
import sifat.catagal.R;

import static sifat.Utilities.CommonUtilities.showToast;
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
        viewHolder.tvContainer.setText(productInfos.get(i).getConatiner());
        viewHolder.tvQuantity.setText(productInfos.get(i).getQuantity());
        if (!productInfos.get(i).getSize().equalsIgnoreCase("Tin"))
            viewHolder.tvPrice.setText(productInfos.get(i).getMrp1Title() + " : " + productInfos.get(i).getMrp1() + " tk");
        else
            viewHolder.tvPrice.setText(productInfos.get(i).getMrp2Title() + " : " + productInfos.get(i).getMrp2() + " tk");
        viewHolder.etAmount.setText("");
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

        public TextView tvPackSize, tvContainer, tvQuantity, tvPrice;
        public CircularImageView circularImageView;
        public FloatLabel etAmount;
        public Button btCalculate, btSendMemo;

        public ViewHolder(View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            tvPackSize = (TextView) itemView.findViewById(R.id.tvPackSize);
            tvContainer = (TextView) itemView.findViewById(R.id.tvContainer);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvQuantity);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
            etAmount = (FloatLabel) itemView.findViewById(R.id.etAmount);
            btCalculate = (Button) itemView.findViewById(R.id.btCalculate);
            btCalculate.setOnClickListener(this);
            btSendMemo = (Button) itemView.findViewById(R.id.btSendMemo);
            btSendMemo.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btSendMemo)
                sendMemoInfo();
            else if (v.getId() == R.id.btCalculate)
                calculatePrice();
        }


        public void calculatePrice() {
            String temp_quantity = etAmount.getEditText().getText().toString();
            //showToast(context,"q :"+temp_quantity );
            if (temp_quantity == null || temp_quantity.equalsIgnoreCase("")) {
                showToast(context, "Enter Amount");
            } else {
                int quantity = Integer.parseInt(temp_quantity), i = getPosition();
                if (!productInfos.get(i).getSize().equalsIgnoreCase("Tin"))
                    tvPrice.setText("Cost: " + productInfos.get(i).getMrp1() * quantity + " tk");
                else
                    tvPrice.setText("Cost: " + productInfos.get(i).getMrp2() * quantity + " tk");
            }
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
