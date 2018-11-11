package com.island.islandhistory;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.island.islandhistory.model.InfoModel;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.ViewHolder> {
    private List<InfoModel> infoModels;
    private Context context;

    public InformationAdapter(Context context,List<InfoModel> infoModels)
    {
        this.context=context;
        this.infoModels=infoModels;
    }
    @Override
    public InformationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.infor_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InformationAdapter.ViewHolder holder, int position) {
        InfoModel playerModel= infoModels.get(position);
        holder.img_info.setImageResource(playerModel.getImageId());
        holder.shield_txt.setText(playerModel.getImageText());
    }

    @Override
    public int getItemCount() {
        return infoModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView shield_txt;
        private ImageView img_info;
        public ViewHolder(View itemView) {
            super(itemView);
            img_info=(ImageView) itemView.findViewById(R.id.img_info);
            shield_txt=(TextView)itemView.findViewById(R.id.shield_txt);
        }
    }
}
