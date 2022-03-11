package com.example.phanduc_store.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phanduc_store.Activity.HomeActivity;
import com.example.phanduc_store.Activity.LoginActivity;
import com.example.phanduc_store.Model.AddToCart;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AddToCartAdapter extends RecyclerView.Adapter<AddToCartAdapter.ViewHolder> {
    private Context context;
    private List<AddToCart> list ;
    FirebaseFirestore firestore ;
    FirebaseAuth auth ;

    int totalBill ;

    public AddToCartAdapter(Context context, List<AddToCart> list) {
        this.context = context;
        this.list = list;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_cart , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.text_Name.setText(list.get(position).getProductName());
        holder.text_Price.setText(list.get(position).getProductPrice());
        holder.text_TotalPrice.setText(String.valueOf(list.get(position).getTotalPrice()) +".VND");
        holder.text_CurrentTime.setText(list.get(position).getCurrentTime());
        holder.text_CurrentDay.setText(list.get(position).getCurrentDate());

        String type = list.get(position).getType();

        if(type.equals("Eggs")){
            holder.text_Number.setText(list.get(position).getTotalProducts() + " :Quả");
        }
        if(type.equals("Milks") || type.equals("Cafes")){
            holder.text_Number.setText( list.get(position).getTotalProducts()+ " :Hộp");

        }if(type.equals("Fruits") || type.equals("Meats") ||
                type.equals("Fishs") || type.equals("Vegetables") ){
            holder.text_Number.setText(list.get(position).getTotalProducts() + " :Kg");

        }if(type.equals("Cakes")){
            holder.text_Number.setText( list.get(position).getTotalProducts() + " :Chiếc");
        }if(type.equals("Waters")){
         holder.text_Number.setText( list.get(position).getTotalProducts() + " :Chai(Lon)");
        }

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
//set icon
                        .setIcon(android.R.drawable.ic_dialog_alert)
//set title
                        .setTitle("Bạn có chắc chắn muốn xóa không ?")
//set positive button
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what would happen when positive button is clicked
                                firestore.collection("CurrentUser").document(auth.getCurrentUser()
                                        .getUid()).collection("AddToCart")
                                        .document(list.get(position).getId())
                                        .delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){
                                                    list.remove(position);
                                                    notifyDataSetChanged();
                                                    Toast.makeText(context , "Đã Xóa " ,Toast.LENGTH_LONG).show();
                                                }else{
                                                    Toast.makeText(context , task.getException().getMessage() ,Toast.LENGTH_LONG).show();
                                                }

                                            }
                                        });
                            }
                        })
//set negative button
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //set what should happen when negative button is clicked

                            }
                        })
                        .show();

                return false;
            }
        });        //Total price in my cart
        totalBill = totalBill + list.get(position).getTotalPrice();
        //Send data price
        Intent intent = new Intent("TotalBill");
        intent.putExtra("totalBill", totalBill);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text_Name , text_Price , text_Number , text_TotalPrice , text_CurrentDay ,text_CurrentTime ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_Name = itemView.findViewById(R.id.name_ProductMyCart);
            text_Price = itemView.findViewById(R.id.price_ProductMyCart);
            text_Number = itemView.findViewById(R.id.number_ProductMyCart);
            text_TotalPrice = itemView.findViewById(R.id.totalPrice_ProductMyCart);
            text_CurrentTime = itemView.findViewById(R.id.time_ProductMyCart);
            text_CurrentDay = itemView.findViewById(R.id.day_ProductMyCart);
        }
    }
}
