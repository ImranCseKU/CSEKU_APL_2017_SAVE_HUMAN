package com.hussain.savehuman;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static com.hussain.savehuman.R.id.textView;

public class SixthActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button update_btn;
    String my_id;
    EditText update_name, update_mobile_no, update_email, update_password, update_ConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);
        myDb = new DatabaseHelper(SixthActivity.this);
        onButtonClickUpdate();
    }

    public void onButtonClickUpdate() {
        update_btn = (Button) findViewById(R.id.Update_button);
        update_name = (EditText) findViewById(R.id.update_name_editText);
        update_mobile_no = (EditText) findViewById(R.id.update_contact_editText);
        update_email = (EditText) findViewById(R.id.update_email_editText);
        update_password = (EditText) findViewById(R.id.update_pass_editText);
        update_ConfirmPassword = (EditText) findViewById(R.id.update_confirm_pass_editText);
        my_id=getIntent().getStringExtra("User_id");

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = update_name.getText().toString();
                String mbl = update_mobile_no.getText().toString();
                String eml = update_email.getText().toString();
                String pass1 = update_password.getText().toString();
                String pass2 = update_ConfirmPassword.getText().toString();
                //String dated = last_date.getText().toString();



                //String new_date = String.valueOf(year)+"-"+String.valueOf(month)+"-"+String.valueOf(day);
                if (name.isEmpty() || mbl.isEmpty() || eml.isEmpty() || pass1.isEmpty() || pass2.isEmpty())
                {
                    Toast.makeText(SixthActivity.this, "Please Give All The Information", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(update_password.getText().toString().equals(update_ConfirmPassword.getText().toString()))
                    {

                        boolean isUpdate=myDb.updateData( Integer.parseInt(my_id), update_name.getText().toString(), Integer.parseInt(update_mobile_no.getText().toString()), update_email.getText().toString(), update_password.getText().toString());
                        if(isUpdate==true)
                        {
                            Toast.makeText(SixthActivity.this, "Data Updated ", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(SixthActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(SixthActivity.this, "Password not match", Toast.LENGTH_LONG).show();
                    }
                }
//               if(update_password.getText().toString().equals(update_ConfirmPassword.getText().toString()))
//                {
//
//                    boolean isUpdate=myDb.updateData( Integer.parseInt(my_id), update_name.getText().toString(), Integer.parseInt(update_mobile_no.getText().toString()), update_email.getText().toString(), update_password.getText().toString());
//                    if(isUpdate==true)
//                    {
//                        Toast.makeText(SixthActivity.this, "Data Updated ", Toast.LENGTH_LONG).show();
//                    }
//                    else
//                    {
//                        Toast.makeText(SixthActivity.this, "Data not updated", Toast.LENGTH_LONG).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(SixthActivity.this, "Password not match", Toast.LENGTH_LONG).show();
//                }
            }
        });


    }
    private int mYear,mMonth,mDay;
    public void datepicker(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(SixthActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox

                        if (year < mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (monthOfYear < mMonth && year == mYear)
                            view.updateDate(mYear,mMonth,mDay);

                        if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                            view.updateDate(mYear,mMonth,mDay);

                       // textView.setText(dayOfMonth + "-"
                         //       + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());
        dpd.show();
    }
}
