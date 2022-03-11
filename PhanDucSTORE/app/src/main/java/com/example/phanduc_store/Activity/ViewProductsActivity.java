package com.example.phanduc_store.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.phanduc_store.Adapter.ViewProductsAdapter;
import com.example.phanduc_store.Model.Categories;
import com.example.phanduc_store.Model.ViewProducts;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private List<ViewProducts> viewProductsList ;
    private ViewProductsAdapter adapter ;

    Toolbar mToolbar ;

    FirebaseFirestore firestore ;
    String type ;
    ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        firestore = FirebaseFirestore.getInstance();

        type = getIntent().getStringExtra("type");

        progressBar = findViewById(R.id.progressViewProducts);


        mToolbar = (Toolbar) findViewById(R.id.tool_Bar);
        mToolbar.setTitle("Tất Cả Sản Phẩm");
        mToolbar.setNavigationIcon(R.drawable.ic_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.recycle_ViewProducts);
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewProductsList  = new ArrayList<>();
        adapter = new ViewProductsAdapter(ViewProductsActivity.this , viewProductsList);
        recyclerView.setAdapter(adapter);

        getDataVegetables();
        getDataFruits();
        getDataCafees();
        getDataFishs();
        getDataEggs();
        getDataCakes();
        getDataMeats();
        getDataMilks();
        getDataWater();


    }

    //Get data Fruits
    private void getDataFruits(){
        if (type != null && type.equalsIgnoreCase("Fruits")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Fruits").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }

    //Get data Vegetables

    private void getDataVegetables(){
        if (type != null && type.equalsIgnoreCase("Vegetables")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Vegetables").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }

//Get data Cakes
    private void getDataCakes(){
        if (type != null && type.equalsIgnoreCase("Cakes")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Cakes").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }

    //Get data Milks
    private void getDataMilks(){
        if (type != null && type.equalsIgnoreCase("Milks")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Milks").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }
//Get Data Eggs
    private void getDataEggs(){
        if (type != null && type.equalsIgnoreCase("Eggs")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Eggs").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }
//Get Data Water
    private void getDataWater(){
        if (type != null && type.equalsIgnoreCase("Waters")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Waters").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }
//Get Data Meats
    private void getDataMeats(){
        if (type != null && type.equalsIgnoreCase("Meats")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Meats").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }
//Gets Data Fishs
    private void getDataFishs(){
        if (type != null && type.equalsIgnoreCase("Fishs")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Fishs").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
        }
    }
//Get Data Caffes
    private void getDataCafees(){
        if (type != null && type.equalsIgnoreCase("Cafes")) {

            firestore.collection("AllProducts").whereEqualTo("type" , "Cafes").get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot :task.getResult().getDocuments()){
                                    ViewProducts viewProducts = documentSnapshot.toObject(ViewProducts.class);
                                    viewProductsList.add(viewProducts);
                                    adapter.notifyDataSetChanged();
                                    progressBar.setVisibility(View.GONE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
    }

    }
}

