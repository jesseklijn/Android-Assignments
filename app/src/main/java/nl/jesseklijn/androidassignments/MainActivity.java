package nl.jesseklijn.androidassignments;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


//Question is an imageId + location
class Question {
    Location location;
    int imageId;
    boolean correctAnswer = false;

    public Question(Location location, int imageId) {
        this.location = location;
        this.imageId = imageId;
    }
}

enum Location {
    Europe,
    America

}

class Quiz {

    Question[] questions = new Question[8];
    int currentQuestionIndex = 0;
    //Quiz constructor
    public Quiz() {
        //Create 8 questions
        questions[0] = new Question(Location.Europe, R.drawable.img1_yes_denmark);
        questions[1] = new Question(Location.America, R.drawable.img2_no_canada);
        questions[2] = new Question(Location.America, R.drawable.img3_no_bangladesh);
        questions[3] = new Question(Location.Europe, R.drawable.img4_yes_kazachstan);
        questions[4] = new Question(Location.America, R.drawable.img5_no_colombia);
        questions[5] = new Question(Location.Europe, R.drawable.img6_yes_poland);
        questions[6] = new Question(Location.Europe, R.drawable.img7_yes_malta);
        questions[7] = new Question(Location.America, R.drawable.img8_no_thailand);
    }

    Boolean answerQuestion(int questionIndex, Location location) {
       if(currentQuestionIndex >= questions.length){
           return true;
       }
       else{
        if (questions[questionIndex].location == location) {
            questions[questionIndex].correctAnswer = true;
            Log.d("myTag", "result: "+  questions[questionIndex].correctAnswer );
        } else {
            questions[questionIndex].correctAnswer = false;
            Log.d("myTag", "result: "+  questions[questionIndex].correctAnswer );
        }

        currentQuestionIndex++;
        if (questionIndex == questions.length) {
            return true;

        } else {

            return false;
        }
       }
    }


}

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView currentQuestionText;
    Button retryButton;
    Quiz quiz;

    public void onRetryClick(View view){
        quiz = new Quiz();
        retryButton.setVisibility(View.GONE);
        updateUI(true);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quiz = new Quiz();
        currentQuestionText = findViewById(R.id.CurrentQuestionLbl);
        retryButton = findViewById(R.id.RetryBtn);
        imageView = findViewById(R.id.imageView2);
        updateUI(true);
        imageView.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {


            public void onSwipeRight() {
                Log.d("myTag", "Swiped right");
                //Swiped for america
                if (quiz.answerQuestion(quiz.currentQuestionIndex, Location.America) == true) {
                    //Quiz is finished
                    updateUI(false);
                }
                else{
                    updateUI(true);
                }

            }

            public void onSwipeLeft() {
                Log.d("myTag", "Swiped left");
                //Swiped for europe
                if (quiz.answerQuestion(quiz.currentQuestionIndex, Location.Europe) == true) {
                    //Quiz is finished
                    updateUI(false);
                }
                else{
                    updateUI(true);
                }
            }


        });
    }

    void updateUI(Boolean quizActive) {
        if (quizActive == true && quiz.currentQuestionIndex < quiz.questions.length) {
            imageView.setImageResource(quiz.questions[quiz.currentQuestionIndex].imageId);
            currentQuestionText.setText((quiz.currentQuestionIndex+1) + "/" + quiz.questions.length);
        } else {
            imageView.setImageResource(R.drawable.quiz_result);
            retryButton.setVisibility(View.VISIBLE);
        }
    }


}
