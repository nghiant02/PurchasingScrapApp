package com.example.purchasingscrapapp.firebase;

import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreManager {
    private FirebaseFirestore firestore;

    public FirestoreManager() {
        this.firestore = FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getFirestore() {
        return firestore;
    }
}
