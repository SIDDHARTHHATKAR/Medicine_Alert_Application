package com.i108.miedicinealert.register_login;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.i108.miedicinealert.R;

public class Register extends AppCompatActivity {

    EditText name, user1, pass, cnfrmpass,email_id;
    Button signin;
    ImageView rback;
    Cursor cursor;
    SQLiteDatabase db;
    CheckBox rshow;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DBHelper(this);

        name = (EditText) findViewById(R.id.name);
        user1 = (EditText) findViewById(R.id.user);
        email_id=(EditText) findViewById(R.id.Email_id);
        pass = (EditText) findViewById(R.id.password);
        cnfrmpass = (EditText) findViewById(R.id.cnfrmpassword);
        rshow = (CheckBox) findViewById(R.id.rshowPass);
        signin = (Button) findViewById(R.id.signin);
        rback = (ImageView) findViewById(R.id.rback);

        showPass();


        rback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, login_page.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void signinBtn(View view) {

        if(name.getText().toString().equals("")|| user1.getText().toString().equals("")||email_id.getText().toString().equals("")||
                pass.getText().toString().equals("")||cnfrmpass.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_LONG).show();
            return;
        }

        // check if both password matches
        if(!pass.getText().toString().equals(cnfrmpass.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
        }



        else {
            dbHelper.addUser(name.getText().toString(), user1.getText().toString(), email_id.getText().toString(), pass.getText().toString(), cnfrmpass.getText().toString());

            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Register.this, login_page.class);
            startActivity(intent);
        }

    }

    public void showPass(){
        rshow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    cnfrmpass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    cnfrmpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    public void onBackPressed()
    {
        Intent intent = new Intent(Register.this, login_page.class);
        startActivity(intent);
        finish();
    }

}
