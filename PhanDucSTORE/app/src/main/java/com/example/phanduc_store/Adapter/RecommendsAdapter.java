package com.example.phanduc_store.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phanduc_store.Activity.DetailProductActivity;
import com.example.phanduc_store.Model.Recommends;
import com.example.phanduc_store.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecommendsAdapter extends RecyclerView.Adapter<RecommendsAdapter.ViewHolder> {

    private Context context;
    private List<Recommends> recommends ;

    public RecommendsAdapter(Context context, List<Recommends> recommends) {
        this.context = context;
        this.recommends = recommends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommends_items, parent , false));

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.with(context).load(recommends.get(position).getImg_Url()).into(holder.imageView);
        holder.text_Name.setText(recommends.get(position).getName());
        holder.text_Descpitions.setText(recommends.get(position).getDescriptions());
        holder.text_starRate.setText(recommends.get(position).getStarRate());

        if(recommends.get(position).getType().equals("Eggs")){
            holder.text_Price.setText("Giá: " + recommends.get(position).getPrice() + " VND /1 quả");
        }
        if(recommends.get(position).getType().equals("Milks")){
            holder.text_Price.setText("Giá: " + recommends.get(position).getPrice() + " VND /1 hộp");

        }if(recommends.get(position).getType().equals("Fruits") || recommends.get(position).getType().equals("Meats") ||
                recommends.get(position).getType().equals("Fishs") || recommends.get(position).getType().equals("Vegetables") ){
            holder.text_Price.setText("Giá: " + recommends.get(position).getPrice() + " VND /1kg");

        }if(recommends.get(position).getType().equals("Cafes")){
            holder.text_Price.setText("Giá: " + recommends.get(position).getPrice() + " VND /1 hộp");
        }if(recommends.get(position).getType().equals("Cakes")){
            holder.text_Price.setText("Giá: " + recommends.get(position).getPrice() + " VND /1 chiếc");
        }if(recommends.get(position).getType().equals("Waters")){
            holder.text_Price.setText("Giá: " + recommends.get(position).getPrice() + " VND /1 lon-chai");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , DetailProductActivity.class);
                intent.putExtra("detail_Recommends" ,  recommends.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommends.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView text_Name , text_Price , text_Descpitions , text_starRate ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_Recommends);
            text_Name = itemView.findViewById(R.id.text_nameRecommends);
            text_Price = itemView.findViewById(R.id.price_Recommends);
            text_Descpitions = itemView.findViewById(R.id.text_Descriptions_Recommends);
            text_starRate = itemView.findViewById(R.id.Recommends_Star_Rate);
        }


    }
}
