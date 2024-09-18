package com.example.hiltgradleimplementation

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var textViewCounter: TextView

    // Inyección del ViewModel
    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewCounter = findViewById(R.id.textViewCounter)

        // Observar los cambios en el LiveData del contador
        counterViewModel.counter.observe(this) { count ->
            textViewCounter.text = count.toString()
        }

        // Incrementar el contador cuando se haga clic en el TextView
        textViewCounter.setOnClickListener {
            counterViewModel.incrementCounter()
        }
    }
}
