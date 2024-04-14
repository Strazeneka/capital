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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quiz1 extends AppCompatActivity {
    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    int score=0;
    String RepCorrect="Amsterdam";

   FirebaseFirestore db ;



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
                    String question = "What is the capital of Netherlands ?";
                    String image = "https://firebasestorage.googleapis.com/v0/b/quizappli-elbahaoui.appspot.com/o/images%2Fq1.jpg?alt=media&token=5ec7f754-db06-4ca8-b164-818a8e83bdfe";
                    String[] reponses = {"Rotterdam", "Tilburg", "Amsterdam"};
                   // int score = 0;
                    //String repCorrecte = "Amsterdam";
                    // String selectedAnswer = rb.getText().toString();
                    String documentId = "1";

                    Map<String, Object> quizData = new HashMap<>();
                    quizData.put("question", question);
                    quizData.put("image", image);
                    quizData.put("reponses", Arrays.asList(reponses));
                    quizData.put("score", score);
                    //quizData.put("reponseCorrecte", reponseCorrecte);
                    // quizData.put("selectedAnswer", selectedAnswer);
                    db.collection("Quiz1").document(documentId)
                            .set(quizData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                    // Redirection ou autres actions
                                }
                            });


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









