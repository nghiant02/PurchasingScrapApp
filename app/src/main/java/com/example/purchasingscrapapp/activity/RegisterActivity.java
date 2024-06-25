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

public class RegisterActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText, confirmPasswordEditText, nameEditText, phoneEditText;
    private Button registerButton;
    private ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidationUtils.isValidEmail(emailEditText) &&
                        ValidationUtils.isValidPassword(passwordEditText) &&
                        ValidationUtils.isValidConfirmPassword(passwordEditText, confirmPasswordEditText) &&
                        ValidationUtils.isValidName(nameEditText) &&
                        ValidationUtils.isValidPhone(phoneEditText)) {

                    FirebaseUtils.registerUser(RegisterActivity.this, emailEditText.getText().toString().trim(), passwordEditText.getText().toString().trim(), nameEditText.getText().toString().trim(), phoneEditText.getText().toString().trim(), progressBar, task -> {
                        // Handle additional actions after registration, if needed
                    });
                }
            }
        });
    }
}
