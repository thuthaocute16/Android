package hcmute.edu.vn.foody_28.Activity.Order;

//import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import hcmute.edu.vn.foody_28.Adapter.CartDetailAdapter;
import hcmute.edu.vn.foody_28.DatabaseConnection.OrderDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.OrderDetailDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.UserDB;
import hcmute.edu.vn.foody_28.R;

public class CartEditActivity extends Activity {

    private RecyclerView recycle_view_cart_edit;
    private Button btn_save;
    private TextView tv_all_price;
    //private List<OrderDetail> orderDetailList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cart_edit);

        Reflect();

        Intent intent = getIntent();
        int store_id = Integer.parseInt(intent.getStringExtra("StoreID"));
        int order_id = Integer.parseInt(intent.getStringExtra("OrderID"));

        setData_Recyclerview_cart_edit(store_id , order_id);
        //Toast.makeText(this, "User_id : " + store_id, Toast.LENGTH_SHORT).show();

        //Toast.makeText(this, username, Toast.LENGTH_SHORT).show();

        tv_all_price.setText(String.valueOf(new OrderDetailDB(this).getAllPrice(order_id)));

        btn_save.setOnClickListener(view -> {
            finish();
            startActivity(getIntent());
        });
    }

    private void Reflect()
    {
        recycle_view_cart_edit = findViewById(R.id.recycle_view_cart_edit);
        btn_save = findViewById(R.id.btn_save);
        tv_all_price = findViewById(R.id.tv_all_price);

    }
    private void setData_Recyclerview_cart_edit(int storeID , int orderID)
    {
        OrderDetailDB orderDetailDB = new OrderDetailDB(this);
        CartDetailAdapter cartDetailAdapter = new CartDetailAdapter(this);
        OrderDB orderDB = new OrderDB(this);
        UserDB userDB = new UserDB(this);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recycle_view_cart_edit.setLayoutManager(linearLayoutManager);

        cartDetailAdapter.setData(orderDetailDB.get_list(orderID));
        if(orderDetailDB.get_list(orderID).size() == 0)
        {
            Toast.makeText(this, "Not data", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, String.valueOf(orderDetailDB.get_list(orderID).size()), Toast.LENGTH_SHORT).show();
        recycle_view_cart_edit.setAdapter(cartDetailAdapter);
    }
}