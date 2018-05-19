package com.example.lela.novikviz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class KrajActivity extends AppCompatActivity {

    TextView mRezultat,mHigh;

    Button mPonoviButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kraj);

        mRezultat=(TextView)findViewById(R.id.ponovo);
        mHigh=(TextView)findViewById(R.id.high);
        mPonoviButton =(Button)findViewById(R.id.ponovoIgraj);

        Bundle bundle=getIntent().getExtras();
        int score=bundle.getInt("rezz");
        mRezultat.setText("Vaš rezultat je "+score);
        SharedPreferences mypf=getPreferences(MODE_PRIVATE);
        int Highscore=mypf.getInt("Highscore",0);

        if(Highscore>=score){
            mHigh.setText("Najbolji:" + Highscore);

        }
else {

            mHigh.setText("Najbolji:" + score);
            SharedPreferences.Editor editor=mypf.edit();
            editor.putInt("Highscore",score);
            editor.commit();


        }



        mPonoviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KrajActivity.this,QuizActivity.class));
                KrajActivity.this.finish();
            }
        });
    }
}
