package hcmute.edu.vn.foody_28.Activity.Home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
//import android.widget.Button;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hcmute.edu.vn.foody_28.Activity.Order.CartActivity;
import hcmute.edu.vn.foody_28.Activity.Order.CartEditActivity;
import hcmute.edu.vn.foody_28.Adapter.StoreActivityAdapter;

import hcmute.edu.vn.foody_28.DatabaseConnection.DivideFoodDB;
//import hcmute.edu.vn.foody_28.DatabaseConnection.OrderDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.OrderDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.StoreOfUserDB;
//import hcmute.edu.vn.foody_28.DatabaseConnection.UserDB;
import hcmute.edu.vn.foody_28.Domain.Store;
//import hcmute.edu.vn.foody_28.Domain.StoreOfUser;
import hcmute.edu.vn.foody_28.R;

public class StoreActivity extends Activity
{
    private ImageView img_store;
    private TextView tv_address;
    private TextView tv_name_store;
    private TextView tv_rate;
    private TextView tv_place;
    private Button btn_checkout;
    private ImageView btn_edit_cart;

    public static int store_id;
    //public int user_id;

    DivideFoodDB divideFoodDB;
    //DatabaseHelper databaseHelper;


    public static String user;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);


        Reflect();

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }

        Store store = (Store) bundle.get("store_information");
        user = (String) bundle.get("Username");

        store_id = store.getStoreID();
        setData(store);
        setData_Recyclerview_food(store.getStoreID());
        //Toast.makeText(this, "Store ID : " + StoreActivity.store_id, Toast.LENGTH_SHORT).show();

        OnclickEditCart(store);
        //Toast.makeText(this, String.valueOf(store.getStoreID()), Toast.LENGTH_SHORT).show();
        OnClickCheckOut(store);

    }

    private void OnClickCheckOut(Store store)
    {
        int order_id = new OrderDB(this).getOrderID(HomeActivity.user_id , StoreActivity.store_id , 0);

        btn_checkout.setOnClickListener(view ->
        {
            Intent intent = new Intent(this, CartActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("store_cart" , store);
            bundle.putInt("Order_id" , order_id);
            intent.putExtras(bundle);
            this.startActivity(intent);
        });

    }

    private void Reflect()
    {
        btn_checkout = findViewById(R.id.btn_checkout);
        img_store = findViewById(R.id.img_store1);
        tv_address = findViewById(R.id.store_address);
        tv_name_store = findViewById(R.id.store_name);
        tv_rate = findViewById(R.id.store_rate);
        tv_place = findViewById(R.id.store_distance);
        //RecyclerView recyclerView_food = findViewById(R.id.recycle_divide_food);
        btn_edit_cart = findViewById(R.id.btn_edit_cart);
    }

    private void OnclickEditCart(Store store)
    {
        btn_edit_cart.setOnClickListener(view -> {

            int order_id = new OrderDB(this).getOrderID(HomeActivity.user_id , StoreActivity.store_id , 0);
            Intent intent = new Intent(this, CartEditActivity.class);

            intent.putExtra("StoreID" , String.valueOf(store.getStoreID()));
            intent.putExtra("OrderID" , String.valueOf(order_id));

            startActivity(intent);
        });
    }
    private void setData(Store store)
    {
        StoreOfUserDB storeOfUserDB = new StoreOfUserDB(this);
        Glide.with(this).load(storeOfUserDB.find_background_store(store.getStoreID())).into(img_store);
        tv_place.setText(R.string.distance);
        tv_rate.setText(String.valueOf(store.getRate()));
        tv_name_store.setText(store.getStoreName());
        tv_address.setText(store.getAddress());
    }

    private void setData_Recyclerview_food(int storeID)
    {
        divideFoodDB = new DivideFoodDB(this);

        StoreActivityAdapter storeActivityAdapter = new StoreActivityAdapter(this);
        RecyclerView recyclerView_category = findViewById(R.id.recycle_divide_food);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView_category.setLayoutManager(linearLayoutManager);

        storeActivityAdapter.setData(divideFoodDB.get_list_divide_food(storeID));
        recyclerView_category.setAdapter(storeActivityAdapter);
    }
    @Override
    protected void onDestroy()
    {
        divideFoodDB.close();
        super.onDestroy();
    }
}