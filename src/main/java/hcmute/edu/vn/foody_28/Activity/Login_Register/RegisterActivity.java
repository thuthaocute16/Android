package hcmute.edu.vn.foody_28.Activity.Login_Register;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;

import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.Date;

import hcmute.edu.vn.foody_28.Activity.Home.HomeActivity;
import hcmute.edu.vn.foody_28.DatabaseConnection.AccountDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.DatabaseHelper;
import hcmute.edu.vn.foody_28.DatabaseConnection.UserDB;
import hcmute.edu.vn.foody_28.Domain.Account;
import hcmute.edu.vn.foody_28.Domain.User;
import hcmute.edu.vn.foody_28.R;


public class RegisterActivity extends Activity
{
    EditText username,password,re_password;
    ImageView btn_home;
    Button login,register;
    CheckBox cb_policy;

    DatabaseHelper databaseHelper;
    AccountDB accountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);

        databaseHelper = new DatabaseHelper(RegisterActivity.this);
        databaseHelper.createDataBase();
        databaseHelper.openDataBase();

        accountDB = new AccountDB(this);

        Reflect();
        OnclickHome();
        OnclickLogin();

        edit_username_change();
        edit_password_change();
        edit_re_password_change();

        OnclickRegister();

    }

    private void OnclickHome()
    {
        btn_home.setOnClickListener(view ->
        {
            Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
            startActivity(intent);
        });
    }
    private void OnclickLogin()
    {
        login.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
            startActivity(intent);
        });
    }

    private void OnclickRegister()
    {

        register.setOnClickListener(view ->
        {
            if(!cb_policy.isChecked())
            {
                showDialog_Register_cb("You have to agree with our policy");
            }
            else
            {
                if(username.getError() != null)
                {
                    showDialog_Register_username(username.getError().toString());
                }

                else if(password.getError() != null)
                {
                    showDialog_Register_pass(password.getError().toString());
                }

                else if(re_password.getError() != null)
                {
                    showDialog_Register_re_pass(re_password.getError().toString());
                }
                else
                {
                    String user_name =username.getText().toString().trim();
                    String pass_word = password.getText().toString().trim();
                    String re_pass_word = re_password.getText().toString().trim();
                    SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy");
                    Date date = new Date();
                    String date_now = ft.format(date).toString();
                    Account account = new Account(user_name,pass_word,"user",1,date_now);
                    if(accountDB.insertAccount(account) == true)
                    {

                        UserDB userDB = new UserDB(this);
                        if(userDB.add_User_with_Account(account) == true)
                        {
                            Toast.makeText(this, "Insert User Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this , LoginActivity.class);
                            intent.putExtra("Username" , user_name);
                            intent.putExtra("Password" , pass_word);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    private void edit_username_change()
    {
        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String text = charSequence.toString();
                if(text.length() == 0)
                {
                    username.requestFocus();
                    username.setError("Username is not empty");
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String text = charSequence.toString();
                if(text.length() == 0)
                {
                    username.requestFocus();
                    username.setError("Username is not empty");
                }
                else
                {
                    if(!text.matches("[a-zA-Z]+") && !text.matches("[0-9]+"))
                    {
                        username.requestFocus();
                        username.setError("Username only alphabet and number character");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if(username.getText().toString().trim() == "")
                {
                    username.requestFocus();
                    username.setError("Username is not empty");
                }
            }
        });


    }
    private void edit_password_change()
    {
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String text = charSequence.toString();
                if(text.length() == 0)
                {
                    password.requestFocus();
                    password.setError("Password is not empty");
                }
                else
                {
                    if(!text.matches("[a-zA-Z]+") && !text.matches("[0-9]+"))
                    {
                        password.requestFocus();
                        password.setError("Password only alphabet and number character");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

                String text = password.getText().toString();
                if(text.length() < 6 || text.length() > 15)
                {
                    password.requestFocus();
                    password.setError("Password length only from 6 to 15 character");
                }

            }
        });
    }
    private void edit_re_password_change()
    {
        re_password.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                String text = charSequence.toString();
                if(text.length() == 0)
                {
                    re_password.requestFocus();
                    re_password.setError("Re Password is not empty");
                }
                else
                {
                    if(!text.matches("[a-zA-Z]+") && !text.matches("[0-9]+"))
                    {
                        re_password.requestFocus();
                        re_password.setError("Re Password only alphabet and number character");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                String text = password.getText().toString();
                String re_text = re_password.getText().toString();

                if(!text.equals(re_text))
                {
                    re_password.requestFocus();
                    re_password.setError("Re Password must similar to Password");
                }
            }
        });
    }
    private void Reflect()
    {
        username = findViewById(R.id.re_username);
        password = findViewById(R.id.re_password);
        re_password = findViewById(R.id.re_password_again);
        btn_home = findViewById(R.id.re_btn_home);
        login = findViewById(R.id.re_login);
        register = findViewById(R.id.re_register);
        cb_policy = findViewById(R.id.cb_policy);
        cb_policy.setChecked(true);

        username.requestFocus();
        username.setError("Username is not empty");
        password.setError("Password is not empty");
        re_password.setError("Re Password is not empty");
    }
    private void showDialog_Register_cb(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Register New Account").setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_user);

        builder.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void showDialog_Register_username(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Register New Account").setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_user);

        builder.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void showDialog_Register_pass(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Register New Account").setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_user);

        builder.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void showDialog_Register_re_pass(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Register New Account").setMessage(message);
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_user);

        builder.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}