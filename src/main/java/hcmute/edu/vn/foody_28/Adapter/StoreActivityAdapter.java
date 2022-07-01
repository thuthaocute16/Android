package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.foody_28.Activity.Home.StoreActivity;
import hcmute.edu.vn.foody_28.DatabaseConnection.DivideFoodDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.FoodOfStoreDB;
import hcmute.edu.vn.foody_28.DatabaseConnection.StoreOfStrategyDB;
import hcmute.edu.vn.foody_28.Domain.DivideFood;
import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.Domain.Strategy;
import hcmute.edu.vn.foody_28.R;

public class StoreActivityAdapter extends RecyclerView.Adapter<StoreActivityAdapter.DivideViewHolder>
{
    private Context context;
    private List<FoodOfStore> foodOfStoreList;
    private List<DivideFood> divideFoodList;

    public StoreActivityAdapter(Context context, List<DivideFood> divideFoodList)
    {
        this.context = context;
        this.divideFoodList = divideFoodList;
    }

    public StoreActivityAdapter(Context context)
    {
        this.context = context;
    }

    public void setData(List<DivideFood> divideFoodList)
    {
        this.divideFoodList = divideFoodList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StoreActivityAdapter.DivideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_recycle_food,parent,false);
        context = parent.getContext();
        return new StoreActivityAdapter.DivideViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull StoreActivityAdapter.DivideViewHolder holder, int position)
    {
        DivideFood divideFood = divideFoodList.get(position);
        System.out.println(divideFood.getDivideFoodName());
        if(divideFood == null)
        {
            return;
        }
        holder.tv_divide_food.setText(divideFood.getDivideFoodName());

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL,false);
        holder.recycle_food.setLayoutManager(linearLayoutManager);

        FoodOfStoreDB foodOfStoreDB = new FoodOfStoreDB(context);
        List<FoodOfStore> foodOfStoreList = foodOfStoreDB.get_list(StoreActivity.store_id,divideFood.getDivideFoodID());

        FoodOfStoreAdapter foodOfStoreAdapter = new FoodOfStoreAdapter();
        foodOfStoreAdapter.setData(foodOfStoreList);
        holder.recycle_food.setAdapter(foodOfStoreAdapter);
    }

    @Override
    public int getItemCount() {
        if(divideFoodList != null)
            return divideFoodList.size();
        return 0;
    }

    public class DivideViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_divide_food;
        private RecyclerView recycle_food;

        public DivideViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_divide_food = itemView.findViewById(R.id.tv_divide_food);
            recycle_food = itemView.findViewById(R.id.recycle_food);
        }
    }
}
