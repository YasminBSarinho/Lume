package com.example.Lume.model

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Lume.database.LumeDatabase
import com.example.Lume.mapper.toEntity
import com.example.Lume.mapper.toModel
import com.example.Lume.repository.LivroRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LivroViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LivroRepository = LivroRepository(
        LumeDatabase.getDatabase(application).livroDao()
    )

    val livros: StateFlow<List<Livro>> = repository.listarLivros()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun adicionarLivro(livro: Livro) {
        viewModelScope.launch {
            repository.adicionarLivro(livro)
        }
    }

    fun atualizarLivro(livro: Livro) {
        viewModelScope.launch {
            repository.atualizarLivro(livro)
        }
    }

    fun removerLivro(livro: Livro) {
        viewModelScope.launch {
            repository.removerLivro(livro)
        }
    }
}