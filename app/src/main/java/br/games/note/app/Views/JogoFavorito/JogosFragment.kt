package br.games.note.app.Views.JogoFavorito

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.games.note.app.Adapters.JogosClickDeleteInterface
import br.games.note.app.Adapters.JogosClickInterface
import br.games.note.app.Adapters.RecyclerJogosAdapter
import br.games.note.app.databinding.FragmentJogosBinding
import br.games.note.app.models.JogoFavorito

class JogosFragment : Fragment() , JogosClickInterface, JogosClickDeleteInterface {

    private var _binding:FragmentJogosBinding? = null
    private val binding get() = _binding!!

    private lateinit var jogoViewModel: JogoViewModel
    private lateinit var jogosRecyclerView:RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentJogosBinding.inflate(inflater,container,false)
        val view = binding.root

        jogosRecyclerView = binding.jogosRecycle
        jogosRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = RecyclerJogosAdapter(this,this)

        binding.jogosRecycle.adapter = adapter

        jogoViewModel = ViewModelProvider(requireActivity())[JogoViewModel::class.java]

        jogoViewModel.todosJogos.observe(viewLifecycleOwner) {
            list -> list?.let {
                adapter.updateJogoList(it)
        }
        }
        return view
    }

    override fun onJogoClick(jogoFavorito: JogoFavorito) {
        val intent = Intent(context, EditJogosActivity::class.java)
        intent.putExtra("tipoJogo", "Edit")
        intent.putExtra("nome", jogoFavorito.nome)
        intent.putExtra("genero", jogoFavorito.genero)
        intent.putExtra("nota", jogoFavorito.nota.toString())
        intent.putExtra("premio", jogoFavorito.premio)
        intent.putExtra("jogoId", jogoFavorito.id)
        startActivity(intent)
    }

    override fun onDeleteIconClick(jogoFavorito: JogoFavorito) {
        jogoViewModel.deletarJogo(jogoFavorito)
        Toast.makeText(context,"${jogoFavorito.nome} deletado com sucesso!", Toast.LENGTH_LONG).show()
    }
}