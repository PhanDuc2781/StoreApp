package com.example.phanduc_store.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.phanduc_store.Model.AddToCart;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    FirebaseAuth auth ;
    FirebaseFirestore firestore ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<AddToCart> list = (ArrayList<AddToCart>) getIntent().getSerializableExtra("myCart");

        if(list !=null && list.size() >0){
            for (AddToCart addToCart :list){


                final HashMap<String , Object> mapCart = new HashMap<>();
                mapCart.put("productName" , addToCart.getProductName());
                mapCart.put("productPrice" , addToCart.getProductPrice());
                mapCart.put("CurrentTime" , addToCart.getCurrentTime());
                mapCart.put("CurrentDate" , addToCart.getCurrentDate());
                mapCart.put("TotalProducts" , addToCart.getTotalProducts());
                mapCart.put("TotalPrice" , addToCart.getTotalPrice());
                mapCart.put("type" , addToCart.getType());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).
                        collection("MyOrder").add(mapCart).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(OrderActivity.this , "Thêm thành công" , Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(OrderActivity.this , task.getException().getMessage() , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }

    }
}