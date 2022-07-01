package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.foody_28.DatabaseConnection.FoodOfStoreDB;
import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.OrderDetail;
import hcmute.edu.vn.foody_28.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
{
    private List<OrderDetail> orderDetailList;
    Context context;

    public CartAdapter(Context context)
    {
        this.context = context;
    }

    public void setData(List<OrderDetail> orderDetailList)
    {
        this.orderDetailList = orderDetailList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
        context = parent.getContext();
        return new CartAdapter.CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        OrderDetail orderDetail = orderDetailList.get(position);

        FoodOfStoreDB foodOfStoreDB = new FoodOfStoreDB(context);

        FoodOfStore foodOfStore = foodOfStoreDB.getFood(orderDetail.getFoodID());


        //holder.imageView.setImageBitmap(banner.getResource_ID());
        Glide.with(context).load(foodOfStore.getFoodImage()).into(holder.food_img_cart);
        holder.tv_price_card.setText(String.valueOf(orderDetail.getPrice()));
        holder.tv_amount_cart.setText(String.valueOf(orderDetail.getAmount()));
        holder.tv_food_name_cart.setText(String.valueOf(foodOfStore.getFoodName()));

    }

    @Override
    public int getItemCount()
    {
        if(orderDetailList != null)
            return orderDetailList.size();
        return 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView food_img_cart;
        private final TextView tv_food_name_cart, tv_all_price;
        private final TextView tv_amount_cart;
        private final TextView tv_price_card;

        //private final LinearLayout ln_cart;
        //private final ImageView btn_add,btn_sub;


        public CartViewHolder(@NonNull View itemView)
        {
            super(itemView);
            //ln_cart = itemView.findViewById(R.id.ln_cart_root);
            food_img_cart = itemView.findViewById(R.id.food_img_cart_2);
            tv_amount_cart = itemView.findViewById(R.id.tv_amount_2);
            tv_food_name_cart = itemView.findViewById(R.id.tv_food_name_cart_2);
            tv_price_card = itemView.findViewById(R.id.tv_price_card_2);
            //btn_add = itemView.findViewById(R.id.btn_add_cart);
            //btn_sub = itemView.findViewById(R.id.btn_sub_cart);
            tv_all_price = itemView.findViewById(R.id.tv_all_price);
        }
    }
}
