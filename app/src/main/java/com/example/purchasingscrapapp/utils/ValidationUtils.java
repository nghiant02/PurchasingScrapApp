package com.example.purchasingscrapapp.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class ValidationUtils {

    public static boolean isValidEmail(EditText editText) {
        String email = editText.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            editText.setError("Email is required");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText.setError("Enter a valid email");
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(EditText editText) {
        String password = editText.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            editText.setError("Password is required");
            return false;
        } else if (password.length() < 6) {
            editText.setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }

    public static boolean isValidConfirmPassword(EditText editTextPassword, EditText editTextConfirmPassword) {
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }
}
