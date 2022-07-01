package hcmute.edu.vn.foody_28.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import hcmute.edu.vn.foody_28.Activity.Home.HomeActivity;
import hcmute.edu.vn.foody_28.Activity.Login_Register.LoginActivity;
import hcmute.edu.vn.foody_28.R;

public class MainActivity extends Activity {

    ProgressBar pb_downloading,pb_cube_grib;
    TextView tv_version;
    TextView tv_downloading;
    private int percent;
    LinearLayout home_btn,saved_btn,notice_btn,account_btn;
    FloatingActionButton oder_btn;

    private static String username;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        // Set Reflect views
        Reflect();

        // Set Custom ProgressBar _ CubeGrid
        pb_cube_grib.setIndeterminateDrawable(new CubeGrid());

        // Set version
        String version = getVersion();
        tv_version.setText(tv_version.getText() + "   " + version);

        // Set time for progressBar
        pb_downloading.setVisibility(View.INVISIBLE);
        pb_downloading.setMax(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pb_downloading.setMin(0);
        }
        pb_downloading.setProgress(0);


        CountDownTimer timer = new CountDownTimer(10000,100)
        {
            @Override
            public void onTick(long l)
            {
                percent = pb_downloading.getProgress();
                if (percent >= pb_downloading.getMax())
                {
                    percent = 0;
                }
                System.out.println(pb_downloading.getProgress());
                pb_downloading.setProgress(percent + 1);
                tv_downloading.setText("Downloading ... " + percent + "%");
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        };

        timer.start();

        check_status_login();

    }

    private String getVersion()
    {
        return "1.0";
    }

    private void Reflect()
    {
        pb_downloading = findViewById(R.id.pb_download);
        pb_cube_grib = findViewById(R.id.pb_cube_grib);
        tv_version = findViewById(R.id.tv_version);
        tv_downloading = findViewById(R.id.tv_downloading);
    }

    private void check_status_login()
    {
        sharedPreferences = getSharedPreferences("data_login",MODE_PRIVATE);
        String user_name = sharedPreferences.getString("user_name","");
        Toast.makeText(this, user_name, Toast.LENGTH_SHORT).show();
    }

}