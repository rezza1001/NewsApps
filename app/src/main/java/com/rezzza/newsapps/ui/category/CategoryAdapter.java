package com.rezzza.newsapps.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.rezzza.newsapps.R;
import com.rezzza.newsapps.entities.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.AdapterView>{

    private final ArrayList<Category> listData;
    private OnSelectedListener listener;

    CategoryAdapter (ArrayList<Category> categories){
        listData = categories;
    }

    @NonNull
    @Override
    public AdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_adapter,parent, false);
        return new AdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterView holder, int position) {
        Category category = listData.get(position);

        holder.imvw_icon.setImageResource(category.getIcon());
        holder.txvw_title.setText(category.getName());

        holder.mrly_action.setOnClickListener(view -> {
            if (listener != null){
                listener.onSelected(category);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class AdapterView extends RecyclerView.ViewHolder{
        ImageView imvw_icon;
        TextView txvw_title;
        MaterialRippleLayout mrly_action;

        public AdapterView(@NonNull View itemView) {
            super(itemView);

            imvw_icon = itemView.findViewById(R.id.imvw_icon);
            txvw_title = itemView.findViewById(R.id.txvw_title);
            mrly_action = itemView.findViewById(R.id.mrly_action);
        }
    }

    public void setOnSelectedListener(OnSelectedListener listener){
        this.listener = listener;
    }
    interface OnSelectedListener{
        void onSelected(Category data);
    }

}
