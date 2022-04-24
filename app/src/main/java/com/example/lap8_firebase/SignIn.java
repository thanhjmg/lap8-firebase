package com.example.lap8_firebase;

import static com.example.lap8_firebase.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    private static final String TAG = "UserAuth";
    private FirebaseAuth mAuth;
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnSignIn;
    private TextView tvMoveRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        txtEmail = findViewById(R.id.editTextEmailSignIn);
        txtPassword = findViewById(R.id.editTextPassWordSignIn);
        btnSignIn = findViewById(id.btnSignIn_SignIn);
        tvMoveRegister = findViewById(id.tvRegisterClick);

        mAuth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignIn();
            }


        });

    }
    private void startSignIn() {
        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG,"SignInWithEmail:succes");

                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            String email = currentUser.getEmail();

                            Intent intent = new Intent(getApplicationContext(), FaceScreen.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        }else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}