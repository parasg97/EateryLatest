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

public class Pizzeria extends AppCompatActivity {

    private ArrayList mData = new ArrayList();
    private TreeSet mSeparatorsSet = new TreeSet();
    private ListView mListView;
    private MenuListAdapter menuListAdapter;
    private Cart mCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzeria);

    mCart= CartHelper.getCart();
    mListView=findViewById(R.id.listView_pizzeria);
    menuListAdapter=new MenuListAdapter(mData,getApplicationContext(),mSeparatorsSet);
    addSeparatorItem("Burger");
    addItem(new Food_Item("a1","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("b1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("c1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("d1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("e1","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("f1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("g1","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addSeparatorItem("Drinks");
    addItem(new Food_Item("h1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("i1","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("j1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(10.0),"Pizzeria"));
    addItem(new Food_Item("k1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("l1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("m1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));
    addItem(new Food_Item("n1","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0),"Pizzeria"));


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
