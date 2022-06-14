package br.games.note.app.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import br.games.note.app.models.JogoFavorito

@Dao
interface JogoDAO {
    //adicionar um novo membro
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(jogoFavorito: JogoFavorito)

    //deletar membro
    @Delete
    suspend fun deletar(jogoFavorito: JogoFavorito)

    //atualizar membro
    @Update
    suspend fun atualizar(jogoFavorito: JogoFavorito)

    //listar membro
    @Query("SELECT * FROM JogoFavorito order by id ASC")
    fun listar(): LiveData<List<JogoFavorito>>

    //exibir membro
    @Query("SELECT * FROM JogoFavorito WHERE nome = :nome")
    suspend fun exibir(nome:String): JogoFavorito
}