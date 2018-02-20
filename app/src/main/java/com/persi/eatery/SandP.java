package com.persi.eatery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

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
    private Cart mCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sand_p);
        mCart= CartHelper.getCart();
        //mCart.
        //LLog.d("Eatery",onCreateSandPmCart.toString());
        mListView=findViewById(R.id.listView_sand);
        menuListAdapter=new MenuListAdapter(mData,getApplicationContext(),mSeparatorsSet);
        addSeparatorItem("Burerg");
        addItem(new Food_Item("a","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("b","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("c","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("d","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("e","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("f","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("g","blah blah",R.drawable.ic_egg, BigDecimal.valueOf(100.0)));
        addSeparatorItem("Burerg2");
        addItem(new Food_Item("h","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("i","blah blah",R.drawable.ic_non_veg2, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("j","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(10.0)));
        addItem(new Food_Item("k","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("l","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("m","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));
        addItem(new Food_Item("n","blah blah",R.drawable.ic_veg, BigDecimal.valueOf(100.0)));


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
