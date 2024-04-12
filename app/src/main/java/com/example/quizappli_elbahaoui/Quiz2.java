package com.example.quizappli_elbahaoui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz2 extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    int score;
    String RepCorrect = "Mexico City";

    private FirebaseAuth mAuth;
    private boolean isPictureUploaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        rg = findViewById(R.id.rg);
        bNext = findViewById(R.id.bNext);
        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Set click listener for the next button
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb = findViewById(rg.getCheckedRadioButtonId());
                if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Merci de choisir une réponse S.V.P !", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the selected answer is correct
                    if (rb.getText().toString().equals(RepCorrect)) {
                        score += 1;
                    }

                    // Upload picture to Firebase Storage

                    if (!isPictureUploaded) {
                        uploadPictureToStorage();
                    }

                    // Start the next quiz activity
                    Intent intent = new Intent(Quiz2.this, Quiz3.class);
                    intent.putExtra("score", score);
                    startActivity(intent);
                    overridePendingTransition(R.anim.exit, R.anim.entry);
                    finish();
                }
            }
        });
    }

    // Method to upload the picture associated with Quiz2 to Firebase Storage
    private void uploadPictureToStorage() {
        // Get a reference to Firebase Storage
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // Get the drawable resource ID of the picture
        int drawableResourceId = R.drawable.q2;

        // Get the drawable resource as a bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);

        // Convert the bitmap to a byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        // Create a reference to the picture you want to upload
        StorageReference pictureRef = storageRef.child("images/q2.jpg");

        // Upload the file to Firebase Storage
        UploadTask uploadTask = pictureRef.putBytes(data);

        // Register observers to listen for upload success or failure
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            // Picture uploaded successfully
            Toast.makeText(getApplicationContext(), "Picture uploaded successfully", Toast.LENGTH_SHORT).show();
            isPictureUploaded = true;
        }).addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
            Toast.makeText(getApplicationContext(), "Failed to upload picture: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        });
    }
}