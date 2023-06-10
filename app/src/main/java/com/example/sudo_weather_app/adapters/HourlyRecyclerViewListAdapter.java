package com.example.sudo_weather_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sudo_weather_app.MainActivity;
import com.example.sudo_weather_app.R;
import com.example.sudo_weather_app.models.HourlyModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HourlyRecyclerViewListAdapter extends RecyclerView.Adapter<HourlyRecyclerViewListAdapter.HourlyRecyclerViewHolder> {

    List<HourlyModel> hourlyModelList;
    MainActivity mainActivity;
    Boolean isSelected;

    public HourlyRecyclerViewListAdapter(List<HourlyModel> hourlyModelList, MainActivity mainActivity, Boolean isSelected) {
        this.hourlyModelList = hourlyModelList;
        this.mainActivity = mainActivity;
        this.isSelected = isSelected;
    }

    @NonNull
    @Override
    public HourlyRecyclerViewListAdapter.HourlyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HourlyRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyRecyclerViewListAdapter.HourlyRecyclerViewHolder holder, int position) {
        holder.marv_Temperature.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), hourlyModelList.get(position).temp));
        holder.marv_Days.setText(mainActivity.getDateTime(hourlyModelList.get(position).getDatetimeEpoch(),2));
        holder.marv_tempDesc.setText(hourlyModelList.get(position).conditions);
        holder.marv_Time.setText(mainActivity.getDateTime(hourlyModelList.get(position).getDatetimeEpoch(),1));
        holder.marv_Image.setImageResource(mainActivity.getIcon(hourlyModelList.get(position).getIcon()));    }

    @Override
    public int getItemCount() {
        return hourlyModelList.size();
    }


    public class HourlyRecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView marv_Days,marv_Time, marv_Temperature, marv_tempDesc;
        ImageView marv_Image;

        public HourlyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            marv_Days = itemView.findViewById(R.id.marv_Days);
            marv_Time = itemView.findViewById(R.id.marv_Time);
            marv_Temperature = itemView.findViewById(R.id.marv_Temperature);
            marv_tempDesc = itemView.findViewById(R.id.marv_tempDesc);
            marv_Image = itemView.findViewById(R.id.marv_Image);
        }
    }
}
