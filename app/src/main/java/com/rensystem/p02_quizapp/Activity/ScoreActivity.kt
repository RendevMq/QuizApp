package com.rensystem.p02_quizapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ActivityScoreBinding
import com.rensystem.p02_quizapp.databinding.ItemViewholderQuestionBinding

class ScoreActivity : BaseActivity() {

    var score:Int = 0
    private lateinit var binding: ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        score = intent.getIntExtra("Score",0)
        binding.apply {
            tvScore.text = score.toString()
            btnBackk.setOnClickListener {
                startActivity(
                    Intent(
                        this@ScoreActivity,
                        MainActivity::class.java
                    )
                )
                finish()
            }
        }
    }
}