package br.games.note.app.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import br.games.note.app.models.Empresa

@Dao
interface EmpresaDAO {
    //adicionar um novo membro
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(empresa:Empresa)

    //deletar membro
    @Delete
    suspend fun deletar(empresa:Empresa)

    //atualizar membro
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun atualizar(empresa:Empresa)

    //listar membro
    @Query("SELECT * FROM Empresa order by id ASC")
    fun listar(): LiveData<List<Empresa>>

    //exibir membro
    @Query("SELECT * FROM Empresa WHERE nome = :nome")
    suspend fun exibir(nome:String):Empresa
}