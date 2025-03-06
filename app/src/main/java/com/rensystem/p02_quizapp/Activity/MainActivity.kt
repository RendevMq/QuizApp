package com.rensystem.p02_quizapp.Activity

import android.content.Intent
import android.os.Bundle
import com.rensystem.p02_quizapp.Domain.QuestionModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSingle.setOnClickListener {
                val intent = Intent(this@MainActivity, QuestionActivity::class.java)
                intent.putParcelableArrayListExtra("list", ArrayList(questionList()))
                startActivity(intent)
            }

            bottomMenu.setItemSelected(R.id.home)
            bottomMenu.setOnItemSelectedListener {
                if (it == R.id.board) {
                    startActivity(Intent(this@MainActivity, LeaderActivity::class.java))
                }
            }

        }


    }

    private fun questionList(): MutableList<QuestionModel> {
        val question: MutableList<QuestionModel> = mutableListOf()

        question.add(
            QuestionModel(
                id = 1,
                question = "Which planet is the largest planet in the solar system?",
                answer_1 = "Earth",
                answer_2 = "Mars",
                answer_3 = "Neptune",
                answer_4 = "Jupiter",
                correctAnswer = "d",
                score = 5,
                picPath = R.drawable.q_1,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 2,
                question = "Which country is the largest country in the world by land area?",
                answer_1 = "Russia",
                answer_2 = "Canada",
                answer_3 = "United States",
                answer_4 = "China",
                correctAnswer = "a",
                score = 5,
                picPath = R.drawable.q_2,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 3,
                question = "Which of the following substances is used as an anti-cancer medication in the medical world?",
                answer_1 = "Cheese",
                answer_2 = "Lemon juice",
                answer_3 = "Cannabis",
                answer_4 = "Paspalum",
                correctAnswer = "c",
                score = 5,
                picPath = R.drawable.q_3,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 4,
                question = "Which moon in the Earth's solar system has an atmosphere?",
                answer_1 = "Luna (Moon)",
                answer_2 = "Phobos (Mars' moon)",
                answer_3 = "Venus' moon",
                answer_4 = "None of the above",
                correctAnswer = "d",
                score = 5,
                picPath = R.drawable.q_4,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 5,
                question = "Which of the following symbols represents the chemical element with the atomic number 6?",
                answer_1 = "O",
                answer_2 = "H",
                answer_3 = "C",
                answer_4 = "N",
                correctAnswer = "c",
                score = 5,
                picPath = R.drawable.q_5,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 6,
                question = "Who is credited with inventing theater as we know it today?",
                answer_1 = "Shakespeare",
                answer_2 = "Arthur Miller",
                answer_3 = "Ashkouri",
                answer_4 = "Ancient Greeks",
                correctAnswer = "d",
                score = 5,
                picPath = R.drawable.q_6,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 7,
                question = "Which ocean is the largest ocean in the world?",
                answer_1 = "Pacific Ocean",
                answer_2 = "Atlantic Ocean",
                answer_3 = "Indian Ocean",
                answer_4 = "Arctic Ocean",
                correctAnswer = "a",
                score = 5,
                picPath = R.drawable.q_7,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 8,
                question = "Which religions are among the most practiced religions in the world?",
                answer_1 = "Islam, Christianity, Judaism",
                answer_2 = "Buddhism, Hinduism, Sikhism",
                answer_3 = "Zoroastrianism, Brahmanism, Yazdanism",
                answer_4 = "Taoism, Shintoism, Confucianism",
                correctAnswer = "a",
                score = 5,
                picPath = R.drawable.q_8,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 9,
                question = "In which continent are the most independent countries located?",
                answer_1 = "Asia",
                answer_2 = "Europe",
                answer_3 = "Africa",
                answer_4 = "Americas",
                correctAnswer = "c",
                score = 5,
                picPath = R.drawable.q_9,
                clickedAnswer = null
            )
        )

        question.add(
            QuestionModel(
                id = 10,
                question = "Which ocean has the greatest average depth?",
                answer_1 = "Pacific Ocean",
                answer_2 = "Atlantic Ocean",
                answer_3 = "Indian Ocean",
                answer_4 = "Southern Ocean",
                correctAnswer = "d",
                score = 5,
                picPath = R.drawable.q_10,
                clickedAnswer = null
            )
        )

        return question
    }

}