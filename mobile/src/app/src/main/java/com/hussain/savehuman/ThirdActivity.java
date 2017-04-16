package com.hussain.savehuman;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class ThirdActivity extends AppCompatActivity {

    RadioGroup radio_group;
    RadioButton radio_button;
    Button search_button,list_button,update_button;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        myDb=new DatabaseHelper(ThirdActivity.this);
        viewAllData();
    }
    public void viewAllData() {

        radio_group = (RadioGroup) findViewById(R.id.radio_g);
        search_button = (Button) findViewById(R.id.search_btn);
        list_button = (Button) findViewById(R.id.listButton_txt);
        update_button= (Button) findViewById(R.id.Update_button);

        final String updateId=getIntent().getStringExtra("USERID");

        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selected_id = radio_group.getCheckedRadioButtonId();
                radio_button = (RadioButton) findViewById(selected_id);

                if( selected_id >=0)
                {
                    String search = "";
                    if (radio_button.getText().toString().equals("A+ BLOOD")) {
                        search = "a+";
                    } else if (radio_button.getText().toString().equals("A- BLOOD")) {
                        search = "a-";
                    } else if (radio_button.getText().toString().equals("AB+ BLOOD")) {
                        search = "ab+";
                    } else if (radio_button.getText().toString().equals("AB- BLOOD")) {
                        search = "ab-";
                    } else if (radio_button.getText().toString().equals("B+ BLOOD")) {
                        search = "b+";
                    } else if (radio_button.getText().toString().equals("B- BLOOD")) {
                        search = "b-";
                    } else if (radio_button.getText().toString().equals("O+ BLOOD")) {
                        search = "o+";
                    } else if (radio_button.getText().toString().equals("O- BLOOD")) {
                        search = "o-";
                    }

                    Cursor result = myDb.getAllData(search);
                    if (result.getCount() == 0) {
                        showMessage("Error", "Nothing Found");
                        return;
                    } else {
                        StringBuilder builder = new StringBuilder();
                        while (result.moveToNext()) {
                            builder.append("ID: " + result.getString(0) + "\n");
                            builder.append("NAME: " + result.getString(1) + "\n");
                            builder.append("DISCIPLINE: " + result.getString(2).toUpperCase() + "\n");
                            builder.append("BLOOD GROUP: " + result.getString(3).toUpperCase() + "\n");
                            builder.append("MOBILE NO: 0" + result.getString(4) + "\n");
                            builder.append("EMAIL: " + result.getString(5) + "\n\n");
                        }
                        showMessage("DATA", builder.toString());
                    }
                }
                else
                {
                    Toast.makeText(ThirdActivity.this,"First Select The Blood Group",Toast.LENGTH_LONG).show();
                }



                // Toast.makeText(ThirdActivity.this,radio_button.getText().toString(),Toast.LENGTH_LONG).show();

            }
        });


        list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selected_id=radio_group.getCheckedRadioButtonId();
                radio_button= (RadioButton) findViewById(selected_id);
                if( selected_id >=0)
                {
                    String search="";
                    if(radio_button.getText().toString().equals("A+ BLOOD"))
                    {
                        search="a+";
                    }
                    else if(radio_button.getText().toString().equals("A- BLOOD"))
                    {
                        search="a-";
                    }
                    else if(radio_button.getText().toString().equals("AB+ BLOOD"))
                    {
                        search="ab+";
                    }
                    else if(radio_button.getText().toString().equals("AB- BLOOD"))
                    {
                        search="ab-";
                    }
                    else if(radio_button.getText().toString().equals("B+ BLOOD"))
                    {
                        search="b+";
                    }
                    else if(radio_button.getText().toString().equals("B- BLOOD"))
                    {
                        search="b-";
                    }
                    else if(radio_button.getText().toString().equals("O+ BLOOD"))
                    {
                        search="o+";
                    }
                    else if(radio_button.getText().toString().equals("O- BLOOD"))
                    {
                        search="o-";
                    }


                    Intent intent=new Intent(ThirdActivity.this,FourthActivity.class);
                    intent.putExtra("GROUP",search);
                    startActivity(intent);
                    radio_group.clearCheck();
                }
                else
                {
                    Toast.makeText(ThirdActivity.this,"First Select The Blood Group",Toast.LENGTH_LONG).show();
                }


            }

        });
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ThirdActivity.this,SixthActivity.class);
                intent.putExtra("User_id",updateId);
                startActivity(intent);
                radio_group.clearCheck();
            }
        });
    }

   /* public void updateAvailability()
    {

    }

    final int[] monthDays = {31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31};

    public boolean checkAvailability(String dated)
    {
        int y,m,d;
        String y1 = dated.substring(0,3);
        String m1 = dated.substring(5,6);
        String d1 = dated.substring(8);
        y = Integer.parseInt(y1);
        m = Integer.parseInt(m1);
        d = Integer.parseInt(d1);
        Log.d("inpt",y+"-"+m+"-"+d);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        Log.e("Current",year+"-"+month+"-"+day);
        int days1 = 0;
        days1 += year*365;
        for(int i=0;i<month-1;++i)
        {
            days1 += monthDays[i];
        }
        days1 += day;

        int days2 = 0;
        days2 += y*365;
        for(int i=0;i<m-1;++i)
        {
            days2 += monthDays[i];
        }
        days2 += d;
        int days_past_since_last_blood_donation = days1 - days2;
        if (days_past_since_last_blood_donation >= 90)
        {
            return true;
        }
        else  return false;
    }*/

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(ThirdActivity.this);
        builder.setCancelable(false);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                radio_group.clearCheck();
                dialog.cancel();
            }
        });
        //builder.show();
        AlertDialog alart=builder.create();
        alart.show();

    }

}
