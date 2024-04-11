package com.example.quizappli_elbahaoui;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz1 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    int score=0;
    String RepCorrect="Rome";
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        rg=(RadioGroup) findViewById(R.id.rg);
        bNext=(Button) findViewById(R.id.bNext);
      db = FirebaseFirestore.getInstance();


        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Merci de choisir une réponse S.V.P !",Toast.LENGTH_SHORT).show();
                }
                else {
                    rb = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                    //Toast.makeText(getApplicationContext(),rb.getText().toString(),Toast.LENGTH_SHORT).show();
                    if (rb.getText().toString().equals(RepCorrect)) {
                        score += 1;
                        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
                    }
                   // String questionText = "Quelle est la capitale de l'italie ?";

                    // Retrieve answer options from XML layout
                   // RadioButton rb1 = findViewById(R.id.rb1);
                    //RadioButton rb2 = findViewById(R.id.rb2);
                   // RadioButton rb3 = findViewById(R.id.rb3);
                   // String answer1 = rb1.getText().toString();
                   // String answer2 = rb2.getText().toString();
                   // String answer3 = rb3.getText().toString();

                   // saveQuestionToFirestore(questionText, RepCorrect, answer1, answer2, answer3);



                                    Intent intent = new Intent(Quiz1.this, Quiz2.class);
                                    intent.putExtra("score", score);
                                    startActivity(intent);
                                    //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                                    overridePendingTransition(R.anim.exit, R.anim.entry);
                                    finish();



                }

            }
        });
    };
   // private void saveQuestionToFirestore(String questionText, String correctAnswer, String answer1, String answer2, String answer3) {
       // Map<String, Object> questionData = new HashMap<>();
       // questionData.put("questionText", questionText);
      //  questionData.put("correctAnswer", correctAnswer);
        //questionData.put("answer1", answer1);
      //  questionData.put("answer2", answer2);
       // questionData.put("answer3", answer3);

        // Add the question data to Firestore

       // db.collection("quizzes").document("quiz1").collection("questions")
               // .add(questionData)
             //   .addOnSuccessListener(documentReference -> Toast.makeText(getApplicationContext(), "Question saved to Firestore", Toast.LENGTH_SHORT).show())
             //   .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to save question: " + e.getMessage(), Toast.LENGTH_SHORT).show());
   // }
}









