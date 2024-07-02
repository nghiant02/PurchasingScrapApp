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

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText, nameEditText, phoneEditText;
    private Button registerButton;
    private TextView loginButton;
    private ProgressBar progressBar;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = findViewById(R.id.editTextEmail);
        passwordEditText = findViewById(R.id.editTextPassword);
        confirmPasswordEditText = findViewById(R.id.editTextConfirmPassword);
        nameEditText = findViewById(R.id.editTextName);
        phoneEditText = findViewById(R.id.editTextPhone);
        registerButton = findViewById(R.id.buttonRegister);
        loginButton = findViewById(R.id.textViewLogin);
        progressBar = findViewById(R.id.progressBar);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        registerButton.setOnClickListener(v -> {
            if (ValidationUtils.isValidEmail(emailEditText) && ValidationUtils.isValidPassword(passwordEditText)) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();
                String name = nameEditText.getText().toString().trim();
                String phone = phoneEditText.getText().toString().trim();

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }

                userViewModel.registerUser(this, email, password, name, phone, progressBar).observe(this, authResult -> {
                    if (authResult != null) {
                        // Registration success, redirect to login activity
                        Toast.makeText(RegisterActivity.this, "Registration successful. Please check your email for verification.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(RegisterActivity.this, "Please enter valid details.", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}
