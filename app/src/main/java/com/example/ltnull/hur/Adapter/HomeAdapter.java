package com.example.ltnull.hur.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ltnull.hur.PojoClass.HomePojo;
import com.example.ltnull.hur.R;

import org.w3c.dom.Text;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder>{

    private Context context;
    private List<HomePojo> news ;

    public HomeAdapter(Context context, List<HomePojo> news) {
        this.context = context;
        this.news = news;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_fragment_adapter,null);

        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        HomePojo newsfeed = news.get(position);

        holder.name.setText(newsfeed.getName());
        holder.time.setText(newsfeed.getTime());
        holder.news.setText(newsfeed.getNews());

    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name;
        TextView time;
        TextView news;

        public HomeViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageHome);
            name = itemView.findViewById(R.id.nameHome);
            time = itemView.findViewById(R.id.timeHome);
            news = itemView.findViewById(R.id.newsHome);

        }
    }
}
