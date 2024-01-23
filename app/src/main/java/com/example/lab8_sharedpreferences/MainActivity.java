package com.example.lab8_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText inUsername, inPassword;

    Button btnLogin, btnRegister, btnClear;

    public static final String myPref = "myPref";
    public static final String username = "username";
    public static final String password = "password";
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inUsername = findViewById(R.id.username_input);
        inPassword = findViewById(R.id.password_input);
        btnLogin = findViewById(R.id.login_button);
        btnRegister = findViewById(R.id.register_button);
        btnClear = findViewById(R.id.clear_button);

        sp = getSharedPreferences(myPref, Context.MODE_PRIVATE);

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String u = inUsername.getText().toString();
                String p = inPassword.getText().toString();
                SharedPreferences.Editor editor = sp.edit();

                editor.putString(username, u);
                editor.putString(password, p);
                editor.apply();

                Toast.makeText(MainActivity.this, "User saved", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!(sp.getAll().isEmpty())) {
                    loginCondition(v);
                }else{
                    Toast.makeText(MainActivity.this, "There is no user saved in the SharedPreferences", Toast.LENGTH_SHORT).show();
                }
            }
        }));

        btnClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.commit();
                Toast.makeText(MainActivity.this, "SharedPreferences cleared", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void loginCondition(View v) {
        Intent intent = new Intent(this, SucessPage.class);
        startActivity(intent);
    }
}