package com.persi.eatery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeSet;

public class SandP extends AppCompatActivity {

    private ArrayList mData = new ArrayList();
    private TreeSet mSeparatorsSet = new TreeSet();
    private ListView mListView;
    private MenuListAdapter menuListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_p);
        mListView=findViewById(R.id.listView_sand);
        menuListAdapter=new MenuListAdapter(mData,getApplicationContext(),mSeparatorsSet);
        addSeparatorItem("Burerg");
        addItem(new Food_Item("food1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(50.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addSeparatorItem("Burerg2");
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("food3","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(10.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addSeparatorItem("Burerg3");
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addSeparatorItem("Burerg4");
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("Foodname","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));

        mListView.setAdapter(menuListAdapter);


    }

    public void addItem(final Food_Item item) {
        mData.add(item);
        menuListAdapter.notifyDataSetChanged();
    }

    public void addSeparatorItem(final String item) {
        mData.add(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        menuListAdapter.notifyDataSetChanged();
    }
}
