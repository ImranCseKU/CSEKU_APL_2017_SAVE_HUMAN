package com.hussain.savehuman;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class FourthActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private static ListView listView;
    String search="";
    int number, i = 0;
    int arr[] = new int[100];
    int arr_id[]=new int[100];
    ArrayList<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        myDb = new DatabaseHelper(FourthActivity.this);
        listViewData();
    }

    public void listViewData() {
        search = getIntent().getStringExtra("GROUP");
        Cursor result = myDb.getAllData(search);

        if (result.getCount() == 0) {

            return;
        } else {
            StringBuilder builder = new StringBuilder();
            while (result.moveToNext()) {
                /*String dated = result.getString(8);
                boolean isAvailable  = checkAvailability(dated);
                if(!isAvailable) continue;*/
                builder.append("ID: " + result.getString(0) + "\n");
                builder.append("NAME: " + result.getString(1) + "\n");
                builder.append("MOBILE NO: " + result.getString(4) + "\n");

                number = Integer.parseInt(result.getString(4));
                arr[i] = number;
                arr_id[i]=Integer.parseInt(result.getString(0));
                i++;

                list.add(builder.toString());
                builder.delete(0, builder.length());

            }
        }
        listView = (ListView) findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.sample, list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:0" + arr[position]));
                if (ActivityCompat.checkSelfPermission(FourthActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);*/

                StringBuilder builder = new StringBuilder();
                Cursor result = myDb.getAllData(search);

                if (result.getCount() == 0) {

                    return;
                } else {

                    while (result.moveToNext()) {
                       if( result.getString(0).equals( String.valueOf(arr_id[position]) ) )
                       {
                           builder.append("ID: " + result.getString(0) + "\n");
                           builder.append("NAME: " + result.getString(1) + "\n");
                           builder.append("DISCIPLINE: " + result.getString(2).toUpperCase() + "\n");
                           builder.append("BLOOD GROUP: " + result.getString(3).toUpperCase() + "\n");
                           builder.append("MOBILE NO: 0" + result.getString(4) + "\n");
                           builder.append("EMAIL: " + result.getString(5) + "\n\n");
                       }
                    }

                }

                Intent intent=new Intent(FourthActivity.this,FifthActivity.class);
                intent.putExtra("PhoneNumber",String.valueOf(arr[position]));
                intent.putExtra("PERSON",builder.toString());
                startActivity(intent);

            }
        });
    }
    /*
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
}
