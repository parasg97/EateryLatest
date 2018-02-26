package com.persi.eatery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.tonyvu.sc.exception.ProductNotFoundException;
import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.model.Saleable;
import com.android.tonyvu.sc.util.CartHelper;

import java.util.ArrayList;

/**
 * Created by Shrey on 22-02-2018.
 */

public class CheckoutAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Saleable> mData;
    private LayoutInflater mLayoutInflater;
    private Cart mCart;


    public CheckoutAdapter(Context context) {
        //mData = arrayList;
        mContext = context;
        mLayoutInflater = (LayoutInflater.from(context));
        mCart = CartHelper.getCart();
        mData=new ArrayList<>(mCart.getProducts());

        //mCart.toString();
        //Log.d("Eatery","constructorCartListAdapter\n"+mCart.toString());
    }

    public void addItem(final Saleable item) {
        //mData.add(item);
        //mCart.getProducts().
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mCart.getItemWithQuantity().size();
    }

    @Override
    public Saleable getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        System.out.println("getView " + position + " " + convertView);
        CheckoutAdapter.ViewHolder holder = null;
        if (convertView == null) {
            holder = new CheckoutAdapter.ViewHolder();
            convertView = mLayoutInflater.inflate(R.layout.checkout_list_view_row, null);
            holder.mFoodname =(TextView)convertView.findViewById(R.id.name);
            holder.mFoodPrice=convertView.findViewById(R.id.price);
            holder.mQuantity=convertView.findViewById(R.id.quantity);
            convertView.setTag(holder);
        } else {
            holder = (CheckoutAdapter.ViewHolder)convertView.getTag();
        }
        holder.mFoodname.setText(mData.get(position).getName());
        holder.mFoodPrice.setText(mData.get(position).getPrice().toString());
        //Log.d("Eatery","wtfffffff"+String.valueOf(position));
        try {
            holder.mQuantity.setText(String.valueOf(mCart.getQuantity(getItem(position))));
        }catch (ProductNotFoundException e){
            holder.mQuantity.setText("0");
            Log.d("Eatery","Lol"+e.toString());
        }
        return convertView;
    }
    public static class ViewHolder {
        TextView mFoodname;
        TextView mFoodPrice;
        TextView mQuantity;
    }
}
