package com.rensystem.p02_quizapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rensystem.p02_quizapp.Adapter.LeaderAdapter
import com.rensystem.p02_quizapp.Domain.UserModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ActivityLeaderBinding

class LeaderActivity : BaseActivity() {
    lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy { LeaderAdapter() }
    private val usersList: MutableList<UserModel> by lazy { loadData() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cargar datos solo una vez
        val topUsers = usersList.take(3) // Los tres primeros usuarios

        // Asignar datos a las vistas de forma optimizada
        binding.apply {
            txtScore1.text = topUsers[0].score.toString()
            txtScore2.text = topUsers[1].score.toString()
            txtScore3.text = topUsers[2].score.toString()

            txtTitle1.text = topUsers[0].name
            txtTitle2.text = topUsers[1].name
            txtTitle3.text = topUsers[2].name
        }

        // Cargar imágenes solo una vez usando Glide
        loadImages(topUsers)

        // Configuración del menú inferior (Navigation Bar)
        setupBottomMenu()

        // Filtrar lista para los siguientes usuarios (excluyendo los top 3)
        val filteredList = usersList.drop(3) // Obtener los usuarios que no están en el top 3
        leaderAdapter.differ.submitList(filteredList)

        // Configuración del RecyclerView
        binding.rvLeader.apply {
            layoutManager = LinearLayoutManager(this@LeaderActivity)
            adapter = leaderAdapter
        }
    }

    // Configurar el menú inferior
    private fun setupBottomMenu() {
        binding.bottomMenu.setItemSelected(R.id.board)
        binding.bottomMenu.setOnItemSelectedListener { it: Int ->
            if (it == R.id.home) {
                startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
            }
        }
    }

    // Función para cargar imágenes usando Glide y los identificadores de recursos
    private fun loadImages(topUsers: List<UserModel>) {
        topUsers.forEachIndexed { index, user ->
            val drawableResourceId: Int = binding.root.resources.getIdentifier(
                user.pic, "drawable", binding.root.context.packageName
            )

            // Usar Glide para cargar la imagen en el ImageView correspondiente
            val imageView = when (index) {
                0 -> binding.pic1
                1 -> binding.pic2
                2 -> binding.pic3
                else -> null
            }

            imageView?.let {
                Glide.with(binding.root.context)
                    .load(drawableResourceId)
                    .into(it)
            }
        }
    }

    // Función para cargar los datos de los usuarios (ejemplo de datos)
    private fun loadData(): MutableList<UserModel> {
        return mutableListOf(
            UserModel(id = 1, name = "Sophia", pic = "person1", score = 4850),
            UserModel(id = 2, name = "Daniel", pic = "person2", score = 4560),
            UserModel(id = 3, name = "James", pic = "person3", score = 3873),
            UserModel(id = 4, name = "John Smith", pic = "person4", score = 3250),
            UserModel(id = 5, name = "Emily Johnson", pic = "person5", score = 3015),
            UserModel(id = 6, name = "David Brown", pic = "person6", score = 2970),
            UserModel(id = 7, name = "Sarah Wilson", pic = "person7", score = 2870),
            UserModel(id = 8, name = "Michael Davis", pic = "person8", score = 2670),
            UserModel(id = 9, name = "Sarah Wilson", pic = "person9", score = 2380),
            UserModel(id = 10, name = "Sarah Wilson", pic = "person9", score = 2380)
        )
    }
}
