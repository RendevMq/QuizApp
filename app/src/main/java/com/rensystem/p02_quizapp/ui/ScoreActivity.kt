package com.rensystem.p02_quizapp.ui

import android.content.Intent
import android.os.Bundle
import com.rensystem.p02_quizapp.databinding.ActivityScoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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