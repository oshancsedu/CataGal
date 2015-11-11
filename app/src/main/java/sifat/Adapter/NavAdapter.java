package sifat.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sifat.Domain.NavItem;
import sifat.catagal.R;

/**
 * Created by Edwin on 28/02/2015.
 */
public class NavAdapter  extends RecyclerView.Adapter<NavAdapter.ViewHolder> {

    List<NavItem> mItems;

    public NavAdapter() {
        super();
        mItems = new ArrayList<NavItem>();
        NavItem species = new NavItem();
        species.setName("Amur Leopard");
        species.setThumbnail(R.drawable.leopard);
        mItems.add(species);

        species = new NavItem();
        species.setName("Black Rhino");
        species.setThumbnail(R.drawable.rhino);
        mItems.add(species);

        species = new NavItem();
        species.setName("Orangutan");
        species.setThumbnail(R.drawable.orangutan);
        mItems.add(species);

        species = new NavItem();
        species.setName("Sea Lions");
        species.setThumbnail(R.drawable.seali);
        mItems.add(species);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NavItem nature = mItems.get(i);
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvspecies;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
        }
    }
}
