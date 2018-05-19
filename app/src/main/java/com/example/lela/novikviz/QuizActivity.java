package com.example.lela.novikviz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView mScoreView;
    private TextView mPitanje;
    private  TextView mVrime;

    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;

    List<Integer>numbers=new ArrayList<>();


    private String mAnswer;
    private int mScore=0;
    private int mBrojPitanja=0;

    private CountDownTimer Time;

    private Firebase mPitanjeRef,mIzbor1Ref,mIzbor2Ref,mIzbor3Ref,mIzbor4Ref,mOdgovorRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mScoreView=(TextView)findViewById(R.id.rezultat);
        mPitanje=(TextView)findViewById(R.id.question);

        for (int i = 0; i <= 68; i++)
        {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        mVrime=(TextView)findViewById(R.id.vrime);


        mButtonChoice1=(Button)findViewById(R.id.choice1);
        mButtonChoice2=(Button)findViewById(R.id.choice2);
        mButtonChoice3=(Button)findViewById(R.id.choice3);
        mButtonChoice4=(Button)findViewById(R.id.choice4);

        updateQuestion();

        mButtonChoice1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mButtonChoice1.getText().equals(mAnswer)){
                    mScore=mScore+1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else {
                    updateQuestion();
                }
            }
        });

        mButtonChoice2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mButtonChoice2.getText().equals(mAnswer)){
                    mScore=mScore+1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else {
                    updateQuestion();
                }
            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mButtonChoice3.getText().equals(mAnswer)){
                    mScore=mScore+1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else {
                    updateQuestion();
                }
            }
        });
        mButtonChoice4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if(mButtonChoice4.getText().equals(mAnswer)){
                    mScore=mScore+1;
                    updateScore(mScore);
                    updateQuestion();
                }
                else {
                    updateQuestion();
                }
            }
        });



    }
    private  void  updateScore(int score){

        mScoreView.setText(""+mScore);
    }
    public int getScore(){

        return mScore;
    };


    public  void pocniVrime(){

        Time=   new CountDownTimer(30000,1000){
            public void onTick(long secondsLeft){
                mVrime.setText("Vrijeme: "+ secondsLeft/1000);

            }
            public  void onFinish(){
                mVrime.setText("0");

                updateQuestion();
            }
        }.start();


    }

    @Override
    public void onBackPressed(){

        super.onBackPressed();
        Time.cancel();
        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();

    }

    @Override
    public void onStop(){

        super.onStop();
        Time.cancel();
        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();

    }

    public void updateQuestion(){
        String KrajVrimena= (String) mVrime.getText();
        if((mBrojPitanja==68)||(KrajVrimena.equals("0")))
        {
            Time.cancel();
            Intent i=new Intent(QuizActivity.this,KrajActivity.class);
            Bundle bundle=new Bundle();
            bundle.putInt("rezz",mScore);
            i.putExtras(bundle);
            QuizActivity.this.finish();
            startActivity(i);

        }


        mPitanjeRef=new Firebase("https://novikviz-6a632.firebaseio.com/Sheet1/" +numbers.get(mBrojPitanja)+"/question");

        mPitanjeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question=dataSnapshot.getValue(String.class);
                mPitanje.setText(question);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mIzbor1Ref=new Firebase("https://novikviz-6a632.firebaseio.com/Sheet1/" +numbers.get(mBrojPitanja)+"/choice1");
        mIzbor1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String izbor=dataSnapshot.getValue(String.class);
                mButtonChoice1.setText(izbor);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mIzbor2Ref=new Firebase("https://novikviz-6a632.firebaseio.com/Sheet1/"+numbers.get(mBrojPitanja)+"/choice2");
        mIzbor2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String izbor=dataSnapshot.getValue(String.class);
                mButtonChoice2.setText(izbor);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mIzbor3Ref=new Firebase("https://novikviz-6a632.firebaseio.com/Sheet1/"+numbers.get(mBrojPitanja)+"/choice3");
        mIzbor3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String izbor=dataSnapshot.getValue(String.class);
                mButtonChoice3.setText(izbor);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mIzbor4Ref=new Firebase("https://novikviz-6a632.firebaseio.com/Sheet1/"+numbers.get(mBrojPitanja)+"/choice4");
        mIzbor4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String izbor=dataSnapshot.getValue(String.class);
                mButtonChoice4.setText(izbor);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mOdgovorRef =new Firebase("https://novikviz-6a632.firebaseio.com/Sheet1/"+numbers.get(mBrojPitanja)+"/answer");

        mOdgovorRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAnswer=dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        if(KrajVrimena.equals("Vrime")){
            pocniVrime();
        }
        mBrojPitanja++;
    }

}
