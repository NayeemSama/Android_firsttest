package com.xpressy.firsttest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.xpressy.firsttest.Model.MovieModel;
import com.xpressy.firsttest.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    ArrayList<MovieModel> arrayList;
    String URL = "https://www.themoviedb.org/t/p/original";
    Onclick onclick;

    public MovieAdapter(Context context, ArrayList<MovieModel> arrayList, Onclick onclick) {
        this.context = context;
        this.arrayList = arrayList;
        this.onclick = onclick;
    }

    public interface Onclick{
        void watchClicked(int movieId);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_movieitem, parent, false);
        return new MovieAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move));
        holder.title.setText(arrayList.get(holder.getAdapterPosition()).getTitle());
        holder.overview.setText(arrayList.get(holder.getAdapterPosition()).getOverview());
        holder.date.setText(arrayList.get(holder.getAdapterPosition()).getDate());
        holder.rating.setText(arrayList.get(holder.getAdapterPosition()).getRating());
        holder.watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onclick!=null){
                    onclick.watchClicked(arrayList.get(holder.getAdapterPosition()).getMovieId());
                    holder.watch.setImageResource(R.drawable.ic_star);
                }
            }
        });
        Picasso.get().load(URL + arrayList.get(position).getImage()).into(holder.image);
        holder.v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View v;
        TextView title,overview,date,rating;
        ImageView image, watch;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvMovieTitle);
            overview = itemView.findViewById(R.id.tvMovieOverView);
            date = itemView.findViewById(R.id.tvMovieDate);
            rating = itemView.findViewById(R.id.tvMovieRating);
            image = itemView.findViewById(R.id.imgMovie);
            watch = itemView.findViewById(R.id.imgMovieWatch);
            v = itemView;
        }
    }
}
