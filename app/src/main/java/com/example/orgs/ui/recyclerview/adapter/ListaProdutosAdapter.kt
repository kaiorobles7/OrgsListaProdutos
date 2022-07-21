package com.example.orgs.ui.recyclerview.adapter

import android.content.Context
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.graphics.Insets
import androidx.core.graphics.Insets.add
import androidx.recyclerview.widget.RecyclerView
import coil.*
import coil.annotation.ExperimentalCoilApi
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.example.orgs.databinding.ProdutoItemBinding
import com.example.orgs.model.Produto
import java.text.NumberFormat
import java.util.*
import kotlin.coroutines.coroutineContext

class ListaProdutosAdapter(
    private val context:Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {
    private val produtos = produtos.toMutableList()

   inner class ViewHolder(private val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {

       private lateinit var ajuste: Produto

       init {
           itemView.setOnClickListener {
               if (::ajuste.isInitialized) {

               }
           }
       }

       fun vincula(produto: Produto) {

           val nome = binding.produtoItemNome
           nome.text = produto.nome
           val descricao = binding.produtoItemDescricao
           descricao.text = produto.descricao
           val valor = binding.produtoItemValor
           val valoremMoeda: String = formataParaMoedaBrasileira(produto)
           valor.text = valoremMoeda

           val imagemLoader =  ImageLoader.Builder(context)
               .components {
                   if (Build.VERSION.SDK_INT >= 28) {
                       add(ImageDecoderDecoder.Factory())
                   } else {
                       add(GifDecoder.Factory())
                   }
               }
               .build()
           binding.imageView.load(produto.imagem, imageLoader = imagemLoader)
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

