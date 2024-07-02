package com.example.purchasingscrapapp.repository;

import android.content.Context;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.purchasingscrapapp.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.checkerframework.checker.nullness.qual.NonNull;

public class UserRepository {

    public LiveData<AuthResult> registerUser(Context context, String email, String password, String name, String phone, ProgressBar progressBar) {
        MutableLiveData<AuthResult> resultLiveData = new MutableLiveData<>();
        FirebaseUtils.registerUser(context, email, password, name, phone, progressBar, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    resultLiveData.setValue(task.getResult());
                } else {
                    resultLiveData.setValue(null);
                }
            }
        });
        return resultLiveData;
    }

    public LiveData<AuthResult> loginUser(Context context, String email, String password, ProgressBar progressBar) {
        MutableLiveData<AuthResult> resultLiveData = new MutableLiveData<>();
        FirebaseUtils.loginUser(context, email, password, progressBar, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    resultLiveData.setValue(task.getResult());
                } else {
                    resultLiveData.setValue(null);
                }
            }
        });
        return resultLiveData;
    }

    public LiveData<Void> resetPassword(Context context, String email, ProgressBar progressBar) {
        MutableLiveData<Void> resultLiveData = new MutableLiveData<>();
        FirebaseUtils.resetPassword(context, email, progressBar, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    resultLiveData.setValue(task.getResult());
                } else {
                    resultLiveData.setValue(null);
                }
            }
        });
        return resultLiveData;
    }
}
