package hcmute.edu.vn.foody_28.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Account;
import hcmute.edu.vn.foody_28.R;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder>
{

    private Context context;

    public AccountAdapter(Context context) {
        this.context = context;
    }

    private List<Account> accountList;

    public AccountAdapter(List<Account> accountList)
    {
        this.accountList = accountList;
    }

    public AccountAdapter(Context context, List<Account> accountList) {
        this.context = context;
        this.accountList = accountList;
    }

    public  void setData(List<Account> accounts)
    {
        this.accountList = accounts;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_admin_account_view,parent,false);
        return new AccountAdapter.AccountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountViewHolder holder, int position)
    {
        Account account = accountList.get(position);
        if(account == null)
        {
            return;
        }
        else
        {
            /**holder.img_item_menu.setImageResource(account.getResource_ID());
            holder.tv_title_menu.setText(account.getTitle());
             */
        }

    }

    @Override
    public int getItemCount() {
        if(accountList != null)
        {
            return accountList.size();
        }
        return 0;
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView img_item_menu;
        private TextView tv_title_menu;

        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item_menu = itemView.findViewById(R.id.img_menu_item);
            tv_title_menu = itemView.findViewById(R.id.tv_menu_item);
        }
    }
}
