package com.rensystem.p02_quizapp.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.RoundedCorner
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.rensystem.p02_quizapp.Adapter.question.QuestionAdapter
import com.rensystem.p02_quizapp.Domain.QuestionModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ActivityMainBinding
import com.rensystem.p02_quizapp.databinding.ActivityQuestionBinding

class QuestionActivity : BaseActivity(), QuestionAdapter.score {

    private lateinit var binding: ActivityQuestionBinding
    var position: Int = 0
    var receivedList: MutableList<QuestionModel> = mutableListOf()
    var allscore = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receivedList = intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        binding.apply {
            btnBack.setOnClickListener {
                finish() }
            progressBar.progress = 1

            tvQuestion.text = receivedList[position].question
            val drawableResourceId: Int = receivedList[position].picPath!!
            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(60))
                )
                .into(ivPicc)

            loadAnswers()

            btnRightArrow.setOnClickListener {
                if (progressBar.progress == 10) {
                    val intent = Intent(this@QuestionActivity,ScoreActivity::class.java)
                    intent.putExtra("Score",allscore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }

                position++
                progressBar.progress = progressBar.progress + 1
                tvQuestionNumber.text = "Question " + progressBar.progress + "/10"
                tvQuestion.text = receivedList[position].question

                val drawableResourceId: Int = receivedList[position].picPath!!

                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(ivPicc)
                loadAnswers()
            }

            btnLeftArrow.setOnClickListener {
                if (progressBar.progress == 1) {

                    return@setOnClickListener
                }

                position--
                progressBar.progress = progressBar.progress - 1
                tvQuestionNumber.text = "Question " + progressBar.progress + "/10"
                tvQuestion.text = receivedList[position].question

                val drawableResourceId: Int = receivedList[position].picPath!!

                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(ivPicc)
                loadAnswers()
            }
        }

    }

    private fun loadAnswers() {
        val answersQuestion: MutableList<String> = mutableListOf()
        answersQuestion.add(receivedList[position].answer_1.toString())
        answersQuestion.add(receivedList[position].answer_2.toString())
        answersQuestion.add(receivedList[position].answer_3.toString())
        answersQuestion.add(receivedList[position].answer_4.toString())

        if (receivedList[position].clickedAnswer != null) {
            answersQuestion.add(receivedList[position].clickedAnswer!!)
            Log.i("loadAnswersRenatoLog1",answersQuestion.toString())
        }
        Log.i("loadAnswersRenatoLog2",answersQuestion.toString())

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