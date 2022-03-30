package com.yurdm.wordscramble

import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var word: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startGame()

        val submit: Button = findViewById(R.id.submit)
        val skip: Button = findViewById(R.id.skip)
        val input: EditText = findViewById(R.id.wordInput)

        submit.setOnClickListener {
            var message: String = ""

            if (input.text.toString().lowercase() == word.lowercase()) {
                input.text.clear()
                message = "Correct!"
                startGame()
            } else {
                message = "Incorrect \ud83d\ude25"
                val example: Animation =
                    AnimationUtils.loadAnimation(applicationContext, R.anim.shake)
                input.startAnimation(example)
            }

            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }

        skip.setOnClickListener {
            input.text.clear()
            startGame()
        }
    }

    private fun startGame() {
        val words = resources.getStringArray(R.array.words_array)
        word = words[Random.nextInt(0, words.size)]
        val card: TextView = findViewById(R.id.wordCardValue)
        val shuffled = word.toCharArray()
        shuffled.shuffle()
        card.text = shuffled.joinToString("")
    }
}