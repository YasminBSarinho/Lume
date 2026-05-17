package com.example.Lume.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class LivroViewModel : ViewModel() {

    private val _livros = mutableStateListOf<Livro>()
    val livros: List<Livro> = _livros

    fun adicionarLivro(livro: Livro) {
        _livros.add(livro)
    }
}