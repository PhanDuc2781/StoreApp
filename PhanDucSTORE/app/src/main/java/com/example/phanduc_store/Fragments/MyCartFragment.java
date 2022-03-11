package com.example.phanduc_store.Fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phanduc_store.Activity.OrderActivity;
import com.example.phanduc_store.Adapter.AddToCartAdapter;
import com.example.phanduc_store.Model.AddToCart;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.protobuf.BoolValueOrBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MyCartFragment extends Fragment {
    RecyclerView recyclerView ;
    List<AddToCart> list ;
    AddToCartAdapter addToCartAdapter ;

    FirebaseAuth auth;
    FirebaseFirestore firestore ;

    TextView text_Total ;
    ProgressBar progressBar ;
    TextView text_Buy;
    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_my_cart, container, false);


        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        progressBar = root.findViewById(R.id.pro_MyCart);


        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("TotalBill"));

        text_Total = root.findViewById(R.id.totalPrice);
        text_Buy = root.findViewById(R.id.buyProducts);
        recyclerView = root.findViewById(R.id.rec_MyCart);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        addToCartAdapter = new AddToCartAdapter(getActivity() , list);
        recyclerView.setAdapter(addToCartAdapter);

        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);

        text_Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , OrderActivity.class);
                intent.putExtra("myCart" , (Serializable) list);
                startActivity(intent);

            }
        });

        firestore.collection("CurrentUser").document(auth.getCurrentUser()
                .getUid()).collection("AddToCart")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
               if(task.isSuccessful()){
                   for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                       String id = documentSnapshot.getId();
                       AddToCart addToCart = documentSnapshot.toObject(AddToCart.class);

                       addToCart.setId(id);

                       list.add(addToCart);
                       addToCartAdapter.notifyDataSetChanged();
                       progressBar.setVisibility(View.GONE);
                       recyclerView.setVisibility(View.VISIBLE);
                   }
               }
            }
        });

        return root;
    }

    public BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int totalBill = intent.getIntExtra("totalBill" , 0);
            text_Total.setText("Tổng Tiền : " + totalBill +" .VND");
        }
    };
}