package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText username= (EditText) findViewById(R.id.username);
        EditText password= (EditText) findViewById(R.id.pass);

        DB =new DatabaseHelper(this);

        MaterialButton login_btn=(MaterialButton) findViewById(R.id.loginbtn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user=username.getText().toString().trim();
                String pass=password.getText().toString().trim();

                if (TextUtils.isEmpty (user) || TextUtils.isEmpty (pass) ){
                    Toast.makeText(MainActivity.this,"Please enter all the fields",Toast.LENGTH_SHORT).show();
                } else  {

                    if ( DB.checkusernamepassword(user,pass)==true){
                        Toast.makeText(MainActivity.this,"Successfull",Toast.LENGTH_SHORT).show();
                        Intent home_page= new Intent(getApplicationContext(), HomePage.class);
                        startActivity(home_page);
                    }else {Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_SHORT).show();}


                }

            }
        });

        MaterialButton signin_btn =(MaterialButton) findViewById(R.id.btn_singup);
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singin_page= new Intent(getApplicationContext(), SingIn.class);
                startActivity(singin_page);
            }
        });
    }
}