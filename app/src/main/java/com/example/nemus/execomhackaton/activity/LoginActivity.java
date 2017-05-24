package com.example.nemus.execomhackaton.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nemus.execomhackaton.R;
import com.example.nemus.execomhackaton.dao.UserDao;
import com.example.nemus.execomhackaton.db.DbHelper;
import com.example.nemus.execomhackaton.util.InputValidation;
import com.example.nemus.execomhackaton.util.ToastUtil;

public class LoginActivity extends AppCompatActivity {

    private Button buttonLogin;
    private EditText etUsername, etPassword;
    private TextView tvRegisterLink;
    private InputValidation inputValidation;
    private DbHelper dbHelper;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.usernameText);
        etPassword = (EditText) findViewById(R.id.passwordText);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegister);
        buttonLogin = (Button) findViewById(R.id.loginButton);

        inputValidation = new InputValidation(this);
        userDao = new UserDao(this);
    }

    public void loginUser(View view) {
       if(view.getId() ==  R.id.loginButton){
           verifyFromSQLite();

       }
    }

    private void verifyFromSQLite() {
        if(!inputValidation.isLoginInputEditTextFilled(etUsername, etPassword, "You entered incorect email/password")){
            return;
        }
        if(userDao.checkUser(etUsername.getText().toString(),
                etPassword.getText().toString())){
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("EMAIL", etUsername.getText().toString());
            emptyInputs();
            startActivity(intent);
        }else{
            ToastUtil.shortToast(this, "You got some error");
        }
    }

    private void emptyInputs() {
        etUsername.setText(null);
        etPassword.setText(null);
    }

    public void registerLink(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
