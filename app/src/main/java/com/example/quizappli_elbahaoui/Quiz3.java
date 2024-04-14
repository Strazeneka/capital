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

public class Quiz3 extends AppCompatActivity {

    RadioGroup rg;
    RadioButton rb;
    Button bNext;
    int score;
    String RepCorrect="Madrid";

    private FirebaseAuth mAuth;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz3);
        rg=(RadioGroup) findViewById(R.id.rg);
        bNext=(Button) findViewById(R.id.bNext);
        Intent intent=getIntent();
        score=intent.getIntExtra("score",0) ;
        db = FirebaseFirestore.getInstance();


        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb=(RadioButton) findViewById(rg.getCheckedRadioButtonId());
                if(rg.getCheckedRadioButtonId()==-1){
                    Toast.makeText(getApplicationContext(),"Merci de choisir une réponse S.V.P !",Toast.LENGTH_SHORT).show();
                }
                else {
                    //Toast.makeText(getApplicationContext(),rb.getText().toString(),Toast.LENGTH_SHORT).show();
                    if(rb.getText().toString().equals(RepCorrect)){
                        score+=1;
                        //Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT).show();
                    }
                    String question = "What is the capital of Spain ? ";
                    String image = "https://firebasestorage.googleapis.com/v0/b/quizappli-elbahaoui.appspot.com/o/images%2Fq3.jpg?alt=media&token=3e1d28d6-4de3-4af2-ba2a-d2e3260bd5cf";
                    String[] reponses = {"Madrid", "Barcelona", "Valencia"};
                    //int score = 0;
                    //String repCorrecte = "Amsterdam";
                    // String selectedAnswer = rb.getText().toString();
                    String documentId = "3";

                    Map<String, Object> quizData = new HashMap<>();
                    quizData.put("question", question);
                    quizData.put("image", image);
                    quizData.put("reponses", Arrays.asList(reponses));
                    quizData.put("score", score);
                    //quizData.put("reponseCorrecte", reponseCorrecte);
                    // quizData.put("selectedAnswer", selectedAnswer);
                    db.collection("Quiz3").document(documentId)
                            .set(quizData)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
                                    // Redirection ou autres actions
                                }
                            });

                                    Intent intent = new Intent(Quiz3.this, Quiz4.class);
                                    intent.putExtra("score", score);
                                    startActivity(intent);
                                    //overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                                    overridePendingTransition(R.anim.exit, R.anim.entry);
                                    finish();

                }
            }
        });
    }
}