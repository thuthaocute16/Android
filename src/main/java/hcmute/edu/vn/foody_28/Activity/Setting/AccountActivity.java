package hcmute.edu.vn.foody_28.Activity.Setting;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import hcmute.edu.vn.foody_28.R;

public class AccountActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
    }
}
