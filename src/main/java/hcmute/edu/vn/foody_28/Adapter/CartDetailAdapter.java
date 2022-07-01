package hcmute.edu.vn.foody_28.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.foody_28.DatabaseConnection.FoodOfStoreDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.OrderDetailDB;
import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.OrderDetail;
import hcmute.edu.vn.foody_28.R;

public class CartDetailAdapter extends RecyclerView.Adapter<CartDetailAdapter.CartDetailViewHolder>
{
    private List<OrderDetail> orderDetailList;
    Context context;


    public CartDetailAdapter(Context context)
    {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<OrderDetail> orderDetailList)
    {
        this.orderDetailList = orderDetailList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_edit,parent,false);
        context = parent.getContext();
        return new CartDetailAdapter.CartDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartDetailViewHolder holder, int position)
    {
        OrderDetail orderDetail = orderDetailList.get(position);

        FoodOfStoreDB foodOfStoreDB = new FoodOfStoreDB(context);

        FoodOfStore foodOfStore = foodOfStoreDB.getFood(orderDetail.getFoodID());


        //holder.imageView.setImageBitmap(banner.getResource_ID());
        Glide.with(context).load(foodOfStore.getFoodImage()).into(holder.food_img_cart);
        holder.tv_price_card.setText(String.valueOf(orderDetail.getPrice()));
        holder.tv_amount_cart.setText(String.valueOf(orderDetail.getAmount()));
        holder.tv_food_name_cart.setText(String.valueOf(foodOfStore.getFoodName()));
        //holder.tv_all_price.setText(String.valueOf(new OrderDetailDB(context).getAllPrice(orderDetail.getOrderID())));


        holder.btn_add.setOnClickListener(view ->
        {
            int amount = Integer.parseInt(holder.tv_amount_cart.getText().toString());
            amount ++;
            OrderDetail or = new OrderDetail();

            or.setOrderID(orderDetail.getOrderID());
            or.setFoodID(orderDetail.getFoodID());
            or.setAmount(amount);
            or.setPrice(amount * foodOfStore.getPriceRoot());

            if(new OrderDetailDB(context).update(or))
            {
                Toast.makeText(context, "Update success", Toast.LENGTH_SHORT).show();
                holder.tv_amount_cart.setText(String.valueOf(or.getAmount()));
                holder.tv_price_card.setText(String.valueOf(or.getPrice()));
            }
            else
            {
                Toast.makeText(context, "No update", Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(context, String.valueOf(amount), Toast.LENGTH_SHORT).show();
        });

        holder.btn_sub.setOnClickListener(view ->
        {
            int amount = Integer.parseInt(holder.tv_amount_cart.getText().toString());

            if(amount == 1)
            {
                if(new OrderDetailDB(context).delete(orderDetail))
                {
                    Toast.makeText(context, "Delete success", Toast.LENGTH_SHORT).show();
                    //holder.ln_cart.setVisibility(View.GONE);
                    orderDetailList.remove(position);
                    notifyDataSetChanged();
                }
                else
                {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                amount --;
                OrderDetail or = new OrderDetail();

                or.setOrderID(orderDetail.getOrderID());
                or.setFoodID(orderDetail.getFoodID());
                or.setAmount(amount);
                or.setPrice(amount * foodOfStore.getPriceRoot());

                if(new OrderDetailDB(context).update(or))
                {
                    Toast.makeText(context, "Update success", Toast.LENGTH_SHORT).show();
                    holder.tv_amount_cart.setText(String.valueOf(or.getAmount()));
                    holder.tv_price_card.setText(String.valueOf(or.getPrice()));
                }
                else
                {
                    Toast.makeText(context, "No update", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(context, String.valueOf(amount), Toast.LENGTH_SHORT).show();

            }
        });
    }
    @Override
    public int getItemCount() {
        if(orderDetailList != null)
            return orderDetailList.size();
        return 0;
    }

    public static class CartDetailViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView food_img_cart;
        private final TextView tv_food_name_cart, tv_all_price;
        private final TextView tv_amount_cart;
        private final TextView tv_price_card;

        private final LinearLayout ln_cart;
        private final ImageView btn_add,btn_sub;


        public CartDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            ln_cart = itemView.findViewById(R.id.ln_cart_root);
            food_img_cart = itemView.findViewById(R.id.food_img_cart_2);
            tv_amount_cart = itemView.findViewById(R.id.tv_amount_cart);
            tv_food_name_cart = itemView.findViewById(R.id.tv_food_name_cart_2);
            tv_price_card = itemView.findViewById(R.id.tv_price_card_2);
            btn_add = itemView.findViewById(R.id.btn_add_cart);
            btn_sub = itemView.findViewById(R.id.btn_sub_cart);
            tv_all_price = itemView.findViewById(R.id.tv_all_price);
        }
    }


}
