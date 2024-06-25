package com.example.purchasingscrapapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import com.example.purchasingscrapapp.R;
import com.example.purchasingscrapapp.utils.FirebaseUtils;
import com.example.purchasingscrapapp.utils.ValidationUtils;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailEditText = findViewById(R.id.editTextEmail);
        resetPasswordButton = findViewById(R.id.buttonResetPassword);
        progressBar = findViewById(R.id.progressBar);

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidationUtils.isValidEmail(emailEditText)) {
                    FirebaseUtils.resetPassword(ForgotPasswordActivity.this, emailEditText.getText().toString().trim(), progressBar, task -> {
                        // Handle additional actions after password reset, if needed
                    });
                }
            }
        });
    }
}
