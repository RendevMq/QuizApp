package com.rensystem.p02_quizapp.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rensystem.p02_quizapp.Domain.UserModel
import com.rensystem.p02_quizapp.databinding.ActivityMainBinding
import com.rensystem.p02_quizapp.databinding.ItemViewholderLeaderBinding

class LeaderViewHolder(private val binding: ItemViewholderLeaderBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UserModel, position: Int) {
        binding.txtTitle.text = user.name

        val drawableResourceId: Int = binding.root.resources.getIdentifier(
            user.pic, "drawable", binding.root.context.packageName
        )

        Glide.with(binding.root.context)
            .load(drawableResourceId)
            .into(binding.ivPic)

        binding.txtRow.text = (position + 4).toString()  // Ajustar posición según sea necesario
        binding.txtScore.text = user.score.toString()
    }
}