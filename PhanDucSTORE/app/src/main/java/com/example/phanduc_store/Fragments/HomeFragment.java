package com.example.phanduc_store.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phanduc_store.Activity.ViewProductsActivity;
import com.example.phanduc_store.Adapter.HomeCategoryAdapter;
import com.example.phanduc_store.Adapter.PopularProductsAdapter;
import com.example.phanduc_store.Adapter.RecommendsAdapter;
import com.example.phanduc_store.Model.HomeCategory;
import com.example.phanduc_store.Model.PopularProducts;
import com.example.phanduc_store.Model.Recommends;
import com.example.phanduc_store.Model.ViewProducts;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    List<PopularProducts> productsList ;
    List<HomeCategory> homeCategoriesList ;
    List<Recommends> recommendsList ;
    List<ViewProducts> viewProducts ;

    HomeCategoryAdapter homeCategoryAdapter ;
    RecommendsAdapter recommendsAdapter ;
    PopularProductsAdapter popularProductsAdapter ;

    RecyclerView recycler_PopularProducts ;
    RecyclerView recycler_HomeCategories;
    RecyclerView recycler_Recommends ;

    FirebaseFirestore firestore ;
    ScrollView view ;
    ProgressBar progressBar ;

//    TextView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home , container , false);

        firestore = FirebaseFirestore.getInstance();
//        imageView = root.findViewById(R.id.text_viewAllProducts);

//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), ViewProductsActivity.class);
//                intent.putExtra("all" , viewProducts.size());
//            }
//        });
        //Init
        recycler_PopularProducts = root.findViewById(R.id.recycle_popular_Products);
        recycler_HomeCategories = root.findViewById(R.id.recycle_Categories);
        recycler_Recommends = root.findViewById(R.id.recycle_Recommends);
        view = root.findViewById(R.id.scollView);
        progressBar = root.findViewById(R.id.progessBar);

        progressBar.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);

        //
        recycler_PopularProducts.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.HORIZONTAL , false));
        productsList = new ArrayList<>();
        popularProductsAdapter = new PopularProductsAdapter(getActivity() , productsList);
        recycler_PopularProducts.setAdapter(popularProductsAdapter);
        readDataPopularProducts();

        recycler_HomeCategories.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.HORIZONTAL , false));
        homeCategoriesList = new ArrayList<>();
        homeCategoryAdapter = new HomeCategoryAdapter(getActivity() , homeCategoriesList);
        recycler_HomeCategories.setAdapter(homeCategoryAdapter);
        readDataHomeCategory();

        recycler_Recommends.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.HORIZONTAL , false));
        recommendsList = new ArrayList<>();
        recommendsAdapter = new RecommendsAdapter(getActivity() , recommendsList);
        recycler_Recommends.setAdapter(recommendsAdapter);
        reDataRecommends();

        return root;
    }

//Read data Recommends Products from Firestore
    private void reDataRecommends() {
        firestore.collection("Recommends")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Recommends recommends = document.toObject(Recommends.class);
                                recommendsList.add(recommends);
                                Collections.shuffle(recommendsList);
                                progressBar.setVisibility(View.GONE);
                                view.setVisibility(View.VISIBLE);
                               recommendsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity() , task.getException().getMessage() , Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

    private void readDataHomeCategory() {
        firestore.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                homeCategoriesList.add(homeCategory);
                                Collections.shuffle(homeCategoriesList);
                                progressBar.setVisibility(View.GONE);
                                view.setVisibility(View.VISIBLE);
                                homeCategoryAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity() , task.getException().getMessage() , Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
//Read data from FireStore Popular Products
    private void readDataPopularProducts() {

        firestore.collection("PopularProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PopularProducts popularProducts = document.toObject(PopularProducts.class);
                                productsList.add(popularProducts);
                                Collections.shuffle(productsList);
                                progressBar.setVisibility(View.GONE);
                                view.setVisibility(View.VISIBLE);
                                popularProductsAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity() , task.getException().getMessage() , Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

}