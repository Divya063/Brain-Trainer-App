package com.example.divyarani.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button go;
    int correctlocation;
    TextView scorestatus;
    TextView scoreboard;
    int score=0;
    int numberofquestions=0;
    Button first;
    Button second;
    Button third;
    Button fourth;
    Button playagain;
    TextView question;
    TextView timer;
    RelativeLayout relativeLayout;

    ArrayList<Integer> answers= new ArrayList<Integer>();
    public void playagain(View view){
        score=0;
        numberofquestions=0;
        timer.setText("30s");
        scorestatus.setText("");
        scoreboard.setText("0/0");
        playagain.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);
                timer.setText("0s");
                scorestatus.setText("Your score is"+Integer.toString(score)+"/"+Integer.toString(numberofquestions));
            }
        }.start();
        generatequestions();
    }
    public void generatequestions(){

        Random rand=new Random();
        int a=rand.nextInt(21);// create random numbers from 0 to 20
        int b=rand.nextInt(21);
        question.setText(Integer.toString(a)+ " + "+Integer.toString(b));
        correctlocation=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if( i==correctlocation){
                answers.add(a+b);
            }
            else{
                int incorrectanswers=rand.nextInt(41);
                while(incorrectanswers==a+b){
                    incorrectanswers=rand.nextInt(41);
                }
                answers.add(incorrectanswers);
            }
        }
        first.setText(Integer.toString(answers.get(0)));
        second.setText(Integer.toString(answers.get(1)));
        third.setText(Integer.toString(answers.get(2)));
        fourth.setText(Integer.toString(answers.get(3)));

    }
    public void chooseanswer(View view){
        if(view.getTag().toString().equals(Integer.toString(correctlocation))){
            score++;
            scorestatus.setText("Correct");

        }

        else{
            scorestatus.setText("wrong");
        }
        numberofquestions++;
        scoreboard.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestions));
        generatequestions();

    }
    public void hide(View view){

        go.setVisibility(View.INVISIBLE);
        relativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playagain(findViewById(R.id.button2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=(Button) findViewById(R.id.button);
        question=(TextView) findViewById(R.id.textView2);
        scoreboard=(TextView) findViewById(R.id.textView);
        scorestatus=(TextView)findViewById(R.id.textView3);
        timer=(TextView)findViewById(R.id.timer);
         first=(Button) findViewById(R.id.button3);
         second=(Button) findViewById(R.id.button4);
         third=(Button) findViewById(R.id.button5);
         fourth=(Button) findViewById(R.id.button6);
        playagain=(Button)findViewById(R.id.button2);
        relativeLayout=(RelativeLayout)findViewById(R.id.relativelayout);




    }
}
