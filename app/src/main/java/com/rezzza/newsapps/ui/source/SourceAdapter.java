package com.rezzza.newsapps.ui.source;

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
import com.rezzza.newsapps.entities.Source;

import java.util.ArrayList;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.AdapterView>{

    private final ArrayList<Source> listData;
    private OnSelectedListener listener;

    SourceAdapter(ArrayList<Source> sources){
        listData = sources;
    }

    @NonNull
    @Override
    public AdapterView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.source_adapter,parent, false);
        return new AdapterView(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterView holder, int position) {
        Source source = listData.get(position);

        holder.txvw_title.setText(source.getName());

        holder.mrly_action.setOnClickListener(view -> {
            if (listener != null){
                listener.onSelected(source);
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
        void onSelected(Source data);
    }

}
