package com.example.orgs.dao

import com.example.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDAO {

    fun adiciona (produto: Produto){
       produtos.add(produto)
    }

    fun buscaTodos(): List<Produto>{
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto (
                nome = "Cesta de Frutas",
                descricao = "Laranja,Maçãs e uva",
                valor = BigDecimal("19.83")
            )
        )
    }

}