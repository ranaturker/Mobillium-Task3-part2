package com.ranaturker.task3_part2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ranaturker.task3_part2.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var numberButtons: List<Button>
    private val viewModel: GameViewModel by viewModels()

    // ViewBinding için değişken "binding" oluşturma
    private lateinit var binding: FragmentGameBinding

    // Fragment oluşturulduğunda çağrılan onCreateView() fonksiyonu
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ViewBinding kullanarak fragment_counter.xml layout'u ile bağlama
        binding = FragmentGameBinding.inflate(layoutInflater)
        bindUI()
        viewModel.generateRandomValues()
        return binding.root
    }

    private fun bindUI() = with(binding) {
        // 0-9 arası sayıları gösteren tuşların listesini oluştur
        numberButtons = listOf(
            button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9
        )
        guessButton.setOnClickListener {
            val guess = guessEditText.text.toString().toInt()
            checkGuess(guess)
        }
        clearButton.setOnClickListener {
            guessEditText.text.clear()
        }
        randomCharTextView.setOnClickListener {
            val result = viewModel.randomNumber.value?.toString() ?: "Değer Bulunamadı"
            randomCharTextView.text = result
        }

        // Sayı tuşlarına tıklama işlemleri ekle
        for (button in numberButtons) {
            button.setOnClickListener {
                val buttonText = button.text.toString()
                val currentGuess = guessEditText.text.toString()
                guessEditText.setText("$currentGuess$buttonText")
            }
        }

        viewModel.randomChar.observe(viewLifecycleOwner) { randomChar ->
            randomCharTextView.text = randomChar.toString()
        }

    }

    private fun checkGuess(guess: Int) {
        val isMatch = viewModel.checkGuess(guess)
        if (isMatch) {
            binding.resultTextView.text = "You won!"
        } else {
            binding.resultTextView.text = "Retry."
        }
    }
}


