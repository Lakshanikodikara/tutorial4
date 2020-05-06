package com.example.tutorial4mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorial4mad.R;
import com.example.tutorial4mad.Database.DBHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText name, password;
    Button selectAll, add, delete, update, signin;
    DBHandler mDatabaseHelper;
    private static final String TAG = ".MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper= new DBHandler(this);
        name = findViewById(R.id.user_name_edit_text);
        password = findViewById(R.id.password_edit_text);
        selectAll = findViewById(R.id.select_all_btn);
        add = findViewById(R.id.add_btn);
        delete = findViewById(R.id.delete_btn);
        update = findViewById(R.id.update_btn);
        signin = findViewById(R.id.sign_in_btn);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameInput = name.getText().toString();
                String passwordInput = password.getText().toString();

                if(!TextUtils.isEmpty(userNameInput) && !TextUtils.isEmpty(passwordInput)){
                    boolean insertion = mDatabaseHelper.addInfo(userNameInput, passwordInput);
                    if(insertion){
                        Toast.makeText(getApplicationContext(),"New user added to the database successfully",Toast.LENGTH_SHORT).show();
                        name.setText("");
                        password.setText("");
                    }else{
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
            }
            }
        });


        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List listUserName = mDatabaseHelper.readAllInfo();

                if(!listUserName.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Users are printing as a log message", Toast.LENGTH_SHORT).show();
                    for(int i=0;i<listUserName.size();i++){
                        Log.d(TAG, "UserName: " +listUserName.get(i).toString());
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"No users in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameInput = name.getText().toString();
                String passwordInput = password.getText().toString();

                boolean signin = mDatabaseHelper.readInfo(userNameInput,passwordInput);

                if(!TextUtils.isEmpty(userNameInput) && !TextUtils.isEmpty(passwordInput)) {
                    if (signin) {
                        Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        password.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameInput = name.getText().toString();
                String passwordInput = password.getText().toString();

                boolean signin = mDatabaseHelper.readInfo(userNameInput,passwordInput);
                Integer deleteInfo = mDatabaseHelper.deleteInfo(userNameInput);

                if(!TextUtils.isEmpty(userNameInput) && !TextUtils.isEmpty(passwordInput)) {
                    if (signin) {
                        if (deleteInfo > 0) {
                            Toast.makeText(getApplicationContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                        name.setText("");
                        password.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), "No account for delete", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userNameInput = name.getText().toString();
                String passwordInput = password.getText().toString();

                if(!TextUtils.isEmpty(userNameInput) && !TextUtils.isEmpty(passwordInput)) {
                    mDatabaseHelper.updateInfo(userNameInput,passwordInput);
                    Toast.makeText(getApplicationContext(),"Updated successfully",Toast.LENGTH_SHORT).show();
                    name.setText("");
                    password.setText("");

                }else{
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}