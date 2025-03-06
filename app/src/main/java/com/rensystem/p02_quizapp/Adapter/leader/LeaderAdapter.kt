package com.rensystem.p02_quizapp.Adapter.leader

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rensystem.p02_quizapp.Domain.UserModel
import com.rensystem.p02_quizapp.databinding.ItemViewholderLeaderBinding

class LeaderAdapter: RecyclerView.Adapter<LeaderViewHolder>(){

    private val differCallback = object:DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            // Comparar si los IDs son iguales
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            // Comparar si los contenidos son iguales
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderViewHolder {
        /*val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_viewholder_leader,parent,false)
        return LeaderViewHolder(layout)*/
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewholderLeaderBinding.inflate(layoutInflater, parent, false)
        return LeaderViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: LeaderViewHolder, position: Int) {
        // Obtener el item actual
        val user = differ.currentList[position]
        holder.bind(user,position)
    }

    // Para actualizar la lista de datos
//    fun submitList(list: List<UserModel>) {
//        differ.submitList(list)
//    }


}