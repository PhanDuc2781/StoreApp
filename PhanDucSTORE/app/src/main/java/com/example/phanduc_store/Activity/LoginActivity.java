package com.example.phanduc_store.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phanduc_store.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText edt_Email , edt_Pass ;
    private TextView txt_SignIn , txt_SignUp ;

    // Format email
    String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    FirebaseAuth auth ;
    FirebaseUser fuser ;
    private ProgressBar progressBar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_Email = findViewById(R.id.edt_EmailLogin);
        edt_Pass = findViewById(R.id.edt_PassLogin);
        txt_SignIn = findViewById(R.id.text_SignIn);
        txt_SignUp = findViewById(R.id.text_SignUp);
        progressBar = findViewById(R.id.progress);

        auth = FirebaseAuth.getInstance();
        fuser = auth.getCurrentUser();
        progressBar.setVisibility(View.GONE);
        txt_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
                finish();
            }
        });


        txt_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                login();
            }
        });
    }

    private void login(){
        String email    = edt_Email.getText().toString();
        String passWord = edt_Pass.getText().toString();

        if(TextUtils.isEmpty(edt_Email.getText().toString())){
            edt_Email.setError("Vui lòng điền email");
            progressBar.setVisibility(View.GONE);

        }else if(TextUtils.isEmpty(edt_Pass.getText().toString())){
            edt_Pass.setError("Vui lòng điền mật khẩu");
            progressBar.setVisibility(View.GONE);

        }else if(edt_Pass.getText().toString().length()<6){
            edt_Pass.setError("Mật khẩu không đúng");
            progressBar.setVisibility(View.GONE);

        }else if (!email.matches(emailPattern)){
            edt_Email.setError("Vui lòng nhập đúng email");
            progressBar.setVisibility(View.GONE);

        } else{
            auth.signInWithEmailAndPassword(email , passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this , "Đăng nhập thành công ", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }else {
                        Toast.makeText(LoginActivity.this , "Sai tài khoản hoặc mật khẩu . Thử lại! ", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });
        }
    }
}