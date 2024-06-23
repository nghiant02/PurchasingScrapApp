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
import com.example.purchasingscrapapp.utils.ValidationUtils;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private Button buttonResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        progressBar = findViewById(R.id.progressBar);

        buttonResetPassword.setOnClickListener(v -> resetPassword());
    }

    private void resetPassword() {
        if (!ValidationUtils.isValidEmail(editTextEmail)) {
            return;
        }

        String email = editTextEmail.getText().toString().trim();

        progressBar.setVisibility(View.VISIBLE);

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPasswordActivity.this, Constants.FIREBASE_PASSWORD_RESET_EMAIL_SENT, Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(ForgotPasswordActivity.this, "Password reset failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
