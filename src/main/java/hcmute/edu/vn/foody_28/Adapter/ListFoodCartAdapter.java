package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.foody_28.DatabaseConnection.FoodOfStoreDB;
import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.OrderDetail;
import hcmute.edu.vn.foody_28.R;

public class ListFoodCartAdapter extends RecyclerView.Adapter<ListFoodCartAdapter.ListFoodViewHolder>
{
    private Context context;
    private List<OrderDetail> orderDetailList;

    public  void setData(List<OrderDetail> orderDetailList)
    {
        this.orderDetailList = orderDetailList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ListFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_cart,parent,false);
        context = parent.getContext();
        return new ListFoodCartAdapter.ListFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListFoodViewHolder holder, int position)
    {
        OrderDetail orderDetail = orderDetailList.get(position);
        if(orderDetail == null)
        {
            return;
        }
        FoodOfStoreDB foodOfStoreDB = new FoodOfStoreDB(context);
        FoodOfStore foodOfStore = new FoodOfStore();
        foodOfStore = foodOfStoreDB.find_food_image(orderDetail.getOrderID());

        Glide.with(context).load(foodOfStore.getFoodImage()).into(holder.food_image_cart);
        holder.food_name_cart.setText(orderDetail.getAmount() + foodOfStore.getFoodName());

        double price = orderDetail.getAmount() * foodOfStore.getPriceRoot();
        holder.food_price_cart.setText(Double.toString(price));
    }

    @Override
    public int getItemCount()
    {
        if(orderDetailList != null)
        {
            return orderDetailList.size();
        }
        return 0;
    }

    public class ListFoodViewHolder extends RecyclerView.ViewHolder
    {

        private TextView food_name_cart, food_price_cart;
        private ImageView food_image_cart;
        private RecyclerView recycle_food;

        public ListFoodViewHolder(@NonNull View itemView)
        {
            super(itemView);
            food_image_cart = itemView.findViewById(R.id.food_img_cart_2);
            food_name_cart = itemView.findViewById(R.id.food_name_cart);
            food_price_cart = itemView.findViewById(R.id.food_price_cart);
        }
    }
}
