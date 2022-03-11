package com.example.phanduc_store.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phanduc_store.Model.User;
import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    CircleImageView image_profile ;
    ImageView image_OpenCamera ;
    EditText edt_Name , edt_Email , edt_Pass , edt_RePass ;
    TextView txt_Register , txt_SignIn;

    String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    FirebaseUser fuser ;
    DatabaseReference reference ;

    public static final int PICK_IMAGE = 111 ;
    Uri imageUri;
    String imageURL ;
    ProgressDialog dialog ;
    String email , pass , name  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        image_profile       = findViewById(R.id.image_Profile);
        image_OpenCamera    = findViewById(R.id.openCamera);
        edt_Email           = findViewById(R.id.edt_Email_Register);
        edt_Name            = findViewById(R.id.edt_Name_Register);
        edt_Pass            = findViewById(R.id.edt_Pass_Register);
        edt_RePass          = findViewById(R.id.edt_Re_Pass_Register);
        txt_Register        = findViewById(R.id.text_Register);
        txt_SignIn          = findViewById(R.id.text_OpenLogin);

        auth        = FirebaseAuth.getInstance();
        fuser       = auth.getCurrentUser();
        database    = FirebaseDatabase.getInstance();
        storage     = FirebaseStorage.getInstance();


        txt_SignIn.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this , LoginActivity.class));
            finish();
        });


        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE);
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setMessage("Vui lòng đợi...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(true);

        txt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();

                name            = edt_Name.getText().toString();
                email           = edt_Email.getText().toString();
                pass            = edt_Pass.getText().toString();
                String re_pass  = edt_RePass.getText().toString();

                if(TextUtils.isEmpty(name)){
                    dialog.dismiss();
                    edt_Name.setError("Điền họ tên");
                }else if(TextUtils.isEmpty(email) || !email.matches(emailPattern)){
                    dialog.dismiss();
                    edt_Email.setError("Điền email đúng hoặc đủ");
                } else if (TextUtils.isEmpty(pass)) {
                    dialog.dismiss();
                    edt_Pass.setError("Vui lòng điền mật khẩu");
                }else if(TextUtils.isEmpty(re_pass)){
                    dialog.dismiss();
                    edt_RePass.setError("Vui lòng điền lại mật khẩu");
                }else if(!pass.equals(re_pass)){
                    dialog.dismiss();
                    edt_RePass.setError("Mật khẩu chưa khớp");
                }else if(pass.length()< 6 || re_pass.length() <6){
                    dialog.dismiss();
                    Toast.makeText(RegisterActivity.this , "Mật khẩu phải lớn hơn 6 ký tự", Toast.LENGTH_LONG).show();
                }else {
                    register();
                }
            }
        });


    }

    private void register() {
        auth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        reference = database.getReference().child("Users").child(auth.getUid());
                        StorageReference storageReference = storage.getReference().child("uploadImage").child(auth.getUid());

                        if(imageUri!=null){
                            //Check if image profile is not null
                            storageReference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                    if(task.isSuccessful()){
                                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                imageURL = uri.toString();
                                                User user = new User(auth.getUid() , name , email , imageURL);
                                                reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(email.equals(database.getReference("Users").orderByChild("email"))){
                                                            dialog.dismiss();
                                                            edt_Email.setError("Email tồn tại !");
                                                        }else if(task.isSuccessful()){
                                                            dialog.dismiss();
                                                            Toast.makeText(RegisterActivity.this , "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                                        }else {
                                                            dialog.dismiss();
                                                            Toast.makeText(RegisterActivity.this , "Lỗi đăng ký . Thử lai !", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                });
                                            }
                                        });
                                    }else {
                                        imageURL ="https://firebasestorage.googleapis.com/v0/b/phanduc-store.appspot.com/o/profile_image.jfif?alt=media&token=1ccb8d14-f096-4567-a62c-66ffcc178e8d";
                                        User user = new User(auth.getUid(), name , email, imageURL);
                                        reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(email.equals(database.getReference("Users").orderByChild("email"))){
                                                    dialog.setCanceledOnTouchOutside(true);
                                                    edt_Email.setError("Email tồn tại !");
                                                }else if(task.isSuccessful()){
                                                    Toast.makeText(RegisterActivity.this , "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE){
            if(data!=null){
                imageUri = data.getData();
                image_profile.setImageURI(imageUri);
            }
        }
    }


}