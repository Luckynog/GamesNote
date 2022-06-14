package br.games.note.app.Datebase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.games.note.app.DAO.EmpresaDAO
import br.games.note.app.models.Empresa


@Database(entities = arrayOf(Empresa::class), version = 1, exportSchema = false)
abstract class EmpresaDatebase : RoomDatabase() {


    abstract fun getEmpresasDao():EmpresaDAO

    companion object {
        @Volatile
        private var INSTANCE:EmpresaDatebase? = null

        fun getDatabase(context: Context): EmpresaDatebase{
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                EmpresaDatebase::class.java,
                "empresa_database").build()
                INSTANCE = instance
                instance
            }
        }
    }
}