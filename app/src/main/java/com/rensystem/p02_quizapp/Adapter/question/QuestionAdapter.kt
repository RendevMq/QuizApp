package com.rensystem.p02_quizapp.Adapter.question

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rensystem.p02_quizapp.Domain.QuestionModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ItemViewholderQuestionBinding
import kotlin.reflect.typeOf


class QuestionAdapter(
    var correctAnswer: String = "",
    val answersQuestion: MutableList<String> = mutableListOf(),
    var returnScore: score
) : RecyclerView.Adapter<QuestionViewHolder>() {

    interface score {
        fun amount(number: Int, clickedAnswer: String)
    }

    private lateinit var binding: ItemViewholderQuestionBinding

    private val differCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount() = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemViewholderQuestionBinding.inflate(inflater, parent, false)
        return QuestionViewHolder(binding,updateAnswer)
    }

//    private val updateAnswer: (String) -> Unit = { str->
//        differ.currentList.add(index=4,str)
//        notifyDataSetChanged()
//    }

    // Callback que ahora recibe 'answersQuestion' y la respuesta 'str',
    // y notifica específicamente los ítems cambiados usando notifyItemChanged
    private val updateAnswer: (String, Int, Int) -> Unit = { str, position, currentPos ->
        val updatedAnswers = differ.currentList.toMutableList() // Copia la lista actual
        updatedAnswers.add(str) // Agrega la respuesta

        Log.i("RenatoID BEFORE UPDATE", "Size = ${answersQuestion.size}") // <-- Log antes del cambio

        differ.submitList(updatedAnswers) {
            // Este callback se ejecuta cuando `submitList` termina
            Log.i("RenatoID AFTER UPDATE", "Size = ${updatedAnswers.size}") // <-- Ahora debe ser 5
            notifyDataSetChanged()
        }
    }
    //El problema era que submitList() no actualiza la lista inmediatamente, sino que lo hace en un proceso asíncrono. Usando el callback de submitList(), puedes asegurarte de que la actualización ya ocurrió antes de que se renderice la vista.


    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {

        holder.bind(position, correctAnswer, differ.currentList, returnScore)
    }
}


//CODIGO EN UN SOLO ARCHIVO
/*
class QuestionAdapter(
    var correctAnswer: String = "",
    val answersQuestion: MutableList<String> = mutableListOf(),
    var returnScore: score
) : RecyclerView.Adapter<QuestionAdapter.Viewholder>() {

    interface score {
        fun amount(number: Int, clickedAnswer: String)
    }

    private lateinit var binding: ItemViewholderQuestionBinding

    inner class Viewholder : RecyclerView.ViewHolder(binding.root)


    private val differCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun getItemCount() = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.Viewholder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemViewholderQuestionBinding.inflate(inflater, parent, false)
        return Viewholder()
    }

    override fun onBindViewHolder(holder: QuestionAdapter.Viewholder, position: Int) {
        Log.i("RenatoID" , "FUTBOL : $position")
        val binding = ItemViewholderQuestionBinding.bind(holder.itemView)
        binding.tvAnswer.text = differ.currentList[position]

        val currentPos = getAnswerPosition(correctAnswer)

        if (differ.currentList.size == 5 && currentPos == position) {
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

        if (differ.currentList.size == 5) {

            /*// Al hacer clic en una respuesta, 'differ.currentList[4]' toma el valor de 'str', que representa la respuesta seleccionada (ejemplo: "a", "b", "c", "d").
            // Este valor se agrega a la lista de respuestas, aumentando el tamaño de la lista a 5 elementos (originalmente 4). Luego, al llamar a 'notifyDataSetChanged()',
            // el RecyclerView se vuelve a cargar, lo que garantiza que la vista se actualice correctamente y se pinte el color adecuado si la respuesta es correcta o incorrecta.
            //
            // Aunque la actividad siempre se mantiene activa, cada vez que el usuario avanza a una nueva pregunta, la lista de datos se actualiza con la nueva pregunta,
            // manteniendo la estructura de las respuestas anteriores. Sin embargo, cuando el usuario regresa a una pregunta previamente contestada, el Adapter **no mantiene**
            // el estado de las respuestas seleccionadas (es decir, los valores de 'clickedAnswer') porque el 'Adapter' recibe nuevamente los 4 primeros elementos de la lista,
            // sin los datos actualizados de las respuestas previamente seleccionadas.
            //
            // Para solucionar este comportamiento, aseguramos que la lista enviada al Adapter siempre contenga 5 elementos, pero **solo cuando 'clickedAnswer' no sea nulo**,
            // lo que indica que el usuario ha seleccionado una respuesta para esa pregunta. Al agregar este nuevo dato a la lista (que representa la respuesta seleccionada),
            // se asegura que el estado de las respuestas previas se mantenga correctamente al cambiar entre preguntas y regresar a una pregunta previamente respondida.
            // Esto garantiza que los colores de las respuestas correctas o incorrectas se apliquen adecuadamente cuando se regresa a la pregunta ya respondida.
            //
            // Además, para mejorar el rendimiento y actualizar solo los elementos relevantes, es recomendable usar 'submitList()' en lugar de 'notifyDataSetChanged()'.
            // Esto asegura que solo se actualicen los cambios en los datos, mejorando la eficiencia al no recargar completamente la vista y manteniendo la persistencia de los datos.
            */

            var clickedPos = getAnswerPosition(differ.currentList[4])

            if (clickedPos == position && clickedPos != currentPos) {

                Log.i("RenatoID SIZE 5 ROJO" , "FUTBOL : POSITION: $position , CURRENTPOSITION $currentPos")


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
        holder.itemView.setOnClickListener {
            var str = getAnswerLetter(position) // Obtiene la letra de la respuesta (a, b, c, d)

            answersQuestion.add(index = 4, str)
//            notifyDataSetChanged()
            notifyItemChanged(position)
            notifyItemChanged(currentPos)

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
                val drawable =
                    ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.tvAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    null,
                    drawable,
                    null
                )
                returnScore.amount(0, str)
            }
        }
        if (differ.currentList.size == 5) holder.itemView.setOnClickListener(null)
    }

    // Nueva función para obtener la posición del índice de la respuesta correcta
    private fun getAnswerPosition(answer: String): Int {
        return when (answer) {
            "a" -> 0
            "b" -> 1
            "c" -> 2
            "d" -> 3
            else -> -1  // En caso de que haya un error
        }
    }

    // Función para obtener la letra de la respuesta basada en el índice
    private fun getAnswerLetter(position: Int): String {
        return when (position) {
            0 -> "a"
            1 -> "b"
            2 -> "c"
            3 -> "d"
            else -> ""  // Este else nunca debería ocurrir si position está entre 0 y 3
        }
    }
}*/


