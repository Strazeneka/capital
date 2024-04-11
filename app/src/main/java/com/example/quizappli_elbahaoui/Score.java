package com.example.quizappli_elbahaoui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class Score extends AppCompatActivity {

    Button bLogout, bTry;
    ProgressBar progressBar;
    TextView tvScore;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        tvScore =(TextView) findViewById(R.id.tvScore);
        progressBar=(ProgressBar) findViewById(R.id.progressBar);
        bLogout=(Button) findViewById(R.id.bLogout);
        bTry=(Button) findViewById(R.id.bTry);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;
        progressBar.setProgress(score*20);
        tvScore.setText((score*20)+" %");

       FirebaseFirestore db = FirebaseFirestore.getInstance();


        saveScoreToFirestore(score);
        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Merci de votre Participation !", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Score.this,MainActivity.class));
                finish();
            }
        });
        bTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Score.this,Quiz1.class));
            }
        });
        saveScoreToFirestore(score);
    }
    private void saveScoreToFirestore(int score) {
        // Create a new instance of FirebaseFirestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Get the current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // If user is not null, get the user ID
            String userId = user.getEmail();
            // Create a new map to store the score data
            Map<String, Object> scoreData = new HashMap<>();
            scoreData.put("score", score);
            // Add the score data to Firestore under the "scores" collection
            db.collection("scores").document(userId)
                    .set(scoreData)
                    .addOnSuccessListener(aVoid -> {
                        // On success, show a toast message
                        Toast.makeText(getApplicationContext(), "Score saved to Firestore", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        // On failure, show a toast message with the error
                        Toast.makeText(getApplicationContext(), "Failed to save score: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        }
    }


}
