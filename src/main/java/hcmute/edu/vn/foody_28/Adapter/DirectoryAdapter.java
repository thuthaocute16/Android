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

import hcmute.edu.vn.foody_28.Domain.Directory;
import hcmute.edu.vn.foody_28.R;

public class DirectoryAdapter extends RecyclerView.Adapter<DirectoryAdapter.DirectoryViewHolder>
{
    private Context context;
    private List<Directory> directoryList;

    public DirectoryAdapter(Context context) {
        this.context = context;
    }

    public DirectoryAdapter(Context context, List<Directory> directoryList)
    {
        this.context = context;
        this.directoryList = directoryList;
    }

    public  void setData(List<Directory> list)
    {
        this.directoryList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public DirectoryAdapter.DirectoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_directory,parent,false);
        return new DirectoryAdapter.DirectoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DirectoryAdapter.DirectoryViewHolder holder, int position) {
        Directory directory = directoryList.get(position);
        if(directoryList == null)
        {
            return;
        }
        else
        {
            holder.img_item_directory.setImageResource(directory.getResource_ID());
            holder.tv_title_directory.setText(directory.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        if(directoryList != null)
        {
            return directoryList.size();
        }
        return 0;
    }

    public class DirectoryViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView img_item_directory;
        private TextView tv_title_directory;

        public DirectoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_item_directory = itemView.findViewById(R.id.img_directory);
            tv_title_directory = itemView.findViewById(R.id.tv_directory);
        }
    }
}
