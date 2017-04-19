package com.example.danie.calculateit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class mainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    //Starts the game.
    public void startGame(View view) {
        Intent beginGame = new Intent(mainMenu.this, MainActivity.class);
        startActivity(beginGame);
    }
}
