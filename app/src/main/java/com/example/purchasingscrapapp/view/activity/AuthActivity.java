// AuthActivity.java
package com.example.purchasingscrapapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.viewmodel.AuthViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class AuthActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;
    private TextInputLayout emailInput, passwordInput;
    private MaterialButton loginButton;
    private TextView registerLink, resetPasswordLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        registerLink = findViewById(R.id.register_link);
        resetPasswordLink = findViewById(R.id.reset_password_link);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        loginButton.setOnClickListener(v -> login());
        registerLink.setOnClickListener(v -> startActivity(new Intent(AuthActivity.this, RegisterActivity.class)));
        resetPasswordLink.setOnClickListener(v -> startActivity(new Intent(AuthActivity.this, PasswordRecoveryActivity.class)));

        observeViewModel();
    }

    private void observeViewModel() {
        authViewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AuthActivity.this, HomeActivity.class));
                finish();
            }
        });

        authViewModel.getErrorLiveData().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login() {
        String email = emailInput.getEditText().getText().toString().trim();
        String password = passwordInput.getEditText().getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        authViewModel.login(email, password);
    }
}
