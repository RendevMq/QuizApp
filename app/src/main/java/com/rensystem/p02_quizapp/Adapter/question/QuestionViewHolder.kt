package com.rensystem.p02_quizapp.Adapter.question

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rensystem.p02_quizapp.Domain.QuestionModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ItemViewholderQuestionBinding

class QuestionViewHolder(private val binding: ItemViewholderQuestionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        position: Int,
        correctAnswer: String,
        answersQuestion: MutableList<String>,
        returnScore: QuestionAdapter.score
    ) {
        binding.tvAnswer.text = answersQuestion[position]
        val currentPos = getAnswerPosition(correctAnswer)

        if (answersQuestion.size == 5 && currentPos == position) {
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

        itemView.setOnClickListener {
            val str = getAnswerLetter(position)
            answersQuestion.add(index = 4, str)

            // Ahora notificamos al Adapter sobre el cambio en el ítem
            val adapter = itemView.context as? QuestionAdapter
            adapter?.notifyItemChangedInAdapter(position)
            adapter?.notifyItemChangedInAdapter(currentPos)

            if (currentPos == position) {
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
