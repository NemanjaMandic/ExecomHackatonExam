package com.example.nemus.execomhackaton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nemus.execomhackaton.R;
import com.example.nemus.execomhackaton.dao.UserDao;
import com.example.nemus.execomhackaton.model.User;
import com.example.nemus.execomhackaton.util.InputValidation;
import com.example.nemus.execomhackaton.util.ToastUtil;

public class RegisterActivity extends AppCompatActivity {

    private Button buttonRegister;
    private EditText regFirstName, regLastName, regUserName, regEmail, regPassword;
    private InputValidation inputValidation;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegister = (Button) findViewById(R.id.registerButton);
        regFirstName = (EditText) findViewById(R.id.regFirstNameText);
        regLastName = (EditText) findViewById(R.id.regLastNameText);
        regUserName = (EditText) findViewById(R.id.regUserNameText);
        regEmail = (EditText) findViewById(R.id.regEmailText);
        regPassword = (EditText) findViewById(R.id.regPasswordText);

        inputValidation = new InputValidation(this);

    }

    public void register(View view) {
        if(view.getId() == R.id.registerButton){
            insertDataToDatabase();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void insertDataToDatabase() {
        if(!inputValidation.isEmptyField(regFirstName, "Enter first name")){
            return;
        }
        if(!inputValidation.isEmptyField(regLastName, "Enter last name")){
            return;
        }
        if(!inputValidation.isEmptyField(regUserName, "Enter user name")){
            return;
        }
        if(!inputValidation.isEmptyField(regEmail, "Enter email")){
            return;
        }
        if(!inputValidation.isEmptyField(regPassword, "Enter password")){
            return;
        }

        if(!userDao.checkUser(regEmail.getText().toString())){
            User user = new User();
            user.setFirstName(regFirstName.getText().toString());
            user.setLastName(regLastName.getText().toString());
            user.setUserName(regUserName.getText().toString());
            user.setEmail(regEmail.getText().toString());
            user.setPassword(regPassword.getText().toString());

            userDao.addUser(user);

            ToastUtil.longToast(this, "You succesfully registered");
            emptyInputs();
        }else{
            ToastUtil.shortToast(this, "Some error occured");
        }
    }

    private void emptyInputs() {
        regFirstName.setText(null);
        regLastName.setText(null);
        regUserName.setText(null);
        regEmail.setText(null);
        regPassword.setText(null);
    }


}
