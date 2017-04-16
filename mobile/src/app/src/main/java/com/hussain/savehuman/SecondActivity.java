package com.hussain.savehuman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button sign_up;
    EditText name,id,mobile_no,email,password;
    AutoCompleteTextView bloodGroup,discipline;
    String bloodGroup_suggest[] ={"A+","A-","AB+","AB-","B+","B-","O+","O-"};
    String discipline_suggest[]={"CSE","CHEMISTY","PHYSICS","ARCHITECTURE","URP","MATH","STATISTICS","ECE",
    "Agrotechnology","BGE","Environmental Science","FMRT","FWT","Pharmacy","Soil Science",
    "Business Administration","Human Resource Management",
    "Development Studies","Economics","Journalism","Sociology",
    "History and Civilization","Bangla","English","Law and Justice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        myDb=new DatabaseHelper(SecondActivity.this);
        AutoText();
        Adddata();
    }
    public void AutoText()
    {
        discipline= (AutoCompleteTextView) findViewById(R.id.discipline_editText);
        bloodGroup= (AutoCompleteTextView) findViewById(R.id.Complete_editText);
        ArrayAdapter adapter_blood=new ArrayAdapter(SecondActivity.this,android.R.layout.select_dialog_item,bloodGroup_suggest);
        ArrayAdapter adapter_discipline=new ArrayAdapter(SecondActivity.this,android.R.layout.select_dialog_item,discipline_suggest);
        discipline.setThreshold(1);
        bloodGroup.setThreshold(1);
        discipline.setAdapter(adapter_discipline);
        bloodGroup.setAdapter(adapter_blood);
    }
    public void Adddata()
    {
        sign_up= (Button) findViewById(R.id.signUp_button);
        name= (EditText) findViewById(R.id.uname_editText);
        id= (EditText) findViewById(R.id.id_editText);
        discipline= (AutoCompleteTextView) findViewById(R.id.discipline_editText);
        bloodGroup= (AutoCompleteTextView) findViewById(R.id.Complete_editText);
        mobile_no= (EditText) findViewById(R.id.contact_editText);
        email= (EditText) findViewById(R.id.email_editText);
        password= (EditText) findViewById(R.id.pass_editText);
        //----------insert the details in database--------
       sign_up.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              /* String nameStr=name.getText().toString();
               int idStr=Integer.parseInt(id.getText().toString());
               String disciplineStr=discipline.getText().toString();
               String bloodStr=bloodGroup.getText().toString();
               int mobileStr=Integer.parseInt(mobile_no.getText().toString());
               String emailStr=email.getText().toString();
               String passwordStr=password.getText().toString();
               //----------insert the details in database--------
               Contact c=new Contact();
               c.setUname(nameStr);
               c.setId(idStr);
               c.setDiscipline(disciplineStr);
               c.setBloodgroup(bloodStr);
               c.setMobile(mobileStr);
               c.setPassword(passwordStr);
               c.setEmail(emailStr);*/

               boolean isInserted=myDb.insertData(Integer.parseInt(id.getText().toString()),name.getText().toString(),discipline.getText().toString().toLowerCase(),bloodGroup.getText().toString().toLowerCase(),Integer.parseInt(mobile_no.getText().toString()),email.getText().toString(),password.getText().toString());

               /* boolean antable = myDb.insertNew(Integer.parseInt(id.getText().toString()));*/


               if(isInserted==true)
               {
                   Toast.makeText(SecondActivity.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
                   Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                   startActivity(intent);
               }
               else
               {
                   Toast.makeText(SecondActivity.this,"DATA NOT INSERTED",Toast.LENGTH_LONG).show();
               }
           }
       });
    }
}
