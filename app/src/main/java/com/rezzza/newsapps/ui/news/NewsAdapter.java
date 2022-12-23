package com.rezzza.newsapps.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rezzza.newsapps.R;
import com.rezzza.newsapps.entities.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.AdapterView>{

    private final ArrayList<News> listData;
    private OnSelectedListener listener;
    private Context context;

    public NewsAdapter(ArrayList<News> sources){
        listData = sources;
    }

    @NonNull
    @Override
    public AdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_adapter,parent, false);
        context = parent.getContext();
        return new AdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterView holder, int position) {
        News news = listData.get(position);
        if (news.isLoading()){
            holder.mrly_body.setVisibility(View.GONE);
            holder.shmr_load.setVisibility(View.VISIBLE);
            holder.shmr_load.startShimmerAnimation();
        }
        else {
            holder.mrly_body.setVisibility(View.VISIBLE);
            holder.shmr_load.setVisibility(View.GONE);
            holder.shmr_load.stopShimmerAnimation();

            holder.txvw_title.setText(news.getTitle());
            String release = news.getCategory()+" | "+ news.getPublishedAt();
            holder.txvw_release.setText(release);
            Glide.with(context).load(news.getUrlToImage()).into(holder.imvw_image);

            holder.mrly_body.setOnClickListener(view -> {
                if (listener != null){
                    listener.onSelected(news);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class AdapterView extends RecyclerView.ViewHolder{
        TextView txvw_title,txvw_release;
        ImageView imvw_image;
        MaterialRippleLayout mrly_body;
        ShimmerFrameLayout shmr_load;

        public AdapterView(@NonNull View itemView) {
            super(itemView);

            txvw_release = itemView.findViewById(R.id.txvw_release);
            txvw_title = itemView.findViewById(R.id.txvw_title);
            mrly_body = itemView.findViewById(R.id.mrly_body);
            imvw_image = itemView.findViewById(R.id.imvw_image);
            shmr_load = itemView.findViewById(R.id.shmr_load);
        }
    }

    public void setOnSelectedListener(OnSelectedListener listener){
        this.listener = listener;
    }
    public interface OnSelectedListener{
        void onSelected(News data);
    }

}
