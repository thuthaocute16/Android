package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.foody_28.Activity.Home.StoreActivity;
import hcmute.edu.vn.foody_28.DatabaseConnection.VoucherOfStoreDB;
import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.Domain.Voucher;
import hcmute.edu.vn.foody_28.R;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder>
{
    private Context context;
    private List<Voucher> voucherList;

    public VoucherAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Voucher> list)
    {
        this.voucherList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public VoucherAdapter.VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voucher,parent,false);
        return new VoucherAdapter.VoucherViewHolder(view);
    }

    public VoucherAdapter(List<Voucher> voucherList) {
        this.voucherList = voucherList;
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherAdapter.VoucherViewHolder holder, int position)
    {
        Voucher voucher = voucherList.get(position);
        if(voucher == null)
        {
            return;
        }
        Glide.with(context).load(voucher.getVoucherImage()).into(holder.img_voucher);
        holder.tv_title.setText(voucher.getVoucherName());

        VoucherOfStoreDB voucherOfStoreDB = new VoucherOfStoreDB(context);
        List<Store> store = voucherOfStoreDB.get_list(voucher.getVoucherID());
        /**holder.img_voucher.setOnClickListener(view -> {
            OnclickRecycleView();
        });*/

    }

    private void OnclickRecycleView(List<Store> store)
    {
        Intent intent = new Intent(context, StoreActivity.class);
        Bundle bundle = new Bundle();
        //bundle.putSerializable("store_information" , storelist);
        context.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(voucherList != null)
        {
            return voucherList.size();
        }
        return 0;
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView img_voucher;
        private TextView tv_title;

        public VoucherViewHolder(@NonNull View itemView)
        {
            super(itemView);
            img_voucher = itemView.findViewById(R.id.img_voucher_item);
            tv_title = itemView.findViewById(R.id.tv_voucher_item);
        }
    }
}
