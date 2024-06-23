package com.example.purchasingscrapapp.utils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseUtils {

    private static final FirebaseAuth auth = FirebaseAuth.getInstance();

    public static FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public static boolean isLoggedIn() {
        return getCurrentUser() != null;
    }

    public static void sendVerificationEmail(FirebaseUser user, OnCompleteListener<Void> listener) {
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(listener);
        }
    }

    public static void resetPassword(String email, OnCompleteListener<Void> listener) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(listener);
    }

    public static void logout() {
        auth.signOut();
    }
}
