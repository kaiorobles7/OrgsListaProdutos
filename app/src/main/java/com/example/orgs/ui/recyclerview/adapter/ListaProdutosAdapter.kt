package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.model.Produto
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(
    private val context:Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    private val produtos = produtos.toMutableList()

   inner class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root){

       private lateinit var ajuste: Produto

       init {
           itemView.setOnClickListener {
               if (::ajuste.isInitialized) {

               }
           }
       }

        fun vincula(produto: Produto) {
            val nome = binding.activityFormularioProdutoNome
            nome.text = produto.nome
            val descricao = binding.activityFormularioProdutoDescricao
            descricao.text = produto.descricao
            val valor = binding.activityFormularioProdutoValor
            val valoremMoeda : String = formataParaMoedaBrasileira(produto)
            valor.text = valoremMoeda

        }
    }

    private fun formataParaMoedaBrasileira(produto: Produto): String {
        val formatador: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        return formatador.format(produto.valor)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProdutoItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }


    override fun getItemCount(): Int = produtos.size



    fun atualiza(produtos: List<Produto>) {
    this.produtos.clear()
        this.produtos.addAll(produtos)
       notifyDataSetChanged()
    }
}

