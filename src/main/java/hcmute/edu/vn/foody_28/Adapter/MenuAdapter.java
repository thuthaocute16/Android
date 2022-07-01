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

import hcmute.edu.vn.foody_28.Domain.Menu;
import hcmute.edu.vn.foody_28.R;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Menu01ViewHolder>
{

    private Context context;
    private List<Menu> menuList;

    public MenuAdapter(List<Menu> menuList)
    {
        this.menuList = menuList;
    }

    public MenuAdapter(Context context)
    {
        this.context = context;
    }

    public  void setData(List<Menu> list)
    {
        this.menuList = list;
        notifyDataSetChanged();
    }

    public MenuAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @NonNull
    @Override
    public Menu01ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_recycle_1,parent,false);
        context = parent.getContext();
        return new Menu01ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Menu01ViewHolder holder, int position) {
        Menu menu = menuList.get(position);
        if(menu == null)
        {
            return;
        }
        else
        {
            //holder.img_item_menu.setImageResource(menu.getResource_ID());
            Glide.with(context).load(menu.getMenuImage()).into(holder.img_item_menu);
            holder.tv_title_menu.setText(menu.getMenuName());
        }
    }

    @Override
    public int getItemCount() {
        if(menuList != null)
        {
            return menuList.size();
        }
        return 0;
    }

    public class Menu01ViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView img_item_menu;
        private TextView tv_title_menu;

        public Menu01ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item_menu = itemView.findViewById(R.id.img_menu_item);
            tv_title_menu = itemView.findViewById(R.id.tv_menu_item);
        }
    }
}
