package com.example.purchasingscrapapp.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.purchasingscrapapp.activity.MainActivity;
import com.example.purchasingscrapapp.activity.LoginActivity;
import com.example.purchasingscrapapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtils {

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Authentication methods
    public static void registerUser(Context context, String email, String password, String name, String phone, ProgressBar progressBar, OnCompleteListener<AuthResult> listener) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        if (firebaseUser != null) {
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            firebaseUser.updateProfile(profileUpdates).addOnCompleteListener(updateTask -> {
                                if (updateTask.isSuccessful()) {
                                    createUserInFirestore(context, firebaseUser, name, phone);
                                    sendVerificationEmail(context);
                                }
                            });
                        }
                    } else {
                        Toast.makeText(context, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public static void loginUser(Context context, String email, String password, ProgressBar progressBar, OnCompleteListener<AuthResult> listener) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(listener)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null && user.isEmailVerified()) {
                            context.startActivity(new Intent(context, MainActivity.class));
                        } else {
                            Toast.makeText(context, "Please verify your email before logging in.", Toast.LENGTH_LONG).show();
                            if (user != null) {
                                sendVerificationEmail(context);
                            }
                        }
                    } else {
                        Toast.makeText(context, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    public static void resetPassword(Context context, String email, ProgressBar progressBar, OnCompleteListener<Void> listener) {
        progressBar.setVisibility(View.VISIBLE);
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(listener)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "Password reset email sent.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private static void createUserInFirestore(Context context, FirebaseUser firebaseUser, String name, String phone) {
        User user = new User(firebaseUser.getUid(), firebaseUser.getEmail(), null, name, phone, null, null, "user", System.currentTimeMillis(), System.currentTimeMillis());
        db.collection(Constants.USERS_COLLECTION).document(firebaseUser.getUid()).set(user)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(context, "User registered successfully. Please check your email for verification.", Toast.LENGTH_LONG).show();
                        context.startActivity(new Intent(context, LoginActivity.class));
                    } else {
                        Toast.makeText(context, "Firestore error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private static void sendVerificationEmail(Context context) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Verification email sent.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Failed to send verification email.", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    public static FirebaseFirestore getFirestore() {
        return db;
    }
}
