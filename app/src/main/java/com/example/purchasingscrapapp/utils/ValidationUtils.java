package com.example.purchasingscrapapp.utils;

import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

public class ValidationUtils {

    public static boolean isValidEmail(EditText emailEditText) {
        String email = emailEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Invalid email");
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(EditText passwordEditText) {
        String password = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            return false;
        }
        return true;
    }

    public static boolean isValidConfirmPassword(EditText passwordEditText, EditText confirmPasswordEditText) {
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPasswordEditText.getText().toString().trim();
        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    public static boolean isValidName(EditText nameEditText) {
        String name = nameEditText.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            nameEditText.setError("Name is required");
            return false;
        }
        return true;
    }

    public static boolean isValidPhone(EditText phoneEditText) {
        String phone = phoneEditText.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || !Patterns.PHONE.matcher(phone).matches()) {
            phoneEditText.setError("Invalid phone number");
            return false;
        }
        return true;
    }
}
