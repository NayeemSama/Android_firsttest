package com.xpressy.firsttest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xpressy.firsttest.Model.VolleyModel;
import com.xpressy.firsttest.R;

import java.util.ArrayList;

public class VolleyAdapter extends RecyclerView.Adapter<VolleyAdapter.ViewHolder> {

    Context context;
    ArrayList<VolleyModel> arrayList;

    public VolleyAdapter(Context context, ArrayList<VolleyModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_volleyitem, parent, false);
        return new VolleyAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(arrayList.get(position).getName());
        holder.airline.setText(arrayList.get(position).getAirline());
        holder.country.setText(arrayList.get(position).getCountry());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,airline,country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameVOLLEY);
            airline = itemView.findViewById(R.id.tvairlineVOLLEY);
            country = itemView.findViewById(R.id.tvcountryVOLLEY);
        }
    }
}
