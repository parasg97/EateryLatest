package com.persi.eatery;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by Persi on 29-01-2018.
 */

public class HotelListViewAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<Hotel_Object> mArrayList=new ArrayList<Hotel_Object>();
    private LayoutInflater mLayoutInflater;
    private TextView mHotelName;
    private ImageView mHotelImage;
    private TextView mLine1;
    private TextView mLine2;

    public HotelListViewAdapter(Context context, ArrayList<Hotel_Object> arrayList) {
        mContext = context;
        mArrayList = arrayList;
        mLayoutInflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=mLayoutInflater.inflate(R.layout.hotel_list_view_row,null);
        mHotelName=convertView.findViewById(R.id.hotel_name);
        mHotelImage=convertView.findViewById(R.id.row_hotel_image);
        mLine1=convertView.findViewById(R.id.row_1);
        mLine2=convertView.findViewById(R.id.row_2);
        mHotelName.setText(mArrayList.get(position).getHotelName());
        mHotelImage.setImageResource(mArrayList.get(position).getHotelImage());
        mLine1.setText(mArrayList.get(position).getFirstrow());
        mLine2.setText(mArrayList.get(position).getSecondrow());
        return convertView;
    }
}
