package com.example.nemus.execomhackaton.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nemus.execomhackaton.db.DbHelper;
import com.example.nemus.execomhackaton.model.Item;

import java.util.ArrayList;
import java.util.List;


public class ItemDao {

    private Context context;
    private DbHelper mDbHelper;
    private SQLiteDatabase db;

    public ItemDao(Context context){
        this.context=context;
    }

    public void addItem(Item item){
        db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.NAME, item.getName());
        values.put(DbHelper.DESCRIPTION, item.getDescription());
        values.put(DbHelper.PRICE, item.getPrice());
        values.put(DbHelper.FK_ITEM_ID, item.getUser().getUserId());


        db.insert(DbHelper.TABLE_ITEMS, null, values);

    }

    public List<Item> getAllItems(){
        String[] columns = {
                DbHelper.ITEM_ID,
                DbHelper.NAME,
                DbHelper.DESCRIPTION,
                DbHelper.PRICE

        };

        String sortOrder = DbHelper.NAME + " DESC";

        List<Item> itemList = new ArrayList<>();

        db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.TABLE_ITEMS,
                columns,
                null,
                null,
                null,
                null,
                sortOrder
        );
        while(cursor.moveToNext()){
            Item item = new Item();
            item.setItemId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.ITEM_ID))));
            item.setName(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.NAME)));
            item.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.DESCRIPTION)));
            item.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PRICE))));


            itemList.add(item);
        }
        cursor.close();
        return itemList;
    }
}
