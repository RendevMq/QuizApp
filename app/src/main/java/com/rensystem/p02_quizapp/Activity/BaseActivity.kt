package com.rensystem.p02_quizapp.Activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.rensystem.p02_quizapp.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ajusta dinámicamente el padding del contenedor principal
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { _, insets ->
            val navBarHeight = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
            applyPaddingToMainContainer(navBarHeight)
            insets
        }
    }

    private fun applyPaddingToMainContainer(navBarHeight: Int) {
        val mainContainer = findViewById<View>(R.id.mainContainer) // Encuentra el contenedor
        mainContainer?.updatePadding(bottom = navBarHeight) // Aplica el padding dinámico
    }
}
