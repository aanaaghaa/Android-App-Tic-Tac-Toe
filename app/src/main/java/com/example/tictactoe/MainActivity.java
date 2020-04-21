package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0: yellow 1: red 2: empty
    int gameState[]={2, 2, 2, 2, 2, 2, 2, 2, 2}; // board is empty at the beginning
    int[][] winningPostions={{0, 1, 2},{3, 4, 5},{6, 7, 8},{0, 3, 6},{1, 4, 7},{2, 5, 8},{0, 4, 8},{2, 4, 6}}; // the image view positions of game
    int activePlayer=0; // yellow coin will be dropped first
    boolean gameActive=true; //game is still on(yellow nor red has won)
public void dropIn(View view) //on click on an image view
{
    ImageView counter=(ImageView) view; //connecting layout(all the image views) and java file(counter variable of ImageView type)

    int tappedCounter=Integer.parseInt(counter.getTag().toString()); //get the tag value on which image view the user has tapped and store in tapped counter variable
    if(gameState[tappedCounter]==2 && gameActive==true) { //checks if the image view is empty, and if yellow nor red has not won, further operations will be performed only if its empty
gameState[tappedCounter]=activePlayer; //changes the empty array values of game state with the yellow and red values

        counter.setTranslationY(-1500); // ImageView out of the screen
        if (activePlayer == 0) { //checking if the player is yellow
            counter.setImageResource(R.drawable.yellow); // set the image view to yellow
            activePlayer = 1; // change the player to red
        } else {
            counter.setImageResource(R.drawable.red); // set the image view to red
            activePlayer = 0; // change the player to yellow
        }
        counter.animate().translationYBy(1500).rotationBy(3600).setDuration(300); // move the imageview through animation and add a rotation with a small duration
        for (int[] winningPosition : winningPostions) { //loop through winningPositions
            if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) { // check if the 3 consecutive position of image view are equal and not even empty
                gameActive=false; //if yellow or red is won, the game get over
                String winner = "";
                if (activePlayer == 1) //the values will be opposite as the activePlayer will have got updated, display a message
                {
                    winner = "Yellow";
                } else {
                    winner = "Red";
                }
               // Toast.makeText(this, winner + " won", Toast.LENGTH_SHORT).show();
                Button playAgain=(Button) findViewById(R.id.playAgainBtn);
                TextView winnertext=(TextView) findViewById(R.id.winnerTextView);
                winnertext.setText(winner + " has won");
                playAgain.setVisibility(View.VISIBLE); // making the button visible, only when the game is won
                winnertext.setVisibility(View.VISIBLE); // making the text view visible, only when the game is won
            }

        }
    }

}
public void playGameAgain(View view) //function on click on PLAY AGAIN button
{
    Button playAgain=(Button) findViewById(R.id.playAgainBtn);
    TextView winnertext=(TextView) findViewById(R.id.winnerTextView);
    playAgain.setVisibility(View.INVISIBLE); //making the button invisible, once clicked on PLAY AGAIN
    winnertext.setVisibility(View.INVISIBLE); //making the text view invisible, once clicked on PLAY AGAIN
   GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
    for(int i=0;i<gridLayout.getChildCount(); i++)
    {
        ImageView counter=(ImageView) gridLayout.getChildAt(i); //parsing through each image view in grid layout
        counter.setImageDrawable(null); //updating all the image views back to empty, so that it can be played
    }
    for(int i=0;i<gameState.length;i++) //setting the game state array to empty i.e., 2
    {
        gameState[i]=2;
    }
    activePlayer=0; //updating the values
    gameActive=true; //updating the values
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
