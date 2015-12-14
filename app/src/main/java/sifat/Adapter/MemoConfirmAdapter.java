package sifat.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sifat.Domain.MemoProductInfo;
import sifat.catagal.R;

/**
 * Created by sifat on 11/19/2015.
 */
public class MemoConfirmAdapter extends RecyclerView.Adapter<MemoConfirmAdapter.MemoDialogHolder> {

    public ArrayList<MemoProductInfo> addedProduct = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public MemoConfirmAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(this.context);
        addedProduct = MemoAdapter.addedProduct;
    }

    @Override
    public MemoDialogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.confirm_memo_single_item, parent, false);
        MemoDialogHolder holder = new MemoDialogHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MemoDialogHolder holder, int position) {
        holder.tvProductName.setText(addedProduct.get(position).getProductName());
        holder.tvSize.setText(addedProduct.get(position).getProductSize());
        holder.tvCost.setText("Cost: " + addedProduct.get(position).getCost());
        holder.tvQuantity.setText("Quantity: " + addedProduct.get(position).getCarton() + " " +
                addedProduct.get(position).getSellingUnit() + "\nand\n" + addedProduct.get(position).getPacket() + " Packet");
        holder.tvComment.setText("Comment: " + addedProduct.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return addedProduct.size();
    }

    class MemoDialogHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvSize, tvQuantity, tvCost, tvComment;

        public MemoDialogHolder(View itemView) {
            super(itemView);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvSize = (TextView) itemView.findViewById(R.id.tvSize);
            tvQuantity = (TextView) itemView.findViewById(R.id.tvProductQuantity);
            tvCost = (TextView) itemView.findViewById(R.id.tvCost);
            tvComment = (TextView) itemView.findViewById(R.id.tvComment);
        }
    }
}