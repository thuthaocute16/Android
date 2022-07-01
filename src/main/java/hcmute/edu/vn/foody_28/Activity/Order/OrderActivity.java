package hcmute.edu.vn.foody_28.Activity.Order;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.foody_28.Activity.Announce.AnnounceActivity;
import hcmute.edu.vn.foody_28.Activity.Home.HomeActivity;
import hcmute.edu.vn.foody_28.Activity.Saved.SavedActivity;
import hcmute.edu.vn.foody_28.Activity.Setting.AccountActivity;
import hcmute.edu.vn.foody_28.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OrderActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order);
        bottomNavigation();
    }
    private void bottomNavigation()
    {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout bt_setting = findViewById(R.id.settingbtn);
        LinearLayout saved_btn = findViewById(R.id.profileBtn);
        LinearLayout notice_btn = findViewById(R.id.supportBtn);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
            startActivity(intent);
        });

        homeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(OrderActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        bt_setting.setOnClickListener(view -> {
            Intent intent = new Intent(OrderActivity.this, AccountActivity.class);
            startActivity(intent);
        });

        saved_btn.setOnClickListener(view -> {
            Intent intent = new Intent(OrderActivity.this, SavedActivity.class);
            startActivity(intent);
        });

        notice_btn.setOnClickListener(view -> {
            Intent intent = new Intent(OrderActivity.this, AnnounceActivity.class);
            startActivity(intent);
        });

    }
}