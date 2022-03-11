package com.example.phanduc_store.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phanduc_store.Adapter.CategoryAdapter;
import com.example.phanduc_store.Model.Categories;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CatogeryFragment extends Fragment {
    List<Categories> categoriesList ;
    CategoryAdapter adapter ;
    RecyclerView recyclerView ;
    ScrollView view ;
    ProgressBar progressBar ;

    FirebaseFirestore db;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.catogery_fragment, container , false);

        db = FirebaseFirestore.getInstance();

        //Init
        recyclerView = root.findViewById(R.id.rec_Category);
        view = root.findViewById(R.id.scool_Category);
        progressBar = root.findViewById(R.id.progress_Category);

        progressBar.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() , RecyclerView.VERTICAL , false));
        categoriesList = new ArrayList<>();
        adapter = new CategoryAdapter(getActivity() , categoriesList);
        recyclerView.setAdapter(adapter);

        //Read data fill in RecycleView

        readData();
        return root;
    }

    private void readData() {
        db.collection("Nav_Categories")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Categories categories = document.toObject(Categories.class);
                                categoriesList.add(categories);
                                progressBar.setVisibility(View.GONE);
                                view.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getActivity() , task.getException().getMessage() , Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

}