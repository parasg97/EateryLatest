package com.persi.eatery;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tonyvu.sc.exception.ProductNotFoundException;
import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.model.Saleable;
import com.android.tonyvu.sc.util.CartHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Persi on 06-02-2018.
 */

public class MenuListAdapter extends BaseAdapter{

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_SEPARATOR = 1;
    private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;
    private Cart mcart;
    private ArrayList mData = new ArrayList();
    private LayoutInflater mInflater;
    private TreeSet mSeparatorsSet = new TreeSet();
    private Context mContext;


    public MenuListAdapter(ArrayList data, Context context,TreeSet separatorsSet) {
        mData = data;
        mContext = context;
        mSeparatorsSet=separatorsSet;
        mcart = CartHelper.getCart();
        mcart.getProducts();
        //Log.d("Eatery","ConstructorMenuListAdapter\n"+mcart.toString());
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder= null;
        ViewHolderSep holderSep=null;

        int type = getItemViewType(position);
        System.out.println("getView " + position + " " + convertView + " type = " + type);
        if (convertView == null) {
            switch (type) {
                case TYPE_ITEM:
                    holder = new ViewHolder();
                    convertView = mInflater.inflate(R.layout.menu_list_view_row, null);
                    holder.mFoodname=convertView.findViewById(R.id.food_name);
                    holder.mFooddetail=convertView.findViewById(R.id.food_detail);
                    holder.mFoodPrice=convertView.findViewById(R.id.food_price);
                    holder.mVegnonVeg=convertView.findViewById(R.id.veg_nonveg);
                    holder.mAddButton=convertView.findViewById(R.id.add_to_cart_button);
                    holder.mDeleteButton=convertView.findViewById(R.id.remove_from_cart_button);
                    holder.mQuantity=convertView.findViewById(R.id.food_quantity);
                    convertView.setTag(holder);
                    break;
                case TYPE_SEPARATOR:
                    holderSep=new ViewHolderSep();
                    convertView = mInflater.inflate(R.layout.food_category, null);
                    holderSep.mFoodSeparator = convertView.findViewById(R.id.food_category);
                    convertView.setTag(holderSep);
                    break;
            }

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

        switch (type){
            case TYPE_ITEM:
                holder.mFoodname.setText(getItem(position).getName());
                holder.mVegnonVeg.setImageResource(getItem(position).getVegnonVeg());
                holder.mFoodPrice.setText(getItem(position).getPrice().toString());
                holder.mFooddetail.setText(getItem(position).getFoodDetail());
                holder.mAddButton.setImageResource(R.drawable.ic_add2);
                holder.mDeleteButton.setImageResource(R.drawable.ic_minus2);
                try {//Log.d("Eatery","try"+String.valueOf(mcart.getQuantity(getItem(position))));
                    holder.mQuantity.setText(String.valueOf(mcart.getQuantity(getItem(position))));
                }catch (ProductNotFoundException e){
                    holder.mQuantity.setText("0");
                    //Log.d("Eatery","mQuantitySandP"+e.toString());
                }

                holder.mAddButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            mcart.update(getItem(position),mcart.getQuantity(getItem(position))+1);

                            //Log.d("Eatery","addButtonupdate");
                            //Log.d("Eatery",getItem(position).toString());

                        }catch (ProductNotFoundException e){
                            mcart.add(getItem(position),1);
                            //Log.d("Eatery","addButtonupadd\n");
                            //Log.d("Eatery",getItem(position).toString());
                        }
                        //Log.d("Eatery","addButtonCartinfo"+mcart.toString());
                        View parent=(View)v.getParent();
                        TextView textView=(TextView) parent.findViewById(R.id.food_quantity);
                        if(textView==null)
                            Log.d("Eatery","damn");
                        else
                        textView.setText(String.valueOf(mcart.getQuantity(getItem(position))));
                    }
                });
                holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try{
                            mcart.remove(getItem(position),1);
                            //Log.d("Eatery","removeButtonCartinfo"+mcart.toString());
                        }catch (ProductNotFoundException e){
                            ////Log.d("Eatery",e.toString());
                        }
                        View parent=(View)v.getParent();
                        TextView textView=(TextView) parent.findViewById(R.id.food_quantity);
                        try{
                            textView.setText(String.valueOf(mcart.getQuantity(getItem(position))));
                        }catch (ProductNotFoundException e){
                            textView.setText("0");
                        }

                    }
                });

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





