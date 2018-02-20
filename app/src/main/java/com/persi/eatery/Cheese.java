package com.persi.eatery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.tonyvu.sc.model.Cart;
import com.android.tonyvu.sc.util.CartHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeSet;

public class Cheese extends AppCompatActivity {

    private ArrayList mData = new ArrayList();
    private TreeSet mSeparatorsSet = new TreeSet();
    private ListView mListView;
    private MenuListAdapter menuListAdapter;
    private Cart mCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheese);

        mCart= CartHelper.getCart();
        mListView=findViewById(R.id.listView_cheese);
        menuListAdapter=new MenuListAdapter(mData,getApplicationContext(),mSeparatorsSet);
        addSeparatorItem("Burger");
        addItem(new Food_Item("a2","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("b2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("c2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("d2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("e2","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("f2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("g2","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0)));
        addSeparatorItem("Burger2");
        addItem(new Food_Item("h2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("i2","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("j2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(10.0)));
        addItem(new Food_Item("k2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("l2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("m2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("n2","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));


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
