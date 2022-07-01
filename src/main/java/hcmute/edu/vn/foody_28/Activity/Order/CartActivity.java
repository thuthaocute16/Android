package hcmute.edu.vn.foody_28.Activity.Order;

import hcmute.edu.vn.foody_28.Adapter.CartAdapter;
import hcmute.edu.vn.foody_28.DatabaseConnection.OrderDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.OrderDetailDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.UserDB;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CartActivity extends Activity
{

    private RecyclerView rv_detail;
    private TextView tv_store_name, tv_all_price_food,tv_del_fee_price,tv_all_bill,tv_fee_apply;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cart);

        rv_detail = findViewById(R.id.rv_order_detail);
        tv_store_name = findViewById(R.id.text_name_store);
        tv_all_price_food = findViewById(R.id.tv_all_price_food);
        tv_del_fee_price = findViewById(R.id.tv_del_fee_price);
        tv_fee_apply = findViewById(R.id.tv_fee_apply);
        tv_all_bill = findViewById(R.id.tv_all_bill);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
        {
            return;
        }


        Store store = (Store) bundle.get("store_cart");
        int orderID = (Integer) bundle.get("Order_id");

        //System.out.println(" " + orderID + " " + store.getStoreName());

        tv_store_name.setText(store.getStoreName());
        setData_Recyclerview_cart_edit(store.getStoreID() , orderID);

        tv_all_price_food.setText(String.valueOf(new OrderDetailDB(this).getAllPrice(orderID)));
        tv_del_fee_price.setText("15000");
        tv_fee_apply.setText("5000");

        double all_bill = Double.parseDouble(tv_all_price_food.getText().toString()) + Double.parseDouble(tv_del_fee_price.getText().toString()) + Double.parseDouble(tv_fee_apply.getText().toString());

        tv_all_bill.setText(String.valueOf(all_bill));

    }

    private void setData_Recyclerview_cart_edit(int storeID , int orderID)
    {
        OrderDetailDB orderDetailDB = new OrderDetailDB(this);
        CartAdapter cartAdapter = new CartAdapter(this);
        OrderDB orderDB = new OrderDB(this);
        UserDB userDB = new UserDB(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rv_detail.setLayoutManager(linearLayoutManager);

        cartAdapter.setData(orderDetailDB.get_list(orderID));
        if(orderDetailDB.get_list(orderID).size() == 0)
        {
            Toast.makeText(this, "Not data", Toast.LENGTH_SHORT).show();
        }
        //Toast.makeText(this, String.valueOf(orderDetailDB.get_list(orderID).size()), Toast.LENGTH_SHORT).show();
        rv_detail.setAdapter(cartAdapter);
    }
}