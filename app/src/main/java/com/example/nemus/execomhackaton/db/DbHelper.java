package com.example.nemus.execomhackaton.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by nemus on 19-May-17.
 */

public class DbHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ExecomHackaton.db";

    public static final String TABLE_USERS = "users";
    public static final String TABLE_ITEMS = "items";


    //Users tables columns
    public static final String  USER_ID= "userId";
    public static final String  FIRST_NAME= "firstName";
    public static final String  LAST_NAME= "lastName";
    public static final String USERNAME = "userName";
    public static final String  EMAIL= "email";
    public static final String  PASSWORD= "password";

    //items table columns
    public static final String ITEM_ID= "itemId";
    public static final String FK_ITEM_ID= "userItemId";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String DESCRIPTION = "description";
    public static final String DATE = "date";

    //Users table created
    private static final String CREATE_USERS_TABLE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FIRST_NAME + " TEXT, " +
                    LAST_NAME + " TEXT," +
                    USERNAME + " TEXT, " +
                    EMAIL + " TEXT, " +
                    PASSWORD + " TEXT)";

    //Items table created
    private static final String CREATE_ITEMS_TABLE =
            "CREATE TABLE " + TABLE_ITEMS + " (" +
                    ITEM_ID + " INTEGER PRIMARY KEY," +
                    NAME + " TEXT," +
                    PRICE + " INTEGER, " +
                    DESCRIPTION + " TEXT, " +
                    DATE + " TEXT, " +
                    FK_ITEM_ID + " INTEGER NOT NULL, FOREIGN KEY (" +FK_ITEM_ID+ ") REFERENCES " +
                    TABLE_USERS + "( " + USER_ID + "))";

    //drop users table
    private static final String DELETE_USER_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_USERS;

    //drop items table
    private static final String DELETE_ITEMS_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_ITEMS;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_ITEMS_TABLE);
        onCreate(db);
    }
}
