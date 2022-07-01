package hcmute.edu.vn.foody_28.Activity.Home;

import android.Manifest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


import java.util.List;
import java.util.Locale;

import hcmute.edu.vn.foody_28.Activity.Announce.AnnounceActivity;
import hcmute.edu.vn.foody_28.Activity.Order.OrderActivity;
import hcmute.edu.vn.foody_28.Activity.Saved.SavedActivity;
import hcmute.edu.vn.foody_28.Activity.Setting.AccountActivity;
import hcmute.edu.vn.foody_28.Adapter.BannerAdapter;
import hcmute.edu.vn.foody_28.Adapter.DirectoryAdapter;
import hcmute.edu.vn.foody_28.Adapter.HomeFragmentAdapter;
import hcmute.edu.vn.foody_28.Adapter.MenuAdapter;

import hcmute.edu.vn.foody_28.Adapter.Strategy1Adapter;
import hcmute.edu.vn.foody_28.Adapter.VoucherAdapter;
import hcmute.edu.vn.foody_28.DatabaseConnection.BannerDB;
//import hcmute.edu.vn.foody_28.DatabaseConnection.DatabaseHelper;
import hcmute.edu.vn.foody_28.DatabaseConnection.MenuDB;
//import hcmute.edu.vn.foody_28.DatabaseConnection.StoreDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.StrategyDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.UserDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.VoucherDB;
import hcmute.edu.vn.foody_28.Domain.Banner;

import java.io.IOException;

import hcmute.edu.vn.foody_28.R;
import me.relex.circleindicator.CircleIndicator3;

public class HomeActivity extends FragmentActivity
{

    private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1;
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000;

    private LocationManager locationManager;


    private TextView tv_address;
    private List<Banner> bannerList;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout_home_tab;

    private BannerDB bannerDB;
    //private double longitude1,latitude1;

    public static int user_id;


    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable()
    {
        @Override
        public void run() {
            int current_banner = viewPager2.getCurrentItem();
            int total_banner = bannerList.size() - 1;
            if (current_banner < total_banner) {
                current_banner++;
                viewPager2.setCurrentItem(current_banner);
            } else {
                viewPager2.setCurrentItem(0);
            }
        }
    };

    private void Reflect() {
        tv_address = findViewById(R.id.text_address);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        Intent intent = getIntent();
        String username = intent.getStringExtra("Username");

        user_id = new UserDB(this).find_user_with_username(username);
        //System.out.println("Username : " + username);


        bottomNavigation();

        Reflect();
        bannerDB = new BannerDB(this);

        create_Slider_Banner();

        setData_recycleView_menu();
        setData_recycleView_Strategy();
        setData_RecycleView_Collection();
        setData_RecycleView_Directory();
        setPage_TabLayout_Home();

        //Toast.makeText(this, "User ID : " + user_id, Toast.LENGTH_SHORT).show();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates
                (
                        LocationManager.GPS_PROVIDER,
                        MINIMUM_TIME_BETWEEN_UPDATES,
                        MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                        new MyLocationListener());

    }

    public void setPage_TabLayout_Home()
    {
        tabLayout_home_tab = findViewById(R.id.tab_layout_home);
        ViewPager viewPager_home_tab = findViewById(R.id.view_pager_home_tab);

        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager_home_tab.setAdapter(homeFragmentAdapter);

        tabLayout_home_tab.setupWithViewPager(viewPager_home_tab);
        setTabDivider();
    }

    private void setTabDivider() {
        View root = tabLayout_home_tab.getChildAt(0);
        if (root instanceof LinearLayout) {
            ((LinearLayout) root).setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setSize(2, 1);
            ((LinearLayout) root).setDividerPadding(10);
            ((LinearLayout) root).setDividerDrawable(drawable);
        }
    }


    public void setData_RecycleView_Collection()
    {
        VoucherDB voucherDB = new VoucherDB(this);
        RecyclerView recyclerView_collection = findViewById(R.id.recycle_collection);
        VoucherAdapter voucherAdapter = new VoucherAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView_collection.setLayoutManager(linearLayoutManager);

        voucherAdapter.setData(voucherDB.get_list());
        recyclerView_collection.setAdapter(voucherAdapter);
    }

    public void setData_RecycleView_Directory()
    {
        RecyclerView recyclerView_directory = findViewById(R.id.recycle_directory);
        DirectoryAdapter directoryAdapter = new DirectoryAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recyclerView_directory.setLayoutManager(linearLayoutManager);

        //directoryAdapter.setData(getDirectoryList());
        recyclerView_directory.setAdapter(directoryAdapter);
    }

    public void create_Slider_Banner() {
        viewPager2 = findViewById(R.id.viewpager_banner);
        CircleIndicator3 circleIndicator = findViewById(R.id.circle_Indicator);

        viewPager2.setOffscreenPageLimit(3);
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        bannerList = bannerDB.get_list();
        BannerAdapter bannerAdapter = new BannerAdapter(bannerList);
        viewPager2.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

    }

    public void setData_recycleView_Strategy() {
        StrategyDB strategyDB = new StrategyDB(this);

        RecyclerView recyclerView_category = findViewById(R.id.recycle_category);
        Strategy1Adapter strategyAdapter = new Strategy1Adapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView_category.setLayoutManager(linearLayoutManager);
        Toast.makeText(this, strategyDB.get_list().get(0).getStrategyName(), Toast.LENGTH_SHORT).show();

        strategyAdapter.setData(strategyDB.get_list());
        recyclerView_category.setAdapter(strategyAdapter);
    }

    public void setData_recycleView_menu() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView_menu = findViewById(R.id.recycle_menu);
        recyclerView_menu.setLayoutManager(linearLayoutManager);

        MenuDB menuDB = new MenuDB(this);
        MenuAdapter menuAdapter = new MenuAdapter(this);
        menuAdapter.setData(menuDB.get_list());

        recyclerView_menu.setAdapter(menuAdapter);
    }

    /**
     * private Bitmap convert_bitmap(int source)
     * {
     * Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), source);
     * Bitmap circularBitmap = ImageConvert.getRoundedCornerBitmap(bitmap, 100);
     * return circularBitmap;
     * }
     */


    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout bt_setting = findViewById(R.id.settingbtn);
        LinearLayout saved_btn = findViewById(R.id.profileBtn);
        LinearLayout notice_btn = findViewById(R.id.supportBtn);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
            startActivity(intent);
        });

        homeBtn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intent);
        });

        bt_setting.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
            startActivity(intent);
        });

        saved_btn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, SavedActivity.class);
            startActivity(intent);
        });

        notice_btn.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, AnnounceActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        bannerDB.close();
    }

    @Override
    protected void onResume() {
        //bannerDB.close();
        super.onResume();
        handler.postDelayed(runnable, 3000);
    }

    @Override
    protected void onDestroy() {
        bannerDB.close();
        super.onDestroy();
    }


    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }



    @SuppressLint("SetTextI18n")
    public void onClick() throws IOException {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(location != null)
        {
            double long_i = location.getLongitude();
            double lat_i = location.getLatitude();

            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this, Locale.getDefault());

            addresses = geocoder.getFromLocation(lat_i, long_i, 1);
            //String address = addresses.get(0).getAddressLine(0);
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            //String postalCode = addresses.get(0).getPostalCode();
            //String knownName = addresses.get(0).getFeatureName();

            tv_address.setText(city + state + country);
        }
    }

    private class MyLocationListener
            implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            try {
                onClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle b) {}

        @Override
        public void onProviderDisabled(String s) {}

        @Override
        public void onProviderEnabled(String s) {}
    }
}