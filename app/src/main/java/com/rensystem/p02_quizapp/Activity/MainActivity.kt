package com.rensystem.p02_quizapp.Activity

import android.os.Bundle
import com.rensystem.p02_quizapp.R
import com.rensystem.p02_quizapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity(){

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnSingle.setOnClickListener {

            }

            bottomMenu.setItemSelected(R.id.home)
            bottomMenu.setOnItemSelectedListener {
                if(it== R.id.board){

                }
            }

        }


    }
}