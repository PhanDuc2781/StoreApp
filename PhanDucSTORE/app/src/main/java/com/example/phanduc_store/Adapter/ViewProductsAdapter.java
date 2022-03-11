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

import com.example.phanduc_store.Activity.DetailProductActivity;
import com.example.phanduc_store.Model.ViewProducts;
import com.example.phanduc_store.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewProductsAdapter extends RecyclerView.Adapter<ViewProductsAdapter.ViewHolder> {

    private Context context;
    private List<ViewProducts> viewProductsList ;

    public ViewProductsAdapter(Context context, List<ViewProducts> viewProductsList) {
        this.context = context;
        this.viewProductsList = viewProductsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_products ,parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.with(context).load(viewProductsList.get(position).getImg_Url()).into(holder.image_ViewProducts);

        holder.text_NameViewProducts.setText(viewProductsList.get(position).getName());
        holder.text_DescriptionsViewProducts.setText(viewProductsList.get(position).getDescriptions());
        holder.text_StarRateViewProducts.setText(viewProductsList.get(position).getStarRate());

        if(viewProductsList.get(position).getType().equals("Eggs")){
            holder.text_Price.setText("Giá: " + viewProductsList.get(position).getPrice() + ".VND / quả");
        }
        if(viewProductsList.get(position).getType().equals("Milks")){
            holder.text_Price.setText("Giá: " + viewProductsList.get(position).getPrice() + ".VND /1 hộp");

        }if(viewProductsList.get(position).getType().equals("Fruits") || viewProductsList.get(position).getType().equals("Meats") ||
                viewProductsList.get(position).getType().equals("Fishs") || viewProductsList.get(position).getType().equals("Vegetables") ){
            holder.text_Price.setText("Giá: " + viewProductsList.get(position).getPrice() + ".VND /1kg");

        }if(viewProductsList.get(position).getType().equals("Cafes")){
            holder.text_Price.setText("Giá: " + viewProductsList.get(position).getPrice() + ".VND /1 hộp");
        }if(viewProductsList.get(position).getType().equals("Cakes")){
            holder.text_Price.setText("Giá: " + viewProductsList.get(position).getPrice() + ".VND /1 chiếc");
        }if(viewProductsList.get(position).getType().equals("Waters")){
            holder.text_Price.setText("Giá: " + viewProductsList.get(position).getPrice() + ".VND /1 lon-chai");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , DetailProductActivity.class);
                intent.putExtra("detail",  viewProductsList.get(position));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return viewProductsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_ViewProducts;
        TextView text_NameViewProducts , text_DescriptionsViewProducts , text_Price , text_StarRateViewProducts;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image_ViewProducts = itemView.findViewById(R.id.Image_ViewProducts);
            text_NameViewProducts = itemView.findViewById(R.id.Name_ViewProducts);
            text_DescriptionsViewProducts = itemView.findViewById(R.id.Descpistion_ViewProduct);
            text_Price = itemView.findViewById(R.id.Price_ViewProducts);
            text_StarRateViewProducts = itemView.findViewById(R.id.Star_Rate_ViewProducts);

        }
    }
}
