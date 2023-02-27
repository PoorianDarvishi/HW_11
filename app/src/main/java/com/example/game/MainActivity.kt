package com.example.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.game.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            dose.setOnClickListener {
                supportFragmentManager.commit {
                    supportFragmentManager.saveBackStack("Dose")
                    setReorderingAllowed(true)
                    replace<DoseFragment>(R.id.fragment_container_view)
                }
            }
            fourInRow.setOnClickListener {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<FourInRowFragment>(R.id.fragment_container_view)
                }
            }
        }
    }
}