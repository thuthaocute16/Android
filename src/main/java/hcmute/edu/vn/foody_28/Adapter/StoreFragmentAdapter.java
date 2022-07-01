package hcmute.edu.vn.foody_28.Adapter;

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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import hcmute.edu.vn.foody_28.Activity.Home.Fragment.BuyALotFragment;
import hcmute.edu.vn.foody_28.Activity.Home.StoreActivity;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.Domain.Voucher;
import hcmute.edu.vn.foody_28.R;

public class StoreFragmentAdapter extends RecyclerView.Adapter<StoreFragmentAdapter.ViewHolder>
{
    private List<Store> storeList;
    private Context context;
    private Fragment fragment;

    public StoreFragmentAdapter(Fragment fragment)
    {
        this.fragment = fragment;

    }

    public void setData(List<Store> list)
    {
        this.storeList = list;
        notifyDataSetChanged();
    }

    public StoreFragmentAdapter(List<Store> storeList, Context context)
    {
        this.storeList = storeList;
        this.context = context;
    }

    public StoreFragmentAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartview_tab_layout_home,parent,false);
        return new StoreFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Store store = storeList.get(position);
        if(store == null)
        {
            return;
        }
       
        Glide.with(context).load(store.getStoreImage()).into(holder.img_store);
        //holder.img_store.setImageResource(store.getResource_ID());
        holder.tv_time.setText(store.getTimeOn());
        holder.tv_rate.setText(Double. toString(store.getRate()));
        holder.tv_distance.setText("2 Km");
        holder.tv_name_store.setText(store.getStoreName());
        holder.tv_address.setText(store.getAddress());

        holder.store_info.setOnClickListener(view -> {
            OnclickStore(store);
        });
    }

    private void OnclickStore(Store store)
    {
        Intent intent = new Intent(context, StoreActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("store_information" , store);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(storeList != null)
        {
            return storeList.size();
        }
        return 0;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder
    {

        private TextView tv_name_store,tv_rate,tv_distance,tv_time,tv_address;
        private ImageView img_store;
        private CardView store_info;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            store_info = itemView.findViewById(R.id.store_info);
            tv_distance = itemView.findViewById(R.id.tv_distance);
            tv_rate = itemView.findViewById(R.id.tv_rate);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_name_store = itemView.findViewById(R.id.tv_name_store);
            img_store = itemView.findViewById(R.id.img_store);
            tv_address = itemView.findViewById(R.id.tv_address);
        }
    }
}
