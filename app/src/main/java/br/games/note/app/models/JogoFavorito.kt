package br.games.note.app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class JogoFavorito(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "nome") val nome:String,
    @ColumnInfo(name = "genero") val genero:String,
    @ColumnInfo(name = "nota") val nota:Double,
    @ColumnInfo(name = "premio") val premio:String
)