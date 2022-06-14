package br.games.note.app.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.games.note.app.R
import br.games.note.app.models.Empresa


class RecycleEmpresasAdapter(
    val empresaClickDeleteInterface: EmpresaClickDeleteInterface,
    val empresaClickInterface: EmpresaClickInterface
) : RecyclerView.Adapter<RecycleEmpresasAdapter.ViewHolder>() {

    val todasEmpresas = ArrayList<Empresa>()

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val nome = itemView.findViewById<TextView>(R.id.nome_item)
        val numJogos = itemView.findViewById<TextView>(R.id.num_jogos_item)
        val patrimonio = itemView.findViewById<TextView>(R.id.patrimonio_item)
        val jogoFamoso = itemView.findViewById<TextView>(R.id.jogo_famoso_item)
        val deletarEmpresa = itemView.findViewById<ImageView>(R.id.delete_icon_item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycle_empresas_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nome.text = todasEmpresas[position].nome
        holder.numJogos.text = "Número de jogos: " + todasEmpresas[position].numeroJogos
        holder.jogoFamoso.text = "Jogo mais famoso: " + todasEmpresas[position].jogoMaisFamoso
        holder.patrimonio.text = "Patrimônio: R$ " + todasEmpresas[position].converterMoeda(todasEmpresas[position].patrimonio.toDouble())
        holder.deletarEmpresa.setOnClickListener{
            empresaClickDeleteInterface.onDeleteIconClick(todasEmpresas[position])
        }

        holder.itemView.setOnClickListener{
            empresaClickInterface.onEmpresaClick(todasEmpresas[position])
        }
    }

    override fun getItemCount(): Int {
        return todasEmpresas.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Empresa>) {
        todasEmpresas.clear()
        todasEmpresas.addAll(newList)
        notifyDataSetChanged()
    }

}

interface EmpresaClickDeleteInterface{
    fun onDeleteIconClick(empresa:Empresa)
}

interface EmpresaClickInterface {
    fun onEmpresaClick(empresa:Empresa)
}