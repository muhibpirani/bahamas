package com.island.islandhistory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MapQuizAdapter extends RecyclerView.Adapter<MapQuizAdapter.ViewHolder> {
    private List<String> infoModels;
    private Context context;
    private ItemClick itemClick;
    private List<Integer> usedButtons;
    private int mPosition=0;
    public MapQuizAdapter(Context context, List<String> infoModels,ItemClick itemClick,List<Integer> usedButtons) {
        this.context = context;
        this.infoModels = infoModels;
        this.itemClick=itemClick;
        this.usedButtons=usedButtons;
    }

    @Override
    public MapQuizAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.map_row, parent, false);
        return new MapQuizAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MapQuizAdapter.ViewHolder holder, int position) {
        if (usedButtons.contains(position)) {
            holder.txt_map_row.setClickable(false);
            holder.txt_map_row.setEnabled(false);
        }
        else
        {
            holder.txt_map_row.setClickable(true);
            holder.txt_map_row.setEnabled(true);
        }
        holder.txt_map_row.setText(infoModels.get(position));
    }

    @Override
    public int getItemCount() {
        return infoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_map_row;
        private ImageView img_info;

        public ViewHolder(View itemView) {
            super(itemView);
            txt_map_row = (TextView) itemView.findViewById(R.id.txt_map_row);
            txt_map_row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.onClick(txt_map_row.getText().toString(),getAdapterPosition());
                    notifyDataSetChanged();
                }
            });
        }
    }
    public void removeLastItem()
    {
        if (usedButtons!=null && usedButtons.size()>0) {
            usedButtons.remove(usedButtons.size() - 1);
            notifyDataSetChanged();
        }
    }
}