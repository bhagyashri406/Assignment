package com.metadesign.mds;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.metadesign.mds.Query.EmployeeAuthQuery;
import com.metadesign.mds.Query.EmployeeDetailQuery;
import com.metadesign.mds.modals.EmployeeDetail;
import com.metadesign.mds.validator.Validator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {


    EditText  ed_user_id ;
    EditText  ed_password;
    Button btn_login;
    Button btn_signup;
    EmployeeAuthQuery employeeAuthQuery;
    EmployeeDetailQuery employeeDetailQuery ;
    Intent  intent ,intentLogin;
    private SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        sharedpreferences = PreferenceManager
                .getDefaultSharedPreferences(getApplicationContext());

        if (sharedpreferences.getBoolean("UserLoggedIn", false)) {
            startActivity(new Intent(this, DetailsActivity.class));
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_user_id = (EditText)findViewById(R.id.user_id);
        ed_password = (EditText)findViewById(R.id.password);
        btn_login = (Button)findViewById(R.id.login_btn);
        btn_signup = (Button)findViewById(R.id.signup_btn);
        employeeAuthQuery = new EmployeeAuthQuery(this);
        employeeDetailQuery= new EmployeeDetailQuery(this);
        intent =  new Intent(this , SignupActivity.class);
        intentLogin= new Intent(this,DetailsActivity.class);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);

            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = ed_user_id.getText().toString();
                String password = ed_password.getText().toString();


                if(Validator.isValidEmail(email)){
                    ed_user_id.setError(null);
                }else ed_user_id.setError("enter valid email Id");
                if(Validator.isEmptyText(password)){
                    ed_password.setError("enter valid password");
                }else ed_password.setError(null);

                ArrayList<String> stringsPass =  employeeAuthQuery.getEmployeeCheck(email);
                if(Validator.isValidEmail(email)&& !Validator.isEmptyText(password)&&
                        stringsPass.size()!=0){

                    if(stringsPass.get(0).equals(password)){
                        SharedPreferences.Editor editor =  sharedpreferences.edit();
                        editor.putBoolean("UserLoggedIn", true);
                        editor.putString("email", email);
                        editor.putString("password",password);
                        editor.commit();
                       startActivity(intentLogin);
                    }else Toast.makeText(getApplicationContext() , "enter valid password",
                            Toast.LENGTH_LONG).show();
                }else Toast.makeText(getApplicationContext() , "No such user in database",
                        Toast.LENGTH_LONG).show();

            }
        });



        // Inserting details ........
        Log.d("Insert: ", "Inserting ..");

        employeeDetailQuery.addEmployeDetails(new EmployeeDetail("ArdeeCity",
                "+9991223445", "android developer", "IT", "$82183"));
        employeeDetailQuery.addEmployeDetails(new EmployeeDetail("DFL gurgaon",
                "+9921232222","ios developer", "IT", "$82435"));
        employeeDetailQuery.addEmployeDetails(new EmployeeDetail("South Delhi",
                "+9921232452","software engineer", "IT", "$52435"));
        employeeDetailQuery.addEmployeDetails(new EmployeeDetail("meerut ,UP",
                "+9341232222","ios developer", "IT", "$45435"));
        // Reading all details.....
        Log.d("Reading: ", "Reading all employee details..");
        List<EmployeeDetail> conts = employeeDetailQuery.getAllEmplayeeDetails();

        for (EmployeeDetail cn1 : conts) {
            String log = "Id: "+cn1.getId()
                    +" ,Name: " + cn1.getPhoneNumber()
                    +",phone: " + cn1.getPhoneNumber();
            Log.d("Name: ", log);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
