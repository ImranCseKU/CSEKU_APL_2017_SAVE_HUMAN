package com.hussain.savehuman;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    Button signInbutton,rButton;
    EditText id_text,pass_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DatabaseHelper(MainActivity.this);
        onButtonClick();
    }
    public void onButtonClick()
    {
        signInbutton= (Button) findViewById(R.id.sign_button);
        rButton= (Button) findViewById(R.id.regester_button);
        id_text= (EditText) findViewById(R.id.Id_editText);
        pass_text= (EditText) findViewById(R.id.pass_editText);
        signInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor result=myDb.searchPassword();
                if(result.getCount()==0)
                {
                    Toast.makeText(MainActivity.this,"ERROR:Invalid UserId or Password",Toast.LENGTH_LONG).show();
                    return;
                }
                else
                {
                    while(result.moveToNext())
                    {
                        String USER_ID=result.getString(0);
                        if(USER_ID.equals(id_text.getText().toString()))
                        {
                            String password=result.getString(6);
                            if(password.equals(pass_text.getText().toString()))
                            {
                                Toast.makeText(MainActivity.this,"UserId or Password is correct",Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(MainActivity.this,ThirdActivity.class);
                                intent.putExtra("USERID",USER_ID);
                                startActivity(intent);
                                break;
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Invalid UserId or Password",Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                    }
                }
            }
        });
        rButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
