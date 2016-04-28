package com.metadesign.mds;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.metadesign.mds.Query.EmployeeAuthQuery;
import com.metadesign.mds.modals.EmployeeAuthentication;
import com.metadesign.mds.validator.Validator;

import java.util.ArrayList;

public class SignupActivity extends Activity {



    EditText ed_First_name;
    EditText ed_last_name;
    EditText ed_user_id;
    EditText ed_password ;
    Button btn_signup;
    EmployeeAuthQuery employeeAuthQuery;
    SharedPreferences sharedpreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
         ed_First_name = (EditText)findViewById(R.id.firstname);
         ed_last_name = (EditText)findViewById(R.id.lastname);
         ed_user_id = (EditText)findViewById(R.id.user_id);
         ed_password = (EditText)findViewById(R.id.password);
         btn_signup = (Button)findViewById(R.id.signup_btn);
        employeeAuthQuery = new EmployeeAuthQuery(this);
        sharedpreferences = getSharedPreferences("MyPREFERENCES", getApplicationContext().MODE_PRIVATE);


        final Intent intent =  new Intent(getApplicationContext() , DetailsActivity.class);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname = ed_First_name.getText().toString();
                String lastname = ed_last_name.getText().toString();
                String userid = ed_user_id.getText().toString();
                String password = ed_password.getText().toString();
                intent.putExtra("firstname", firstname);
                intent.putExtra("lastname" ,lastname);
                intent.putExtra("userid" , userid);
                if(Validator.isValidEmail(userid) && !Validator.isEmptyText(firstname)&&
                        !Validator.isEmptyText(password)){
                    ArrayList<String> user = employeeAuthQuery.getEmployeeCheck(userid);
                    if(user.size()==0){
                    employeeAuthQuery.addEmployee(new EmployeeAuthentication(firstname,
                            lastname,userid ,password)) ;
                        Toast.makeText(SignupActivity.this,"welcome to employee details page" ,
                                Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = sharedpreferences.edit();
                        editor.putString("key", "success");
                        editor.putString("firstname", firstname);
                        editor.putString("lastname", lastname);
                        editor.putString("userid", userid);
                        editor.commit();
                    startActivity(intent);
                    }else
                        Toast.makeText(SignupActivity.this, "user email already exists",
                                Toast.LENGTH_SHORT).show();

                }else if(Validator.isEmptyText(firstname)){
                    ed_First_name.setError("enter first name");
                }else if(!Validator.isValidEmail(userid)){
                    ed_user_id.setError("enter valid email id");
                }else if(Validator.isEmptyText(password)){
                    ed_First_name.setError("enter password");
                }

            }
        });

    }


}
