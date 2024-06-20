package com.example.purchasingscrapapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.viewmodel.AuthViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class PasswordRecoveryActivity extends AppCompatActivity {
    private AuthViewModel authViewModel;
    private TextInputLayout emailInput;
    private MaterialButton resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);

        emailInput = findViewById(R.id.email_input);
        resetPasswordButton = findViewById(R.id.reset_password_button);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        resetPasswordButton.setOnClickListener(v -> resetPassword());

        observeViewModel();
    }

    private void observeViewModel() {
        authViewModel.getErrorLiveData().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        authViewModel.getResetEmailSentLiveData().observe(this, isSent -> {
            if (isSent) {
                Toast.makeText(this, "Password reset email sent. Check your email.", Toast.LENGTH_LONG).show();
                startActivity(new Intent(PasswordRecoveryActivity.this, AuthActivity.class));
                finish();
            }
        });
    }

    private void resetPassword() {
        String email = emailInput.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(this, "Email must not be empty", Toast.LENGTH_SHORT).show();
            return;
        }
        authViewModel.sendPasswordResetEmail(email);
    }
}
