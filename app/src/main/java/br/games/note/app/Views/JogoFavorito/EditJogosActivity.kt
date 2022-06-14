package br.games.note.app.Views.JogoFavorito

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.games.note.app.Views.MainActivity
import br.games.note.app.databinding.ActivityEditJogosBinding
import br.games.note.app.models.JogoFavorito

class EditJogosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditJogosBinding
    lateinit var viewModel: JogoViewModel
    var jogoID = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditJogosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[JogoViewModel::class.java]

        val tipoJogo = intent.getStringExtra("tipoJogo")
        if (tipoJogo.equals("Edit")) {
            val nome = intent.getStringExtra("nome")
            val genero = intent.getStringExtra("genero")
            val nota = intent.getStringExtra("nota")
            val premio = intent.getStringExtra("premio")
            jogoID = intent.getIntExtra("jogoId", -1)
            binding.editJogoButton.text = "Atualizar jogo"
            binding.NomeJogoEdit.setText(nome)
            binding.generoJogoEdit.setText(genero)
            binding.notaJogoEdit.setText(nota)
            binding.premioEdit.setText(premio)
        }
        else {
            binding.editJogoButton.text = "Salvar jogo"
        }

        binding.editJogoButton.setOnClickListener{
            val nomeJogo = binding.NomeJogoEdit.text.toString()
            val generoJogo = binding.generoJogoEdit.text.toString()
            val notaJogo = binding.notaJogoEdit.text.toString()
            val premioJogo = binding.premioEdit.text.toString()

            if (tipoJogo.equals("Edit")) {
                if (nomeJogo.isNotEmpty() && generoJogo.isNotEmpty() && notaJogo.isNotEmpty() && premioJogo.isNotEmpty()) {
                    val jogoAtualizado = JogoFavorito(nome = nomeJogo, genero = generoJogo, nota = notaJogo.toDouble(), premio = premioJogo)
                    jogoAtualizado.id = jogoID
                    viewModel.atualizarJogo(jogoAtualizado)
                    Toast.makeText(this,"Jogo atualizado!", Toast.LENGTH_LONG).show()

                }
            }
            else {
                if (nomeJogo.isNotEmpty() && generoJogo.isNotEmpty() && notaJogo.isNotEmpty() && premioJogo.isNotEmpty()) {
                    viewModel.adicionarJogo(JogoFavorito(nome = nomeJogo, genero = generoJogo, nota = notaJogo.toDouble(), premio = premioJogo))
                    Toast.makeText(this,"Jogo adicionado!",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}