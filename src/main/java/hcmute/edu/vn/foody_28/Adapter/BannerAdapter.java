package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import hcmute.edu.vn.foody_28.Activity.Home.HomeActivity;
import hcmute.edu.vn.foody_28.Domain.Banner;
import hcmute.edu.vn.foody_28.R;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder>
{

    private final List<Banner> bannerList;
    Context context;

    public BannerAdapter(List<Banner> bannerList) {
        this.bannerList = bannerList;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner,parent,false);
        context = parent.getContext();
        return new BannerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position)
    {
        Banner banner = bannerList.get(position);
        String url = banner.getBannerLink();
        System.out.println(url);
        if(banner == null)
            return;

        //holder.imageView.setImageBitmap(banner.getResource_ID());
        Glide.with(context).load(url).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        if(bannerList != null)
            return bannerList.size();
        return 0;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView imageView;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.banner_01);
        }
    }


}
