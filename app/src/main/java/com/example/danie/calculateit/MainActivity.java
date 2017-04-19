package com.example.danie.calculateit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.AlertDialog;
import java.lang.Object;


public class MainActivity extends AppCompatActivity {
    private Button addSeven;
    private Button subtractFour;
    private Button divideTwo;
    private Button setZero;
    private TextView userNumber;
    private TextView targetNumber;
    private TextView timeLeft;
    private int userNum = 0;
    private int goal = 1;
    private int buttonsPressed = 0;
    private int time = 20;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        targetNumber = (TextView) findViewById(R.id.textView2);
        userNumber = (TextView) findViewById(R.id.textView4);
        addSeven = (Button) findViewById(R.id.button);
        subtractFour = (Button) findViewById(R.id.button2);
        divideTwo = (Button) findViewById(R.id.button3);
        setZero = (Button) findViewById(R.id.button4);
        timeLeft = (TextView) findViewById(R.id.textTimer);

        /*When the game starts, a timer counts down from 10 seconds. The user has 10 seconds to
        * calculate the correct number. If the timer finished beforehand, the game is lost. */
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int time = (int) (millisUntilFinished/1000);
                timeLeft.setText(String.valueOf(time));
                nextLevel();
            }

            @Override
            public void onFinish() {
                Intent backToMain = new Intent(MainActivity.this, gameOver.class);
                backToMain.putExtra("Score", goal);
                startActivity(backToMain);
            }
        }.start();
    }

    /*If user hits target number, it displays the number of calculations done as well as starting
     *the next level. The timer will restart if the user hits target number.*/
    public void nextLevel() {
        if (userNum == goal) {
            timer.cancel();
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Nice Job!")
                    .setMessage("Calculations Executed: " + buttonsPressed)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            timer.start();
                            buttonsPressed = 0;
                            userNum = 0;
                            goal++;
                            userNumber.setText(String.valueOf(userNum));
                            targetNumber.setText(String.valueOf(goal));                        }
                    })
                    .setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            timer.start();
                            buttonsPressed = 0;
                            userNum = 0;
                            goal++;
                            userNumber.setText(String.valueOf(userNum));
                            targetNumber.setText(String.valueOf(goal));
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
    //allows the user to add seven to their number.
    public void addingSeven(View view) {
        buttonsPressed++;
        userNum += 7;
        userNumber.setText(String.valueOf(userNum));
        nextLevel();
    }
    //allows the user to subtract 4 from their number. The number cannot go below 0.
    public void subtractingFour(View view) {
        if (userNum >= 4) {
            buttonsPressed++;
            userNum -= 4;
            userNumber.setText(String.valueOf(userNum));
            nextLevel();
        }
    }
    //allows the user to divide by two only if their number is even.
    public void divideByTwo(View view) {
        if ((userNum % 2) == 0) {
            buttonsPressed++;
            userNum = userNum / 2;
            userNumber.setText(String.valueOf(userNum));
            nextLevel();
        }
    }

    //Resets the user's current number to zero.
    public void resetNumber(View view) {
        buttonsPressed++;
        userNum = 0;
        userNumber.setText(String.valueOf(userNum));
    }

}
