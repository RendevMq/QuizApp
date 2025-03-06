package com.rensystem.p02_quizapp.Adapter.question

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rensystem.p02_quizapp.Domain.QuestionModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ItemViewholderQuestionBinding

class QuestionViewHolder(
    private val binding: ItemViewholderQuestionBinding,
    private val updateAnswer: (String, Int, Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        position: Int,
        correctAnswer: String,
        answersQuestion: MutableList<String>,
        returnScore: QuestionAdapter.score
    ) {
        Log.i("RenatoID" , "FUTBOL : $position - SIZE = ${answersQuestion.size}" )
        binding.tvAnswer.text = answersQuestion[position]

        val currentPos = getAnswerPosition(correctAnswer)

        if (answersQuestion.size == 5 && currentPos == position) {
            Log.i("RenatoID SIZE 5 VERDE" , "FUTBOL : POSITION: $position , CURRENTPOSITION $currentPos")

            binding.tvAnswer.setBackgroundResource(R.drawable.green_bg)
            val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
            binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                null,
                null,
                drawable,
                null
            )
        }

        if (answersQuestion.size == 5) {
            Log.i("RenatoID SIZE 5 ROJO" , "FUTBOL : POSITION: $position , CURRENTPOSITION $currentPos")

            var clickedPos = getAnswerPosition(answersQuestion[4])
            if (clickedPos == position && clickedPos != currentPos) {

                binding.tvAnswer.setBackgroundResource(R.drawable.red_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
            }
        }

        if (position == 4) {
            binding.root.visibility = View.GONE
        }

        itemView.setOnClickListener {
            val str = getAnswerLetter(position) // Obtiene la letra de la respuesta (a, b, c, d)

//            answersQuestion.add(index = 4, str)

            // Llamamos al callback para actualizar solo los ítems afectados

            Log.i("POosition" , position.toString())
            Log.i("currentpos" , currentPos.toString())

            updateAnswer(str, position, currentPos)

            if (currentPos == position) {
                Log.i("answerVERDE" , answersQuestion.toString())
                binding.tvAnswer.setBackgroundResource(R.drawable.green_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
                binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
                returnScore.amount(5, str)
            } else {
                Log.i("answerROJO" , answersQuestion.toString())
                binding.tvAnswer.setBackgroundResource(R.drawable.red_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
                returnScore.amount(0, str)
            }
        }
        if (answersQuestion.size == 5) itemView.setOnClickListener(null)
    }

    // Función para obtener la posición del índice de la respuesta correcta
    private fun getAnswerPosition(answer: String): Int {
        return when (answer) {
            "a" -> 0
            "b" -> 1
            "c" -> 2
            "d" -> 3
            else -> -1
        }
    }

    // Función para obtener la letra de la respuesta basada en el índice
    private fun getAnswerLetter(position: Int): String {
        return when (position) {
            0 -> "a"
            1 -> "b"
            2 -> "c"
            3 -> "d"
            else -> ""
        }
    }
}
