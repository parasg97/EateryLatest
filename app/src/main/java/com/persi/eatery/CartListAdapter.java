package com.persi.eatery;

import android.content.Context;
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

/**
 * Created by Persi on 11-02-2018.
 */

public class CartListAdapter extends BaseAdapter {


        private ArrayList<Saleable> mData;
        private LayoutInflater mLayoutInflater;
        private Cart mCart;


        public CartListAdapter(Context context, ArrayList<Saleable> arrayList) {
            //mData = arrayList;
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
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.cart_list_view_row, null);
                holder.mFoodname =(TextView)convertView.findViewById(R.id.food_name);
                holder.mFoodPrice=convertView.findViewById(R.id.food_price);
                holder.mAddButton=convertView.findViewById(R.id.add_to_cart_button);
                holder.mDeleteButton=convertView.findViewById(R.id.remove_from_cart_button);
                holder.mQuantity=convertView.findViewById(R.id.food_quantity);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            holder.mFoodname.setText(mData.get(position).getName());
            holder.mFoodPrice.setText(mData.get(position).getPrice().toString());
            holder.mAddButton.setImageResource(R.drawable.ic_add2);
            holder.mDeleteButton.setImageResource(R.drawable.ic_minus2);
            //Log.d("Eatery","wtfffffff"+String.valueOf(position));
            try {
                holder.mQuantity.setText(String.valueOf(mCart.getQuantity(getItem(position))));
            }catch (ProductNotFoundException e){
                holder.mQuantity.setText("0");
                Log.d("Eatery","Lol"+e.toString());
            }

            holder.mAddButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCart.add(getItem(position),1);
                    ////Log.d("Eatery",getItem(position).getName()+":"+String.valueOf(mCart.getQuantity(getItem(position))));
                    View parent=(View)v.getParent();
                    TextView textView=(TextView) parent.findViewById(R.id.food_quantity);
                    if(textView==null)
                        Log.d("Eatery","damn");
                    else
                        textView.setText(String.valueOf(mCart.getQuantity(getItem(position))));
                }
            });
            holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try{
                        mCart.remove(getItem(position),1);
                    }catch (ProductNotFoundException e){
                        //Log.d("Eatery",e.toString());
                    }
                    View parent=(View)v.getParent();
                    TextView textView=(TextView) parent.findViewById(R.id.food_quantity);
                    try{
                        textView.setText(String.valueOf(mCart.getQuantity(getItem(position))));
                    }catch (ProductNotFoundException e){
                        textView.setText("0");
                    }

                }
            });
            return convertView;
        }
    public static class ViewHolder {
        TextView mFoodname;
        TextView mFoodPrice;
        ImageView mAddButton;
        ImageView mDeleteButton;
        TextView mQuantity;
    }
    }
