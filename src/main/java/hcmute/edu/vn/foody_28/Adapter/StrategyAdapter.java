package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.TransitionRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.DatabaseConnection.StoreOfStrategyDB;
import hcmute.edu.vn.foody_28.Domain.Category;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.Domain.StoreOfStrategy;
import hcmute.edu.vn.foody_28.Domain.Strategy;
import hcmute.edu.vn.foody_28.R;

public class StrategyAdapter extends RecyclerView.Adapter<StrategyAdapter.StrategyViewHolder>
{
    private Context context;
    private List<Strategy> strategyList;

    public StrategyAdapter(Context context, List<Strategy> strategyList) {
        this.context = context;
        this.strategyList = strategyList;
    }

    public StrategyAdapter(Context context)
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
    public StrategyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_recycle_2,parent,false);
        return new StrategyAdapter.StrategyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StrategyViewHolder holder, int position)
    {
        Strategy strategy = strategyList.get(position);
        if(strategy == null)
        {
            return;
        }

        List<Store> storeLis;

        StoreOfStrategyDB storeOfStrategyDB = new StoreOfStrategyDB(context);
        storeLis = storeOfStrategyDB.get_list(strategy.getStrategyID());

        holder.tv_category.setText(strategy.getStrategyName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false);
        holder.recycler_store.setLayoutManager(linearLayoutManager);

        Toast.makeText(context, storeLis.get(0).getStoreName(), Toast.LENGTH_SHORT).show();

        StoreAdapter storeAdapter = new StoreAdapter();
        storeAdapter.setData(storeLis);
        holder.recycler_store.setAdapter(storeAdapter);
    }
    @Override
    public int getItemCount() {
        return 0;
    }

    public class StrategyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tv_category;
        private RecyclerView recycler_store;

        public StrategyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            tv_category = itemView.findViewById(R.id.tv_collection);
            recycler_store = itemView.findViewById(R.id.recycle_collection);
        }
    }
}
