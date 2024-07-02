package com.example.purchasingscrapapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.utils.ValidationUtils;
import com.example.purchasingscrapapp.viewmodel.UserViewModel;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView registerButton, forgotPasswordButton;
    private ProgressBar progressBar;
    private UserViewModel userViewModel;

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

        loginButton.setOnClickListener(v -> {
            if (ValidationUtils.isValidEmail(emailEditText) && ValidationUtils.isValidPassword(passwordEditText)) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();

                userViewModel.loginUser(this, email, password, progressBar).observe(this, authResult -> {
                    if (authResult != null) {
                        // Login success, handle navigation to next activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
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
