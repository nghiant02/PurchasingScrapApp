package com.example.purchasingscrapapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.viewmodel.UserViewModel;
import com.example.purchasingscrapapp.utils.ValidationUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerButton, forgotPasswordButton;
    private ProgressBar progressBar;
    private UserViewModel userViewModel;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        registerButton = findViewById(R.id.textViewRegister);
        forgotPasswordButton = findViewById(R.id.textViewForgotPassword);
        progressBar = findViewById(R.id.progressBar);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        db = FirebaseFirestore.getInstance();

        loginButton.setOnClickListener(v -> {
            if (ValidationUtils.isValidEmail(emailEditText) && ValidationUtils.isValidPassword(passwordEditText)) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);
                userViewModel.loginUser(this, email, password, progressBar).observe(this, authResult -> {
                    progressBar.setVisibility(View.GONE);
                    if (authResult != null && authResult.getUser().isEmailVerified()) {
                        FirebaseUser user = authResult.getUser();
                        db.collection("users").document(user.getUid()).get().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    String status = document.getString("status");
                                    if ("active".equals(status)) {
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Your account is not active.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                        });
                    } else if (authResult != null) {
                        Toast.makeText(LoginActivity.this, "Please verify your email before logging in.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(LoginActivity.this, "Please enter valid email and password.", Toast.LENGTH_SHORT).show();
            }
        });

        registerButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
        forgotPasswordButton.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));
    }
}
