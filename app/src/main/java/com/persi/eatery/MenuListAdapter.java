package com.persi.eatery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Persi on 06-02-2018.
 */

public class MenuListAdapter extends BaseAdapter{

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

    private ArrayList mData = new ArrayList();
    private LayoutInflater mInflater;
    private TreeSet mSeparatorsSet = new TreeSet();
    private Context mContext;
    /*private TextView mFoodname;
    private ImageView mVegnonVeg;
    private TextView mFooddetail;
    private TextView mFoodPrice;*/

    public MenuListAdapter(ArrayList data, Context context,TreeSet separatorsSet) {
        mData = data;
        mContext = context;
        mSeparatorsSet=separatorsSet;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    /*public void addItem(final Food_Item item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSeparatorItem(final String item) {
        mData.add(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        notifyDataSetChanged();
    }*/

    @Override
    public int getItemViewType(int position) {
        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Food_Item getItem(int position) {
        return (Food_Item) mData.get(position);
    }
    public String getItemSep(int position) {
        return  mData.get(position).toString();
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        ViewHolderSep holderSep=null;

        int type = getItemViewType(position);
        System.out.println("getView " + position + " " + convertView + " type = " + type);
        if (convertView == null) {
            //holder = new ViewHolder();
            switch (type) {
                case TYPE_ITEM:
                    holder = new ViewHolder();
                    convertView = mInflater.inflate(R.layout.menu_list_view_row, null);
                    //holder.textView = (TextView)convertView.findViewById(R.id.text);
                    holder.mFoodname=convertView.findViewById(R.id.food_name);
                    holder.mFooddetail=convertView.findViewById(R.id.food_detail);
                    holder.mFoodPrice=convertView.findViewById(R.id.food_price);
                    holder.mVegnonVeg=convertView.findViewById(R.id.veg_nonveg);
                    holder.mAddButton=convertView.findViewById(R.id.add_to_cart_button);
                    holder.mDeleteButton=convertView.findViewById(R.id.remove_from_cart_button);
                    holder.mQuantity=convertView.findViewById(R.id.food_quantity);

                    /*holder.mFoodname.setText(getItem(position).getName());
                    holder.mVegnonVeg.setImageResource(getItem(position).getVegnonVeg());
                    holder.mFoodPrice.setText(getItem(position).getPrice().toString());
                    holder.mFooddetail.setText(getItem(position).getFoodDetail());*/
                    convertView.setTag(holder);
                    break;
                case TYPE_SEPARATOR:
                    holderSep=new ViewHolderSep();
                    convertView = mInflater.inflate(R.layout.food_category, null);
                    holderSep.mFoodSeparator = convertView.findViewById(R.id.food_category);
                    //holderSep.mFoodSeparator.setText(getItemSep(position));
                    convertView.setTag(holderSep);
                    break;
            }
            //convertView.setTag(holder);
        } else {
            switch (type){
                case TYPE_ITEM:
                    holder = (ViewHolder)convertView.getTag();
                    break;
                case TYPE_SEPARATOR:
                    holderSep=(ViewHolderSep)convertView.getTag();
                    break;

            }

        }
       // holder.textView.setText(mData.get(position));
        switch (type){
            case TYPE_ITEM:
                holder.mFoodname.setText(getItem(position).getName());
                holder.mVegnonVeg.setImageResource(getItem(position).getVegnonVeg());
                holder.mFoodPrice.setText(getItem(position).getPrice().toString());
                holder.mFooddetail.setText(getItem(position).getFoodDetail());
                holder.mAddButton.setImageResource(R.drawable.ic_plus);
                holder.mDeleteButton.setImageResource(R.drawable.ic_minus);
                holder.mQuantity.setText("0");
                break;
            case TYPE_SEPARATOR:
                holderSep.mFoodSeparator.setText(getItemSep(position));
                break;

        }
        return convertView;
    }

    static class ViewHolder {
         TextView mFoodname;
         ImageView mVegnonVeg;
         TextView mFooddetail;
         TextView mFoodPrice;
         ImageView mAddButton;
         ImageView mDeleteButton;
         TextView mQuantity;
    }
    static class ViewHolderSep{
        TextView mFoodSeparator;
    }

}





