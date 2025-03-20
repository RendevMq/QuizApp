package com.rensystem.p02_quizapp.ui.question

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rensystem.p02_quizapp.ui.ScoreActivity
import com.rensystem.p02_quizapp.ui.question.questionAdapter.QuestionAdapter
import com.rensystem.p02_quizapp.domain.model.QuestionModel
import com.rensystem.p02_quizapp.databinding.ActivityQuestionBinding
import com.rensystem.p02_quizapp.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionActivity : BaseActivity(), QuestionAdapter.score {

    private lateinit var binding: ActivityQuestionBinding

    // ViewModel inyectado
    private val questionViewModel: QuestionViewModel by viewModels()

    var position: Int = 0
    var allscore = 0
    private var receivedList: MutableList<QuestionModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observar los datos del ViewModel
        observeQuestions()

        // Solicitar las preguntas al ViewModel
        questionViewModel.onCreateMe()

        binding.apply {
            btnBack.setOnClickListener { finish() }

            btnRightArrow.setOnClickListener {
                if (position < receivedList.size - 1) {
                    position++
                    progressBar.progress = position + 1
                    tvQuestionNumber.text = "Question ${position + 1}/10"
                    updateUI()
                } else {
                    val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                    intent.putExtra("Score", allscore)
                    startActivity(intent)
                    finish()
                }
            }

            btnLeftArrow.setOnClickListener {
                if (position > 0) {
                    position--
                    progressBar.progress = position + 1
                    tvQuestionNumber.text = "Question ${position + 1}/10"
                    updateUI()
                }
            }
        }
    }

    private fun observeQuestions() {
        lifecycleScope.launch {
            questionViewModel.questionList.collectLatest { questions ->
                questions?.let {
                    receivedList = it.toMutableList() // Guardamos las preguntas en la lista
                    if (receivedList.isNotEmpty()) {
                        updateUI() // Cargamos la primera pregunta
                    }
                }
            }
        }
    }

    private fun updateUI() {
        val currentQuestion = receivedList[position]

        binding.apply {
            tvQuestion.text = currentQuestion.question
            val drawableResourceId: Int = currentQuestion.picPath!!

            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                .into(ivPicc)
        }

        loadAnswers()
    }

    private fun loadAnswers() {
        val answersQuestion: MutableList<String> = mutableListOf(
            receivedList[position].answer_1.toString(),
            receivedList[position].answer_2.toString(),
            receivedList[position].answer_3.toString(),
            receivedList[position].answer_4.toString()
        )

        receivedList[position].clickedAnswer?.let {
            answersQuestion.add(it)
        }

        Log.i("loadAnswersRenatoLog", answersQuestion.toString())

        val questionAdapter = QuestionAdapter(receivedList[position].correctAnswer.toString(), answersQuestion, this)

        // Ahora llama a submitList después de que el adapter ha sido asignado
        questionAdapter.differ.submitList(answersQuestion)

        // Asigna el adaptador antes de llamar a submitList
        binding.rvQuestionList.apply {
            layoutManager = LinearLayoutManager(this@QuestionActivity, LinearLayoutManager.VERTICAL, false)
            adapter = questionAdapter
        }
    }

    override fun amount(number: Int, clickedAnswer: String) {
        allscore += number
        receivedList[position].clickedAnswer = clickedAnswer
    }
}