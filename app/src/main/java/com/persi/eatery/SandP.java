package com.persi.eatery;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeSet;

public class SandP extends AppCompatActivity {

    private ArrayList mData = new ArrayList();
    private TreeSet mSeparatorsSet = new TreeSet();
    private ListView mListView;
    private MenuListAdapter menuListAdapter;
    //private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_p);
        mListView=findViewById(R.id.listView_sand);
        //mSearchView=findViewById(R.id.search_view);
        menuListAdapter=new MenuListAdapter(mData,getApplicationContext(),mSeparatorsSet);
        addSeparatorItem("Burger");
        addItem(new Food_Item("a4","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("b4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("c4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("d4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("e4","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("f4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("g4","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0),"SandPiper"));
        addSeparatorItem("Burger2");
        addItem(new Food_Item("h4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("i4","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("j4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(10.0),"SandPiper"));
        addItem(new Food_Item("k4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("l4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("m4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));
        addItem(new Food_Item("n4","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"SandPiper"));


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

    public void goToCart(View v) {
        Intent intent = new Intent(this,ShoppingCart.class);
        intent.putExtra("USER_NAME", "parasg1997");
        finish();
        startActivity(intent);
    }
}
