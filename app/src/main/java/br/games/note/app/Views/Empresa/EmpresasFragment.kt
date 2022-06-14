package br.games.note.app.Views.Empresa

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.games.note.app.Adapters.EmpresaClickDeleteInterface
import br.games.note.app.Adapters.EmpresaClickInterface
import br.games.note.app.R
import br.games.note.app.Adapters.RecycleEmpresasAdapter
import br.games.note.app.databinding.FragmentEmpresasBinding
import br.games.note.app.models.Empresa
import com.google.android.material.floatingactionbutton.FloatingActionButton


class EmpresasFragment : Fragment(), EmpresaClickInterface, EmpresaClickDeleteInterface {

    private var _binding:FragmentEmpresasBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: EmpresasViewModel
    private lateinit var empresasRecyclerView: RecyclerView
    private lateinit var addFAB:FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEmpresasBinding.inflate(inflater,container,false)
        val view = binding.root

        empresasRecyclerView = binding.empresasRecycle
        addFAB = requireActivity().findViewById(R.id.actionButton)

        empresasRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = RecycleEmpresasAdapter(this, this)


        binding.empresasRecycle.adapter = adapter

        viewModel = ViewModelProvider(requireActivity())[EmpresasViewModel::class.java]

        viewModel.todasEmpresas.observe(viewLifecycleOwner) { list ->
            list?.let {
                adapter.updateList(it)
            }
        }


        return view
    }

    override fun onDeleteIconClick(empresa: Empresa) {
        viewModel.deletarEmpresa(empresa)
        Toast.makeText(context,"${empresa.nome} deletada com sucesso!", Toast.LENGTH_LONG).show()
    }

    override fun onEmpresaClick(empresa: Empresa) {
        val intent = Intent(context, EditEmpresasActivity::class.java)
        intent.putExtra("tipoEmpresa", "Edit")
        intent.putExtra("nome", empresa.nome)
        intent.putExtra("numeroJogos", empresa.numeroJogos)
        intent.putExtra("jogoFamoso" , empresa.jogoMaisFamoso)
        intent.putExtra("patrimonio", empresa.patrimonio)
        intent.putExtra("empresaId", empresa.id)
        startActivity(intent)
    }

}


