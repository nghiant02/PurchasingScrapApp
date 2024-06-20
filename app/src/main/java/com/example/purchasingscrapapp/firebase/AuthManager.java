// AuthManager.java
package com.example.purchasingscrapapp.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;

public class AuthManager {
    private FirebaseAuth auth;

    public AuthManager() {
        this.auth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public void login(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            onCompleteListener.onComplete(null);
            return;
        }
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(onCompleteListener);
    }

    public void register(String email, String password, OnCompleteListener<AuthResult> onCompleteListener) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            onCompleteListener.onComplete(null);
            return;
        }
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = task.getResult().getUser();
                if (user != null) {
                    user.sendEmailVerification().addOnCompleteListener(verificationTask -> {
                        onCompleteListener.onComplete(task);
                    });
                }
            } else {
                onCompleteListener.onComplete(task);
            }
        });
    }

    public void sendPasswordResetEmail(String email, OnCompleteListener<Void> onCompleteListener) {
        if (email == null || email.isEmpty()) {
            onCompleteListener.onComplete(null);
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(onCompleteListener);
    }

    public void logout() {
        auth.signOut();
    }
}
