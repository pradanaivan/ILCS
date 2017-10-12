package com.ikt.main.to.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ikt.main.to.object.DriverObject;
import com.ikt.main.to.object.NotifObject;
import com.ikt.main.to.object.ProfileObject;
import com.ikt.main.to.object.TruckObject;
import com.ikt.main.to.util.Config;
import com.ikt.main.to.util.PreferenceManagers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Arifin on 1/7/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";
    private Context context;

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "IKTAPPS";

    // Table Names
    private static final String TABLE_DRIVER = "tbl_driver";
    private static final String TABLE_PROFILE = "tbl_profile";
    private static final String TABLE_TRUCK = "tbl_truck";
    private static final String TABLE_NOTIF = "tbl_notif";


    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DRIVER_ID = "driver_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_CREATED_AT = "created";
    private static final String KEY_IDENTITY_NO = "identity_no";
    private static final String KEY_LICENSE_NO = "license_no";
    private static final String KEY_ORG_ID = "org_id";
    private static final String KEY_ORG_CODE = "org_code";
    private static final String KEY_ORG_NAME = "org_name";
    private static final String KEY_SESSION = "session";
    private static final String KEY_PLAT_NO = "plat_no";
    private static final String KEY_PLAT_NO_CODE = "plat_no_code";
    private static final String KEY_TRUCK_ID = "truck_id";
    private static final String KEY_PHONE_NO = "phone_no";
    private static final String KEY_MSG = "msg_notif";
    private static final String KEY_MSG_TYPE = "msg_type";


    private static final String CREATE_TABLE_DRIVER = "CREATE TABLE "
            + TABLE_DRIVER + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_DRIVER_ID + " VARCHAR,"
            + KEY_NAME + " VARCHAR,"
            + KEY_USERNAME + " VARCHAR,"
            + KEY_IDENTITY_NO + " VARCHAR,"
            + KEY_LICENSE_NO + " VARCHAR,"
            + KEY_PHONE_NO + " VARCHAR,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE "
            + TABLE_PROFILE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_USER_ID + " VARCHAR,"
            + KEY_NAME + " VARCHAR,"
            + KEY_USERNAME + " VARCHAR,"
            + KEY_ORG_ID + " VARCHAR,"
            + KEY_ORG_CODE + " VARCHAR,"
            + KEY_ORG_NAME + " VARCHAR,"
            + KEY_SESSION + " VARCHAR,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    private static final String CREATE_TABLE_TRUCK = "CREATE TABLE "
            + TABLE_TRUCK + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_PLAT_NO + " VARCHAR,"
            + KEY_PLAT_NO_CODE + " VARCHAR,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    private static final String CREATE_TABLE_NOTIF = "CREATE TABLE "
            + TABLE_NOTIF + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_MSG + " VARCHAR,"
            + KEY_MSG_TYPE + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DRIVER);
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_TRUCK);
        db.execSQL(CREATE_TABLE_NOTIF);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRIVER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRUCK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIF);
        // create new tables
        onCreate(db);
    }

    public long createAccountDriver(DriverObject driver) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, driver.getDriverName());
        values.put(KEY_USERNAME, driver.getUsername());
        values.put(KEY_IDENTITY_NO, driver.getIdentityNo());
        values.put(KEY_LICENSE_NO, driver.getLicenseNo());
        values.put(KEY_CREATED_AT, getDateTime());
        values.put(KEY_PHONE_NO, driver.getDriverPhone());
        values.put(KEY_DRIVER_ID, driver.getDriverId());

        // insert row
        long id = db.insert(TABLE_DRIVER, null, values);

        return id;
    }

    public DriverObject getDriverById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DRIVER + " WHERE " + KEY_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        DriverObject object = new DriverObject();
        object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        object.setDriverName((c.getString(c.getColumnIndex(KEY_NAME))));
        object.setUsername((c.getString(c.getColumnIndex(KEY_USERNAME))));
        object.setLicenseNo((c.getString(c.getColumnIndex(KEY_LICENSE_NO))));
        object.setIdentityNo((c.getString(c.getColumnIndex(KEY_IDENTITY_NO))));
        object.setDriverPhone((c.getString(c.getColumnIndex(KEY_PHONE_NO))));
        object.setDriverId((c.getString(c.getColumnIndex(KEY_DRIVER_ID))));

        return object;
    }

    public int updateDriver(DriverObject object) {
        SQLiteDatabase db = this.getWritableDatabase();
        String session = PreferenceManagers.getData(Config.KEY_SESSION, context);
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, object.getDriverName());
        values.put(KEY_IDENTITY_NO, object.getIdentityNo());
        values.put(KEY_LICENSE_NO, object.getLicenseNo());
        values.put(KEY_PHONE_NO, object.getDriverPhone());

        return db.update(TABLE_DRIVER, values, KEY_ID + " = ?",
                new String[]{String.valueOf(object.getId())});
    }

    public void deleteDriverById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from " + TABLE_DRIVER + " where " + KEY_ID + " = '" + id + "'");
        db.close();
    }

    public List<DriverObject> getDriverByName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DriverObject> drivers = new ArrayList<DriverObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_DRIVER + " WHERE " + KEY_NAME + " like '%" + name + "%' order by " + KEY_NAME + " ASC";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                DriverObject object = new DriverObject();
                object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                object.setDriverName((c.getString(c.getColumnIndex(KEY_NAME))));
                object.setUsername((c.getString(c.getColumnIndex(KEY_USERNAME))));
                object.setLicenseNo((c.getString(c.getColumnIndex(KEY_LICENSE_NO))));
                object.setIdentityNo((c.getString(c.getColumnIndex(KEY_IDENTITY_NO))));
                object.setDriverPhone((c.getString(c.getColumnIndex(KEY_PHONE_NO))));
                object.setDriverId((c.getString(c.getColumnIndex(KEY_DRIVER_ID))));
                drivers.add(object);
            } while (c.moveToNext());
        }

        return drivers;
    }

    public List<DriverObject> getAllDriver() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DriverObject> drivers = new ArrayList<DriverObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_DRIVER + " order by " + KEY_NAME + " ASC";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                DriverObject object = new DriverObject();
                object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                object.setDriverName((c.getString(c.getColumnIndex(KEY_NAME))));
                object.setUsername((c.getString(c.getColumnIndex(KEY_USERNAME))));
                object.setLicenseNo((c.getString(c.getColumnIndex(KEY_LICENSE_NO))));
                object.setIdentityNo((c.getString(c.getColumnIndex(KEY_IDENTITY_NO))));
                object.setDriverPhone((c.getString(c.getColumnIndex(KEY_PHONE_NO))));
                object.setDriverId((c.getString(c.getColumnIndex(KEY_DRIVER_ID))));
                drivers.add(object);
            } while (c.moveToNext());
        }

        return drivers;
    }

    public DriverObject getLastDriver(){
        SQLiteDatabase db = this.getWritableDatabase();
        DriverObject object = null;

        String selectQuery = "SELECT  * FROM " + TABLE_DRIVER + " ORDER BY " + KEY_ID + " DESC LIMIT 1 ";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if(c.getCount() == 0)
            return null;

        object = new DriverObject();
        object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        object.setDriverName((c.getString(c.getColumnIndex(KEY_NAME))));
        object.setUsername((c.getString(c.getColumnIndex(KEY_USERNAME))));
        object.setLicenseNo((c.getString(c.getColumnIndex(KEY_LICENSE_NO))));
        object.setIdentityNo((c.getString(c.getColumnIndex(KEY_IDENTITY_NO))));
        object.setDriverPhone((c.getString(c.getColumnIndex(KEY_PHONE_NO))));
        object.setDriverId((c.getString(c.getColumnIndex(KEY_DRIVER_ID))));

        return object;
    }

    public int getDriverCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DRIVER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public long createProfile(ProfileObject object) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, object.getUserId());
        values.put(KEY_NAME, object.getName());
        values.put(KEY_USERNAME, object.getUsername());
        values.put(KEY_ORG_ID, object.getOrgId());
        values.put(KEY_ORG_CODE, object.getOrgCode());
        values.put(KEY_ORG_NAME, object.getOrgName());
        values.put(KEY_SESSION, object.getSession());
        values.put(KEY_CREATED_AT, getDateTime());
        long id = db.insert(TABLE_PROFILE, null, values);
        return id;

    }

    public int updateProfile(ProfileObject object) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, object.getUserId());
        values.put(KEY_NAME, object.getName());
        values.put(KEY_USERNAME, object.getUsername());
        values.put(KEY_ORG_ID, object.getOrgId());
        values.put(KEY_ORG_CODE, object.getOrgCode());
        values.put(KEY_ORG_NAME, object.getOrgName());
        values.put(KEY_SESSION, object.getSession());
        values.put(KEY_CREATED_AT, getDateTime());

        return db.update(TABLE_PROFILE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(object.getId())});
    }

    public ProfileObject getProfile() {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PROFILE;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if(c.getCount() == 0)
            return null;

        ProfileObject object = new ProfileObject();
        object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        object.setUserId(c.getString(c.getColumnIndex(KEY_USER_ID)));
        object.setName((c.getString(c.getColumnIndex(KEY_NAME))));
        object.setUsername((c.getString(c.getColumnIndex(KEY_USERNAME))));
        object.setOrgId((c.getString(c.getColumnIndex(KEY_ORG_ID))));
        object.setOrgCode((c.getString(c.getColumnIndex(KEY_ORG_CODE))));
        object.setOrgName((c.getString(c.getColumnIndex(KEY_ORG_NAME))));
        object.setSession((c.getString(c.getColumnIndex(KEY_SESSION))));

        return object;
    }

    public long createTruck(TruckObject object) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PLAT_NO, object.getPlatNo());
        values.put(KEY_PLAT_NO_CODE, object.getPlatNoCode());

        long id = db.insert(TABLE_TRUCK, null, values);
        return id;

    }

    public TruckObject getTruck(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_TRUCK + " WHERE " + KEY_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        TruckObject object = new TruckObject();
        object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        object.setPlatNo((c.getString(c.getColumnIndex(KEY_PLAT_NO))));
        object.setPlatNoCode((c.getString(c.getColumnIndex(KEY_PLAT_NO_CODE))));

        return object;
    }

    // Satrio
    public List<TruckObject> getAllTruck() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<TruckObject> trucks = new ArrayList<TruckObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_TRUCK + " order by " + KEY_PLAT_NO + " ASC";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                TruckObject object = new TruckObject();
                object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                object.setPlatNo((c.getString(c.getColumnIndex(KEY_PLAT_NO))));
                object.setPlatNoCode((c.getString(c.getColumnIndex(KEY_PLAT_NO_CODE))));
                trucks.add(object);
            } while (c.moveToNext());
        }

        return trucks;
    }

    public List<TruckObject> getTruckByPlat(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<TruckObject> objects = new ArrayList<TruckObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_TRUCK + " WHERE " + KEY_PLAT_NO + " like '%" + name + "%' order by " + KEY_PLAT_NO + " ASC";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                TruckObject object = new TruckObject();
                object.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                object.setPlatNo((c.getString(c.getColumnIndex(KEY_PLAT_NO))));
                object.setPlatNoCode((c.getString(c.getColumnIndex(KEY_PLAT_NO_CODE))));
                objects.add(object);
            } while (c.moveToNext());
        }
        return objects;
    }

    public int getTruckCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TRUCK;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

    public void deleteProfile() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_PROFILE);
        db.close();
    }

    public void deleteTruck() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_TRUCK);
        db.close();
    }

    public void deleteNotif() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_NOTIF);
        db.close();
    }

    public void deleteDriver() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + TABLE_DRIVER);
        db.close();
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public long insertNotif(NotifObject notifObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MSG, notifObject.getMessage());
        values.put(KEY_MSG_TYPE, notifObject.getType());

        // insert row
        long id = db.insert(TABLE_NOTIF, null, values);
        return id;
    }

    public List<NotifObject> getAllNotif(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<NotifObject> objects = new ArrayList<NotifObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIF +" order by " + KEY_CREATED_AT + " DESC";

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                NotifObject notifObject = new NotifObject();
                int id = c.getInt(c.getColumnIndex(KEY_ID));
                String msg = c.getString(c.getColumnIndex(KEY_MSG));
                int type = c.getInt(c.getColumnIndex(KEY_MSG_TYPE));
                notifObject.setType(type);
                notifObject.setMessage(msg);
                notifObject.setId(id);
                objects.add(notifObject);
            } while (c.moveToNext());
        }
        return objects;
    }

    public NotifObject getNotifById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        NotifObject notifObject = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIF + " WHERE " + KEY_ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        if(c.getCount() == 0)
            return null;

        notifObject = new NotifObject();
        String msg = c.getString(c.getColumnIndex(KEY_MSG));
        int type = c.getInt(c.getColumnIndex(KEY_MSG_TYPE));
        notifObject.setType(type);
        notifObject.setMessage(msg);
        notifObject.setId((int) id);

        return notifObject;
    }

    public List<NotifObject> getNotifByType(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<NotifObject> objects = new ArrayList<NotifObject>();
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIF + " WHERE " + KEY_MSG_TYPE + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                NotifObject notifObject = new NotifObject();
                int idNotif = c.getInt(c.getColumnIndex(KEY_ID));
                String msg = c.getString(c.getColumnIndex(KEY_MSG));
                int type = c.getInt(c.getColumnIndex(KEY_MSG_TYPE));
                notifObject.setType(type);
                notifObject.setMessage(msg);
                notifObject.setId(idNotif);
                objects.add(notifObject);
            } while (c.moveToNext());
        }
        return objects;

    }

    public void deleteNotifById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("delete from " + TABLE_NOTIF + " where " + KEY_ID + " = " + id);
        db.close();
    }
}
