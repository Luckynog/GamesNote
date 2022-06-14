package br.games.note.app.Datebase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.games.note.app.DAO.JogoDAO
import br.games.note.app.models.JogoFavorito


@Database(entities = arrayOf(JogoFavorito::class), version = 1, exportSchema = false)
abstract class JogoFavoritoDatebase: RoomDatabase() {
    abstract fun getJogosDao():JogoDAO

    companion object {
        @Volatile
        private var INSTANCE:JogoFavoritoDatebase? = null

        fun getDatabase(context: Context):JogoFavoritoDatebase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, JogoFavoritoDatebase::class.java, "jogo_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}