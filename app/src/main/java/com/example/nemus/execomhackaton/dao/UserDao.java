package com.example.nemus.execomhackaton.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nemus.execomhackaton.db.DbHelper;
import com.example.nemus.execomhackaton.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemus on 21-May-17.
 */

public class UserDao {

    private Context context;
    private DbHelper mDbHelper;
    private SQLiteDatabase db;


    public UserDao(Context context){
        this.context=context;
        mDbHelper = new DbHelper(context);

    }

    public void addUser(User user){
        db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.FIRST_NAME, user.getFirstName());
        values.put(DbHelper.LAST_NAME, user.getLastName());
        values.put(DbHelper.USERNAME, user.getUserName());
        values.put(DbHelper.EMAIL, user.getEmail());
        values.put(DbHelper.PASSWORD, user.getPassword());

        db.insert(DbHelper.TABLE_USERS, null, values);

    }

    public List<User> getAllUser(){
        String[] columns = {
                DbHelper.USER_ID,
                DbHelper.FIRST_NAME,
                DbHelper.LAST_NAME,
                DbHelper.EMAIL,
                DbHelper.USERNAME,
                DbHelper.PASSWORD
        };

        String sortOrder = DbHelper.USERNAME + " DESC";

        List<User> userList = new ArrayList<User>();

        db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.TABLE_USERS,
                columns,
                null,
                null,
                null,
                null,
                sortOrder
        );
       while(cursor.moveToNext()){
           User user = new User();
           user.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.USER_ID))));
           user.setFirstName(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.FIRST_NAME)));
           user.setLastName(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.LAST_NAME)));
           user.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.EMAIL)));
           user.setUserName(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.USERNAME)));
           user.setPassword(cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.PASSWORD)));

          userList.add(user);
       }
       cursor.close();
        return userList;
    }

    public void updateUser(User user){
        db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.FIRST_NAME, user.getFirstName());
        values.put(DbHelper.LAST_NAME, user.getLastName());
        values.put(DbHelper.USERNAME, user.getUserName());
        values.put(DbHelper.EMAIL, user.getEmail());
        values.put(DbHelper.PASSWORD, user.getPassword());

        db.update(DbHelper.TABLE_USERS, values, DbHelper.USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserId())});
    }

    public void deleteUser(User user){
        db = mDbHelper.getWritableDatabase();
        db.delete(DbHelper.TABLE_USERS, DbHelper.USER_ID + " = ?",
                new String[]{String.valueOf(user.getUserId())});
    }

    public boolean checkUser(String email){
      String[] columns = {
              DbHelper.USER_ID
      };
      db = mDbHelper.getReadableDatabase();
        String selection = DbHelper.EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(
                DbHelper.TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        int cursorCount = cursor.getCount();
        cursor.close();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                DbHelper.USER_ID
        };
        db = mDbHelper.getReadableDatabase();
        String selection = DbHelper.EMAIL + " = ?" + " AND " + DbHelper.PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(
                DbHelper.TABLE_USERS,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        int cursorCount = cursor.getCount();
        cursor.close();

        if(cursorCount > 0){
            return true;
        }
        return false;
    }
}
