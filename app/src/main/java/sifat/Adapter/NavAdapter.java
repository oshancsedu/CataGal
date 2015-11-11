package sifat.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import sifat.Domain.NavItem;
import sifat.catagal.R;

/**
 * Created by sifat on 11/10/2015.
 */
public class NavAdapter extends BaseAdapter {

    private ArrayList<NavItem> navItems=new ArrayList<>();
    private Context context;
    private ViewHolder holder;
    public NavAdapter(Context context)
    {
        this.context=context;
        NavItem navItem= new NavItem(R.drawable.leopard,"BISCUIT");
        navItems.add(navItem);
        navItem= new NavItem(R.drawable.orangutan,"CANDY");
        navItems.add(navItem);
        navItem= new NavItem(R.drawable.panda,"OTHERs");
        navItems.add(navItem);
        navItem= new NavItem(R.drawable.rhino,"MEMO");
        navItems.add(navItem);
    }

    @Override
    public int getCount() {
        return navItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {


        if(view==null)
        {
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.single_nav_item,parent,false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        NavItem navItem = navItems.get(position);
        holder.ivOptionImage.setImageResource(navItem.getImage());

        return view;
    }

    private class ViewHolder
    {
        ImageView ivOptionImage;

        ViewHolder(View view)
        {
            ivOptionImage = (ImageView) view.findViewById(R.id.ivNavOption);
        }
    }
}
