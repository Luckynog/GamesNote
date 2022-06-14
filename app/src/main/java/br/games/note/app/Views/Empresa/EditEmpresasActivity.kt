package br.games.note.app.Views.Empresa

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.games.note.app.Views.MainActivity
import br.games.note.app.databinding.ActivityEditEmpresasBinding
import br.games.note.app.databinding.ActivityEditEmpresasBinding.*
import br.games.note.app.models.Empresa

class EditEmpresasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditEmpresasBinding
    lateinit var viewModel: EmpresasViewModel
    var empresaID = -1

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))[EmpresasViewModel::class.java]

        val tipoEmpresa = intent.getStringExtra("tipoEmpresa")
        if (tipoEmpresa.equals("Edit")) {
            val nome = intent.getStringExtra("nome")
            val numeroJogos = intent.getIntExtra("numeroJogos", 0)
            val jogoFamoso = intent.getStringExtra("jogoFamoso")
            val patrimonio = intent.getStringExtra("patrimonio")
            empresaID = intent.getIntExtra("empresaId", -1)
            binding.editButton.text = "Atualizar empresa"
            binding.NomeEdit.setText(nome)
            binding.numJogosEdit.setText(numeroJogos.toString())
            binding.JogoMaisFamosoEdit.setText(jogoFamoso)
            binding.patrimonioEdit.setText(patrimonio)
        } else {
            binding.editButton.text = "Salvar empresa"
        }

        binding.editButton.setOnClickListener{

            val nomeEmpresa = binding.NomeEdit.text.toString()
            val numeroJogos = binding.numJogosEdit.text.toString()
            val jogoFamoso = binding.JogoMaisFamosoEdit.text.toString()
            val patrimonio = binding.patrimonioEdit.text.toString()

            if (tipoEmpresa.equals("Edit")) {
                if(nomeEmpresa.isNotEmpty() && numeroJogos.isNotEmpty() && jogoFamoso.isNotEmpty() && patrimonio.isNotEmpty()) {
                    val empresaAtualizada = Empresa(nome = nomeEmpresa, numeroJogos = numeroJogos.toInt(), jogoMaisFamoso = jogoFamoso, patrimonio = patrimonio)
                    empresaAtualizada.id = empresaID
                    viewModel.atualizarEmpresa(empresaAtualizada)
                    Toast.makeText(this,"Empresa atualizada!",Toast.LENGTH_LONG).show()
                }
            } else{
                if(nomeEmpresa.isNotEmpty() && numeroJogos.isNotEmpty() && jogoFamoso.isNotEmpty() && patrimonio.isNotEmpty()) {
                    viewModel.adicionarEmpresa(Empresa(nome = nomeEmpresa, numeroJogos = numeroJogos.toInt(), jogoMaisFamoso = jogoFamoso, patrimonio = patrimonio))
                    Toast.makeText(this,"Empresa adicionada!",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}