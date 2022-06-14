package br.games.note.app.Repository

import androidx.lifecycle.LiveData
import br.games.note.app.DAO.EmpresaDAO
import br.games.note.app.models.Empresa

class EmpresaRepository(private val empresaDAO: EmpresaDAO) {

    val todasEmpresas:LiveData<List<Empresa>> = empresaDAO.listar()

    suspend fun inserir(empresa: Empresa){
        empresaDAO.inserir(empresa)
    }

    suspend fun deletar(empresa: Empresa){
        empresaDAO.deletar(empresa)
    }

    suspend fun atualizar(empresa: Empresa){
        empresaDAO.atualizar(empresa)
    }
}