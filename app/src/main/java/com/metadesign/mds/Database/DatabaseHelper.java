package com.metadesign.mds.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employeedb";
    private static final int DATABASE_VERSION = 1;

    public static final String EMPLOYEE_AUTH_TABLE = "employee_authentication";
    public static final String EMPLOTEE_DETAIL_TABLE = "employee_details";

    public static final String KEY_ID_COLUMN = "id";
    public static final String FIRST_NAME_COLUMN = "first_name";
    public static final String LAST_NAME_COLUMN = "last_name";
    public static final String EMAIL_COLUMN = "email";
    public static final String PASSWORD_COLUMN = "password";

    public static final String KEY_ID_DETAIL_COLUMN = "id";
    public static final String ADDRESS_COLUMN = "address";
    public static final String PHONE_NO_COLUMN = "phone_no";
    public static final String DESIGNATION_COLUMN = "designation";
    public static final String DEPARTMENT_COLUMN = "department";
    public static final String INCOME_COLUMN = "income";

    private static DatabaseHelper instance;

    String CREATE_EMPLOYEE_AUTHENTICATION_TABLE = "CREATE TABLE " + EMPLOYEE_AUTH_TABLE + "("
            + KEY_ID_COLUMN + " INTEGER PRIMARY KEY,"
            + FIRST_NAME_COLUMN + " TEXT,"
            + LAST_NAME_COLUMN + " TEXT,"
            + EMAIL_COLUMN + " TEXT unique,"
            + PASSWORD_COLUMN + " TEXT"
            +")";

    String CREATE_EMPLOYEE_DETAIL_TABLE = "CREATE TABLE " + EMPLOTEE_DETAIL_TABLE + "("
            + KEY_ID_DETAIL_COLUMN + " INTEGER ,"
            + ADDRESS_COLUMN + " TEXT,"
            + PHONE_NO_COLUMN + " TEXT,"
            + DESIGNATION_COLUMN + " TEXT,"
            + DEPARTMENT_COLUMN + " TEXT,"
            + INCOME_COLUMN + " TEXT,"
            + "FOREIGN KEY(" + KEY_ID_DETAIL_COLUMN +") REFERENCES " + EMPLOYEE_AUTH_TABLE +"(" + KEY_ID_COLUMN +")"
            +")";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("database created" ,"db created");
        db.execSQL(CREATE_EMPLOYEE_AUTHENTICATION_TABLE);
        db.execSQL(CREATE_EMPLOYEE_DETAIL_TABLE);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
        Log.i("database open", "db open");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("database upgrade" ,"updated");
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOTEE_DETAIL_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_AUTH_TABLE);

    }


    public static synchronized DatabaseHelper getHelper(Context context) {

        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }



}
