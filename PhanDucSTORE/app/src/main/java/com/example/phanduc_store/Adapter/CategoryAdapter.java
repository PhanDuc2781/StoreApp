package com.example.phanduc_store.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phanduc_store.Activity.DetailProductActivity;
import com.example.phanduc_store.Activity.ViewProductsActivity;
import com.example.phanduc_store.Model.Categories;
import com.example.phanduc_store.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context ;
    private List<Categories> categoriesList ;

    public CategoryAdapter(Context context, List<Categories> categoriesList) {
        this.context = context;
        this.categoriesList = categoriesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_cat_item , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Picasso.with(context).load(categoriesList.get(position).getImg_Url()).into(holder.imageView);
        holder.text_Name.setText(categoriesList.get(position).getName());
        holder.text_Descriptions.setText(categoriesList.get(position).getDescriptions());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , ViewProductsActivity.class);
                intent.putExtra("type", categoriesList.get(position).getType());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView ,image_ViewAll ;
        TextView text_Name  , text_Descriptions ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image_Category);
            image_ViewAll = itemView.findViewById(R.id.img_ViewAll);
            text_Name = itemView.findViewById(R.id.name_Category);
            text_Descriptions = itemView.findViewById(R.id.descpistion_Category);


        }
    }
}
