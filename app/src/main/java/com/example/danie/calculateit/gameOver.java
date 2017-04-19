package com.example.danie.calculateit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

//Takes the user out of the game once the game is lost. Notifies user of their score for that session.
public class gameOver extends AppCompatActivity {
    private int userScore = 1;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        score = (TextView) findViewById(R.id.usersScore);
        Intent intent = getIntent();
        userScore = intent.getExtras().getInt("Score");
        score.setText(String.valueOf(userScore));

    }

    //Takes the users back to the main menu.
    public void backToMenu(View view) {
        Intent backToMain = new Intent(gameOver.this, mainMenu.class);
        startActivity(backToMain);
    }
}
