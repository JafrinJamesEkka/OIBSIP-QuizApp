package com.example.quiz_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AlphabetIndexer
import android.widget.Toast
import com.example.quiz_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val questions = arrayOf("1.) Where was India’s first national Museum opened ?",
        "2.) The green planet in the solar system is ?",
        "3.) The largest public sector undertaking in the country is ?",
        "4.) The study of Heavenly bodies is Known as ?",
        "5.) The world’s nation 5G mobile network was launched by which country?")

    private val options = arrayOf(arrayOf("Delhi","Hyderabad","Mumbai"),
        arrayOf("Mars","Uranus","Venus"),
        arrayOf("Railways","Airsways","Roadways"),
        arrayOf("Astrophysics","Astrology","Astronomy"),
        arrayOf("Japan","South Korea","China")
    )

    private val correctAnswers = arrayOf(2, 1, 0, 2, 1)

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        displayQuestion()

        binding.optionBtn1.setOnClickListener {
            checkAnswer(0)
        }
        binding.optionBtn2.setOnClickListener {
            checkAnswer(1)
        }
        binding.optionBtn3.setOnClickListener {
            checkAnswer(2)
        }
        binding.restartBtn.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.optionBtn1.setBackgroundColor(Color.GREEN)
            1 -> binding.optionBtn2.setBackgroundColor(Color.GREEN)
            2 -> binding.optionBtn3.setBackgroundColor(Color.GREEN)

        }
    }

    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 -> binding.optionBtn1.setBackgroundColor(Color.RED)
            1 -> binding.optionBtn2.setBackgroundColor(Color.RED)
            2 -> binding.optionBtn3.setBackgroundColor(Color.RED)

        }
    }

    private fun resetButtonColors(){
            binding.optionBtn1.setBackgroundColor(Color.rgb(50,59,96))
            binding.optionBtn2.setBackgroundColor(Color.rgb(50,59,96))
            binding.optionBtn3.setBackgroundColor(Color.rgb(50,59,96))

        }

    private fun showResults(){
        Toast.makeText(this,"Your score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartBtn.isEnabled = true
    }

    private fun displayQuestion(){
        binding.questionTxt.text = questions[currentQuestionIndex]
        binding.optionBtn1.text = options[currentQuestionIndex][0]
        binding.optionBtn2.text = options[currentQuestionIndex][1]
        binding.optionBtn3.text = options[currentQuestionIndex][2]
        resetButtonColors()
    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]

        if (selectedAnswerIndex == correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        }
        else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size - 1){
            currentQuestionIndex++
            binding.questionTxt.postDelayed({displayQuestion()},1000)
        }
        else {
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartBtn.isEnabled = false
    }
}