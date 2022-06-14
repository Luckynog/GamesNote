package br.games.note.app.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DecimalFormat

@Entity
data class Empresa (
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "nome") val nome:String,
    @ColumnInfo(name = "numero de jogos") val numeroJogos:Int,
    @ColumnInfo (name = "jogo mais famoso") val jogoMaisFamoso:String,
    @ColumnInfo (name = "patrimonio")  val patrimonio:String
){
    fun converterMoeda(valor:Double):String{
        val dec = DecimalFormat("#,##0.00")

        var valorCorrigido = dec.format(valor)

        valorCorrigido = valorCorrigido.replace(".","@")
        valorCorrigido = valorCorrigido.replace(",","&")
        valorCorrigido = valorCorrigido.replace("@",",")
        valorCorrigido = valorCorrigido.replace("&",".")

        return valorCorrigido.toString()
    }
}