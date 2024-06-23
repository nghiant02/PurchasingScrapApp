package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.utils.Constants;
import com.example.purchasingscrapapp.utils.FirebaseUtils;
import com.example.purchasingscrapapp.utils.ValidationUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonRegister;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        progressBar = findViewById(R.id.progressBar);

        buttonRegister.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        if (!ValidationUtils.isValidEmail(editTextEmail) || !ValidationUtils.isValidPassword(editTextPassword) || !ValidationUtils.isValidConfirmPassword(editTextPassword, editTextConfirmPassword)) {
            return;
        }

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            FirebaseUtils.sendVerificationEmail(user, task1 -> {
                                if (task1.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, Constants.FIREBASE_VERIFICATION_EMAIL_SENT, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Failed to send verification email: " + task1.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        Toast.makeText(RegisterActivity.this, "Registration successful! Please verify your email before logging in.", Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
