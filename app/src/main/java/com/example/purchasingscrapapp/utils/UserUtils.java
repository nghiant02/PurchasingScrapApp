package com.example.purchasingscrapapp.utils;

import com.example.purchasingscrapapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

public class UserUtils {

    public static User getCurrentUser() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (auth.getCurrentUser() == null) return null;

        db.collection("users").document(auth.getCurrentUser().getUid()).get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            User user = document.toObject(User.class);
                            // Return user or store in a variable for further use
                        }
                    }
                });

        return null; // Should be replaced with actual user retrieval logic
    }
}
