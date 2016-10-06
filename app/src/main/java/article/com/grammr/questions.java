package article.com.grammr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class questions extends AppCompatActivity {


    private boolean done;
    private int QuestionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
        findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
        findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
        String[] questions = getResources().getStringArray(R.array.Questions);
        TextView t = (TextView) findViewById(R.id.question);
        t.setText(questions[QuestionNo]);
    }

    public void onHintClick (View view) {
        String[] hints = getResources().getStringArray(R.array.Hints);
        Toast toasty = Toast.makeText(getApplicationContext(),hints[QuestionNo],Toast.LENGTH_SHORT);
        toasty.show();
    }
    public void onAnswerClick (View view) {
        if(done == false) {
        String answer = ((EditText)findViewById(R.id.answer)).getText().toString();
        String[] answers = getResources().getStringArray(R.array.Answers);
        String correctanswer = answers[QuestionNo];
        answer = answer.toLowerCase();
        correctanswer = correctanswer.toLowerCase();

        if(answer.equals(correctanswer)) {
            TextView t = (TextView)findViewById(R.id.correctornot);
            t.setText("CORRECT!");
            ImageView i = (ImageView) findViewById(R.id.tickcross);
            i.setImageDrawable(getDrawable(R.drawable.weirdtick));
            answersSubmitted();
        }
        else {
            TextView t = (TextView) findViewById(R.id.correctornot);
            t.setText("Wrong! Correct Answer is " + correctanswer);
            ImageView i = (ImageView) findViewById(R.id.tickcross);
            i.setImageDrawable(getDrawable(R.drawable.weirdcross));
            answersSubmitted();
        }
            done = true;
        }
    }

    public void answersSubmitted() {
        findViewById(R.id.tickcross).setVisibility(View.VISIBLE);
        TranslateAnimation animation = new TranslateAnimation(0,0, 2000,0);
        animation.setDuration(1000);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd ( Animation animation) {
                findViewById(R.id.nextbutton).setVisibility(View.VISIBLE);
                findViewById(R.id.correctornot).setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat ( Animation animation) {

            }
        });
        findViewById(R.id.tickcross).setAnimation(animation);
        }

    public void onNextClick(View vIew) {

        if (done) {
            String[] questions = getResources().getStringArray(R.array.Questions);
            if (QuestionNo < questions.length-1) {
                QuestionNo++;
                findViewById(R.id.tickcross).setVisibility(View.INVISIBLE);
                findViewById(R.id.correctornot).setVisibility(View.INVISIBLE);
                findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
                EditText et = (EditText) findViewById(R.id.answer);
                et.setText("");
                TextView t = (TextView) findViewById(R.id.question);
                t.setText(questions[QuestionNo]);
                done = false;
            }
        }
    }
}
