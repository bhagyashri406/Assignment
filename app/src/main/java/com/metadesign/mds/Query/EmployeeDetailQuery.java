package com.metadesign.mds.Query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.metadesign.mds.Database.DatabaseHelper;
import com.metadesign.mds.Database.EmployeeDatabase;
import com.metadesign.mds.modals.EmployeeDetail;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetailQuery extends EmployeeDatabase {

    public EmployeeDetailQuery(Context context) {
        super(context);
    }

    public void addEmployeDetails(EmployeeDetail detail) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ADDRESS_COLUMN, detail.getAddress());
        values.put(DatabaseHelper.PHONE_NO_COLUMN, detail.getPhoneNumber());
        values.put(DatabaseHelper.DEPARTMENT_COLUMN, detail.getDepartment());
        values.put(DatabaseHelper.DESIGNATION_COLUMN, detail.getDesignation());
        values.put(DatabaseHelper.INCOME_COLUMN, detail.getIncome());
        // Inserting Row


    }


    public EmployeeDetail getEmplayeDetails(int id) {

        Cursor cursor = database.query(DatabaseHelper.EMPLOTEE_DETAIL_TABLE,
                new String[]{DatabaseHelper.KEY_ID_DETAIL_COLUMN,
                        DatabaseHelper.ADDRESS_COLUMN,
                        DatabaseHelper.PHONE_NO_COLUMN,
                        DatabaseHelper.DESIGNATION_COLUMN,
                        DatabaseHelper.DEPARTMENT_COLUMN,
                        DatabaseHelper.INCOME_COLUMN},
                DatabaseHelper.KEY_ID_DETAIL_COLUMN + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        EmployeeDetail employeeDetail = new EmployeeDetail(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));

        return employeeDetail;
    }

    public List<EmployeeDetail> getAllEmplayeeDetails() {
        List<EmployeeDetail> detaillist = new ArrayList<EmployeeDetail>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.EMPLOTEE_DETAIL_TABLE;
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EmployeeDetail employeeDetail = new EmployeeDetail();
                employeeDetail.setId(Integer.parseInt(cursor.getString(0)));
                employeeDetail.setAddress(cursor.getString(1));
                employeeDetail.setPhoneNumber(cursor.getString(2));
                employeeDetail.setDepartment(cursor.getString(3));
                employeeDetail.setDesignation(cursor.getString(4));
                employeeDetail.setIncome(cursor.getString(5));

                detaillist.add(employeeDetail);
            } while (cursor.moveToNext());
        }


        return detaillist;
    }


    public int updateDetailTable(EmployeeDetail employeeDetail) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.ADDRESS_COLUMN, employeeDetail.getAddress());
        values.put(DatabaseHelper.PHONE_NO_COLUMN, employeeDetail.getPhoneNumber());
        values.put(DatabaseHelper.DESIGNATION_COLUMN, employeeDetail.getDesignation());
        values.put(DatabaseHelper.DEPARTMENT_COLUMN, employeeDetail.getDepartment());
        values.put(DatabaseHelper.INCOME_COLUMN, employeeDetail.getIncome());
        // updating row
        return database.update(DatabaseHelper.EMPLOTEE_DETAIL_TABLE, values, DatabaseHelper.KEY_ID_DETAIL_COLUMN + " = ?",
                new String[]{String.valueOf(employeeDetail.getId())});
    }

    public void deleteDetailTable(EmployeeDetail employeeDetail) {
        database.delete(DatabaseHelper.EMPLOTEE_DETAIL_TABLE, DatabaseHelper.KEY_ID_DETAIL_COLUMN + " = ?",
                new String[]{String.valueOf(employeeDetail.getId())});

    }

    public int getDetailTableCount() {
        String countQuery = "SELECT  * FROM " + DatabaseHelper.EMPLOTEE_DETAIL_TABLE;
        Cursor cursor = database.rawQuery(countQuery, null);
        // return count
        return cursor.getCount();
    }


}
