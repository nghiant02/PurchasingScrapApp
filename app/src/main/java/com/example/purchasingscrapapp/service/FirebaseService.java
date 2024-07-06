package com.example.purchasingscrapapp.service;

import android.content.Context;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.purchasingscrapapp.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import org.checkerframework.checker.nullness.qual.NonNull;

public class FirebaseService {

//    public LiveData<AuthResult> registerUser(Context context, String email, String password, String name, String phone, ProgressBar progressBar) {
//        MutableLiveData<AuthResult> result = new MutableLiveData<>();
//        FirebaseUtils.registerUser(context, email, password, name, phone, progressBar, task -> {
//            if (task.isSuccessful()) {
//                result.setValue(task.getResult());
//            } else {
//                result.setValue(null);
//            }
//        });
//        return result;
//    }
//
//    public LiveData<AuthResult> loginUser(Context context, String email, String password, ProgressBar progressBar) {
//        MutableLiveData<AuthResult> result = new MutableLiveData<>();
//        FirebaseUtils.loginUser(context, email, password, progressBar, task -> {
//            if (task.isSuccessful()) {
//                result.setValue(task.getResult());
//            } else {
//                result.setValue(null);
//            }
//        });
//        return result;
//    }
//
//    public LiveData<Void> resetPassword(Context context, String email, ProgressBar progressBar) {
//        MutableLiveData<Void> result = new MutableLiveData<>();
//        FirebaseUtils.resetPassword(context, email, progressBar, task -> {
//            if (task.isSuccessful()) {
//                result.setValue(task.getResult());
//            } else {
//                result.setValue(null);
//            }
//        });
//        return result;
//    }
}
