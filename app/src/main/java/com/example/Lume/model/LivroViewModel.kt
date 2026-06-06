package com.example.Lume.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class LivroViewModel : ViewModel() {

    val livros = mutableStateListOf<Livro>()

    fun adicionarLivro(livro: Livro) {
        livros.add(livro)
    }

    fun removerLivro(livro: Livro) {
        livros.remove(livro)
    }

}