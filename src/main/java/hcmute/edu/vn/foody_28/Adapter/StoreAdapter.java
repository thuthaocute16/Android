package hcmute.edu.vn.foody_28.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import hcmute.edu.vn.foody_28.Activity.Home.HomeActivity;
import hcmute.edu.vn.foody_28.Activity.Home.StoreActivity;
import hcmute.edu.vn.foody_28.Activity.Order.CartActivity;
import hcmute.edu.vn.foody_28.DatabaseConnection.StoreOfUserDB;
import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.Domain.StoreOfUser;
import hcmute.edu.vn.foody_28.R;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder>
{
    private List<Store> storeList;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public  void setData(List<Store> list)
    {
        this.storeList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store,parent,false);
        context = parent.getContext();
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position)
    {
        Store store = storeList.get(position);
        if(store == null)
        {
            return;
        }
        //holder.img_store.setImageResource(store.get());

        StoreOfUserDB storeOfUserDB = new StoreOfUserDB(context);

        //Glide.with(context).load(storeOfUserDB.find_background_store(store.getStoreID())).into(holder.img_store);
        Glide.with(context).load(store.getStoreImage()).into(holder.img_store);
        holder.tv_title.setText(store.getStoreName());

        holder.ln_store.setOnClickListener(view ->
                OnclickStore(store));

    }

    @Override
    public int getItemCount() {
        if(storeList != null)
        {
            return storeList.size();
        }
        return 0;
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder
    {
        private final ImageView img_store;
        private final TextView tv_title;
        private final LinearLayout ln_store;

        public StoreViewHolder(@NonNull View itemView)
        {
            super(itemView);
            img_store = itemView.findViewById(R.id.img_store_item);
            tv_title = itemView.findViewById(R.id.tv_store_item);
            ln_store = itemView.findViewById(R.id.ln_store_str);
        }
    }

    private void OnclickStore(Store store)
    {

        Intent intent = new Intent(context, StoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("store_information" , store);
        bundle.putString("Username" , "thuthao");
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
