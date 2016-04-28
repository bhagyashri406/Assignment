package com.metadesign.mds.Query;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;
import com.metadesign.mds.Database.DatabaseHelper;
import com.metadesign.mds.Database.EmployeeDatabase;
import com.metadesign.mds.modals.EmployeeAllDetails;
import com.metadesign.mds.modals.EmployeeAuthentication;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAuthQuery extends EmployeeDatabase {

    public EmployeeAuthQuery(Context context) {

        super(context);
    }


    public void addEmployee(EmployeeAuthentication employeeAuthentication) {

        ContentValues values = new ContentValues();
        Log.i("tag", employeeAuthentication.getEmail());

        values.put(DatabaseHelper.FIRST_NAME_COLUMN, employeeAuthentication.getFirstname());
        values.put(DatabaseHelper.LAST_NAME_COLUMN, employeeAuthentication.getLastname());
        values.put(DatabaseHelper.EMAIL_COLUMN, employeeAuthentication.getEmail());
        values.put(DatabaseHelper.PASSWORD_COLUMN, employeeAuthentication.getPassword());
        // Inserting Row
        database.insert(DatabaseHelper.EMPLOYEE_AUTH_TABLE, null, values);


    }


    public EmployeeAuthentication getEmployee(int id) {
        Cursor cursor = database.query(DatabaseHelper.EMPLOYEE_AUTH_TABLE,
                new String[]{
                        DatabaseHelper.KEY_ID_COLUMN,
                        DatabaseHelper.FIRST_NAME_COLUMN,
                        DatabaseHelper.LAST_NAME_COLUMN,
                        DatabaseHelper.EMAIL_COLUMN,
                        DatabaseHelper.PASSWORD_COLUMN
                }, DatabaseHelper.KEY_ID_COLUMN + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);


        EmployeeAuthentication employeeAuthentication = new EmployeeAuthentication(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
        // return contact
        return employeeAuthentication;
    }


    public List<EmployeeAuthentication> getAllEmployee() {
        List<EmployeeAuthentication> List = new ArrayList<EmployeeAuthentication>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.EMPLOYEE_AUTH_TABLE;
        Cursor cursor = database.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                EmployeeAuthentication employeeAuthentication = new EmployeeAuthentication();
                employeeAuthentication.setId(Integer.parseInt(cursor.getString(0)));
                employeeAuthentication.setFirstname(cursor.getString(1));
                employeeAuthentication.setLastname(cursor.getString(2));
                employeeAuthentication.setEmail(cursor.getString(3));
                employeeAuthentication.setPassword(cursor.getString(4));
                // Adding contact to list
                List.add(employeeAuthentication);
            } while (cursor.moveToNext());
        }

        // return contact list
        return List;
    }


    public int updateEmployee(EmployeeAuthentication employeeAuthentication) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.FIRST_NAME_COLUMN, employeeAuthentication.getFirstname());
        values.put(DatabaseHelper.LAST_NAME_COLUMN, employeeAuthentication.getLastname());
        values.put(DatabaseHelper.EMAIL_COLUMN, employeeAuthentication.getEmail());
        values.put(DatabaseHelper.PASSWORD_COLUMN, employeeAuthentication.getPassword());

        return database.update(DatabaseHelper.EMPLOYEE_AUTH_TABLE, values,
                DatabaseHelper.KEY_ID_COLUMN + " = ?",
                new String[]{String.valueOf(employeeAuthentication.getId())});
    }

    public void deleteEmployee(EmployeeAuthentication employeeAuthentication) {
        database.delete(DatabaseHelper.EMPLOYEE_AUTH_TABLE, DatabaseHelper.KEY_ID_COLUMN + " = ?",
                new String[]{String.valueOf(employeeAuthentication.getId())});

    }


    public ArrayList<String> getEmployeeCheck(String email) {

        String [] settingsProjection = {
                DatabaseHelper.PASSWORD_COLUMN,
        };

        String whereClause = DatabaseHelper.EMAIL_COLUMN+"=?";
        String [] whereArgs = {email};

        Cursor c = database.query(
                DatabaseHelper.EMPLOYEE_AUTH_TABLE,
                settingsProjection,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
      ArrayList<String> sc = new ArrayList<>();
          Log.i("email",""+ c.getCount());
        if (c.moveToFirst()) {
            do {
               sc.add(c.getString(0));
            } while (c.moveToNext());
        }

        return sc;
    }


    public int getEmployeeCount() {
        String countQuery = "SELECT  * FROM " + DatabaseHelper.EMPLOYEE_AUTH_TABLE;
        Cursor cursor = database.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }



    public List<EmployeeAllDetails> getInnerJoin(){
        List<EmployeeAllDetails> Details = new ArrayList<EmployeeAllDetails>();

        String[] columns = new String[] {
                DatabaseHelper.FIRST_NAME_COLUMN,
                DatabaseHelper.LAST_NAME_COLUMN,
                DatabaseHelper.EMAIL_COLUMN,
                DatabaseHelper.ADDRESS_COLUMN,
                DatabaseHelper.PHONE_NO_COLUMN,
                DatabaseHelper.DESIGNATION_COLUMN,
                DatabaseHelper.DEPARTMENT_COLUMN,
                DatabaseHelper.INCOME_COLUMN};
        String whereClause = "employee_authentication.id" + "=" + "employee_details.id ";
        Cursor cursor = database.query(true, DatabaseHelper.EMPLOYEE_AUTH_TABLE + " INNER JOIN " +
                DatabaseHelper.EMPLOTEE_DETAIL_TABLE, columns,
                whereClause, null, null, null, null, null);
        Log.i("count" , " "+cursor.getCount());
      if(cursor.getCount()!=0){
        if (cursor.moveToFirst()) {
            do {
                  EmployeeAllDetails employeeAllDetails =  new EmployeeAllDetails();
                Log.i("firstname , last name" ,cursor.getString(0)+cursor.getString(1));
                employeeAllDetails.setFirstname(cursor.getString(0));
                employeeAllDetails.setLastname(cursor.getString(1));
                employeeAllDetails.setEmail(cursor.getString(2));
                employeeAllDetails.setAddress(cursor.getString(3));
                employeeAllDetails.setPhoneNumber(cursor.getString(4));
                employeeAllDetails.setDesignation(cursor.getString(5));
                employeeAllDetails.setDepartment(cursor.getString(6));
                employeeAllDetails.setIncome(cursor.getString(7));
                Details.add(employeeAllDetails);
            } while (cursor.moveToNext());
        }
      }
        return Details;
    }


}
