package com.xpressy.firsttest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.xpressy.firsttest.Model.MovieModel;
import com.xpressy.firsttest.R;

import java.util.ArrayList;

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.ViewHolder> {

    Context context;
    ArrayList<MovieModel> arrayList;
    String URL = "https://www.themoviedb.org/t/p/original";
//    Onclick onclick;

    public MovieGridAdapter(@NonNull Context context, ArrayList<MovieModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

//    @Override
//    public int getCount() {
//        return arrayList.size();
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View v = convertView;
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = inflater.inflate(R.layout.custom_griditem, null);
//        ImageView imageView = (ImageView) v.findViewById(R.id.poster);
//        Picasso.get().load(URL  + arrayList.get(position).getImage()).into(imageView);
//        return v;
//    }

    //    public MovieGridAdapter(Context context, ArrayList<MovieModel> arrayList) {
//        super(context, arrayList);
//        this.context = context;
//        this.arrayList = arrayList;
//    }

//    public interface Onclick{
//        void watchClicked(int movieId);
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_griditem, parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(URL  + arrayList.get(holder.getAdapterPosition()).getImage()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.poster);
        }
    }
}
