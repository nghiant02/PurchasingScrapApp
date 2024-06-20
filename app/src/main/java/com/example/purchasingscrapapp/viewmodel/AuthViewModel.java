// AuthViewModel.java
package com.example.purchasingscrapapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.purchasingscrapapp.firebase.AuthManager;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends ViewModel {
    private AuthManager authManager;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<Boolean> resetEmailSentLiveData;

    public AuthViewModel() {
        this.authManager = new AuthManager();
        this.userLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();
        this.resetEmailSentLiveData = new MutableLiveData<>();
    }

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public LiveData<Boolean> getResetEmailSentLiveData() {
        return resetEmailSentLiveData;
    }

    public void login(String email, String password) {
        authManager.login(email, password, task -> {
            if (task == null || task.isSuccessful()) {
                FirebaseUser user = authManager.getCurrentUser();
                if (user != null && user.isEmailVerified()) {
                    userLiveData.postValue(user);
                } else if (user != null) {
                    errorLiveData.postValue("Email not verified. Please check your email.");
                } else {
                    errorLiveData.postValue("User not found.");
                }
            } else {
                errorLiveData.postValue("Login failed: " + task.getException().getMessage());
            }
        });
    }

    public void register(String email, String password) {
        authManager.register(email, password, task -> {
            if (task == null || task.isSuccessful()) {
                userLiveData.postValue(authManager.getCurrentUser());
            } else {
                errorLiveData.postValue("Registration failed: " + task.getException().getMessage());
            }
        });
    }

    public void sendPasswordResetEmail(String email) {
        authManager.sendPasswordResetEmail(email, task -> {
            if (task == null || task.isSuccessful()) {
                resetEmailSentLiveData.postValue(true);
            } else {
                errorLiveData.postValue("Error: " + task.getException().getMessage());
            }
        });
    }

    public void logout() {
        authManager.logout();
        userLiveData.postValue(null);
    }
}
