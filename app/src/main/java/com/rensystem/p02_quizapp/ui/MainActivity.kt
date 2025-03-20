package com.rensystem.p02_quizapp.ui

import android.content.Intent
import android.os.Bundle
import com.rensystem.p02_quizapp.domain.model.QuestionModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ActivityMainBinding
import com.rensystem.p02_quizapp.ui.board.LeaderActivity
import com.rensystem.p02_quizapp.ui.question.QuestionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSingle.setOnClickListener {
                // Solo inicia la actividad sin pasar datos (a diferencia de la version1)
                startActivity(Intent(this@MainActivity, QuestionActivity::class.java))
            }

            bottomMenu.setItemSelected(R.id.home)
            bottomMenu.setOnItemSelectedListener {
                if (it == R.id.board) {
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                }
            }
        }
    }
}
