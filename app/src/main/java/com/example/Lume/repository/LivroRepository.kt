package com.example.Lume.repository

import com.example.Lume.dao.LivroDao
import com.example.Lume.mapper.toEntity
import com.example.Lume.mapper.toModel
import com.example.Lume.model.Livro
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LivroRepository(
    private val livroDao: LivroDao
) {
    fun listarLivros(): Flow<List<Livro>> {
        return livroDao.listarLivros().map { livros ->
            livros.map { livroEntity ->
                livroEntity.toModel()
            }
        }
    }

    fun listarGeneros(): Flow<List<String>> {
        return livroDao.listarGeneros()
    }

    suspend fun adicionarLivro(livro: Livro) {
        livroDao.inserirLivro(livro.toEntity())
    }

    suspend fun atualizarLivro(livro: Livro) {
        livroDao.atualizarLivro(livro.toEntity())
    }

    suspend fun removerLivro(livro: Livro) {
        livroDao.deletarLivro(livro.toEntity())
    }
}