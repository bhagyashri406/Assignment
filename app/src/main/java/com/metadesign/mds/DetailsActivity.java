package com.metadesign.mds;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.metadesign.mds.Database.DatabaseHelper;
import com.metadesign.mds.Query.EmployeeAuthQuery;
import com.metadesign.mds.Query.EmployeeDetailQuery;
import com.metadesign.mds.modals.EmployeeAllDetails;
import com.metadesign.mds.modals.EmployeeAuthentication;
import com.metadesign.mds.modals.EmployeeDetail;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends Activity {

    EditText ed_Firstname;
    EditText ed_lastname;
    EditText ed_address;
    EditText ed_designation;
    EditText ed_department;
    EditText ed_income;
    EditText ed_emailid;
    EditText ed_phone;
    Button btn_edit;
    SharedPreferences sharedPreferences ;

    EmployeeDetailQuery employeeDetailQuery ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        sharedPreferences = getSharedPreferences("MyPREFERENCES", getApplicationContext().MODE_PRIVATE);
        ed_Firstname = (EditText)findViewById(R.id.first_name);
        ed_lastname = (EditText)findViewById(R.id.last_name);
        ed_address = (EditText)findViewById(R.id.address);
        ed_designation = (EditText)findViewById(R.id.designation);
        ed_department = (EditText)findViewById(R.id.department);
        ed_income = (EditText)findViewById(R.id.income);
        ed_emailid = (EditText)findViewById(R.id.email_id);
        ed_phone = (EditText)findViewById(R.id.phone_no);
        btn_edit=(Button)findViewById(R.id.edit_details);

        String first = sharedPreferences.getString("firstname" ,"");
        String lastname = sharedPreferences.getString("lastname", "");
        String userid = sharedPreferences.getString("userid", "");
        final String email=sharedPreferences.getString("email", "");
        employeeDetailQuery = new EmployeeDetailQuery(this);
        final SharedPreferences.Editor editor =  sharedPreferences.edit();
        ed_Firstname.setText(first);
        ed_lastname.setText(lastname);
        ed_emailid.setText(userid);
        ed_address.setText(sharedPreferences.getString("address", ""));
        ed_phone.setText(sharedPreferences.getString("phone" ,""));
        ed_designation.setText(sharedPreferences.getString("designation", ""));
        ed_department.setText(sharedPreferences.getString("deparment", ""));
        ed_income.setText(sharedPreferences.getString("income", ""));

        EmployeeAuthQuery databaseDetail = new EmployeeAuthQuery(this);
        List<EmployeeAllDetails> list =  databaseDetail.getInnerJoin() ;

        if(email!=null){
            List<EmployeeAuthentication> conts = databaseDetail.getAllEmployee();
            for (EmployeeAuthentication cn1 : conts) {
                Log.i(email ,cn1.getEmail());
                if( cn1.getEmail().equals(email) ) {
                    ed_Firstname.setText(cn1.getFirstname());
                    ed_lastname.setText(cn1.getLastname());
                    ed_emailid.setText(cn1.getEmail());
                }
            }
        }

        if(list.size()!= 0) {
            for (int i = 0; i < list.size(); i++){
              if( list.get(i).getFirstname().equals(first)&& list.get(i).getLastname().equals(lastname)){
                  ed_Firstname.setText(list.get(i).getFirstname());
                  ed_lastname.setText(list.get(i).getLastname());
                  ed_address.setText(list.get(i).getAddress());
                  ed_phone.setText(list.get(i).getPhoneNumber());
                  ed_income.setText(list.get(i).getIncome());
                  ed_department.setText(list.get(i).getDepartment());
                  ed_designation.setText(list.get(i).getDesignation());
                  ed_emailid.setText(list.get(i).getEmail());}

        }

        }

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("address",ed_address.getText().toString());
                editor.putString("phone", ed_address.getText().toString());
                editor.putString("designation", ed_designation.getText().toString());
                editor.putString("deparment", ed_department.getText().toString());
                editor.putString("income", ed_income.getText().toString());
                editor.commit();
                employeeDetailQuery.addEmployeDetails(new EmployeeDetail(
                        ed_address.getText().toString(),
                        ed_phone.getText().toString(),
                        ed_designation.getText().toString(),
                        ed_department.getText().toString(),
                        ed_income.getText().toString()));
                Toast.makeText(getApplicationContext(),"details updated...",Toast.LENGTH_SHORT).show();
            }
        });




    }


}
