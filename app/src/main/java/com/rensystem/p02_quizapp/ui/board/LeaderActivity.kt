package com.rensystem.p02_quizapp.ui.board

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rensystem.p02_quizapp.ui.MainActivity
import com.rensystem.p02_quizapp.ui.board.leaderAdapter.LeaderAdapter
import com.rensystem.p02_quizapp.domain.model.UserModel
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ActivityLeaderBinding
import com.rensystem.p02_quizapp.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/*
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
//            val drawableResourceId: Int = binding.root.resources.getIdentifier(
//                user.pic, "drawable", binding.root.context.packageName
//            )

            val drawableResourceId = user.pic
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
            UserModel(id = 1, name = "Sophia", pic = R.drawable.person1, score = 4850),
            UserModel(id = 2, name = "Daniel", pic = R.drawable.person2, score = 4560),
            UserModel(id = 3, name = "James", pic = R.drawable.person3, score = 3873),
            UserModel(id = 4, name = "John Smith", pic = R.drawable.person4, score = 3250),
            UserModel(id = 5, name = "Emily Johnson", pic = R.drawable.person5, score = 3015),
            UserModel(id = 6, name = "David Brown", pic = R.drawable.person6, score = 2970),
            UserModel(id = 7, name = "Sarah Wilson", pic = R.drawable.person7, score = 2870),
            UserModel(id = 8, name = "Michael Davis", pic = R.drawable.person8, score = 2670),
            UserModel(id = 9, name = "Sarah Wilson", pic = R.drawable.person9, score = 2380),
            UserModel(id = 10, name = "Sarah Wilson", pic = R.drawable.person9, score = 2380)
        )
    }
}*/

@AndroidEntryPoint
class LeaderActivity : BaseActivity() {

    private lateinit var binding: ActivityLeaderBinding
    private val leaderAdapter by lazy { LeaderAdapter() }

    // Usamos viewModels() para obtener una instancia del ViewModel
    private val leaderViewModel: LeaderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        setupRecyclerView()

        // Configurar menú inferior de navegación
        setupBottomMenu()

        // Observar los datos del ViewModel
        observeUsers()

        // Llamar al ViewModel para obtener los datos
        leaderViewModel.onCreateMe()
    }

    private fun setupRecyclerView() {
        binding.rvLeader.apply {
            layoutManager = LinearLayoutManager(this@LeaderActivity)
            adapter = leaderAdapter
        }
    }

    private fun observeUsers() {
        lifecycleScope.launch {
            leaderViewModel.userList.collectLatest { userList ->
                userList?.let { updateUI(it) }
            }
        }
    }

    private fun updateUI(usersList: List<UserModel>) {
        if (usersList.isNotEmpty()) {
            val topUsers = usersList.take(3)

            // Asignar nombres y puntajes a los TextViews
            binding.apply {
                txtScore1.text = topUsers[0].score.toString()
                txtScore2.text = topUsers[1].score.toString()
                txtScore3.text = topUsers[2].score.toString()

                txtTitle1.text = topUsers[0].name
                txtTitle2.text = topUsers[1].name
                txtTitle3.text = topUsers[2].name
            }

            // Cargar imágenes con Glide
            loadImages(topUsers)

            // Actualizar el RecyclerView con los usuarios restantes
            val filteredList = usersList.drop(3)
            leaderAdapter.differ.submitList(filteredList)
        }
    }

    private fun loadImages(topUsers: List<UserModel>) {
        val imageViews = listOf(binding.pic1, binding.pic2, binding.pic3)

        topUsers.forEachIndexed { index, user ->
            Glide.with(binding.root.context)
                .load(user.pic)
                .into(imageViews[index])
        }
    }

    private fun setupBottomMenu() {
        binding.bottomMenu.setItemSelected(R.id.board)
        binding.bottomMenu.setOnItemSelectedListener { it: Int ->
            if (it == R.id.home) {
                startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
            }
        }
    }
}

