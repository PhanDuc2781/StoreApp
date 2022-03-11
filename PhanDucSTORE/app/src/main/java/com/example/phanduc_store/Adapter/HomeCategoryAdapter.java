package com.example.phanduc_store.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phanduc_store.Activity.ViewProductsActivity;
import com.example.phanduc_store.Model.HomeCategory;
import com.example.phanduc_store.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeCategoryAdapter extends RecyclerView.Adapter<HomeCategoryAdapter.ViewHolder> {
    private Context context ;
    private List<HomeCategory> homeCategories ;

    public HomeCategoryAdapter(Context context, List<HomeCategory> homeCategories) {
        this.context = context;
        this.homeCategories = homeCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(context).load(homeCategories.get(position).getImg_Url()).into(holder.imageView);
        holder.textView.setText(homeCategories.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ViewProductsActivity.class);
                intent.putExtra("type", homeCategories.get(position).getType());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return homeCategories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img_Category);
            textView = itemView.findViewById(R.id.txt_name_Category);
        }
    }
}
