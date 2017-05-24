package com.example.nemus.execomhackaton.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import android.support.design.widget.TabLayout;
import android.widget.EditText;

import com.example.nemus.execomhackaton.R;
import com.example.nemus.execomhackaton.db.DbHelper;
import com.example.nemus.execomhackaton.fragment.MyItemsFragment;
import com.example.nemus.execomhackaton.fragment.ProfileFragment;
import com.example.nemus.execomhackaton.fragment.PublicItemsFragment;
import com.example.nemus.execomhackaton.model.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

private EditText itemName, description, price, date;
    private DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DbHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }



    private void setupViewPager(ViewPager viewPager) {
       Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileFragment(), "Profile");
        adapter.addFragment(new MyItemsFragment(), "My Items");
        adapter.addFragment(new PublicItemsFragment(), "Public items");
        viewPager.setAdapter(adapter);
    }

    public void openInsertDialog(View view) {
        View myView = getLayoutInflater().inflate(R.layout.add_item, null);
        itemName = (EditText) myView.findViewById(R.id.itemName) ;
        description = (EditText) myView.findViewById(R.id.description);
        price = (EditText) myView.findViewById(R.id.price);
        date = (EditText) myView.findViewById(R.id.date);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("New Item")
                .setView(myView)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Item item = new Item();
                        item.setName(itemName.getText().toString());
                        item.setDescription(description.getText().toString());
                        item.setPrice(Integer.parseInt(price.getText().toString()));
                        item.setDate(date.getText().toString());

                        SQLiteDatabase database = db.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put(DbHelper.NAME, item.getName());
                        values.put(DbHelper.DESCRIPTION, item.getDescription());
                        values.put(DbHelper.PRICE, item.getPrice());
                        values.put(DbHelper.DATE, item.getDate());

                        database.insertWithOnConflict(DbHelper.TABLE_ITEMS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    static class Adapter extends FragmentPagerAdapter{

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}
