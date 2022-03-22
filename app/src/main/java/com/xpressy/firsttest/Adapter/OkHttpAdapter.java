package com.xpressy.firsttest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xpressy.firsttest.Model.OkHttpModel;
import com.xpressy.firsttest.R;

import java.util.ArrayList;

public class OkHttpAdapter extends RecyclerView.Adapter<OkHttpAdapter.ViewHolder>{

    Context context;
    ArrayList<OkHttpModel> arrayList;

    public OkHttpAdapter(Context context, ArrayList<OkHttpModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_okhttpitem, parent, false);
        return new OkHttpAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.first.setText(arrayList.get(position).getFirstName());
        holder.last.setText(arrayList.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView first,last;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            first = itemView.findViewById(R.id.tvNameOKHTTP);
            last = itemView.findViewById(R.id.tvlastameOKHTTP);
        }
    }
}
