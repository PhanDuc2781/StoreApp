package com.example.phanduc_store.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.phanduc_store.Activity.ViewProductsActivity;
import com.example.phanduc_store.Model.PopularProducts;
import com.example.phanduc_store.Model.ViewProducts;
import com.example.phanduc_store.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularProductsAdapter extends RecyclerView.Adapter<PopularProductsAdapter.ViewHolder> {
    private Context context ;
    private List<PopularProducts> productsList ;

    public PopularProductsAdapter(Context context, List<PopularProducts> lists) {
        this.context = context;
        this.productsList = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_products_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(context).load(productsList.get(position).getImg_Url()).into(holder.imageView);
        holder.name.setText( productsList.get(position).getName());
        holder.descriptions.setText(productsList.get(position).getDescriptions());
        holder.starRate.setText(productsList.get(position).getStarRate());
        holder.discount.setText(productsList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ViewProductsActivity.class);
                intent.putExtra("type", productsList.get(position).getType());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       ImageView imageView ;
       TextView name , descriptions ,starRate , discount ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_popProducts);

            name = itemView.findViewById(R.id.text_namePopular_Products);
            descriptions = itemView.findViewById(R.id.text_Descriptions_PopPro);
            starRate = itemView.findViewById(R.id.popPro_Star_Rate);
            discount = itemView.findViewById(R.id.sale_PopPro);
        }
    }
}
