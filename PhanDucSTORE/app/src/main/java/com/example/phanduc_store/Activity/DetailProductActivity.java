package com.example.phanduc_store.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phanduc_store.Model.Recommends;
import com.example.phanduc_store.Model.ViewProducts;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class DetailProductActivity extends AppCompatActivity {

    private TextView text_Name, text_Price, text_Des, text_AddtoCart, text_StarRate, text_Number;
    private ImageView image_Product, image_Add, image_Minus;
    Toolbar toolbar;
    ViewProducts viewProducts = null;
    Recommends recommends = null ;


    int totalProducts = 1;
    int totalPrice = 0  ;


    FirebaseFirestore firestore ;
    FirebaseAuth auth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        text_Name = findViewById(R.id.name_DetailProducts);
        text_Price = findViewById(R.id.price_DetailProducts);
        text_Des = findViewById(R.id.descriptions_DetailProducts);
        text_StarRate = findViewById(R.id.starRate_DetailProducts);
        text_Number = findViewById(R.id.number_Products);
        text_AddtoCart = findViewById(R.id.text_addtoCart);
        image_Product = findViewById(R.id.Image_DetailProducts);
        image_Add = findViewById(R.id.add_Products);
        image_Minus = findViewById(R.id.minus_Products);

        text_Number.setText(String.valueOf(totalProducts));

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolBar_Detail);
        mToolbar.setTitle("Chi Tiết Sản Phẩm");
        mToolbar.setNavigationIcon(R.drawable.ic_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getDataViewProducts();
        getDataRecomends();

    }

    //GetData Recommends
    private void getDataRecomends() {

        final Object o_Recommends = getIntent().getSerializableExtra("detail_Recommends");


        if (o_Recommends instanceof Recommends) {
            recommends = (Recommends) o_Recommends;
        }
        if (recommends != null) {
            Picasso.with(DetailProductActivity.this).load(recommends.getImg_Url()).into(image_Product);
            text_Name.setText(recommends.getName());
            text_StarRate.setText(recommends.getStarRate());
            text_Des.setText(recommends.getDescriptions());

            String type_R = recommends.getType().toString();
            if(type_R.equals("Fruits") || type_R.equals("Vegetables") || type_R.equals("Meats") || type_R.equals("Fishs")){
                text_Price.setText("Giá: " +recommends.getPrice()+ ".VND /1kg");
                totalPrice = recommends.getPrice() * totalProducts ;
            }
            if(type_R.equals("Cafes") || type_R.equals("Milks")){
                text_Price.setText("Giá: " +recommends.getPrice()+ ".VND /1Hộp");
                totalPrice = recommends.getPrice() * totalProducts ;
            }
            if(type_R.equals("Eggs")){
                text_Price.setText("Giá: " +recommends.getPrice()+ ".VND /1Quả");
                totalPrice = recommends.getPrice() * totalProducts ;
            }
            if(type_R.equals("Cakes")){
                text_Price.setText("Giá: " +recommends.getPrice()+ ".VND /1Chiếc");
                totalPrice = recommends.getPrice() * totalProducts ;
            }
            if(type_R.equals("Waters")){
                text_Price.setText("Giá: " +recommends.getPrice()+ ".VND /1Lon_Chai");
                totalPrice = recommends.getPrice() * totalProducts ;
            }

            image_Minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(totalProducts > 1){
                        totalProducts-- ;
                        text_Number.setText(String.valueOf(totalProducts));
                        totalPrice = recommends.getPrice() * totalProducts ;
                    }else {
                        Toast.makeText(DetailProductActivity.this , "Vui lòng chọn số lượng lớn hơn 0" , Toast.LENGTH_LONG).show();
                    }
                }
            });

            image_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    totalProducts++ ;
                    text_Number.setText(String.valueOf(totalProducts));
                    totalPrice = recommends.getPrice() * totalProducts;

                }
            });

            text_AddtoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(totalProducts == 0){
                        Toast.makeText(DetailProductActivity.this , "Vui lòng chọn số lượng" , Toast.LENGTH_LONG).show();
                    }else {
                        String currentDate , currentTime ;
                        Calendar calendar = Calendar.getInstance();

                        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                        currentDate = date.format(calendar.getTime());

                        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss a");
                        currentTime = time.format(calendar.getTime());

                        final HashMap<String , Object> mapCart = new HashMap<>();
                        mapCart.put("productName" , recommends.getName());
                        mapCart.put("productPrice" , text_Price.getText().toString());
                        mapCart.put("CurrentTime" , currentTime);
                        mapCart.put("CurrentDate" , currentDate);
                        mapCart.put("TotalProducts" , text_Number.getText().toString());
                        mapCart.put("TotalPrice" , totalPrice);
                        mapCart.put("type" , recommends.getType());

                        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).
                                collection("AddToCart").add(mapCart).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DetailProductActivity.this , "Thêm thành công" , Toast.LENGTH_LONG).show();
                                    finish();
                                }else {
                                    Toast.makeText(DetailProductActivity.this , task.getException().getMessage() , Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });

        }
    }

    private void getDataViewProducts() {
        final Object o_ViewProducts = getIntent().getSerializableExtra("detail");


        if (o_ViewProducts instanceof ViewProducts) {
            viewProducts = (ViewProducts) o_ViewProducts;
        }
        if (viewProducts != null) {
            Picasso.with(DetailProductActivity.this).load(viewProducts.getImg_Url()).into(image_Product);
            text_Name.setText(viewProducts.getName());
            text_StarRate.setText(viewProducts.getStarRate());
            text_Des.setText(viewProducts.getDescriptions());

            String type_V = viewProducts.getType().toString();
            if(type_V.equals("Fruits") || type_V.equals("Vegetables") || type_V.equals("Meats") || type_V.equals("Fishs")){
                text_Price.setText("Giá: " +viewProducts.getPrice()+ ".VND /1kg");
                totalPrice = viewProducts.getPrice() * totalProducts ;
            }
            if(type_V.equals("Cafes") || type_V.equals("Milks")){
                text_Price.setText("Giá: " +viewProducts.getPrice()+ ".VND /1Hộp");
                totalPrice = viewProducts.getPrice() * totalProducts ;
            }
            if(type_V.equals("Eggs")){
                text_Price.setText("Giá: " +viewProducts.getPrice()+ ".VND /1Quả");
                totalPrice = viewProducts.getPrice() * totalProducts ;
            }
            if(type_V.equals("Cakes")){
                text_Price.setText("Giá: " +viewProducts.getPrice()+ ".VND /1Chiếc");
                totalPrice = viewProducts.getPrice() * totalProducts ;
            }
            if(type_V.equals("Waters")){
                text_Price.setText("Giá: " +viewProducts.getPrice()+ ".VND /1Chai(Lon)");
                totalPrice = viewProducts.getPrice() * totalProducts ;
            }

            image_Minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(totalProducts > 1){
                        totalProducts-- ;
                        text_Number.setText(String.valueOf(totalProducts));
                        totalPrice = viewProducts.getPrice() * totalProducts ;
                    }else {
                        Toast.makeText(DetailProductActivity.this , "Vui lòng chọn số lượng lớn hơn 0" , Toast.LENGTH_LONG).show();
                    }
                }
            });

            image_Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    totalProducts++ ;
                    text_Number.setText(String.valueOf(totalProducts));
                    totalPrice = viewProducts.getPrice() * totalProducts;

                }
            });

            text_AddtoCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(totalProducts == 0){
                        Toast.makeText(DetailProductActivity.this , "Vui lòng chọn số lượng" , Toast.LENGTH_LONG).show();
                    }else {
                        String currentDate , currentTime ;
                        Calendar calendar = Calendar.getInstance();

                        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
                        currentDate = date.format(calendar.getTime());

                        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss a");
                        currentTime = time.format(calendar.getTime());

                        final HashMap<String , Object> mapCart = new HashMap<>();
                        mapCart.put("productName" , viewProducts.getName());
                        mapCart.put("productPrice" , text_Price.getText().toString());
                        mapCart.put("CurrentTime" , currentTime);
                        mapCart.put("CurrentDate" , currentDate);
                        mapCart.put("TotalProducts" , text_Number.getText().toString());
                        mapCart.put("TotalPrice" , totalPrice);
                        mapCart.put("type" , viewProducts.getType());

                        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid()).
                                collection("AddToCart").add(mapCart).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DetailProductActivity.this , "Thêm thành công" , Toast.LENGTH_LONG).show();
                                }else {
                                    Toast.makeText(DetailProductActivity.this , task.getException().getMessage() , Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                }
            });

        }
    }

    //GetData Recommends



}