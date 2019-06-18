package com.kaleem.aboutaustrailia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Created by Samad Ali on 1/28/2018.
 */

public class DbHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "settingsManager.db";
    // Settings table name
    private static final String TABLE_SETTINGS = "user";
    // Settings Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASS = "pass";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SETTINGS_TABLE = "CREATE TABLE " + TABLE_SETTINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PASS + " TEXT" + ")";
        db.execSQL(CREATE_SETTINGS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTINGS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    // Adding new settings
    void addSettings(SettingsDTO settingsDTO) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, settingsDTO._name); // settings fuel
        values.put(KEY_PASS, settingsDTO._pass); // settings temp

        // Inserting Row
        long chk = db.insert(TABLE_SETTINGS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    SettingsDTO getSettings(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SETTINGS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PASS }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        SettingsDTO settingsDTO = new SettingsDTO(parseInt(cursor.getString(0)),
                                                    cursor.getString(1),
                                                    cursor.getString(2));
        // return Settings
        return settingsDTO;
    }

    // Getting single setting
    boolean login(SettingsDTO dto) {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {dto._name, dto._pass};
        Cursor cursor = db.query(TABLE_SETTINGS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PASS }, KEY_NAME + "=? AND "+KEY_PASS + "=?",
                        selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    // Getting All Contacts
    public List<SettingsDTO> getAllUsers() {

        List<SettingsDTO> settingsDTOList = new ArrayList<SettingsDTO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SETTINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SettingsDTO settingsDTO = new SettingsDTO();
                settingsDTO._name = cursor.getString(1);
                settingsDTO._pass = cursor.getString(2);
                // Adding settings to list
                settingsDTOList.add(settingsDTO);
            } while (cursor.moveToNext());
        }
        // return settings list
        return settingsDTOList;
    }

    // Updating single contact
    public int updateSettings(SettingsDTO settingsDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, settingsDTO._name); //
        values.put(KEY_PASS, settingsDTO._pass); //
        // updating row
        return db.update(TABLE_SETTINGS, values, KEY_NAME + " = ?",
                new String[] { String.valueOf(settingsDTO._name) });
    }

    // Deleting single contact
    public void deleteSettings(SettingsDTO settingsDTO) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SETTINGS, KEY_ID + " = ?",
                new String[] { String.valueOf(settingsDTO._id) });
        db.close();
    }

    // Getting contacts Count
    public int getSettingsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SETTINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }
}
