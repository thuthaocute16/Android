package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import hcmute.edu.vn.foody_28.Activity.Home.StoreActivity;
import hcmute.edu.vn.foody_28.DatabaseConnection.StoreOfStrategyDB;
import hcmute.edu.vn.foody_28.Domain.Category;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.Domain.Strategy;
import hcmute.edu.vn.foody_28.R;

public class Strategy1Adapter extends RecyclerView.Adapter<Strategy1Adapter.CategoryViewHolder>
{
    private Context context;
    private List<Strategy> strategyList;

    public Strategy1Adapter(Context context, List<Strategy> strategyList)
    {
        this.context = context;
        this.strategyList = strategyList;
    }

    public Strategy1Adapter(Context context)
    {
        this.context = context;
    }

    public void setData(List<Strategy> list)
    {
        this.strategyList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_recycle_2,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position)
    {
        Strategy strategy = strategyList.get(position);
        if(strategy == null)
        {
            return;
        }

        holder.tv_category.setText(strategy.getStrategyName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.recycler_store.setLayoutManager(linearLayoutManager);

        StoreOfStrategyDB storeOfStrategyDB = new StoreOfStrategyDB(context);
        List<Store> storeList = storeOfStrategyDB.get_list(strategy.getStrategyID());
        StoreAdapter storeAdapter = new StoreAdapter();
        storeAdapter.setData(storeList);
        holder.recycler_store.setAdapter(storeAdapter);
    }

    @Override
    public int getItemCount() {
        if(strategyList != null)
            return strategyList.size();
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_category;
        private RecyclerView recycler_store;

        public CategoryViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_collection);
            recycler_store = itemView.findViewById(R.id.recycle_collection);
        }
    }

    private void OnclickStore(Store store)
    {
        Intent intent = new Intent(context, StoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("store_information" , store);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
