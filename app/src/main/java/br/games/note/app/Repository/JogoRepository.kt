package br.games.note.app.Repository

import androidx.lifecycle.LiveData
import br.games.note.app.DAO.JogoDAO
import br.games.note.app.models.JogoFavorito

class JogoRepository(private val jogoDao: JogoDAO) {

    val todosJogos: LiveData<List<JogoFavorito>> = jogoDao.listar()

    suspend fun inserir(jogoFavorito: JogoFavorito){
        jogoDao.inserir(jogoFavorito)
    }

    suspend fun deletar(jogoFavorito: JogoFavorito){
        jogoDao.deletar(jogoFavorito)
    }

    suspend fun atualizar(jogoFavorito: JogoFavorito){
        jogoDao.atualizar(jogoFavorito)
    }
}

