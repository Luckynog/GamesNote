package br.games.note.app.Views.JogoFavorito

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.games.note.app.Datebase.JogoFavoritoDatebase
import br.games.note.app.Repository.JogoRepository
import br.games.note.app.models.JogoFavorito
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JogoViewModel(application: Application) : AndroidViewModel(application) {
    val todosJogos:LiveData<List<JogoFavorito>>
    val repositorioJogo:JogoRepository

    init {
        val daoJogo = JogoFavoritoDatebase.getDatabase(application).getJogosDao()
        repositorioJogo = JogoRepository(daoJogo)
        todosJogos = repositorioJogo.todosJogos
    }

    fun deletarJogo(jogoFavorito:JogoFavorito) = viewModelScope.launch(Dispatchers.IO) {
        repositorioJogo.deletar(jogoFavorito)
    }

    fun atualizarJogo(jogoFavorito:JogoFavorito) = viewModelScope.launch(Dispatchers.IO) {
        repositorioJogo.atualizar(jogoFavorito)
    }

    fun adicionarJogo(jogoFavorito:JogoFavorito) = viewModelScope.launch(Dispatchers.IO) {
        repositorioJogo.inserir(jogoFavorito)
    }
}