package br.games.note.app.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.games.note.app.R
import br.games.note.app.models.JogoFavorito

class RecyclerJogosAdapter(val jogosClickDeleteInterface: JogosClickDeleteInterface,
                           val jogosClickInterface: JogosClickInterface
) : RecyclerView.Adapter<RecyclerJogosAdapter.ViewHolder>() {

    val todosJogos = ArrayList<JogoFavorito>()

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.findViewById<TextView>(R.id.nome_item)
        val genero = itemView.findViewById<TextView>(R.id.num_jogos_item)
        val nota = itemView.findViewById<TextView>(R.id.jogo_famoso_item)
        val premio = itemView.findViewById<TextView>(R.id.patrimonio_item)
        val deletarJogo = itemView.findViewById<ImageView>(R.id.delete_icon_item)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_empresas_item,parent,false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nome.text = todosJogos[position].nome
        holder.genero.text = "Gênero: " + todosJogos[position].genero
        holder.nota.text = "Nota: " + todosJogos[position].nota.toString()
        holder.premio.text = "Prêmio(s) ganho(s): " + todosJogos[position].premio
        holder.deletarJogo.setOnClickListener{
            jogosClickDeleteInterface.onDeleteIconClick(todosJogos[position])
        }

        holder.itemView.setOnClickListener{
            jogosClickInterface.onJogoClick(todosJogos[position])
        }
    }

    override fun getItemCount(): Int {
        return todosJogos.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateJogoList(newList:List<JogoFavorito>) {
        todosJogos.clear()
        todosJogos.addAll(newList)
        notifyDataSetChanged()
    }
}

interface JogosClickInterface {
    fun onJogoClick(jogoFavorito: JogoFavorito)
}

interface JogosClickDeleteInterface {
    fun onDeleteIconClick(jogoFavorito: JogoFavorito)
}
