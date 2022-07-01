package hcmute.edu.vn.foody_28.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import android.graphics.Paint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.foody_28.Activity.Home.HomeActivity;
import hcmute.edu.vn.foody_28.Activity.Home.StoreActivity;
import hcmute.edu.vn.foody_28.DatabaseConnection.*;
import hcmute.edu.vn.foody_28.Domain.FoodOfStore;

import hcmute.edu.vn.foody_28.Domain.OrderD;
import hcmute.edu.vn.foody_28.Domain.OrderDetail;
import hcmute.edu.vn.foody_28.R;

public class FoodOfStoreAdapter extends RecyclerView.Adapter<FoodOfStoreAdapter.FoodViewHolder>
{
    private List<FoodOfStore> foodOfStoreList;
    private Context context;


    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<FoodOfStore> foodOfStoreList)
    {
        this.foodOfStoreList = foodOfStoreList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food,parent,false);
        context = parent.getContext();
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position)
    {
        FoodOfStore foodOfStore = foodOfStoreList.get(position);
        if(foodOfStore == null)
        {
            return;
        }
        Glide.with(context).load(foodOfStore.getFoodImage()).into(holder.img_food);

        holder.food_price_root.setText(String.valueOf(foodOfStore.getPriceRoot()));

        if(foodOfStore.getIsSale() == 0)
        {
            holder.food_price_root.setText("");
            holder.food_price_sale.setText(String.valueOf((int) Math.round(foodOfStore.getPriceRoot())));
        }
        else
        {
            holder.food_price_sale.setText(String.valueOf((int) Math.round(foodOfStore.getPriceSale())));
            holder.food_price_root.setText(String.valueOf((int) Math.round(foodOfStore.getPriceRoot())));
            holder.food_price_root.setPaintFlags(holder.food_price_sale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.food_des.setText(foodOfStore.getDes());

        holder.food_name.setText(foodOfStore.getFoodName());
        holder.tv_food_id.setText(String.valueOf(foodOfStore.getFoodID()));

        //UserDB userDB = new UserDB(context);

        double cost = Double.parseDouble(holder.food_price_sale.getText().toString());
        holder.img_add.setOnClickListener(view ->
        {

            add_order(HomeActivity.user_id , StoreActivity.store_id , 0 , foodOfStore.getFoodID() , cost );
            //Toast.makeText(context, "User ID " + HomeActivity.user_id + ": Store ID: " + StoreActivity.store_id + " : Food ID : " + foodOfStore.getFoodID() + " : Cost : " + cost, Toast.LENGTH_SHORT).show();
            //Toast.makeText(context, "Successfully add cart", Toast.LENGTH_SHORT).show();

            int order_id = new OrderDB(context).getOrderID(HomeActivity.user_id , StoreActivity.store_id , 0);
            Toast.makeText(context, String.valueOf(order_id), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount()
    {
        if(foodOfStoreList != null)
        {
            return foodOfStoreList.size();
        }
        return 0;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder
    {

        private final TextView food_name;
        private final TextView food_des;
        private final TextView food_price_root;
        private final TextView food_price_sale;
        private final TextView tv_food_id;
        private final ImageView img_food;
        private final ImageView img_add;


        public FoodViewHolder(@NonNull View itemView)
        {
            super(itemView);


            food_des = itemView.findViewById(R.id.food_des);
            food_name = itemView.findViewById(R.id.food_name_cart);
            img_food = itemView.findViewById(R.id.food_img_cart_2);
            food_price_root = itemView.findViewById(R.id.food_price_root);
            food_price_sale = itemView.findViewById(R.id.food_price_sale);

            img_add = itemView.findViewById(R.id.image_add_cart);

            tv_food_id = itemView.findViewById(R.id.tv_food_id);
        }
    }

    public void add_order(int user_id , int store_id , int active , int food_id , double price_root)
    {
        OrderDB orderDB = new OrderDB(context);
        OrderDetailDB orderDetailDB = new OrderDetailDB(context);
        FoodOfStoreDB foodOfStoreDB = new FoodOfStoreDB(context);

        if(orderDB.checkOrder(user_id, store_id, active))
        {
            System.out.println("Tồn tại 1 order");
            int order_id = new OrderDB(context).getOrderID(HomeActivity.user_id , StoreActivity.store_id , 0);

            if(orderDetailDB.check_food_existed(food_id, order_id))
            {
                System.out.println("Ton tai food");
                int amount = orderDetailDB.get_amount_current_food(food_id,order_id);
                amount ++;


                OrderDetail or = new OrderDetail();
                or.setOrderID(order_id);
                or.setFoodID(food_id);
                or.setAmount(amount);
                or.setPrice(amount * price_root);

                System.out.println(or.getOrderID());
                System.out.println(or.getFoodID());

                System.out.println(or.getAmount());
                System.out.println(or.getPrice());

                if(orderDetailDB.update(or))
                {
                    Toast.makeText(context, "Insert successfully", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {

                System.out.println("Khong ton tai food");

                OrderDetail or = new OrderDetail();
                or.setOrderID(order_id);
                or.setFoodID(food_id);
                or.setAmount(1);
                or.setPrice(1 * foodOfStoreDB.get_price(food_id,store_id));

                System.out.println(or.getOrderID());
                System.out.println(or.getFoodID());
                System.out.println(or.getAmount());
                System.out.println(or.getPrice());

                if(orderDetailDB.insert_order(or))
                {
                    Toast.makeText(context, "Insert successfully", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            System.out.println("Không Tồn tại 1 order");


            OrderD orderD = new OrderD(user_id,store_id,0);

            System.out.println(orderD.getOrderID());
            System.out.println(orderD.getStoreID());
            System.out.println(orderD.getActive());
            System.out.println(orderD.getUserID());

            if(orderDB.insertOrder(orderD))
            {
                System.out.println("Add thanh cong 1 order");
            }
        }
    }

}
