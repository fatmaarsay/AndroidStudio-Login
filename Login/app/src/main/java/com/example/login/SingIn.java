package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SingIn extends AppCompatActivity {
    DatabaseHelper DB;
    public SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);


        // veritabanı oluşturma işlemi
        DB = new DatabaseHelper(this);

        MaterialButton signUpButton = findViewById(R.id.btn_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSignUp();
            }
        });


    }

    private void handleSignUp() {
        EditText nicknameTxt = findViewById(R.id.username);
        EditText emailTxt = findViewById(R.id.email);
        EditText passTxt = findViewById(R.id.pass);
        EditText repassTxt = findViewById(R.id.repass);

        String nickname = nicknameTxt.getText().toString().trim();
        String email = emailTxt.getText().toString().trim();
        String password = passTxt.getText().toString().trim();
        String repassword = repassTxt.getText().toString().trim();

        try {
            if (password.equals(repassword)) {
                boolean checkUser = DB.checkusername(nickname);
                if (!checkUser) {
                    boolean insert = DB.insertData(nickname, password);
                    if (insert) {
                        Toast.makeText(SingIn.this, "Registry successfull", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(mainIntent);
                    } else {
                        Toast.makeText(SingIn.this, "Registry failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SingIn.this, "User already exists! Please log in.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(SingIn.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(SingIn.this, "Failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}