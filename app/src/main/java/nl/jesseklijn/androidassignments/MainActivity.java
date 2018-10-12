package nl.jesseklijn.androidassignments;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

class Dice {
int value;

public Dice(int value){
    this.value = value;
}

}

enum Choice {
    higher,
    lower

}
public class MainActivity extends AppCompatActivity {
    //View components
    TextView currentScoreText;
    TextView highscoreText;
    ImageView diceImageView;
    Button lowerButton;
    Button upperButton;

    //Fields
    Dice diceInstance;
    Random randomInstance;
    int currentScore = 0;
    int highscore = 0;
    int rolledNumber = 0;
    Choice prediction = Choice.lower;

    int[] diceImages = new int[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setup random
        randomInstance = new Random();
        //Creates a dice, setting it up with a random value between 0 - 6
        diceInstance = new Dice(rollDice());
        setContentView(R.layout.activity_main);

        //Fill image array with id's of assets
        diceImages[0] = R.drawable.d1;
        diceImages[1] = R.drawable.d2;
        diceImages[2] = R.drawable.d3;
        diceImages[3] = R.drawable.d4;
        diceImages[4] = R.drawable.d5;
        diceImages[5] = R.drawable.d6;

        //Get ui components by id
        currentScoreText = findViewById(R.id._currentScore);
        highscoreText = findViewById(R.id._highscore);
        diceImageView = findViewById(R.id._diceView);
        lowerButton = findViewById(R.id._lowerButton);
        upperButton = findViewById(R.id._upperButton);


    }



    public void lowerButtonOnClick(View view) {
        // Do something in response to button
        //Predicts it will be higher
        prediction = Choice.higher;
        //Then updates
        update();

    }
    public void upperButtonOnClick(View view) {
        // Do something in response to button
        //Predicts it will be lower
        prediction = Choice.lower;
        //Then updates
        update();
    }

    public int rollDice(){
       return randomInstance.nextInt(6);
    }

    public void update(){
        //Roll the dice
        int rolledDice = diceInstance.value;
        while(rolledDice == diceInstance.value){
            rolledDice = rollDice();
        }


        //Game logic
        if(prediction == Choice.lower)
        {
            if(rolledDice > diceInstance.value){
                //Get points
                currentScore++;
                if(currentScore > highscore) {
                    highscore++;
                }
            }
            else{
                //Lose points

                currentScore = 0;
            }
        }
        else if(prediction == Choice.higher){
            if(rolledDice < diceInstance.value){
                //Get points
                currentScore++;
                if(currentScore > highscore) {
                    highscore++;
                }
            }
            else{
                //Lose points
                currentScore = 0;
            }
        }
        diceInstance.value = rolledDice;
        //Show dice image
        updateUI();
        //
    }
    public void updateUI(){
        //Display the score
        diceImageView.setImageResource(diceImages[diceInstance.value]);
        currentScoreText.setText("Score: " + currentScore);
        highscoreText.setText("highscore: " + highscore);

    }



}
