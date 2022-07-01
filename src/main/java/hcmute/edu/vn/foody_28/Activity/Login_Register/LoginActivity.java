package hcmute.edu.vn.foody_28.Activity.Login_Register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
//import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
//import com.facebook.GraphResponse;
//import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;


import org.json.JSONException;
//import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.concurrent.atomic.AtomicReference;

import hcmute.edu.vn.foody_28.Activity.Home.HomeActivity;
import hcmute.edu.vn.foody_28.DatabaseConnection.AccountDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.DatabaseHelper;
import hcmute.edu.vn.foody_28.R;

public class LoginActivity extends Activity
{

    EditText username,password;
    Button login,register;

    LinearLayout facebook , gmail,phone,zalo;
    TextView btn_forget_pass;
    CheckBox cb_remember;

    DatabaseHelper databaseHelper;
    AccountDB accountDB;

    SharedPreferences sharedPreferences;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        try
        {
            Intent intent = getIntent();
            String user = intent.getStringExtra("Username");
            String pass = intent.getStringExtra("Password");

            username.setText(user);
            password.setText(pass);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Not data", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


        databaseHelper = new DatabaseHelper(LoginActivity.this);
        databaseHelper.createDataBase();
        databaseHelper.openDataBase();

        accountDB = new AccountDB(this);

        Reflect();
        remember_login();
        OnclickLogin();
        OnclickRegister();
        loginFacebook();

    }

    private void loginFacebook()
    {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("Success","Login");
                Toast.makeText(LoginActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() 
            {
                Toast.makeText(LoginActivity.this, "Login Cancel", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(@NonNull FacebookException e) {

                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        facebook.setOnClickListener(view -> LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("email", "public_profile")));
        check_login_facebook_status();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /*AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(@Nullable AccessToken accessToken, @Nullable AccessToken accessToken1) {
            if(accessToken1 == null)
            {
                //txt.setText("");
                Toast.makeText(LoginActivity.this, "User Logout", Toast.LENGTH_SHORT).show();
            }
            else
            {
                load_user_profile_facebook(accessToken1);
            }
        }
    };*/

    private void load_user_profile_facebook(AccessToken accessToken)
    {
        List<String> text = new ArrayList<>();
        @SuppressLint("CheckResult") GraphRequest request = GraphRequest.newMeRequest(accessToken, (jsonObject, graphResponse) -> {
            try
            {
                assert jsonObject != null;
                String first_name = jsonObject.getString("first_name");
                String last_name = jsonObject.getString("last_name");
                String email = jsonObject.getString("email");
                String id = jsonObject.getString("id");

                //String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.dontAnimate();

                //Glide.with(LoginActivity.this).load(image_url).into(imageview);

                text.set(0,first_name + ',' + last_name + ',' + email + ',' + id);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        });


        Bundle parameter = new Bundle();
        parameter.putString("fields", text.get(0));
        request.setParameters(parameter);
        request.executeAsync();

    }

    private void remember_login()
    {
        sharedPreferences = getSharedPreferences("data_login",MODE_PRIVATE);
        username.setText(sharedPreferences.getString("user_name",""));
        password.setText(sharedPreferences.getString("pass_word" , ""));
        cb_remember.setChecked(sharedPreferences.getBoolean("remember" , false));
    }
    private void Reflect()
    {
        username = findViewById(R.id.edit_username);
        password = findViewById(R.id.edit_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);
        facebook = findViewById(R.id.btn_facebook);
        gmail = findViewById(R.id.btn_gmail);
        phone = findViewById(R.id.btn_phone);
        zalo = findViewById(R.id.btn_zalo);
        btn_forget_pass = findViewById(R.id.btn_forget);
        cb_remember = findViewById(R.id.cb_remember);
    }

    private void OnclickLogin()
    {

        login.setOnClickListener(view ->
        {
            String user_name = username.getText().toString().trim();
            String pass_word = password.getText().toString().trim();

            boolean check_login = accountDB.checkAccount(user_name,pass_word);
            if (check_login)
            {
                if(cb_remember.isChecked())
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("user_name" , user_name);
                    editor.putString("pass_word" , pass_word);
                    editor.putBoolean("remember" , true);
                    editor.apply();

                }
                else
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove("user_name");
                    editor.remove("pass_word");
                    editor.remove("remember");
                    editor.apply();
                }
                Toast.makeText(this, user_name, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("Username" , user_name);
                startActivity(intent);
            }
            else
            {
                showDialog_Register_account();
            }
        });
    }

    private void OnclickRegister()
    {
        register.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onDestroy()
    {
        accountDB.close();
        super.onDestroy();
    }

    private void showDialog_Register_account()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Register New Account").setMessage("Information Login is incorrect!!");
        builder.setCancelable(true);
        builder.setIcon(R.drawable.icon_user);

        builder.setNegativeButton("OK", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void check_login_facebook_status()
    {
        if(AccessToken.getCurrentAccessToken() != null)
        {
            load_user_profile_facebook(AccessToken.getCurrentAccessToken());
        }
    }

}