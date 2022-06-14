package br.games.note.app.Views.Empresa

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.games.note.app.Datebase.EmpresaDatebase
import br.games.note.app.Repository.EmpresaRepository
import br.games.note.app.models.Empresa
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmpresasViewModel(application: Application) : AndroidViewModel(application) {

    val todasEmpresas:LiveData<List<Empresa>>
    val repositorio:EmpresaRepository

    init{
        val dao = EmpresaDatebase.getDatabase(application).getEmpresasDao()
        repositorio = EmpresaRepository(dao)
        todasEmpresas = repositorio.todasEmpresas
    }

    fun deletarEmpresa(empresa: Empresa) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.deletar(empresa)
    }

    fun atualizarEmpresa(empresa: Empresa) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.atualizar(empresa)
    }

    fun adicionarEmpresa(empresa: Empresa) = viewModelScope.launch(Dispatchers.IO) {
        repositorio.inserir(empresa)
    }
}