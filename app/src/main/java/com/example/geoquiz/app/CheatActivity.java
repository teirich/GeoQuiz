package com.example.geoquiz.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by captain on 5/14/14.
 */
public class CheatActivity extends Activity {

    public static final String TAG = "CheatActivity";
    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.app.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.example.geoquiz.app.answer_shown";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private boolean mIsAnswerShown = false;
    private static final String KEY_IS_SHOWN = "isShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
        mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                revealAnswer();
            }
        });
        if (savedInstanceState != null) {
            mIsAnswerShown = savedInstanceState.getBoolean(KEY_IS_SHOWN, false);
            if (mIsAnswerShown) {
                revealAnswer();
            }
            Log.i(TAG, "onCreate instance recovered");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putBoolean(KEY_IS_SHOWN, mIsAnswerShown);
    }

    protected void revealAnswer() {
        if (mAnswerIsTrue) {
            mAnswerTextView.setText(R.string.true_button);
        } else {
            mAnswerTextView.setText(R.string.false_button);
        }
        setAnswerShownResult();
    }

    private void setAnswerShownResult() {
        mIsAnswerShown = true;
        Log.i(TAG, "setAnswerShownResult answer shown: " + mIsAnswerShown );
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, mIsAnswerShown);
        setResult(RESULT_OK, data);
    }
}
