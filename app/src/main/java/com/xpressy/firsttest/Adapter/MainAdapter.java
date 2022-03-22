package com.xpressy.firsttest.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xpressy.firsttest.Model.UserModel;
import com.xpressy.firsttest.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    Context context;
    ArrayList<UserModel> arrayList;
    onHoldListner holdListner;

    public MainAdapter(Context context, ArrayList<UserModel> arrayList, onHoldListner holdListner) {
        this.context = context;
        this.arrayList = arrayList;
        this.holdListner = holdListner;
    }

    public interface onHoldListner {
        void onLongClickListner(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.v.startAnimation(AnimationUtils.loadAnimation(context, R.anim.move));
        holder.UserID.setText(String.valueOf(arrayList.get(holder.getAdapterPosition()).getUserID()));
        holder.name.setText(arrayList.get(holder.getAdapterPosition()).getUsername());
        holder.password.setText(String.valueOf(arrayList.get(holder.getAdapterPosition()).getPassword()));
        holder.email.setText(arrayList.get(holder.getAdapterPosition()).getEmail());
        holder.phone.setText(String.valueOf(arrayList.get(holder.getAdapterPosition()).getPhone()));
        holder.age.setText(String.valueOf(arrayList.get(holder.getAdapterPosition()).getAge()));
        holder.country.setText(arrayList.get(holder.getAdapterPosition()).getCountry());
        holder.date.setText(arrayList.get(holder.getAdapterPosition()).getDate());
        holder.time.setText(arrayList.get(holder.getAdapterPosition()).getTime());
        holder.rating.setText(String.valueOf(arrayList.get(holder.getAdapterPosition()).getRating()));
        if (arrayList.get(holder.getAdapterPosition()).getGender()==2131231231){
            holder.gender.setText("Male");
        }
        else {
            holder.gender.setText("Female");
        }

        holder.v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (holdListner!=null){
                    holdListner.onLongClickListner(holder.getAdapterPosition());
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View v;
        TextView name, password, UserID, email, phone, age, country, date, time, rating, gender;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvNameFORM);
            password = itemView.findViewById(R.id.tvpassFORM);
            email = itemView.findViewById(R.id.tvemailFORM);
            phone = itemView.findViewById(R.id.tvphoneFORM);
            age = itemView.findViewById(R.id.tvageFORM);
            country = itemView.findViewById(R.id.tvcountryFORM);
            date = itemView.findViewById(R.id.tvdateFORM);
            time = itemView.findViewById(R.id.tvtimeFORM);
            rating = itemView.findViewById(R.id.tvratingFORM);
            gender = itemView.findViewById(R.id.tvgenderFORM);
            UserID = itemView.findViewById(R.id.tvID);
            v = itemView;
        }
    }

}
