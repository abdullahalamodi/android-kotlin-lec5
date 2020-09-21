package com.abdullahalamodi.androidkotlinlec_3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var questionView: TextView;
    private lateinit var trueBtn: Button;
    private lateinit var falseBtn: Button;
    private lateinit var nextBtn: ImageButton;
    private lateinit var previousBtn: ImageButton;
    private val questionBank = listOf(
        Question(R.string.question_1, true),
        Question(R.string.question_2, false),
        Question(R.string.question_3, false),
        Question(R.string.question_4, false),
        Question(R.string.question_5, false)
    )
    private var currentIndex: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        questionView = findViewById(R.id.question_v);
        trueBtn = findViewById(R.id.true_btn);
        falseBtn = findViewById(R.id.false_btn);
        nextBtn = findViewById(R.id.next_btn);
        previousBtn = findViewById(R.id.previous_btn);

        updateQuestion();

        trueBtn.setOnClickListener {
            checkAnswer(true);
        }

        falseBtn.setOnClickListener {
            checkAnswer(false);
        }

        nextBtn.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size;
            updateQuestion();
        }

        previousBtn.setOnClickListener {
            currentIndex = (questionBank.size + (currentIndex - 1)) % questionBank.size;
            updateQuestion();
        }

        questionView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size;
            updateQuestion();
        }


    }

    private fun updateQuestion() {
        val questionText = questionBank[currentIndex].textResId;
        questionView.setText(questionText);
        checkIfAnswering();
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer;
        val messageResId = if (userAnswer == correctAnswer) {
            Question.grade++;
            R.string.correct_toast;
        } else {
            R.string.incorrect_toast;
        }
        val toast = Toast.makeText(
            this, messageResId,
            Toast.LENGTH_SHORT
        );
        toast.show();
        // set question state to answered
        questionBank[currentIndex].isAnswered = true;
        //check if all questions is answered display grade
        if (Question.checkAnsweredQuestions(questionBank.size))
            Toast.makeText(
                this,
                "You git: ${Question.grade} from: ${questionBank.size}",
                Toast.LENGTH_SHORT
            ).show();

    }

    private fun setAnswerBtnsVisibility(visibility: Int) {
        trueBtn.visibility = visibility;
        falseBtn.visibility = visibility;
    }

    private fun checkIfAnswering() {
        if (questionBank[currentIndex].isAnswered) {
            setAnswerBtnsVisibility(View.INVISIBLE);
        } else {
            setAnswerBtnsVisibility(View.VISIBLE);
        }
    }


}